package ChatClient.ClientCom;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.omg.CORBA.portable.OutputStream;

import ChatClient.ClientGUI.SendFileGUI;

public class FileSender implements Runnable {
	ServerSocket servsock;
	File sendFile;
	SendFileGUI GUI;

	public FileSender(File f,SendFileGUI g) {
		sendFile = f;
		GUI=g;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			ServerSocket ss = new ServerSocket( 9988 );
			//System.out.println("Waiting for connection");
			Socket s = ss.accept();

			if( sendFile == null ) {
				GUI.setStatus("Status: File Open Error");
				return;
			}

			DataInputStream in = new DataInputStream( s.getInputStream() );
			DataOutputStream out = new DataOutputStream( s.getOutputStream() );
			FileInputStream fs = new FileInputStream( sendFile );
			BufferedInputStream bis = new BufferedInputStream(fs);
			int filesize = (int)sendFile.length();

			out.writeUTF(sendFile.getName());
			//System.out.println("size = "+filesize);
			out.writeInt(filesize);

			if( in.readUTF().equals("/avalible") ) {
				GUI.setStatus("Status: File start to transport");
				byte [] bufferArray = new byte [ filesize ];
				bis.read( bufferArray, 0, bufferArray.length );
				out.write(bufferArray, 0, bufferArray.length);
				out.flush();
				bis.close();
				out.close();
				s.close();
				ss.close();
				GUI.setStatus("Status: File transportation end");
			}
			else {
				GUI.setStatus("Status: Deny transfer");
				bis.close();
				s.close();
				ss.close();
			}
		}
		catch (IOException e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
	}

}
