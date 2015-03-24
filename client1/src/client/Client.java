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
	private int towho;
	
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
				parseMsg(TransferLine);
			}
		}
		catch (Exception e) {
			interrupt();
		}
	}
	
	private void parseMsg(String msg) {
		// TODO Auto-generated method stub
		
		//  /p <user> <msg>
		if (msg.startsWith("/p")) {
			System.out.println(msg);
			String[] split = msg.split(" ",3);
			GUIObject.printonGUI(split[1]+ " " +split[2]);
		}
		// /ul <username> <id> **setting user list
		else if (msg.startsWith("/ul")) {
			System.out.println("in userlist");
			String[] split = msg.split(" ",3);
			GUIObject.userList(split[1], Integer.parseInt(split[2]));
		}
		// /au <username> <id> **add new user
		else if (msg.startsWith("/au")) {
			String[] split = msg.split(" ",3);
			GUIObject.addUser(split[1], Integer.parseInt(split[2]));
		}
		else
			GUIObject.printonGUI(msg);
		
	}

	private void interrupt() {
		// TODO Auto-generated method stub
		System.out.println("be interrupt!!!");
		//reconnect();
	}

	public synchronized void reconnect() {
		// TODO Auto-generated method stub
		try {
			socket.connect(isa, 10000);
			System.out.println("connect succeed~");
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
		username = "f";
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
			os.writeUTF("/sa " + msg);
			System.out.println("send: " + msg);
		}
		catch (Exception e) {
			interrupt();
		}		
	}

}
