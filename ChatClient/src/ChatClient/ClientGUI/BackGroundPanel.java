package ChatClient.ClientGUI;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BackGroundPanel extends JPanel{
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		ImageIcon icon = new ImageIcon("image/back.jpg");//[color=red]�Ϥ����|����T�w�_�h�L�k�ʺA�ק�Ϥ�[/color]
		if(icon!= null){
			g.drawImage(icon.getImage(), 0, 0, null);
		}
	} 
}
