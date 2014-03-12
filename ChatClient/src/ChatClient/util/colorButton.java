package ChatClient.util;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;

public class colorButton extends JButton{
	private Color chooseColor;
	
	public void paint(Graphics g){
		g.setColor(chooseColor);
		g.fillRect(0, 0, 22, 22);
	}
	
	public void setButtonColor(Color c){
		chooseColor = c;
	}

	public int getButtonColor(){
		return chooseColor.getRGB();
	}
}
