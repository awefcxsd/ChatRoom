package ChatClient.ClientGUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyledDocument;

import ChatClient.ClientCom.ChatSlaveClient;
import ChatClient.util.colorButton;

import javax.swing.JComboBox;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class ChatClientWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField EnterMessage;
	private JTextField EnterIP;
	private JTextField EnterPort;
	private JButton btnEicon = new JButton();
	private Vector<JButton> btnEiconList;
	private Vector<JButton> btnEiconGList;
	private String [] fontSize = { "6", "8", "10", "12", "14", "16", "18", "20", "22", "24", "26", "28", "30"};
	private JColorChooser colorChooser;
	private Container con = getContentPane();
	private colorButton color; 
	
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
	
	ImageIcon fishImage[];
	Cursor fishCursor[];
	private JTextField textField;
	Point oldPoint;
	Point currentPoint;
	MouseMotionAdapter motionAdapter;
	int count=0;
	int imageCount = 2;

	public ChatClientWindow() {
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		btnEiconList = new Vector<JButton>();
		btnEiconGList = new Vector<JButton>();
		thisFrame = this;
		Font font = new Font(Font.DIALOG_INPUT, Font.PLAIN, 12);

		// {{ Layout set up
		setResizable(false);
		setTitle("ChatClient");
		setSize(879, 586);
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
		btnConnect.setFont(new Font("Eras Demi ITC", Font.PLAIN, 12));
		btnConnect.setBounds(10, 184, 124, 23);
		panel.add(btnConnect);

		JLabel lblIp = new JLabel("IP");
		lblIp.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 16));
		// blIp.setForeground(Color.PINK);
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
		scrollPane.setBounds(5, 0, 467, 332);
		panel_1.add(scrollPane);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		textPane = new JTextPane();
		textPane.setFont(font);
		textPane.setBackground(new Color(255, 255, 255, 255));
		scrollPane.setViewportView(textPane);
		textPane.setEditable(false);

		EnterMessage = new JTextField();
		EnterMessage.setFont(font);
		EnterMessage.setBounds(4, 336, 286, 41);
		panel_1.add(EnterMessage);
		EnterMessage.setColumns(10);

		JButton btnSend = new JButton("Send");
		btnSend.setFont(new Font("Eras Demi ITC", Font.PLAIN, 12));
		btnSend.setBounds(300, 336, 87, 23);
		panel_1.add(btnSend);

		final JButton btnEiconProfile = new JButton("");
		btnEiconProfile.setBounds(395, 341, 60, 60);
		btnEiconProfile.setFocusPainted(false); 
		btnEiconProfile.setIcon(new ImageIcon("image/emoticon/profile.jpg"));
		btnEiconProfile.setRolloverIcon(new ImageIcon("image/emoticon/profile2.jpg"));
		panel_1.add(btnEiconProfile);

		final JPopupMenu popupMenu = new JPopupMenu();
		popupMenu.setPopupSize(new Dimension(250, 250));
		popupMenu.setAutoscrolls(true);
		popupMenu.setLayout(new GridLayout(5, 5));

		final JPopupMenu popupMenuG = new JPopupMenu();
		popupMenuG.setPopupSize(new Dimension(200, 200));
		popupMenuG.setAutoscrolls(true);
		popupMenuG.setLayout(new GridLayout(4, 4));

		// popupMenu.s;
		btnEiconProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				popupMenu.show(btnEiconProfile, 50, 50);
			}
		});
		addPopup(btnEiconProfile, popupMenuG);
		
		JToggleButton bold = new JToggleButton("");
	
		bold.setIcon(new ImageIcon("image/Font/bold.png"));
		bold.setBounds(193, 382, 20, 20);
		panel_1.add(bold);
		
		bold.addActionListener(new ActionListener() { 
			private boolean flag = true;
			ImageIcon icon1 = new ImageIcon("image/Font/bold.png"); 
			ImageIcon icon2	= new ImageIcon("image/Font/bold2.png");

			public void actionPerformed(ActionEvent e) {
				((JToggleButton)e.getSource()).setIcon( flag ? icon2 : icon1 );
				flag = !flag;
				Font f = EnterMessage.getFont();
				if(f.getStyle()==0) {
					EnterMessage.setFont(f.deriveFont(1));
				} else {
					EnterMessage.setFont(f.deriveFont(0));
				}
			}
		});
		
		color = new colorButton();
		color.setBounds(218, 382, 20, 20);
		color.setButtonColor(Color.BLACK);
		panel_1.add(color);
		
		color.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 colorChooser = new JColorChooser();
				 colorButton B = (colorButton)e.getSource();
				 Color c = colorChooser.showDialog(B, "Choose the color of words.", B.getBackground());
				 B.setButtonColor(c);
				 EnterMessage.setForeground(c);
			}
		});
		

		
		JComboBox sizeBox = new JComboBox(fontSize);
		sizeBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String size = (String)e.getItem();
				Font f = EnterMessage.getFont();
				EnterMessage.setFont(f.deriveFont((float)Integer.valueOf(size).intValue()));;
			}
		});
		
		sizeBox.setBounds(242, 381, 48, 24);
		sizeBox.setSelectedIndex(3);
		panel_1.add(sizeBox);

		
		
		fishImage = new ImageIcon[imageCount];
		fishCursor = new Cursor[imageCount];
		
		for(int i = 0;i<imageCount;++i){
			int k = i+1;
			System.out.println(i);
			fishImage[i] = new ImageIcon("image/fish/pokemon["+k+"].png");
			
			System.out.println(fishImage[i]);
			fishCursor[i]= Toolkit.getDefaultToolkit().createCustomCursor(fishImage[i].getImage(),new Point(16 ,16), "fish");
		}
		
		oldPoint = new Point(0,0);
		
		setCursor(fishCursor[0]);
		
		motionAdapter = new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				currentPoint = e.getPoint();
				if (currentPoint.distance(oldPoint)>15){
					setCursor(FishCursorGetter(currentPoint));
					oldPoint = currentPoint;
				}
			}
		};
		
		addMouseMotionListener(motionAdapter);
		DfsAddListener(this);
		

		// Add by Fred
		for (int i = 0; i < 25; i++) {
			btnEicon = new JButton();
			if (i >= 0 && i <= 8) {
				btnEicon.setIcon(new ImageIcon("image/emoticon/0" + String.valueOf(i + 1) + ".gif"));
				btnEicon.setRolloverIcon(new ImageIcon("image/emoticon/0" + String.valueOf(i + 1) + ".gif"));
			} else {
				btnEicon.setIcon(new ImageIcon("image/emoticon/" + String.valueOf(i + 1) + ".gif"));
				btnEicon.setRolloverIcon(new ImageIcon("image/emoticon/" + String.valueOf(i + 1) + ".gif"));
			}
			btnEicon.setFocusPainted(false);
			popupMenu.add(btnEicon);
			btnEiconList.add(btnEicon);
			btnEiconList.get(i).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					ClientObject.send("/BoradCastIcon " + String.valueOf(btnEiconList.indexOf(arg0.getSource()) + 1));
				}
			});
		}
//		// 封印
//		for (int i = 0; i < 16; i++) {
//			btnEicon = new JButton();
//			if (i >= 0 && i <= 8) {
//				btnEicon.setIcon(new ImageIcon("image/Gricon/menu/0" + String.valueOf(i + 1) + ".jpg"));
//				btnEicon.setRolloverIcon(new ImageIcon("image/Gricon/menu/0" + String.valueOf(i + 1) + ".jpg"));
//			} else {
//				btnEicon.setIcon(new ImageIcon("image/Gricon/menu/" + String.valueOf(i + 1) + ".jpg"));
//				btnEicon.setRolloverIcon(new ImageIcon("image/Gricon/menu/" + String.valueOf(i + 1) + ".jpg"));
//			}
//			btnEicon.setFocusPainted(false);
//			popupMenuG.add(btnEicon);
//			btnEiconGList.add(btnEicon);
//			btnEiconGList.get(i).addActionListener(new ActionListener() {
//				public void actionPerformed(ActionEvent arg0) {
//					ClientObject.send("/BoradCastGIcon " + String.valueOf(btnEiconGList.indexOf(arg0.getSource()) + 1));
//				}
//			});
//		}

		JButton btnChatroom = new JButton("New Chat Room");
		btnChatroom.setFont(new Font("Eras Demi ITC", Font.PLAIN, 12));

		btnChatroom.setBounds(10, 217, 124, 23);
		panel.add(btnChatroom);

		JButton btnAddMember = new JButton("Add Member");
		btnAddMember.setFont(new Font("Eras Demi ITC", Font.PLAIN, 12));
		btnAddMember.setBounds(693, 410, 130, 23);
		panel.add(btnAddMember);

		JButton btnSecret = new JButton("Secret Message");
		btnSecret.setFont(new Font("Eras Demi ITC", Font.PLAIN, 12));
		btnSecret.setBounds(693, 442, 130, 23);
		panel.add(btnSecret);

		JButton btnSendFile = new JButton("Send File");
		btnSendFile.setFont(new Font("Eras Demi ITC", Font.PLAIN, 12));
		btnSendFile.setBounds(693, 475, 130, 23);
		panel.add(btnSendFile);

		JButton btnVideo = new JButton("Video");
		btnVideo.setFont(new Font("Eras Demi ITC", Font.PLAIN, 12));
		btnVideo.setBounds(693, 508, 130, 23);
		panel.add(btnVideo);

		JLabel labelBack = new JLabel("");
		labelBack.setIcon(new ImageIcon("image/insta.gif"));
		labelBack.setBounds(0, 0, 877, 562);
		panel.add(labelBack);

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
					JOptionPane.showMessageDialog(thisFrame, "UserName can not be empty", "Error", JOptionPane.INFORMATION_MESSAGE);
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
					ClientObject.send("/BoradCastMessage " + EnterMessage.getFont().getStyle() + " " + 
									 EnterMessage.getFont().getSize() + " " + color.getButtonColor() + " " + sendText);
				}
			}
		});
		EnterMessage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String sendText = EnterMessage.getText();
				EnterMessage.setText("");
				if (!sendText.isEmpty()) {
					ClientObject.send("/BoradCastMessage " + EnterMessage.getFont().getStyle() + " " + 
							 			EnterMessage.getFont().getSize() + " " + color.getButtonColor() + " " + sendText);
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

					RoomPanel currentRoom = (RoomPanel) tabbedPane.getSelectedComponent();
					System.out.println("Room name " + currentRoom.getName());

					String userName = userList.getSelectedItem();
					System.out.println("Add memeber " + userName);
					if (userName != null && !userName.equals(ClientObject.getName())) {

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
							String sendText = "/invite " + currentRoom.getName() + " " + userName;
							ClientObject.send(sendText);
						}
					} else {
						System.out.println("User Name Not Selected, Attempt to Add Oneself");

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
					JOptionPane.showMessageDialog(thisFrame, "User Name Not Selected", "Error", JOptionPane.INFORMATION_MESSAGE);
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
					JOptionPane.showMessageDialog(thisFrame, "User Name Not Selected", "Error", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		// add by Michael
		btnVideo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userName = userList.getSelectedItem();
				if (userName != null) {
					ClientObject.send("/videoStream " + userName);
				} else {
					JOptionPane.showMessageDialog(thisFrame, "User Name Not Selected", "Error", JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});
	}

	public void addText(String add, SimpleAttributeSet texture) {
		try {
			doc.insertString(doc.getLength(), add , texture);
			JScrollBar sBar = scrollPane.getVerticalScrollBar();
			sBar.setValue(sBar.getMaximum());
		} catch (Exception e) {

		}
	}

	public void addIcon(String add, String IconIndex, SimpleAttributeSet texture) {
		try {
			doc.insertString(doc.getLength(), add, texture);
			textPane.setCaretPosition(doc.getLength());
			int intIcon = Integer.valueOf(IconIndex);
			if (intIcon >= 1 && intIcon <= 9) {
				textPane.insertIcon(new ImageIcon("image/emoticon/0" + IconIndex + ".gif"));
			} else {
				textPane.insertIcon(new ImageIcon("image/emoticon/" + IconIndex + ".gif"));
			}

			doc.insertString(doc.getLength(), "\n", texture);
			JScrollBar sBar = scrollPane.getVerticalScrollBar();
			sBar.setValue(sBar.getMaximum());
		} catch (Exception e) {

		}
	}
	
	public void addGIcon(String add, String IconIndex, SimpleAttributeSet texture) {
		try {
			doc.insertString(doc.getLength(), add, texture);
			textPane.setCaretPosition(doc.getLength());
			int intIcon = Integer.valueOf(IconIndex);
			if (intIcon >= 1 && intIcon <= 9) {
				textPane.insertIcon(new ImageIcon("image/Gricon/show/0" + IconIndex + ".jpg"));
			} else {
				textPane.insertIcon(new ImageIcon("image/Gricon/show/" + IconIndex + ".jpg"));
			}
			doc.insertString(doc.getLength(), "\n", texture);
			JScrollBar sBar = scrollPane.getVerticalScrollBar();
			sBar.setValue(sBar.getMaximum());
		} catch (Exception e) {

		}
	}

	// Add by Sid
	public void addRoomText(String roomNumber, String add, SimpleAttributeSet texture) {
		try {
			StyledDocument roomDoc;

			for (RoomPanel room : ClientObject.roomList) {
				if (room.getName().equals(roomNumber)) {
					roomDoc = room.getDoc();
					roomDoc.insertString(roomDoc.getLength(), add + "\n", texture);
					JScrollBar sBar = room.scrollPane.getVerticalScrollBar();
					sBar.setValue(sBar.getMaximum());
					break;
				}
			}

		} catch (Exception e) {

		}
	}

	// Add by Fred
	public void addRoomIcon(String roomNumber, String add, String IconIndex, SimpleAttributeSet texture) {
		try {
			StyledDocument roomDoc;

			for (RoomPanel room : ClientObject.roomList) {
				if (room.getName().equals(roomNumber)) {
					roomDoc = room.getDoc();
					roomDoc.insertString(roomDoc.getLength(), add, texture);
					room.textPane.setCaretPosition(roomDoc.getLength());
					int intIcon = Integer.valueOf(IconIndex);
					if (intIcon >= 1 && intIcon <= 9) {
						room.textPane.insertIcon(new ImageIcon("image/emoticon/0" + IconIndex + ".gif"));
					} else {
						room.textPane.insertIcon(new ImageIcon("image/emoticon/" + IconIndex + ".gif"));
					}
					roomDoc.insertString(roomDoc.getLength(), "\n", texture);
					JScrollBar sBar = scrollPane.getVerticalScrollBar();
					sBar.setValue(sBar.getMaximum());
					break;
				}
			}

		} catch (Exception e) {

		}
	}
	
	public void addRoomGIcon(String roomNumber, String add, String IconIndex, SimpleAttributeSet texture) {
		try {
			StyledDocument roomDoc;

			for (RoomPanel room : ClientObject.roomList) {
				if (room.getName().equals(roomNumber)) {
					roomDoc = room.getDoc();
					roomDoc.insertString(roomDoc.getLength(), add, texture);
					room.textPane.setCaretPosition(roomDoc.getLength());
					int intIcon = Integer.valueOf(IconIndex);
					if (intIcon >= 1 && intIcon <= 9) {
						room.textPane.insertIcon(new ImageIcon("image/Gricon/show/0" + IconIndex + ".jpg"));
					} else {
						room.textPane.insertIcon(new ImageIcon("image/Gricon/show/" + IconIndex + ".jpg"));
					}
					roomDoc.insertString(roomDoc.getLength(), "\n", texture);
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
	
	
	public void DfsAddListener(Container component){
		
		try {
			for(Component compt: component.getComponents()){
				compt.addMouseMotionListener(motionAdapter);
				if(compt instanceof Container){
					DfsAddListener((JComponent)compt);
				}
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public Cursor FishCursorGetter(Point current){
		
		return fishCursor[count++%imageCount]; 
	}
	
	
	
}
