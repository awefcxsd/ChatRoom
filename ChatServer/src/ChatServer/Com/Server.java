package ChatServer.Com;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server {
	Vector<MasterClient> clientList;
	Vector<String> UserNameList;
	private ServerSocket serverSock;
	private int id = 0;

	public Server() {
		clientList = new Vector<MasterClient>();
		UserNameList = new Vector<String>();
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
	
	public void broadCast(String text){
		for(MasterClient c : clientList){
			c.send(text);
		}
		System.out.println("Boradcast "+ text);
	}

	public void remove(MasterClient client, int id2) {
		// TODO Auto-generated method stub
		String dead=client.getName();
		UserNameList.remove(dead);
		clientList.remove(client);
		System.out.println("Client "+id2+" disconnect");
		broadCast("/userLeave "+dead);
	}
	
	public MasterClient search(String userName){
		for(MasterClient c : clientList){
			if(c.getName().equals(userName)){
				return c;
			}
		}
		return null;
	}
	
}
