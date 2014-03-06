package ChatClient.ClientGUI;

import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;

import ChatClient.ClientCom.ChatSlaveClient;

public class SendFile extends JDialog{
	private JTextField textField;
	String receiver;
	ChatSlaveClient ClientObject;
	
	public SendFile(String recv,ChatSlaveClient user) {
		receiver=recv;
		ClientObject=user;
		
		setTitle("Send file to "+recv);
		setResizable(false);
		getContentPane().setLayout(null);
		this.setSize(392, 116);
		
		BackGroundPanel panel = new BackGroundPanel();
		panel.setBounds(0, 0, 390, 90);
		getContentPane().add(panel);
		panel.setLayout(null);
		textField = new JTextField();
		textField.setBounds(27, 11, 247, 21);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.setBounds(284, 10, 87, 23);
		panel.add(btnBrowse);
		
		JButton btnComfirm = new JButton("Comfirm");
		btnComfirm.setBounds(284, 43, 87, 23);
		panel.add(btnComfirm);
	}
}
