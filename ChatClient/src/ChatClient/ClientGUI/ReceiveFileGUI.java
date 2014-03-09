package ChatClient.ClientGUI;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;

import java.awt.Color;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

public class ReceiveFileGUI extends JDialog{
	JLabel lblNewLabel;
	boolean accept=false;
	File fileToSave;
	String fileName;
	
	public ReceiveFileGUI() {
		setModal(true);
		this.setSize(381, 128);
		
		BackGroundPanel panel = new BackGroundPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		lblNewLabel = new JLabel("File info");
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(Color.BLACK);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("·s²Ó©úÅé", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 10, 345, 27);
		panel.add(lblNewLabel);
		
		JButton btnAccept = new JButton("Accept");
		btnAccept.setBounds(81, 47, 87, 23);
		panel.add(btnAccept);
		
		JButton btnDeny = new JButton("Deny");
		btnDeny.setBounds(196, 47, 87, 23);
		panel.add(btnDeny);
		
		
		
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser saver=new JFileChooser();
				saver.setSelectedFile(new File(fileName));
				int userSelection = saver.showDialog(null, "Save As...");
				try {
					if (userSelection == JFileChooser.APPROVE_OPTION) {
						fileToSave = saver.getSelectedFile();
						setVisible(false);
					}
				} catch (Exception e1) {

				}
			}
		});
		
		btnDeny.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileToSave=null;
				setVisible(false);
			}
		});
	}

	public void setFileInformation(String fileName, int fileSize) {
		// TODO Auto-generated method stub
		this.fileName=fileName;
		lblNewLabel.setText("File Name: "+fileName+"  File Size: "+fileSize+" bytes");
	}

	public File showGUI() {
		this.setVisible(true);
		return fileToSave;
		// TODO Auto-generated method stub
		
	}

}
