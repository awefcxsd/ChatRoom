package debugClient.degubCom;

import java.awt.Color;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import debugClient.debugGUI.DebugWindow;

public class Client implements Runnable {

	DebugWindow GUIObject;
	// Connection Objects
	private Socket socket;
	private int port;
	private String serverIP;
	private DataOutputStream os;
	private DataInputStream is;
	private Thread thread;
	private String username;

	public Client(DebugWindow gui) {
		GUIObject = gui;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while (true) {
				String TransferLine = is.readUTF();
				System.out.println("Recv: " + TransferLine);
				SimpleAttributeSet recv = new SimpleAttributeSet();
				StyleConstants.setForeground(recv, Color.RED);
				GUIObject.addText("Recv: " + TransferLine, recv);
			}
		} catch (Exception e) {

		}
	}

	public void connectToServer(String ip, int port2, String name) {
		// TODO Auto-generated method stub
		serverIP = ip;
		port = port2;
		username = name;

		// create socket
		try {
			socket = new Socket(InetAddress.getByName(serverIP), port);

			os = new DataOutputStream(socket.getOutputStream());
			is = new DataInputStream(socket.getInputStream());
			// this.os = new DataOutputStream(os);
			// this.is = new DataInputStream(is);

			thread = new Thread(this);
			thread.start(); // call run()
		} catch (Exception e) {

		}
	}

	public void send(String sendText) {
		// TODO Auto-generated method stub
		try {
			os.writeUTF(sendText);
			System.out.println("Send: " + sendText);
			SimpleAttributeSet send = new SimpleAttributeSet();
			StyleConstants.setForeground(send, Color.BLUE);
			GUIObject.addText("Send: " + sendText, send);
		} catch (Exception e) {
		}
	}

}
