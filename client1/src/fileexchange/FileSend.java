package fileexchange;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import client.Client;

public class FileSend implements Runnable{
	private SendGUI gui;
	private String serverIP;
	private Client clientobject;
	
	
	public FileSend(Client client, String ip) {
		clientobject = client;
		serverIP = ip;
	}
	
	@Override
	public void run() {
		gui = new SendGUI();
		File sendfile = gui.getFile();
		
		try {
			System.out.println("connecting…");
			Socket socket = new Socket(serverIP,9988);
			
			String filename = sendfile.getName() ;
			//File sendfile = new File(filename);
			           
			DataOutputStream dataOut = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
	        dataOut.writeUTF(sendfile.getName());   
			
            long filesize = sendfile.length();    
           
	        BufferedInputStream inputStream = new BufferedInputStream( new FileInputStream(sendfile)); 
	        
            System.out.println("Start sending file..." + filesize);
            clientobject.sendfstate("Start sending file... filesize = " + filesize + " bytes");
            
            int readin; 
            while((readin = inputStream.read()) != -1) { 
                 dataOut.write(readin); 
                 Thread.yield();
            } 
            
            dataOut.close();
            inputStream.close();  
            socket.close();
            System.out.println("Finish! ");
            clientobject.sendfstate("Send file is finish!");
			
		}
		catch(Exception e) {
			System.out.println("error at sending file");
			e.printStackTrace();
		}		
	} 
}