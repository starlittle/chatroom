package server;
import java.io.*;
import java.net.*;

public class server{
	private ServerSocket ss;
	private final int sPort = 8010;
	private DataInputStream in;
	private DataOutputStream out;
	public server(){
		try{
			ss = new ServerSocket(sPort);
			while(true){
				Socket s = null;
				synchronized(this){
					s = ss.accept();
					in = new DataInputStream(s.getInputStream());
					out = new DataOutputStream(s.getOutputStream());
				}
				System.out.println("connected!");
//				s.setSoTimeout(15000);
//                byte[] b = new byte[1024];
//                String data = "";
//                int length;
//                while((length = in.read(b)) > 0)
//                	data += new String(b,0,length);
                System.out.println("printing...");
//				System.out.println(data);

//                while(in.available() > 0) {
//                	String k = in.readUTF();
//                	System.out.println("receive: " + k);
//                }
				
				try {
					while (true) {
						String TransferLine = in.readUTF();
						System.out.println("Recv: " + TransferLine);			            
		                //parseMsg(TransferLine);
		                out.writeUTF("from server:"+TransferLine);
		            }
				}catch (Exception e) {
			            interrupt();
				}                
                in.close();
                in = null;
                //s.close();
			}
		}
		catch(java.io.IOException e){
			System.out.println("err"+e.toString());
		}		
	}
	private void interrupt() {
		// TODO Auto-generated method stub
		System.out.println("interrupt");
	}
	/*	public void run(){
		
	}*/
	private void parseMsg(String msg) {
		// TODO Auto-generated method stub
		String[] splitedLine = msg.split(" ", 3);
        System.out.println("User joined:" + splitedLine[1]);
	}
	
}