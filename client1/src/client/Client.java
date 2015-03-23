package client;

import gui.ChatFrame;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;


public class Client implements Runnable{
	
	//
	ChatFrame GUIObject;
	//ConnectWindow 

	private static Socket socket;
	private String address = "140.112.18.219";//"140.112.18.219"; 
	private int port = 8010;
	private static InetSocketAddress isa;
	private DataOutputStream os;
	private DataInputStream is;
	private static Thread thread;
	private String username;
	
	Client() {
		GUIObject = new ChatFrame(this);
		//GUIObject.setVisible(true);
		
		//
		socket = new Socket();
		isa = new InetSocketAddress(this.address, this.port);		
		//connect();
	}

	@Override
	public void run() { 
		// TODO parse message not do //
		String m = GUIObject.message;
		try {
			while (true) {
				
				String TransferLine = is.readUTF();
				System.out.println("Recv: " + TransferLine);
				GUIObject.printonGUI(TransferLine);
				//parseMsg(TransferLine);
			}
		}
		catch (Exception e) {
			interrupt();
		}
	}
	
	private void parseMsg(String transferLine) {
		// TODO Auto-generated method stub
		
	}

	private void interrupt() {
		// TODO Auto-generated method stub
		System.out.println("interrupt!!!");
		//reconnect();
	}

	public synchronized void reconnect() {
		// TODO Auto-generated method stub
		try {
			socket.connect(isa, 10000);
			System.out.println("資料庫連線成功");
			sendName();
			
			os = new DataOutputStream(socket.getOutputStream());
            is = new DataInputStream(socket.getInputStream());
            this.os = new DataOutputStream(os);
            this.is = new DataInputStream(is);
            
            send("Hi again!!");
            thread = new Thread(this);
            thread.start(); // run()
            
		}
		catch (Exception e) {
			
		}
		
	}

	public void connect() {		
		try {			
			socket.connect(isa, 10000); //timeout
     
            System.out.println("Connect succeed!");
            sendName();
            
            os = new DataOutputStream(socket.getOutputStream());
            is = new DataInputStream(socket.getInputStream());
            this.os = new DataOutputStream(os);
            this.is = new DataInputStream(is);
            
            // 送出字串
            //send("hi!");
            thread = new Thread(this);
            thread.start(); // run()
            
		}
		catch(Exception e){
			System.out.println("Socket connect failed!");
            System.out.println("IOException :" + e.toString());
			
		}
	}
	
	public synchronized void sendName() throws IOException {
		// TODO Auto-generated method stub
		DataOutputStream o = new DataOutputStream(socket.getOutputStream());
		DataInputStream i = new DataInputStream(socket.getInputStream());
		
		//String msg = i.readUTF();
		username = "I am user!";
		//System.out.println(msg);
		
		System.out.println("Send user name: " + username);
		o.writeUTF(username);
		
		try {
			while (true) {
				String msg = i.readUTF();
				if (msg.equals("Recvname")) {
					System.out.println("Receive name check");
					break;
				}
				else {
					System.out.println("not check receive?");
					System.out.println(msg);
				    break;
				}
			}
		}
		catch (Exception e) {
			interrupt();
		}
		
	}

	public void send(String msg) {
		// TODO Auto-generated method stub	
		try {
			os.writeUTF(msg);
			System.out.println("send: " + msg);
		}
		catch (Exception e) {
			interrupt();
		}
		
	}

}
