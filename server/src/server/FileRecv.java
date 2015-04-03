package server;

import java.io.*;
import java.net.*;

public class FileRecv implements Runnable{
	private Socket ss = null;
	clientfile d;
	
	public FileRecv(Socket name,clientfile dest){	
			this.ss = name;
			d = dest;
//			fileServer = f;
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
             
             d.send("/f");
             
             d.fileName = fileName;

             outputStream.close();                
             inputStream.close();
//             ss.close();
         }catch(IOException e) {
             e.printStackTrace();             
         }

	}
	
}