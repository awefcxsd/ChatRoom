package ChatClient.video.trans;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import javax.imageio.ImageIO;

import ChatClient.util.ImageComponent;

public class Recv implements Runnable {
	DatagramSocket ds;
	byte[] buffer;
	ImageComponent recvImage;
	public boolean action = true;

	public Recv(ImageComponent recv) {
		recvImage = recv;

		// A byte array to read into (max size of 65536, could be smaller)
		buffer = new byte[65536];
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		DatagramPacket p = new DatagramPacket(buffer, buffer.length);

		try {
			while (action) {
				ds = new DatagramSocket(9100);
				while (true) {
					ds.receive(p);
					byte[] data = p.getData();
					ByteArrayInputStream bais = new ByteArrayInputStream(data);

					// Make a BufferedImage out of the incoming bytes
					BufferedImage bimg = ImageIO.read(bais);
					recvImage.setImage(bimg);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

}
