package ChatServer.Com;

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
	private boolean overLeap=false;

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
				mainThread.remove(this, id, overLeap);
			} else {
				mainThread.remove(this, id, overLeap);
				// System.out.println(e.toString());
				// e.printStackTrace();
			}
		}
	}

	private void Handle(String msg) {
		// TODO Auto-generated method stub
		if (msg.startsWith("/name")) {
			ClientName = msg.split(" ", 2)[1];
			if (mainThread.UserNameList.contains(ClientName)) {
				overLeap=true;
				send("/nameOccupied");
			} else {
				for (String other : mainThread.UserNameList) {
					send("/newUser " + other);
				}
				mainThread.UserNameList.add(ClientName);
				mainThread.broadCast("/newUser " + ClientName);
			}
		} else if (msg.startsWith("/BoradCastMessage")) {
			String boradCastMessage = msg.split(" ", 2)[1];
			mainThread.broadCast("/BoradCastMessage " + ClientName + " " + boradCastMessage);
		} else if (msg.startsWith("/SecretMsg")) {
			String recieve = msg.split(" ", 3)[1];
			String SecretMessage = msg.split(" ", 3)[2];
			mainThread.search(recieve).send("/SecretMsg "+ClientName+" " + SecretMessage);
		} else if (msg.startsWith("/openNewRoom")) {
			System.out.println("newRoom");
			//maintain room list in server and add creator into the room
			int roomid =(mainThread.getNewRoomId()+1);
			ServerRoom room = new ServerRoom("Room#"+ roomid );
			mainThread.roomList.add(room);
			room.addClient(this);
			
			//Assign a room number and send¡§Open new room¡¨and ¡§User join¡¨to client that ask for new room.
			
			send("/openNewRoom "+ "Room#"+ roomid);
			send("/userJoin "+ "Room#"+ roomid +" "+ this.ClientName);
			
		} else if (msg.startsWith("/invite")) {
			String RoomNumber = msg.split(" ",3)[1];
			String UserName = msg.split(" ",3)[2];
			MasterClient InvitedClient = mainThread.search(UserName);
			
			ServerRoom thisRoom = mainThread.searchRoom(RoomNumber);
			thisRoom.sendRoomMsg("/userJoin " + RoomNumber + " " + UserName);
			
			thisRoom.addClient(InvitedClient);
			InvitedClient.send("/openNewRoom "+ RoomNumber);
			for (MasterClient c : thisRoom.clientInRoom){
				InvitedClient.send("/userJoin " + RoomNumber + " " + c.ClientName);
			}
			
		} else if (msg.startsWith("/roomMsg")){
			String RoomNumber = msg.split(" ",3)[1];
			String Message = msg.split(" ",3)[2];
			
			ServerRoom thisRoom = mainThread.searchRoom(RoomNumber);
			thisRoom.sendRoomMsg("/roomMsg " + RoomNumber + " " + this.ClientName + " " + Message);
		} else if (msg.startsWith("/leaveRoom")){
			String RoomNumber = msg.split(" ",2)[1];
			ServerRoom thisRoom = mainThread.searchRoom(RoomNumber);
			
			thisRoom.removeClient(this);
			thisRoom.sendRoomMsg("/leaveRoom " + RoomNumber + " " + this.ClientName);
			send("/closeRoom " + RoomNumber);
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

	public String getName() {
		// TODO Auto-generated method stub
		return ClientName;
	}

}
