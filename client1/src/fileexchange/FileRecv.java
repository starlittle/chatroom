package fileexchange;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class FileRecv implements Runnable{
	
	private RecvGUI gui;
	private String srcaddr;
	private String srcname;
	private Socket s;
	
	public FileRecv( String addr, String name) {
		srcaddr = addr;
		srcname = name;
	}
	
	public void run() {
		gui = new RecvGUI();
		try {
			s = new Socket(srcaddr, 9988);
			DataInputStream in = new DataInputStream( s.getInputStream() );
			DataOutputStream out = new DataOutputStream( s.getOutputStream() );
			String filename = in.readUTF();
			
			
		}
		catch (Exception e) {
			
		}
	}

}
