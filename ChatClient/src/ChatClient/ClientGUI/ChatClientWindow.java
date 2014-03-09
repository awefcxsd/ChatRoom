package ChatClient.ClientGUI;

import javax.swing.JFrame;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyledDocument;

import ChatClient.ClientCom.ChatSlaveClient;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JComboBox;

import java.awt.List;
import java.awt.Color;
import java.awt.Font;
import java.util.Vector;
import javax.swing.JTabbedPane;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Dimension;

public class ChatClientWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField EnterMessage;
	private JTextField EnterIP;
	private JTextField EnterPort;
	private JButton btnEicon = new JButton();
	private Vector <JButton> btnEiconList;
	
	JScrollPane scrollPane;
	private List userList;
	private ChatSlaveClient ClientObject;
	private StyledDocument doc;
	public String ip;
	public int port;
	public String name;
	private JTextField EnterName;
	public JTabbedPane tabbedPane;
	private JFrame thisFrame;
	public JTextPane textPane;

	public ChatClientWindow() {
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		btnEiconList = new  Vector <JButton>();
		thisFrame = this;
		Font font = new Font(Font.DIALOG_INPUT, Font.PLAIN, 12);

		// {{ Layout set up
		setResizable(false);
		setTitle("ChatClient");
		setSize(883, 590);
		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));

		BackGroundPanel panel = new BackGroundPanel();
		getContentPane().add(panel);
		panel.setLayout(null);

		EnterIP = new JTextField();
		EnterIP.setFont(font);
		EnterIP.setText("140.112.18.205");
		EnterIP.setBounds(10, 36, 124, 21);
		panel.add(EnterIP);
		EnterIP.setColumns(10);

		EnterPort = new JTextField();
		EnterPort.setFont(font);
		EnterPort.setText("9987");
		EnterPort.setColumns(10);
		EnterPort.setBounds(10, 91, 124, 21);
		panel.add(EnterPort);

		final JButton btnConnect = new JButton("Connect");
		btnConnect.setFont(font);
		btnConnect.setBounds(10, 184, 124, 23);
		panel.add(btnConnect);

		JLabel lblIp = new JLabel("IP");
		lblIp.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 16));
		//blIp.setForeground(Color.PINK);
		lblIp.setForeground(Color.WHITE);
		lblIp.setBounds(10, 16, 46, 15);
		panel.add(lblIp);

		JLabel lblPort = new JLabel("Port");
		lblPort.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 16));
		lblPort.setForeground(Color.WHITE);
		lblPort.setBounds(10, 67, 46, 15);
		panel.add(lblPort);

		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 16));
		lblName.setForeground(Color.WHITE);
		lblName.setBounds(10, 122, 46, 15);
		panel.add(lblName);

		EnterName = new JTextField();
		EnterName.setFont(font);
		EnterName.setText("");
		EnterName.setColumns(10);
		EnterName.setBounds(10, 147, 124, 21);
		panel.add(EnterName);

		userList = new List();
		userList.setFont(new Font(Font.DIALOG_INPUT, Font.PLAIN, 16));
		userList.setMultipleSelections(false);
		userList.setBounds(689, 59, 143, 339);
		panel.add(userList);

		JLabel lblUserOnline = new JLabel("User Online");
		lblUserOnline.setBackground(Color.WHITE);
		lblUserOnline.setForeground(Color.WHITE);
		lblUserOnline.setFont(new Font("Bauhaus 93", Font.PLAIN, 25));
		lblUserOnline.setBounds(689, 20, 143, 43);
		panel.add(lblUserOnline);

		UIManager.put("TabbedPane.contentOpaque", false);
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setBackground(Color.WHITE);
		tabbedPane.setBounds(171, 35, 477, 443);
		panel.add(tabbedPane);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255, 100));
		tabbedPane.addTab("Main", null, panel_1, null);
		tabbedPane.setFont(font);
		panel_1.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(255, 255, 255, 255));
		scrollPane.setBounds(0, 0, 472, 332);
		panel_1.add(scrollPane);
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
		panel_1.add(EnterMessage);
		EnterMessage.setColumns(10);

		JButton btnSend = new JButton("Send");
		btnSend.setFont(font);
		btnSend.setBounds(10, 381, 87, 23);
		panel_1.add(btnSend);
		
		
		final JButton btnEiconProfile = new JButton("");
		btnEiconProfile.setBounds(370, 341, 64, 64);
		btnEiconProfile.setFocusPainted(false); 
		btnEiconProfile.setIcon(new ImageIcon("image/emoticon/profile.jpg"));
		btnEiconProfile.setRolloverIcon(new ImageIcon("image/emoticon/profile.jpg"));
		panel_1.add(btnEiconProfile);
		
		final JPopupMenu popupMenu = new JPopupMenu();
		popupMenu.setPopupSize(new Dimension(300, 300));
		popupMenu.setAutoscrolls(true);
		popupMenu.setLayout(new GridLayout(5, 5));
		//popupMenu.s;
		btnEiconProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				popupMenu.show(btnEiconProfile, 50, 50);
			}
		});
		//addPopup(btnEiconProfile, popupMenu);

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
					ClientObject.send("/BoradCastIcon "+ String.valueOf(btnEiconList.indexOf(arg0.getSource())+1));
				}
			});
		}

			

		JButton btnChatroom = new JButton("New Chat Room");
		btnChatroom.setFont(font);

		btnChatroom.setBounds(10, 217, 124, 23);
		panel.add(btnChatroom);

		JButton btnAddMember = new JButton("Add Member");
		btnAddMember.setFont(font);
		btnAddMember.setBounds(693, 410, 130, 23);
		panel.add(btnAddMember);

		JButton btnSecret = new JButton("Secret Message");
		btnSecret.setFont(font);
		btnSecret.setBounds(693, 442, 130, 23);
		panel.add(btnSecret);

		JButton btnSendFile = new JButton("Send File");
		btnSendFile.setFont(font);
		btnSendFile.setBounds(693, 475, 130, 23);
		panel.add(btnSendFile);
		
		JButton btnVideo = new JButton("Video");
		btnVideo.setFont(font);
		btnVideo.setBounds(693, 508, 130, 23);
		panel.add(btnVideo);

		// }}

		doc = textPane.getStyledDocument();

		ClientObject = new ChatSlaveClient(this);

		// {{ Event
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ip = EnterIP.getText();
				String port_str = EnterPort.getText();
				port = new java.lang.Integer(port_str).intValue();
				name = EnterName.getText();
				if (name.isEmpty()) {
					JOptionPane.showMessageDialog(thisFrame,
							"UserName can not be empty", "Error",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					ClientObject.connectToServer(ip, port, name);
					ClientObject.setName(name);
				}
			}
		});
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String sendText = EnterMessage.getText();
				EnterMessage.setText("");
				if (!sendText.isEmpty()) {
					ClientObject.send("/BoradCastMessage " + sendText);
				}
			}
		});
		EnterMessage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String sendText = EnterMessage.getText();
				EnterMessage.setText("");
				if (!sendText.isEmpty()) {
					ClientObject.send("/BoradCastMessage " + sendText);
				}
			}
		});
		// Add by Sid
		btnChatroom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String sendText = "/openNewRoom";
				ClientObject.send(sendText);
			}
		});
		// Add by Sid
		btnAddMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					RoomPanel currentRoom = (RoomPanel) tabbedPane
							.getSelectedComponent();
					System.out.println("Room name " + currentRoom.getName());

					String userName = userList.getSelectedItem();
					System.out.println("Add memeber " + userName);
					if (userName != null
							&& !userName.equals(ClientObject.getName())) {

						boolean alreadyChoose = false;

						for (String name : currentRoom.roomUsers) {
							if (name.equals(userName)) {
								alreadyChoose = true;
								break;
							}
						}
						if (alreadyChoose) {
							System.out.println("Member Already Added");
						} else {
							String sendText = "/invite "
									+ currentRoom.getName() + " " + userName;
							ClientObject.send(sendText);
						}
					} else {
						System.out
								.println("User Name Not Selected, Attempt to Add Oneself");

					}

				} catch (Exception err) {
					System.out.println(err.toString());

				}
			}
		});
		// Add by Michael
		btnSecret.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userName = userList.getSelectedItem();
				if (userName != null) {
					SecretMsg s = new SecretMsg(userName, ClientObject);
					s.setLocationRelativeTo(thisFrame);
					s.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(thisFrame,
							"User Name Not Selected", "Error",
							JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});
		// Add by Michael
		btnSendFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userName = userList.getSelectedItem();
				if (userName != null) {
					SendFileGUI s = new SendFileGUI(userName, ClientObject);
					s.setLocationRelativeTo(thisFrame);
					s.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(thisFrame,
							"User Name Not Selected", "Error",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		//add by Michael
		btnVideo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userName = userList.getSelectedItem();
				if (userName != null) {
					ClientObject.send("/videoStream "+userName);
				} else {
					JOptionPane.showMessageDialog(thisFrame,
							"User Name Not Selected", "Error",
							JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});
	}

	public void addText(String add, SimpleAttributeSet texture) {
		try {
			doc.insertString(doc.getLength(), add + "\n", texture);
			JScrollBar sBar = scrollPane.getVerticalScrollBar();
			sBar.setValue(sBar.getMaximum());
		} catch (Exception e) {

		}
	}
	
	public void addIcon(String add, String IconIndex, SimpleAttributeSet texture) {
		try {
			doc.insertString(doc.getLength(), add , texture);
			textPane.setCaretPosition(doc.getLength());
			int intIcon = Integer.valueOf(IconIndex);
			if(intIcon >= 1 && intIcon <= 9){
				textPane.insertIcon(new ImageIcon("image/emoticon/0"+ IconIndex + ".gif"));	
			}else{
				textPane.insertIcon(new ImageIcon("image/emoticon/"+ IconIndex + ".gif"));	
			}
						
			doc.insertString(doc.getLength(), "\n" , texture);
			JScrollBar sBar = scrollPane.getVerticalScrollBar();
			sBar.setValue(sBar.getMaximum());
		} catch (Exception e) {

		}
	}

	// Add by Sid
	public void addRoomText(String roomNumber, String add,
			SimpleAttributeSet texture) {
		try {
			StyledDocument roomDoc;

			for (RoomPanel room : ClientObject.roomList) {
				if (room.getName().equals(roomNumber)) {
					roomDoc = room.getDoc();
					roomDoc.insertString(roomDoc.getLength(), add + "\n",
							texture);
					JScrollBar sBar = room.scrollPane.getVerticalScrollBar();
					sBar.setValue(sBar.getMaximum());
					break;
				}
			}

		} catch (Exception e) {

		}
	}
	
	// Add by Fred
	public void addRoomIcon(String roomNumber, String add, String IconIndex,
			SimpleAttributeSet texture) {
		try {
			StyledDocument roomDoc;

			for (RoomPanel room : ClientObject.roomList) {
				if (room.getName().equals(roomNumber)) {
					roomDoc = room.getDoc();
					roomDoc.insertString(roomDoc.getLength(), add , texture);
					room.textPane.setCaretPosition(roomDoc.getLength());
					int intIcon = Integer.valueOf(IconIndex);
					if(intIcon >= 1 && intIcon <= 9){
						room.textPane.insertIcon(new ImageIcon("image/emoticon/0"+ IconIndex + ".gif"));	
					}else{
						room.textPane.insertIcon(new ImageIcon("image/emoticon/"+ IconIndex + ".gif"));	
					}				
					roomDoc.insertString(roomDoc.getLength(), "\n" , texture);
					JScrollBar sBar = scrollPane.getVerticalScrollBar();
					sBar.setValue(sBar.getMaximum());
					break;					
				}
			}

		} catch (Exception e) {

		}
	}

	public void addUser(String other) {
		userList.add(other);
	}

	public void removeUser(String other) {
		userList.remove(other);
	}

	public void userListClear() {
		// TODO Auto-generated method stub
		userList.removeAll();
	}

	// Add by Sid
	public void addNewTab(RoomPanel newRoom) {

		tabbedPane.addTab(newRoom.getName(), null, newRoom, null);

	}

	public void removeRoom(RoomPanel removeRoom) {

		System.out.println("reomve" + removeRoom.getName());
		tabbedPane.remove(removeRoom);

	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
