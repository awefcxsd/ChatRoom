package ChatClient.util;
import java.awt.Point;
import java.util.Random;

import ChatClient.ClientGUI.ChatClientWindow;


public class Vibration implements Runnable{

	private ChatClientWindow gui;
	private Point position;
	private Random random = new Random();
	public Vibration(ChatClientWindow GUIObject) {
		gui = GUIObject;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		position = gui.getLocation();
		
		for(int i=0; i<50; i++){
			gui.setLocation(position.x + (int)Math.floor(random.nextDouble()*10-5) , position.y + (int)Math.floor(random.nextDouble()*10-5));
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		gui.setLocation(position.x, position.y);
	}
}
