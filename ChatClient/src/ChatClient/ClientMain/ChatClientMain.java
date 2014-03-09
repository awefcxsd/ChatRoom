package ChatClient.ClientMain;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import ChatClient.ClientGUI.ChatClientWindow;

public class ChatClientMain {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ChatClientWindow gui = new ChatClientWindow();
	    gui.setLocation(100, 50);
	    gui.setVisible(true);
	    
	    
	    
    }
}
