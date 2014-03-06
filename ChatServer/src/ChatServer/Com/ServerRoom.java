package ChatServer.Com;

import java.util.Vector;

public class ServerRoom {
	
	public Vector<MasterClient> clientInRoom;
	public String roomId;
	
	
	public ServerRoom(String id){
		
		roomId = id;
		clientInRoom = new Vector<MasterClient>();
		
		
	}
	
	public void addClient(MasterClient client){
		
		clientInRoom.add(client);
		
	}
	
	public void removeClient(MasterClient client){
		
		clientInRoom.remove(client);
		
	}
	
	public Vector<MasterClient> getClients(){
		
		return clientInRoom;
		
	}
	
	public String getId(){
		
		return roomId;
		
	}

}
