package debugClient.debugGUI;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;

public class DebugWindow extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField EnterMessage;
	private JTextField EnterIP;
	private JTextField EnterPort;
	private JTextField EnterUserName;
	public DebugWindow() {
		setResizable(false);
		setTitle("Debug Client");
		setSize(560, 430);
		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(158, 10, 349, 298);
		panel.add(textPane);
		
		EnterMessage = new JTextField();
		EnterMessage.setBounds(158, 318, 309, 21);
		panel.add(EnterMessage);
		EnterMessage.setColumns(10);
		
		JButton btnSend = new JButton("Send");
		btnSend.setBounds(158, 349, 87, 23);
		panel.add(btnSend);
		
		EnterIP = new JTextField();
		EnterIP.setBounds(10, 24, 124, 21);
		panel.add(EnterIP);
		EnterIP.setColumns(10);
		
		EnterPort = new JTextField();
		EnterPort.setColumns(10);
		EnterPort.setBounds(10, 79, 124, 21);
		panel.add(EnterPort);
		
		EnterUserName = new JTextField();
		EnterUserName.setColumns(10);
		EnterUserName.setBounds(10, 131, 124, 21);
		panel.add(EnterUserName);
		
		JButton btnConnect = new JButton("Connect");
		btnConnect.setBounds(10, 183, 87, 23);
		panel.add(btnConnect);
		
		JLabel lblIp = new JLabel("IP");
		lblIp.setBounds(10, 10, 46, 15);
		panel.add(lblIp);
		
		JLabel lblPort = new JLabel("Port");
		lblPort.setBounds(10, 55, 46, 15);
		panel.add(lblPort);
		
		JLabel lblUsername = new JLabel("UserName");
		lblUsername.setBounds(10, 106, 56, 15);
		panel.add(lblUsername);
	}
}
