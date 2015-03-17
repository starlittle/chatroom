package server;
import java.io.*;
import java.net.*;

public class server{
	private ServerSocket ss;
	private final int sPort = 8010;
	private DataInputStream in;
	public server(){
		try{
			ss = new ServerSocket(sPort);
			while(true){
				Socket s = null;
				synchronized(this){
					s = ss.accept();
					in = new DataInputStream(s.getInputStream());
				}
				System.out.println("connected!");
//				s.setSoTimeout(15000);
                byte[] b = new byte[1024];
                String data = "";
                int length;
                while((length = in.read(b)) > 0)
                	data += new String(b,0,length);
				System.out.println(data);
				in.close();
                in = null;
                s.close();
			}
		}
		catch(java.io.IOException e){
			System.out.println("err"+e.toString());
		}		
	}
/*	public void run(){
		
	}*/
	
}