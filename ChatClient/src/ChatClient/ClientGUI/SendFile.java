package ChatClient.ClientGUI;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;

import ChatClient.ClientCom.ChatSlaveClient;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

public class SendFile extends JDialog {
	private JTextField textField;
	String receiver;
	ChatSlaveClient ClientObject;
	JFileChooser opener = new JFileChooser();
	File sendFile;
	private JTextField Status;
	public SendFile(String recv, ChatSlaveClient user) {
		receiver = recv;
		ClientObject = user;

		// {{ Layout setup
		setTitle("Send file to " + recv);
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
		
		Status = new JTextField();
		Status.setText("Status : Waiting for user to select file");
		Status.setEditable(false);
		Status.setBounds(27, 42, 247, 24);
		panel.add(Status);
		Status.setColumns(10);
		// }}

		// {{ Event Handle
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int userSelection = opener.showDialog(null, "Open");
				try {
					if (userSelection == JFileChooser.APPROVE_OPTION) {
						textField.setText(opener.getSelectedFile().getAbsolutePath());
					}
				} catch (Exception e1) {

				}
			}
		});

		btnComfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					sendFile=new File(textField.getText());
					Status.setText("Status : Wait for accept");
				} catch (Exception e2) {
					Status.setText("Status : Cannot open file");
				}
			}
		});
		// }}

	}
}
