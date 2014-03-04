package boradcastServer.Com;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server {
	private Vector<MasterClient> clientList;
	private ServerSocket serverSock;
	private int id = 0;

	public Server() {
		clientList = new Vector<MasterClient>();
		try {
			serverSock = new ServerSocket(9987);
			while (true) {
				synchronized (this) {
					Socket s = serverSock.accept();
					System.out.println("Client "+id+" connect");
					clientList.add(new MasterClient(this, s, id++));
				}
				Thread thd = new Thread(clientList.lastElement());
				thd.start();
				
			}
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
	}
	
	public void boradCast(String text){
		for(MasterClient c : clientList){
			c.send(text);
		}
		System.out.println("Boradcast "+ text);
	}

	public void remove(MasterClient client, int id2) {
		// TODO Auto-generated method stub
		clientList.remove(client);
		System.out.println("Client "+id2+" disconnect");
	}
	
}
