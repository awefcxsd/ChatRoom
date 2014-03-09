package ChatClient.ClientGUI;


import javax.swing.JDialog;
import javax.swing.JTextField;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import ChatClient.ClientCom.ChatSlaveClient;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SecretMsg extends JDialog{
	
	private ChatSlaveClient ClientObject;
	private String userName;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	public SecretMsg(String user, ChatSlaveClient c){
		setResizable(false);
		
		userName=user;
		ClientObject=c;
		
		
		
		
		setModal(true);
		setTitle("Enter Secret Message to "+user);
		setSize(356, 103);
		getContentPane().setLayout(null);
		
		BackGroundPanel panel = new BackGroundPanel();
		panel.setBounds(0, 0, 371, 94);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 24, 329, 21);
		panel.add(textField);
		textField.setColumns(10);
		
		
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ClientObject.send("/SecretMsg " + userName+ " "+ textField.getText());
				setVisible(false);
			}
		});
		
		
		
	}
	
}
