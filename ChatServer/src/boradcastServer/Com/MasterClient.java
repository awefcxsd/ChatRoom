package boradcastServer.Com;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

public class MasterClient implements Runnable {
	private Server mainThread;
	private Socket sock;
	private int id;
	private DataInputStream is;
	private DataOutputStream os;
	private String name;

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
			while(true) {
				String msg = is.readUTF();
				System.out.println(msg);
				mainThread.boradCast(msg);
			}
		} catch (IOException e) {
			if( e instanceof SocketException ) {
				mainThread.remove(this, id);
			}
			else {
				System.out.println(e.toString());
				e.printStackTrace();
			}
		}
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
