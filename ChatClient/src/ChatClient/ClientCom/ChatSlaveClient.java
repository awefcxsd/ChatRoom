package ChatClient.ClientCom;

import java.awt.Color;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.JOptionPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import ChatClient.ClientGUI.ChatClientWindow;

public class ChatSlaveClient implements Runnable {

	ChatClientWindow GUIObject;
	// Connection Objects
	private Socket socket;
	private int port;
	private String serverIP;
	private DataOutputStream os;
	private DataInputStream is;
	private Thread thread;
	private String username;

	public ChatSlaveClient(ChatClientWindow chatClientWindow) {
		GUIObject = chatClientWindow;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while (true) {
				String TransferLine = is.readUTF();
				Handle(TransferLine);
				//
				System.out.println("Recv: " + TransferLine);
				// SimpleAttributeSet recv = new SimpleAttributeSet();
				// StyleConstants.setForeground(recv, Color.RED);
				// GUIObject.addText("Recv: " + TransferLine, recv);
			}
		} catch (Exception e) {

		}
	}

	private void Handle(String transferLine) {
		// TODO Auto-generated method stub

		if (transferLine.startsWith("/nameOccupied")) {
			try {
				socket.shutdownInput();
				socket.shutdownOutput();
				socket.close();
				JOptionPane.showMessageDialog(null, "This name has been occupied", "Error", JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e) {
			}

		} else if (transferLine.startsWith("/BoradCastMessage")) {
			String boradCastMessage = transferLine.split(" ", 3)[2];
			String Sender = transferLine.split(" ", 3)[1];
			SimpleAttributeSet recv = new SimpleAttributeSet();
			StyleConstants.setForeground(recv, Color.RED);
			GUIObject.addText(Sender + " : " + boradCastMessage, recv);
		} else if (transferLine.startsWith("/newUser")) {
			String newUser = transferLine.split(" ", 2)[1];
			GUIObject.addUser(newUser);
		} else if (transferLine.startsWith("/userLeave ")) {
			String leave = transferLine.split(" ", 2)[1];
			GUIObject.removeUser(leave);
		}

		// System.out.println("Recv: " + transferLine);
		// SimpleAttributeSet recv = new SimpleAttributeSet();
		// StyleConstants.setForeground(recv, Color.RED);
		// GUIObject.addText("Recv: " + transferLine, recv);
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
			// SimpleAttributeSet send = new SimpleAttributeSet();
			// StyleConstants.setForeground(send, Color.BLUE);
			// GUIObject.addText("Send: " + sendText, send);
		} catch (Exception e) {
		}
	}

	public void setName(String name) {
		// TODO Auto-generated method stub
		send("/name " + name);
	}

}
