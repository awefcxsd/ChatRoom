package ChatClient.ClientGUI;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BackGroundPanel extends JPanel{
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		ImageIcon icon = new ImageIcon("image/back.jpg");//[color=red]圖片路徑不能固定否則無法動態修改圖片[/color]
		if(icon!= null){
			g.drawImage(icon.getImage(), 0, 0, null);
		}
	} 
}
