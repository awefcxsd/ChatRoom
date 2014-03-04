package ChatClient.ClientGUI;

import javax.swing.JFrame;

import java.awt.GridLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
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

public class ChatClientWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField EnterMessage;
	private JTextField EnterIP;
	private JTextField EnterPort;
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
		setTitle("Debug Client");
		setSize(833, 626);
		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));

		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(null);

		EnterMessage = new JTextField();

		EnterMessage.setBounds(158, 488, 309, 21);
		panel.add(EnterMessage);
		EnterMessage.setColumns(10);

		JButton btnSend = new JButton("Send");
		btnSend.setBounds(158, 519, 87, 23);
		panel.add(btnSend);

		EnterIP = new JTextField();
		EnterIP.setText("140.112.18.205");
		EnterIP.setBounds(10, 24, 124, 21);
		panel.add(EnterIP);
		EnterIP.setColumns(10);

		EnterPort = new JTextField();
		EnterPort.setText("9987");
		EnterPort.setColumns(10);
		EnterPort.setBounds(10, 79, 124, 21);
		panel.add(EnterPort);

		final JButton btnConnect = new JButton("Connect");

		btnConnect.setBounds(10, 172, 87, 23);
		panel.add(btnConnect);

		JLabel lblIp = new JLabel("IP");
		lblIp.setBounds(10, 10, 46, 15);
		panel.add(lblIp);

		JLabel lblPort = new JLabel("Port");
		lblPort.setBounds(10, 55, 46, 15);
		panel.add(lblPort);

		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 110, 46, 15);
		panel.add(lblName);

		EnterName = new JTextField();
		EnterName.setText("");
		EnterName.setColumns(10);
		EnterName.setBounds(10, 135, 124, 21);
		panel.add(EnterName);

		userList = new List();
		userList.setBackground(Color.WHITE);
		userList.setMultipleSelections(false);
		userList.setBounds(651, 47, 143, 419);
		panel.add(userList);

		JLabel lblUserOnline = new JLabel("User Online");
		lblUserOnline.setForeground(Color.BLACK);
		lblUserOnline.setFont(new Font("Bauhaus 93", Font.PLAIN, 25));
		lblUserOnline.setBounds(651, 8, 143, 43);
		panel.add(lblUserOnline);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(158, 23, 477, 443);
		panel.add(tabbedPane);

		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("New tab", null, scrollPane, null);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		JTextPane textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
		textPane.setEditable(false);

		doc = textPane.getStyledDocument();

		// }}

		ClientObject = new ChatSlaveClient(this);

		// {{ Event
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ip = EnterIP.getText();
				String port_str = EnterPort.getText();
				port = new java.lang.Integer(port_str).intValue();
				name=EnterName.getText();
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
					ClientObject.send("/BoradCastMessage "+sendText);
				}
			}
		});
		EnterMessage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String sendText = EnterMessage.getText();
				EnterMessage.setText("");
				if (sendText != "") {
					ClientObject.send("/BoradCastMessage "+sendText);
				}
			}
		});
		// }}

	}

	public void addText(String add, SimpleAttributeSet texture) {
		try {
			doc.insertString(doc.getLength(), add + "\n", texture);
		} catch (Exception e) {

		}
	}
	
	public void addUser(String other) {
		userList.add(other);
	}
	
	public void removeUser(String other) {
		userList.remove(other);
	}
	
}
