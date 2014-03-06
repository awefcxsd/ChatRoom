package ChatClient.ClientGUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.StyledDocument;

import ChatClient.ClientCom.ChatSlaveClient;

//Add by Sid

public class RoomPanel extends JPanel{
	
	public JScrollPane scrollPane;
	public JTextPane textPane;
	public JTextField EnterMessage;
	public JButton btnSend;
	public String roomName;
	public StyledDocument doc;
	public ChatSlaveClient client;
	public Vector<String> roomUsers;

	private JLabel memberLabel;
	
	
	public RoomPanel(ChatSlaveClient clientObject){
		
		
	
	    client = clientObject;
		roomUsers = new Vector<String>();
	    
	    this.setBackground(new Color(255, 255, 255, 100));
	    //tabbedPane.addTab("Main", null, this, null);
		this.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(255, 255, 255, 255));
		scrollPane.setBounds(0, 0, 472, 332);
		this.add(scrollPane);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

	    textPane = new JTextPane();
		textPane.setBackground(new Color(255, 255, 255, 255));
		scrollPane.setViewportView(textPane);
		textPane.setEditable(false);

	    EnterMessage = new JTextField();
		EnterMessage.setBounds(10, 350, 309, 21);
		this.add(EnterMessage);
		EnterMessage.setColumns(10);

		btnSend = new JButton("Send");
		btnSend.setBounds(10, 381, 87, 23);
		this.add(btnSend);
		
		/*
		memberLabel = new JLabel("Members: ");
		memberLabel.setBounds(120, 381, 200, 23);
		this.add(memberLabel);
		*/
		
		doc = textPane.getStyledDocument();
		
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String sendText = EnterMessage.getText();
				EnterMessage.setText("");
				if (!sendText.isEmpty()) {
					client.send("/roomMsg "+ roomName +" "+ sendText);
				}
			}
		});
		EnterMessage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String sendText = EnterMessage.getText();
				EnterMessage.setText("");
				if (!sendText.isEmpty()) {
					client.send("/roomMsg "+ roomName +" "+ sendText);
				}
			}
		});

	}
	
	public String getName(){
		
		
		return roomName;
		
	}
	
	public void setName(String newName){
		
		roomName = newName;
		
	}
	
	public StyledDocument getDoc(){
		
		return doc;
	}
	
	public void joinUser(String user){
		
		roomUsers.add(user);
		String memberString = "Members: ";
		for(String userString: roomUsers){
			
			memberString += (userString+" ");
		}
		
		//memberLabel.setText(memberString);
		
	}
}
