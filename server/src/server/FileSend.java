package server;

import java.io.*;
import java.net.*;

public class FileSend implements Runnable{
	private Socket ss = null;
	clientfile d;
	String filename;
	
	public FileSend(Socket name,clientfile dest,String fname){	
			this.ss = name;
			d = dest;
			filename = fname;
	}
	public void run() {
		try {
			System.out.println("in run()");
			receiveFile();	
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	private void receiveFile() throws IOException{
		 try {

			 DataOutputStream dataOut = new DataOutputStream(new BufferedOutputStream(ss.getOutputStream()));
             
             dataOut.writeUTF(filename);
             
             BufferedInputStream sendin = new BufferedInputStream(new FileInputStream(filename));   
             
             int readin; 
             while((readin = sendin.read()) != -1) { 
                  dataOut.write(readin);
             } 
             
             dataOut.close();
             sendin.close();
             ss.close();
         }catch(IOException e) {
             e.printStackTrace();             
         }

	}
	
}