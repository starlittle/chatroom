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

public class FileSend implements Runnable{
	private SendGUI gui;
	
	@Override
	public void run() {
		//gui = new SendGUI();
		
		try {
			//ServerSocket ss = new ServerSocket(9988);
			System.out.println("connecting…");
			Socket socket = new Socket("140.112.18.219",9988);
			System.out.println("is a local host");
			
			//Socket socketClient = m_serverSocket.accept();
			//File sendfile = gui.getFile();
			String filename = "t1.png" ;
			File sendfile = new File(filename);
			//gui.setVisible(true);
			
			OutputStream outText = socket.getOutputStream();
            PrintStream outTextP = new PrintStream(outText);
            outTextP.println(filename);
            
            long filesize = sendfile.length();    
            byte[] bufferArray = new byte[(int) filesize];        
            FileInputStream fis = new FileInputStream(sendfile);
            BufferedInputStream bis = new BufferedInputStream(fis);     
            BufferedOutputStream out = new BufferedOutputStream(socket.getOutputStream());
			
            int count;
            System.out.println("Start sending file...");

            while ((count = bis.read(bufferArray)) > 0) {         
                //System.out.println("count: " + count);
                out.write(bufferArray, 0, count);
            }
            System.out.println("Finish!");
            
			//DataInputStream in = new DataInputStream(s.getInputStream());
			//DataOutputStream out = new DataOutputStream( s.getOutputStream());
			
			//out.writeUTF(sendfile.getName());
			//out.writeInt(filesize);
			
			
			//while(num != (-1)) { 
	        //      out.write(bufferArray,0,num);
	        //      out.flush();
	        //      num = fs.read(bufferArray);
	        //}
			
            out.flush();
            socket.shutdownInput(); 
            socket.shutdownOutput();
            
            socket.close();
			
		}
		catch(Exception e) {
			System.out.println("error at sending file");
			e.printStackTrace();
		}		
	} 
}
