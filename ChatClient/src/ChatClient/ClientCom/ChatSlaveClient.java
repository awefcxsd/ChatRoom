package ChatClient.ClientCom;

import java.awt.Color;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Random;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import ChatClient.ClientGUI.ChatClientWindow;
import ChatClient.ClientGUI.RoomPanel;
import ChatClient.util.Vibration;
import ChatClient.video.VideoGUI;

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
	public Vector<RoomPanel> roomList;
	private Random random = new Random();

	public ChatSlaveClient(ChatClientWindow chatClientWindow) {
		GUIObject = chatClientWindow;
		roomList = new Vector<RoomPanel>();
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
			String Sender = transferLine.split(" ", 6)[1];
			String Style = transferLine.split(" ", 6)[2];
			String Size = transferLine.split(" ", 6)[3];
			String color = transferLine.split(" ", 6)[4];
			String boradCastMessage = transferLine.split(" ", 6)[5];

			SimpleAttributeSet recv = new SimpleAttributeSet();
			StyleConstants.setForeground(recv, Color.BLACK);
			GUIObject.addText(Sender + " : ", recv);

			StyleConstants.setFontSize(recv, Integer.parseInt(Size));
			StyleConstants.setForeground(recv, new Color(Integer.parseInt(color)));
			if (Integer.parseInt(Style) == 1)
				StyleConstants.setBold(recv, true);
			GUIObject.addText(boradCastMessage + "\n", recv);

		} else if (transferLine.startsWith("/BoradCastIcon")) {
			String IconIndex = transferLine.split(" ", 3)[1];
			String Sender = transferLine.split(" ", 3)[2];
			SimpleAttributeSet recv = new SimpleAttributeSet();
			StyleConstants.setForeground(recv, Color.BLACK);
			GUIObject.addIcon(Sender + " : ", IconIndex, recv);

			// Add by Sid
		} else if (transferLine.startsWith("/BoradCastGIcon")) {
			String IconIndex = transferLine.split(" ", 3)[1];
			String Sender = transferLine.split(" ", 3)[2];
			SimpleAttributeSet recv = new SimpleAttributeSet();
			StyleConstants.setForeground(recv, Color.BLACK);
			GUIObject.addGIcon(Sender + " : ", IconIndex, recv);

		} else if (transferLine.startsWith("/openNewRoom")) {
			RoomPanel newRoom = new RoomPanel(this);
			String newName = transferLine.split(" ", 2)[1];
			newRoom.setName(newName);
			roomList.add(newRoom);
			GUIObject.addNewTab(newRoom);
			GUIObject.tabbedPane.setSelectedComponent(newRoom);

			// Add by Sid
		} else if (transferLine.startsWith("/userJoin")) {
			String roomNumber = transferLine.split(" ", 3)[1];
			String userName = transferLine.split(" ", 3)[2];
			for (RoomPanel room : roomList) {
				if (room.getName().equals(roomNumber)) {
					room.joinUser(userName);
					System.out.println("User " + userName + " join " + roomNumber);
					// need to update user name list
					break;
				}

			}

			// Add by Sid
		} else if (transferLine.startsWith("/roomMsg")) {
			String roomNumber = transferLine.split(" ", 7)[1];
			String sender = transferLine.split(" ", 7)[2];
			String Style = transferLine.split(" ", 7)[3];
			String Size = transferLine.split(" ", 7)[4];
			String color = transferLine.split(" ", 7)[5];
			String message = transferLine.split(" ", 7)[6];

			SimpleAttributeSet recv = new SimpleAttributeSet();
			StyleConstants.setForeground(recv, Color.BLACK);
			GUIObject.addRoomText(roomNumber, sender + " : ", recv);

			StyleConstants.setFontSize(recv, Integer.parseInt(Size));
			StyleConstants.setForeground(recv, new Color(Integer.parseInt(color)));
			if (Integer.parseInt(Style) == 1)
				StyleConstants.setBold(recv, true);
			GUIObject.addRoomText(roomNumber, message + "\n", recv);

			// Add by Fred
		} else if (transferLine.startsWith("/roomIcon")) {
			String IconIndex = transferLine.split(" ", 4)[1];
			String roomNumber = transferLine.split(" ", 4)[2];
			String sender = transferLine.split(" ", 4)[3];
			SimpleAttributeSet recv = new SimpleAttributeSet();
			StyleConstants.setForeground(recv, Color.BLACK);
			GUIObject.addRoomIcon(roomNumber, sender + " : ", IconIndex, recv);

			// Add by Sid
		} else if (transferLine.startsWith("/roomGIcon")) {
			String IconIndex = transferLine.split(" ", 4)[1];
			String roomNumber = transferLine.split(" ", 4)[2];
			String sender = transferLine.split(" ", 4)[3];
			SimpleAttributeSet recv = new SimpleAttributeSet();
			StyleConstants.setForeground(recv, Color.BLACK);
			GUIObject.addRoomGIcon(roomNumber, sender + " : ", IconIndex, recv);

			// Add by Sid
		} else if (transferLine.startsWith("/leaveRoom")) {
			String roomNumber = transferLine.split(" ", 3)[1];
			String removeUser = transferLine.split(" ", 3)[2];
			// RoomNumber UserName

			for (RoomPanel room : roomList) {

				if (room.getName().equals(roomNumber)) {
					room.removeUser(removeUser);
					break;
				}
			}

			// Add by Sid
		} else if (transferLine.startsWith("/closeRoom")) {
			String roomNumber = transferLine.split(" ", 2)[1];
			RoomPanel removeRoom = null;
			// RoomNumber
			for (RoomPanel room : roomList) {
				if (room.getName().equals(roomNumber)) {
					removeRoom = room;
					GUIObject.removeRoom(removeRoom);
					break;
				}

			}

			roomList.remove(removeRoom);

		} else if (transferLine.startsWith("/SecretMsg ")) {
			String sender = transferLine.split(" ", 3)[1];
			String msg = transferLine.split(" ", 3)[2];
			JOptionPane.showMessageDialog(GUIObject, msg, "Secret Message from " + sender, JOptionPane.INFORMATION_MESSAGE);

		} else if (transferLine.startsWith("/newUser")) {
			String newUser = transferLine.split(" ", 2)[1];
			GUIObject.addUser(newUser);

		} else if (transferLine.startsWith("/userLeave ")) {
			String leave = transferLine.split(" ", 2)[1];
			GUIObject.removeUser(leave);

		} else if (transferLine.startsWith("/recvFile")) {
			String sender = transferLine.split(" ", 3)[1];
			String ip = transferLine.split(" ", 3)[2];
			FileReceiver fs = new FileReceiver(ip, sender, GUIObject);
			Thread thd = new Thread(fs);
			thd.start();

			// add by Michael
		} else if (transferLine.startsWith("/videoStream")) {
			String sender = transferLine.split(" ", 3)[1];
			String ip = transferLine.split(" ", 3)[2];
			VideoGUI fs = new VideoGUI(ip, sender);
			fs.setLocationRelativeTo(GUIObject);
			fs.setVisible(true);

		} else if (transferLine.startsWith("/roomAlarm")) {
			String roomNumber = transferLine.split(" ", 3)[1];
			String sender = transferLine.split(" ", 3)[2];

			Vibration vibrate = new Vibration(GUIObject);
			Thread thd = new Thread(vibrate);
			thd.start();

			SimpleAttributeSet recv = new SimpleAttributeSet();
			StyleConstants.setForeground(recv, new Color((int) Math.floor(random.nextDouble() * 256),(int) Math.floor(random.nextDouble() * 256),(int) Math.floor(random.nextDouble() * 256),255));
			StyleConstants.setFontSize(recv, Integer.parseInt("30"));
			String randomS = getRandomString();
			GUIObject.addRoomText(roomNumber, sender + " : " + randomS + "\n", recv);
		}

		// System.out.println("Recv: " + transferLine);
		// SimpleAttributeSet recv = new SimpleAttributeSet();
		// StyleConstants.setForeground(recv, Color.RED);
		// GUIObject.addText("Recv: " + transferLine, recv);
	}

	private String getRandomString() {
		// TODO Auto-generated method stub
		String randomS;
		int ranNum = (int) Math.floor(random.nextDouble() * 7);
		switch (ranNum) {
		case 0:
			randomS="退回服貿!!!";
			break;
		case 1:
			randomS="捍衛民主!!!";
			break;
		case 2:
			randomS="馬英九下台!!!";
			break;
		case 4:
			randomS="占領立法院!!!";
			break;
		case 5:
			randomS="占領行政院!!!";
			break;
		default:
			randomS="占領總統府!!!";
		}
		return randomS;
	}

	public void connectToServer(String ip, int port2, String name) {
		// TODO Auto-generated method stub
		serverIP = ip;
		port = port2;
		username = name;

		// create socket
		try {
			socket.close();
			GUIObject.userListClear();
		} catch (Exception e) {

		}

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

	public String getName() {

		return username;
	}

}
