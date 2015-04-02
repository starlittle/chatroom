package server;

import java.io.*;
import java.net.*;

public class FileSend implements Runnable{
    private ServerSocket filesock;
    private final int fileport = 9980;
    
	public FileSend(){
		Socket s = null;
		try {
            filesock = new ServerSocket(fileport);
            s = filesock.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		sendfile();
	}
	public void sendfile(){
		
	}
	
}