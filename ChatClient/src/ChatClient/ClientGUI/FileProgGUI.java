package ChatClient.ClientGUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;

public class FileProgGUI extends JFrame{
	JLabel lblProgress;
	public FileProgGUI() {

		this.setSize(336, 90);
		
		BackGroundPanel panel = new BackGroundPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		lblProgress = new JLabel("Progress");
		lblProgress.setOpaque(true);
		lblProgress.setBackground(Color.BLACK);
		lblProgress.setHorizontalAlignment(SwingConstants.CENTER);
		lblProgress.setForeground(Color.WHITE);
		lblProgress.setFont(new Font("·s²Ó©úÅé", Font.BOLD, 16));
		lblProgress.setBounds(0, 10, 320, 34);
		panel.add(lblProgress);
	}

	public void showProgress(int current, int fileSize) {
		// TODO Auto-generated method stub
		lblProgress.setText("Progress: "+current+" / "+fileSize);
	}

	public void done() {
		// TODO Auto-generated method stub
		lblProgress.setText("Progress: DONE");
	}

}
