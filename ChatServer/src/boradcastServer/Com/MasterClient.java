package boradcastServer.Com;

import java.awt.Color;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class MasterClient implements Runnable {
	private Server mainThread;
	private Socket sock;
	private int id;
	private DataInputStream is;
	private DataOutputStream os;
	private String ClientName;

	public MasterClient(Server server, Socket s, int i) {
		// TODO Auto-generated constructor stub
		try {
			mainThread = server;
			sock = s;
			id = i;
			is = new DataInputStream(sock.getInputStream());
			os = new DataOutputStream(sock.getOutputStream());
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while (true) {
				String msg = is.readUTF();
				Handle(msg);
				// System.out.println(msg);
				// mainThread.boradCast(msg);

			}
		} catch (IOException e) {
			if (e instanceof SocketException) {
				mainThread.remove(this, id);
			} else {
				mainThread.remove(this, id);
				// System.out.println(e.toString());
				// e.printStackTrace();
			}
		}
	}

	private void Handle(String msg) {
		
		mainThread.boradCast(msg);


	}

	public void send(String text) {
		// TODO Auto-generated method stub
		try {
			os.writeUTF(text);
		} catch (IOException e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
	}

}
