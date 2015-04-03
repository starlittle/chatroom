package fileexchange;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

public class FileRecv implements Runnable{
	
	private RecvGUI gui;
	private String serverIP;
	//private String srcname;
	private Socket socket;
	//private Socket ss = null;
	private final int fileport = 9988;
	
	public FileRecv( String ip) {
		serverIP = ip;
	}
	
	public void run() {	
		gui = new RecvGUI();
		
		try {
			socket = new Socket(serverIP,9988);
			System.out.println("in run()");
			receiveFile();	
			return;
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	private void receiveFile() throws IOException{
		
		 try {
			 BufferedInputStream inputStream = new BufferedInputStream(socket.getInputStream());
             String fileName = new DataInputStream(inputStream).readUTF();
             System.out.println("filename: " + fileName);
             BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(fileName)); 
              
             int count;
             int bytcount = 1;
             while ((count = inputStream.read()) != -1) {          
            	 outputStream.write(count);
                 bytcount = bytcount + 1;
                 outputStream.flush();                                                                        
             }
             System.out.println(bytcount);

             outputStream.close();                
             inputStream.close();
//             ss.close();
         }
		 catch(IOException e) {
             e.printStackTrace();             
         }
		
	}

}
