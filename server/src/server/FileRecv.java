package server;

import java.io.*;
import java.net.*;

public class FileRecv implements Runnable{
	private Socket ss = null;
	private final int fileport = 9988;
//	private String IP;
//	private final static String filepath = "C:/Users/nmlab/workspace/server/img";
//    private final static int filesize = 10000000;
	public FileRecv(Socket name){			
//			IP = name;
//			System.out.println("in 1 "+IP);
			//ss = new Socket(IP,fileport);
			this.ss = name;
			System.out.println("in 2");
		//	ss = s.accept();
		//	System.out.println("in 4");
	}
	public void run() {
		receiveFile();	
		System.out.println("in run()");
	}
	private void receiveFile(){
		 try {
             InputStream is = null;
             FileOutputStream fos = null;
             BufferedOutputStream bos = null;
             int bufferSize = 0;

             InputStream outText = ss.getInputStream();     
             
             InputStreamReader outTextI = new InputStreamReader(outText);
             BufferedReader inTextB = new BufferedReader(outTextI);
             String dateiname = inTextB.readLine();
             System.out.println("Dateiname: " + dateiname);

             try {
                 is = ss.getInputStream(); 
                 
                 bufferSize = ss.getReceiveBufferSize();
                 System.out.println("Buffer size: " + bufferSize);
             } catch (IOException ex) {
                 System.out.println("Can't get socket input stream. ");
             }

             try {
                 fos = new FileOutputStream(dateiname);  
                 bos = new BufferedOutputStream(fos);

             } catch (FileNotFoundException ex) {
                 System.out.println("File not found.");
             }

             byte[] bytes = new byte[bufferSize];

             int count;

             while ((count = is.read(bytes)) > 0) {          
                 bos.write(bytes, 0, count);                 
                 System.out.println("This is never shown!!!");   // In this while-loop the file is normally receiving and written to the directory. But this loop is never embarrassed...                                                                        
             }

             bos.flush();

             bos.close();
             is.close();
             ss.shutdownInput();
             ss.shutdownOutput();  
             ss.close();
         }catch(IOException e) {
             e.printStackTrace();
             
         }
	}
	
}