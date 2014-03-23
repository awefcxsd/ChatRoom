package ChatClient.ClientGUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.StyledDocument;

import ChatClient.ClientCom.ChatSlaveClient;

import javax.swing.JToggleButton;

import ChatClient.util.colorButton;

import javax.swing.JComboBox;

//Add by Sid

public class RoomPanel extends JPanel {

	public JScrollPane scrollPane;
	public JTextPane textPane;
	public JTextField EnterMessage;
	public JButton btnSend;
	public JButton btnEicon;
	private JColorChooser colorChooser;
	private String [] fontSize = { "6", "8", "10", "12", "14", "16", "18", "20", "22", "24", "26", "28", "30"};
	private JToggleButton bold;
	private JComboBox sizeBox;
	private Vector <JButton> btnEiconList;
	private Vector <JButton> btnEiconGList;
	private colorButton color; 
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
		btnEiconGList = new  Vector <JButton>();

		this.setBackground(new Color(255, 255, 255, 100));
		// tabbedPane.addTab("Main", null, this, null);
		this.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(255, 255, 255, 255));
		scrollPane.setBounds(5, 25, 467, 307);
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
		EnterMessage.setBounds(4, 336, 286, 41);
		this.add(EnterMessage);
		EnterMessage.setColumns(10);

		btnSend = new JButton("Send");
		btnSend.setFont(new Font("Eras Demi ITC", Font.PLAIN, 12));
		btnSend.setBounds(300, 336, 87, 23);
		this.add(btnSend);

		btnLeave = new JButton("Leave");
		btnLeave.setFont(new Font("Eras Demi ITC", Font.PLAIN, 12));
		btnLeave.setBounds(300, 382, 87, 23);
		this.add(btnLeave);

		final JButton btnEiconProfile = new JButton("");
		btnEiconProfile.setBounds(395, 341, 60, 60);
		btnEiconProfile.setFocusPainted(false); 
		btnEiconProfile.setIcon(new ImageIcon("image/emoticon/profile.jpg"));
		btnEiconProfile.setRolloverIcon(new ImageIcon("image/emoticon/profile2.jpg"));
		this.add(btnEiconProfile);
		
		final JPopupMenu popupMenu = new JPopupMenu();
		popupMenu.setPopupSize(new Dimension(250, 250));
		popupMenu.setAutoscrolls(true);
		popupMenu.setLayout(new GridLayout(5, 5));
		
		final JPopupMenu popupMenuG = new JPopupMenu();
		popupMenuG.setPopupSize(new Dimension(200, 200));
		popupMenuG.setAutoscrolls(true);
		popupMenuG.setLayout(new GridLayout(4, 4));
		
		btnEiconProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				popupMenu.show(btnEiconProfile, 50, 50);
			}
		});
		addPopup(btnEiconProfile, popupMenuG);
		
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
		//�ʦL
		for (int i = 0; i < 16; i++) {
			btnEicon = new JButton();
			if (i >= 0 && i <= 8) {
				btnEicon.setIcon(new ImageIcon("image/Gricon/menu/0" + String.valueOf(i + 1) + ".jpg"));
				btnEicon.setRolloverIcon(new ImageIcon("image/Gricon/menu/0" + String.valueOf(i + 1) + ".jpg"));
			} else {
				btnEicon.setIcon(new ImageIcon("image/Gricon/menu/" + String.valueOf(i + 1) + ".jpg"));
				btnEicon.setRolloverIcon(new ImageIcon("image/Gricon/menu/" + String.valueOf(i + 1) + ".jpg"));
			}
			btnEicon.setFocusPainted(false);
			popupMenuG.add(btnEicon);
			btnEiconGList.add(btnEicon);
			btnEiconGList.get(i).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					client.send("/roomGIcon "+ String.valueOf(btnEiconGList.indexOf(arg0.getSource())+1) + " " + roomName );
				}
			});
		}
		
		btnVibrate = new JButton("Vibrate");
		btnVibrate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				client.send("/roomAlarm " + roomName);
			}
		});
		btnVibrate.setFont(new Font("Eras Demi ITC", Font.PLAIN, 12));
		btnVibrate.setBounds(300, 359, 87, 23);
		add(btnVibrate);

		
		memberLabel = new JTextField("Members: ");
		memberLabel.setFont(font);
		memberLabel.setBounds(5, 0, 467, 23);
		memberLabel.setEditable(false);
		this.add(memberLabel);
		
		bold = new JToggleButton("");
		bold.setIcon(new ImageIcon("image/Font/bold.png"));
		bold.setBounds(193, 382, 20, 20);
		add(bold);
		
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
		add(color);
		color.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 colorChooser = new JColorChooser();
				 colorButton B = (colorButton)e.getSource();
				 Color c = colorChooser.showDialog(B, "Choose the color of words.", B.getBackground());
				 B.setButtonColor(c);
				 EnterMessage.setForeground(c);
			}
		});
		
		
		
		sizeBox = new JComboBox(fontSize);
		sizeBox.setSelectedIndex(3);
		sizeBox.setBounds(242, 381, 48, 24);
		add(sizeBox);
		sizeBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String size = (String)e.getItem();
				Font f = EnterMessage.getFont();
				EnterMessage.setFont(f.deriveFont((float)Integer.valueOf(size).intValue()));;
			}
		});
		
		doc = textPane.getStyledDocument();

		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String sendText = EnterMessage.getText();
				EnterMessage.setText("");
				if (!sendText.isEmpty()) {
					client.send("/roomMsg " + roomName + " " + EnterMessage.getFont().getStyle() + " " +
								EnterMessage.getFont().getSize() + " " + color.getButtonColor() + " " + sendText);
				}
			}
		});
		EnterMessage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String sendText = EnterMessage.getText();
				EnterMessage.setText("");
				if (!sendText.isEmpty()) {
					client.send("/roomMsg " + roomName + " " + EnterMessage.getFont().getStyle() + " " +
								EnterMessage.getFont().getSize() + " " + color.getButtonColor() + " " + sendText);
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
