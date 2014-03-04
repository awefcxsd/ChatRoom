package debugClient.debugGUI;

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

import debugClient.degubCom.Client;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class DebugWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField EnterMessage;
	private JTextField EnterIP;
	private JTextField EnterPort;
	private Client ClientObject;
	private StyledDocument doc;
	public String ip;
	public int port;
	public String name;

	public DebugWindow() {
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		
		// {{ Layout set up
		setResizable(false);
		setTitle("Debug Client");
		setSize(560, 430);
		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));

		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(null);

		EnterMessage = new JTextField();
		
		EnterMessage.setBounds(158, 318, 309, 21);
		panel.add(EnterMessage);
		EnterMessage.setColumns(10);

		JButton btnSend = new JButton("Send");
		btnSend.setBounds(158, 349, 87, 23);
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

		btnConnect.setBounds(10, 110, 87, 23);
		panel.add(btnConnect);

		JLabel lblIp = new JLabel("IP");
		lblIp.setBounds(10, 10, 46, 15);
		panel.add(lblIp);

		JLabel lblPort = new JLabel("Port");
		lblPort.setBounds(10, 55, 46, 15);
		panel.add(lblPort);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(158, 24, 321, 278);
		panel.add(scrollPane);
		
				JTextPane textPane = new JTextPane();
				scrollPane.setViewportView(textPane);
				textPane.setEditable(false);
				
						doc = textPane.getStyledDocument();

		// }}

		ClientObject = new Client(this);

		//{{ Event
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ip = EnterIP.getText();
				String port_str = EnterPort.getText();
				port = new java.lang.Integer(port_str).intValue();
				ClientObject.connectToServer(ip, port, name);
			}
		});
		
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String sendText = EnterMessage.getText();
				EnterMessage.setText("");
				ClientObject.send(sendText);
			}
		});
		EnterMessage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String sendText = EnterMessage.getText();
				EnterMessage.setText("");
				ClientObject.send(sendText);
			}
		});
		//}}
		
		
	}

	public void addText(String add, SimpleAttributeSet texture) {
		try {
			doc.insertString(doc.getLength(), add + "\n", texture);
		} catch (Exception e) {

		}
	}
}
