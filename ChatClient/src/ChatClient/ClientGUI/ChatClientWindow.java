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

import javax.swing.JTabbedPane;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;

public class ChatClientWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField EnterMessage;
	private JTextField EnterIP;
	private JTextField EnterPort;
	JScrollPane scrollPane;
	private List userList;
	private ChatSlaveClient ClientObject;
	private StyledDocument doc;
	public String ip;
	public int port;
	public String name;
	private JTextField EnterName;

	public ChatClientWindow() {
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		// {{ Layout set up
		setResizable(false);
		setTitle("ChatClient");
		setSize(883, 574);
		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));

		BackGroundPanel panel = new BackGroundPanel();
		getContentPane().add(panel);
		panel.setLayout(null);
		
		EnterIP = new JTextField();
		EnterIP.setText("140.112.18.205");
		EnterIP.setBounds(10, 36, 124, 21);
		panel.add(EnterIP);
		EnterIP.setColumns(10);

		EnterPort = new JTextField();
		EnterPort.setText("9987");
		EnterPort.setColumns(10);
		EnterPort.setBounds(10, 91, 124, 21);
		panel.add(EnterPort);

		final JButton btnConnect = new JButton("Connect");

		btnConnect.setBounds(10, 184, 87, 23);
		panel.add(btnConnect);

		JLabel lblIp = new JLabel("IP");
		lblIp.setForeground(Color.WHITE);
		lblIp.setBounds(10, 22, 46, 15);
		panel.add(lblIp);

		JLabel lblPort = new JLabel("Port");
		lblPort.setForeground(Color.WHITE);
		lblPort.setBounds(10, 67, 46, 15);
		panel.add(lblPort);

		JLabel lblName = new JLabel("Name");
		lblName.setForeground(Color.WHITE);
		lblName.setBounds(10, 122, 46, 15);
		panel.add(lblName);

		EnterName = new JTextField();
		EnterName.setText("");
		EnterName.setColumns(10);
		EnterName.setBounds(10, 147, 124, 21);
		panel.add(EnterName);


		userList = new List();
		userList.setMultipleSelections(false);
		userList.setBounds(689, 59, 143, 419);
		panel.add(userList);

		JLabel lblUserOnline = new JLabel("User Online");
		lblUserOnline.setBackground(Color.WHITE);
		lblUserOnline.setForeground(Color.WHITE);
		lblUserOnline.setFont(new Font("Bauhaus 93", Font.PLAIN, 25));
		lblUserOnline.setBounds(689, 20, 143, 43);
		panel.add(lblUserOnline);

		UIManager.put("TabbedPane.contentOpaque", false);
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.WHITE);
		tabbedPane.setBounds(171, 35, 477, 443);
		panel.add(tabbedPane);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255, 100));
		tabbedPane.addTab("Main", null, panel_1, null);
		panel_1.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(255, 255, 255, 255));
		scrollPane.setBounds(0, 0, 472, 332);
		panel_1.add(scrollPane);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		JTextPane textPane = new JTextPane();
		textPane.setBackground(new Color(255, 255, 255, 255));
		scrollPane.setViewportView(textPane);
		textPane.setEditable(false);

		EnterMessage = new JTextField();
		EnterMessage.setBounds(10, 350, 309, 21);
		panel_1.add(EnterMessage);
		EnterMessage.setColumns(10);

		JButton btnSend = new JButton("Send");
		btnSend.setBounds(10, 381, 87, 23);
		panel_1.add(btnSend);

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
					JOptionPane.showMessageDialog(null, "UserName can not be empty", "Error", JOptionPane.INFORMATION_MESSAGE);
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
				if (sendText != "") {
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
		// }}

	}

	public void addText(String add, SimpleAttributeSet texture) {
		try {
			doc.insertString(doc.getLength(), add + "\n", texture);
			JScrollBar sBar = scrollPane.getVerticalScrollBar();
			sBar.setValue(sBar.getMaximum());
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

	
	
	
	
	
}
