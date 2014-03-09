package ChatClient.ClientCom;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JFileChooser;

import ChatClient.ClientGUI.ChatClientWindow;
import ChatClient.ClientGUI.FileProgGUI;
import ChatClient.ClientGUI.ReceiveFileGUI;

public class FileReceiver implements Runnable {
	private ReceiveFileGUI gui;
	private String ipAddr;
	private String senderName;
	private Socket s;
	DataInputStream in;
	DataOutputStream out;
	String fileName;
	int fileSize;
	File fileToSave;
	ChatClientWindow mainGUI;

	public FileReceiver(String IP, String sender, ChatClientWindow main) {
		ipAddr = IP;
		senderName = sender;
		mainGUI=main;
	}

	@Override
	public void run() {
		gui = new ReceiveFileGUI();

		try {
			s = new Socket(ipAddr, 9988);
			in = new DataInputStream(s.getInputStream());
			out = new DataOutputStream(s.getOutputStream());
			fileName = in.readUTF();
			fileSize = in.readInt();
			System.out.println(fileSize);
			gui.setTitle("File from " + senderName);
			gui.setFileInformation(fileName, fileSize);
			gui.setLocationRelativeTo(mainGUI);
			fileToSave = gui.showGUI();

			if (fileToSave != null) {
				FileProgGUI prog=new FileProgGUI();
				prog.setLocationRelativeTo(mainGUI);
				prog.setVisible(true);
				out.writeUTF("/avalible"); // accept transfer
				FileOutputStream fos = new FileOutputStream(fileToSave);
				BufferedOutputStream bos = new BufferedOutputStream(fos);
				int bytesRead;
				int current = 0;
				byte[] bufferArray = new byte[fileSize];

				bytesRead = in.read(bufferArray, 0, fileSize);
				current = bytesRead;
				prog.showProgress(current, fileSize);
				// System.out.print( current + " read, " );
				do {
					// System.out.println( current*100/filesize +
					// "% completed" );
					bytesRead = in.read(bufferArray, current, (bufferArray.length - current));
					// System.out.print( bytesRead+" read, " );
					if (bytesRead >= 0) {
						current += bytesRead;
						// System.out.print( "current total=" + current +
						// " bytes, ");
					}
					prog.showProgress(current, fileSize);
				} while (current < fileSize);
				// out.writeUTF("ok");
				// System.out.println("transfer ok");
				bos.write(bufferArray, 0, current);
				bos.flush();
				bos.close();
				// System.out.println("file output ok");
				prog.done();
				s.close();
			} else {
				out.writeUTF("/deny");
				s.close();
			}

		} catch (IOException e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}

	}


}
