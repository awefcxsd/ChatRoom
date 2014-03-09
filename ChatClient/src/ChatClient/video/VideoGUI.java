package ChatClient.video;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.lti.civil.CaptureDeviceInfo;
import com.lti.civil.CaptureException;
import com.lti.civil.CaptureObserver;
import com.lti.civil.CaptureStream;
import com.lti.civil.CaptureSystem;
import com.lti.civil.CaptureSystemFactory;
import com.lti.civil.DefaultCaptureSystemFactorySingleton;
import com.lti.civil.awt.AWTImageConverter;

import ChatClient.ClientGUI.BackGroundPanel;
import ChatClient.util.ImageComponent;
import ChatClient.video.trans.Recv;

import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.List;

public class VideoGUI extends JFrame {

	private CaptureSystem system;
	private CaptureStream captureStream;
	private final CaptureSystemFactory factory;
	private volatile boolean disposing = false;
	DatagramSocket ds;
	String IpAddr;
	ImageComponent recvImage;
	ImageComponent sendImage;
	Recv recvThread;
	
	public VideoGUI(String ip,String name) {
		this.setTitle("µø°T with "+name);
		
		IpAddr=ip;
		factory=DefaultCaptureSystemFactorySingleton.instance();
		
		try {
			ds = new DatagramSocket();
		} catch (SocketException e) {
			e.printStackTrace();
		}
		
		this.setSize(822, 588);
		
		BackGroundPanel panel = new BackGroundPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		recvImage = new ImageComponent();
		recvImage.setBounds(0, 0, 640, 480);
		sendImage = new ImageComponent();
		sendImage.setBounds(600, 400, 180, 120);
		panel.add(sendImage);
		panel.add(recvImage);
		
		
		try {
			this.run();
		} catch (CaptureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		recvThread=new Recv(recvImage);
		Thread thd=new Thread(recvThread);
		thd.start();
		
	}

	public void run() throws CaptureException {

		initCapture();

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				try {
					disposeCapture();
					recvThread.action=false;
				} catch (CaptureException e1) {
					e1.printStackTrace();
				}
			}
		});


		startCapture();
	}

	public void initCapture() throws CaptureException {

		system = factory.createCaptureSystem();
		system.init();
		List list = system.getCaptureDeviceInfoList();
		for (int i = 0; i < list.size(); ++i) {
			CaptureDeviceInfo info = (CaptureDeviceInfo) list.get(i);

			System.out.println("Device ID " + i + ": " + info.getDeviceID());
			System.out.println("Description " + i + ": " + info.getDescription());
			captureStream = system.openCaptureDeviceStream(info.getDeviceID());
			captureStream.setObserver(new MyCaptureObserver());

			break;
		}

	}

	public void startCapture() throws CaptureException {
		captureStream.start();
	}

	public void disposeCapture() throws CaptureException {
		disposing = true;

		if (captureStream != null) {
			System.out.println("disposeCapture: stopping capture stream...");
			captureStream.stop();
			System.out.println("disposeCapture: stopped capture stream.");
			captureStream.dispose();
			captureStream = null;
		}

		if (system != null)
			system.dispose();
		System.out.println("disposeCapture done.");
	}

	class MyCaptureObserver implements CaptureObserver {

		public void onError(CaptureStream sender, CaptureException e) {
			e.printStackTrace();
		}

		public void onNewImage(CaptureStream sender, com.lti.civil.Image image) {
			if (disposing)
				return;
			try {
				sendImage.setImage(reduce(AWTImageConverter.toBufferedImage(image),0.3));
			} catch (Throwable t) {
				t.printStackTrace();
			}

			// We need a buffered image to do the JPG encoding
			BufferedImage bimg = AWTImageConverter.toBufferedImage(image);

			// Need these output streams to get image as bytes for UDP
			// communication
			ByteArrayOutputStream baStream = new ByteArrayOutputStream();
			BufferedOutputStream bos = new BufferedOutputStream(baStream);

			// Turn the BufferedImage into a JPG and put it in the
			// BufferedOutputStream
			// Requires try/catch
			try {
				ImageIO.write(bimg, "jpg", bos);
			} catch (IOException e) {
				e.printStackTrace();
			}

			// Get the byte array, which we will send out via UDP!
			byte[] packet = baStream.toByteArray();

			// Send JPEG data as a datagram
			try {
				ds.send(new DatagramPacket(packet, packet.length, InetAddress.getByName(IpAddr), 9100));
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}
	
	
	public BufferedImage reduce(final BufferedImage original, double scale) {  
	    final int w = new Double(original.getWidth() * scale).intValue();  
	    final int h = new Double(original.getHeight() * scale).intValue();  
	    final Image rescaled = original.getScaledInstance(w, h,  
	            Image.SCALE_AREA_AVERAGING);  
	    final BufferedImage result = new BufferedImage(w, h,  
	            BufferedImage.TYPE_INT_RGB);  
	    final Graphics2D g = result.createGraphics();  
	    g.drawImage(rescaled, 0, 0, null);  
	    g.dispose();  
	    return result;  
	}  
	

}
