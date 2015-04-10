package server;

import java.io.*;
import java.net.*;

public class FileRecv implements Runnable{
	private Socket ss = null;
	clientfile d;
	String username;
	server mainserver;
	
	public FileRecv(Socket name,clientfile dest,String uname,server ms){	
			this.ss = name;
			d = dest;
			username = uname; 
			mainserver = ms;
	}
	public void run() {
		try {
			System.out.println("in recv()");
			receiveFile();	
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	private void receiveFile() throws IOException{
		 try {
			 BufferedInputStream inputStream = new BufferedInputStream(ss.getInputStream());
             String fileName = new DataInputStream(inputStream).readUTF(); 
             BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(fileName)); 
             
             int count;
             int bytcount = 0;
             while ((count = inputStream.read()) != -1) {          
            	 outputStream.write(count);
                 bytcount = bytcount + 1;                                                                    
             }
             System.out.println(bytcount);
             
             for(clientfile c:mainserver.clientList){
            	 if(c.isonleave()==false && c!=d)
            		 c.send("/f " + username);
            		 c.fileName = fileName;
             }
             

             outputStream.close();                
             inputStream.close();
             ss.close();
         }catch(IOException e) {
             e.printStackTrace();             
         }

	}
	
}