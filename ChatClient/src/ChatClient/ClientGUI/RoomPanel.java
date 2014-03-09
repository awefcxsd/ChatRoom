package ChatClient.ClientGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.StyledDocument;

import ChatClient.ClientCom.ChatSlaveClient;

//Add by Sid

public class RoomPanel extends JPanel {

	public JScrollPane scrollPane;
	public JTextPane textPane;
	public JTextField EnterMessage;
	public JButton btnSend;
	public JButton btnEicon;
	private Vector <JButton> btnEiconList;
	
	public String roomName;
	public StyledDocument doc;
	public ChatSlaveClient client;
	public Vector<String> roomUsers;

	private JTextField memberLabel;
	private JButton btnLeave;
	private JButton btnVibrate;

	public RoomPanel(ChatSlaveClient clientObject) {
		Font font = new Font(Font.DIALOG_INPUT, Font.PLAIN, 12);
		client = clientObject;
		roomUsers = new Vector<String>();
		btnEiconList = new  Vector <JButton>();

		this.setBackground(new Color(255, 255, 255, 100));
		// tabbedPane.addTab("Main", null, this, null);
		this.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(255, 255, 255, 255));
		scrollPane.setBounds(0, 25, 472, 307);
		this.add(scrollPane);
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		textPane = new JTextPane();
		textPane.setFont(font);
		textPane.setBackground(new Color(255, 255, 255, 255));
		scrollPane.setViewportView(textPane);
		textPane.setEditable(false);

		EnterMessage = new JTextField();
		EnterMessage.setFont(font);
		EnterMessage.setBounds(10, 350, 309, 21);
		this.add(EnterMessage);
		EnterMessage.setColumns(10);

		btnSend = new JButton("Send");
		btnSend.setFont(font);
		btnSend.setBounds(10, 381, 87, 23);
		this.add(btnSend);

		btnLeave = new JButton("Leave");
		btnLeave.setFont(font);
		btnLeave.setBounds(120, 381, 87, 23);
		this.add(btnLeave);

		final JButton btnEiconProfile = new JButton("");
		btnEiconProfile.setBounds(370, 341, 64, 64);
		btnEiconProfile.setFocusPainted(false); 
		btnEiconProfile.setIcon(new ImageIcon("image/emoticon/profile.jpg"));
		btnEiconProfile.setRolloverIcon(new ImageIcon("image/emoticon/profile.jpg"));
		this.add(btnEiconProfile);
		
		final JPopupMenu popupMenu = new JPopupMenu();
		popupMenu.setPopupSize(new Dimension(300, 300));
		popupMenu.setAutoscrolls(true);
		popupMenu.setLayout(new GridLayout(5, 5));
		btnEiconProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				popupMenu.show(btnEiconProfile, 50, 50);
			}
		});
		
		// Add by Fred
		for(int i=0; i<25; i++){
			btnEicon = new JButton(); 
			if(i>=0 && i<=8){
				btnEicon.setIcon(new ImageIcon("image/emoticon/0"+ String.valueOf(i+1) + ".gif"));
				btnEicon.setRolloverIcon(new ImageIcon("image/emoticon/0"+ String.valueOf(i+1) + ".gif"));
			} else{
				btnEicon.setIcon(new ImageIcon("image/emoticon/"+ String.valueOf(i+1) + ".gif"));
				btnEicon.setRolloverIcon(new ImageIcon("image/emoticon/"+ String.valueOf(i+1) + ".gif"));
			}
			btnEicon.setFocusPainted(false); 
			popupMenu.add(btnEicon);
			btnEiconList.add(btnEicon);
			btnEiconList.get(i).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					client.send("/roomIcon "+ String.valueOf(btnEiconList.indexOf(arg0.getSource())+1) + " " + roomName );
				}
			});
		}
		
		btnVibrate = new JButton("Vibrate");
		btnVibrate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				client.send("/roomAlarm " + roomName);
			}
		});
		btnVibrate.setFont(new Font("DialogInput", Font.PLAIN, 12));
		btnVibrate.setBounds(230, 381, 87, 23);
		add(btnVibrate);

		
		memberLabel = new JTextField("Members: ");
		memberLabel.setFont(font);
		memberLabel.setBounds(0, 0, 472, 23);
		memberLabel.setEditable(false);
		this.add(memberLabel);

		doc = textPane.getStyledDocument();

		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String sendText = EnterMessage.getText();
				EnterMessage.setText("");
				if (!sendText.isEmpty()) {
					client.send("/roomMsg " + roomName + " " + sendText);
				}
			}
		});
		EnterMessage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String sendText = EnterMessage.getText();
				EnterMessage.setText("");
				if (!sendText.isEmpty()) {
					client.send("/roomMsg " + roomName + " " + sendText);
				}
			}
		});
		btnLeave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				client.send("/leaveRoom " + roomName);
			}
		});

	}

	public String getName() {

		return roomName;

	}

	public void setName(String newName) {

		roomName = newName;

	}

	public StyledDocument getDoc() {

		return doc;
	}

	public void joinUser(String user) {

		roomUsers.add(user);
		String memberString = "Members: ";
		for (String userString : roomUsers) {

			memberString += (userString + "  ");
		}

		memberLabel.setText(memberString);

	}

	public void removeUser(String user) {

		roomUsers.remove(user);
		String memberString = "Members: ";
		for (String userString : roomUsers) {

			memberString += (userString + "  ");
		}

		memberLabel.setText(memberString);

	}
}
