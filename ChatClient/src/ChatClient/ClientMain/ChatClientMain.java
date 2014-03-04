package ChatClient.ClientMain;

import javax.swing.UIManager;

import ChatClient.ClientGUI.ChatClientWindow;

public class ChatClientMain {
	public static void main(String[] args) {
		
		ChatClientWindow gui = new ChatClientWindow();
	    gui.setLocation(100, 50);
	    gui.setVisible(true);
    }
}
