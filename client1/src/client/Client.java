package client;

import fileexchange.FileRecv;
import fileexchange.FileSend;
import fileexchange.RecvGUI;
import gui.ChatFrame;
import gui.ConnectWindow;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

import javax.swing.ImageIcon;

import audio.audio;
import fileexchange.RecvGUI;
import sun.audio.*;


public class Client implements Runnable {
	
	//
	ChatFrame GUIObject;
	ConnectWindow connectWin;

	private static Socket socket;
	private String address;// = "140.112.18.219"; 
	private int port = 8010;
	private static InetSocketAddress isa;
	private DataOutputStream os;
	private DataInputStream is;
	private static Thread thread;
	private int fileport;
	public int receive;	
	private RecvGUI receivegui;
	public ImageIcon profilepic;
	private audio au;
	
	public String username;
	private int towho;
	
	Client() {
		GUIObject = new ChatFrame(this);	
		GUIObject.setVisible(true);
		
		fileport = 8898;
		receive = 0;
		//socket = new Socket();
		//isa = new InetSocketAddress(this.address, this.port);		
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
			System.out.println("error at run");
			interrupt();
		}
	}
	
	
	private void parseMsg(String msg) {
		//  /p <roomID> <user> <msg>
		if (msg.startsWith("/p")) {
			System.out.println(msg);
			String[] split = msg.split(" ",4);
			GUIObject.setID(split[1]);
			GUIObject.printonGUI(split[2]+ " " +split[3]);
			if (split[2].compareTo(username) == 0) {
				System.out.println("same");
				//playSound("message.au");
			}
			else if (split[3].compareTo("joined!") == 0) {
				System.out.println("join");
			}
			else if (split[3].compareTo("left") == 0) {
				System.out.println("left");
			}
			else {
				System.out.println(split[1] + " " + username);
				playSound("message.au");
			}
		}
		else if(msg.startsWith("/i")){
			String[] split = msg.split(" ",4);
			GUIObject.setID(split[1]);
			GUIObject.printimage(split[3], split[2]);
		}
		// /ul <username> <id> **setting user list
		else if (msg.startsWith("/ul")) {
		//	System.out.println("in userlist");
			String[] split = msg.split(" ",3);
			GUIObject.userList(split[1], Integer.parseInt(split[2]));
		}
		// /au <username> <id> **add new user
		else if (msg.startsWith("/au")) {
			String[] split = msg.split(" ",3);
			GUIObject.addUser(split[1], Integer.parseInt(split[2]));
			playSound("in.au");
		}
		// /du <userID> **delete user
		else if (msg.startsWith("/du")) {
			String[] split = msg.split(" ",2);
			System.out.println(msg);
			GUIObject.delUser(Integer.parseInt(split[1]));
			playSound("in.au");
		}
		// /r <roomID>
		else if (msg.startsWith("/r")) {
			// add in room list
			String[] split = msg.split(" ",2);
			GUIObject.addTab(split[1]);
		
		}	// /dt <roomID> 
	   else if (msg.startsWith("/dt")){
		   String[] split = msg.split(" ",2);
		   GUIObject.setID(split[1]);
			GUIObject.delTab(split[1]);
		   
 		}// /aru <roomID> <username> <userID>
        else if(msg.startsWith("/aru")){
            String[] split = msg.split(" ",4);
            GUIObject.addrUser(split[1],split[2],split[3]);
            
        }// /dru <roomID> <userID>
        else if(msg.startsWith("/dru")){
        	String[] split = msg.split(" ", 3);
        	GUIObject.delrUser(split[1], split[2]);
        }
		// /at <roomID> <roomname>
        else if(msg.startsWith("/at")){
        	String[] split = msg.split(" ",3);
        	GUIObject.invitedtoRoom(split[1],split[2]);
        }
		else if (msg.startsWith("/f")) { //file receive /f sendname
			String[] split = msg.split(" ",2);
			//System.out.println(" start file receive");	
			receivegui = new RecvGUI(this, split[1]);
			playSound("b.au");
			if (receive == 1) {
				send("/ok");
				Thread frecv = new Thread(new FileRecv(this,address));
				frecv.start();
			}
			else {
				send("/no");
			}
		}
		else if (msg.startsWith("/as")) { //be connect audio
			send("/sp");
			//send("/na");  不要
		}
		else if (msg.startsWith("/sn")) {
			//put on console
		}
		else if (msg.startsWith("/st")) {  // /st ip port1 port2
			String[] split = msg.split(" ",4);
			au = new audio(split[1],split[2],split[3]);
			au.runVOIP();
		}
		else
			GUIObject.printonGUI(msg);
		
	}
	public void sendfstate(String msg) {
		GUIObject.setID("0");
		GUIObject.printonGUI(msg);
	}

	private void interrupt() {
		// TODO Auto-generated method stub
		System.out.println("error interrupt!!!");
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
            
            //send("Hi again!!");
            thread = new Thread(this);
            thread.start(); // run()
            
		}
		catch (Exception e) {
			
		}
		
	}

	public synchronized void connect() {	
		
		connectWin = new ConnectWindow(GUIObject);
		connectWin.setVisible(true);
		
		//this.notify();
		
		address = connectWin.IP;
		port = connectWin.port;
		username = connectWin.name;
		profilepic = connectWin.image;
		GUIObject.setName(username);
		
		try {
			//socket = new Socket();
			//isa = new InetSocketAddress(address, port);	
			
			socket = new Socket(InetAddress.getByName(address), port);
			//socket.connect(isa, 10000); //timeout
     
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
		//username = "j";
		//System.out.println(msg);
		
		System.out.println("Send user name: " + username);
		o.writeUTF(username);
		
		try {
			while (true) {
				String msg = i.readUTF();
				if (msg.equals("Recvname")) {
					System.out.println("Receive name check");
					GUIObject.setprofile(profilepic);
					break;
				}
				else if (msg.equals("Name used!")) {
					System.out.println(msg);
					GUIObject.rename();
					sendName();
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
	
	public void sendall(String msg) {
		send("/sa " + msg);
	}	
	public void sendWhis(int whisID, int roomID, String msg) { //		
		send("/sw " + whisID + " " + roomID + " "+ msg);
	}
	public void sendroom(int roomID, String msg) {
		send("/sr " + roomID + " " + msg);
	}
	public void addroom(String msg) {
		send("/ar " + msg);
	}
	public void droom(int roomID){
		send("/lr " + roomID);
	}
	public void addutroom(int roomID, int iuID) {
		send("/aur " + roomID + " " + iuID);
	}
	public void sendFile(String filename) {
		//fileport++;
		
		send("/f " + "who");
		Thread fsend = new Thread(new FileSend(this, address));
		fsend.start();
	}
	public void sendImage(int roomID, String image){
		send("/i " + roomID + " "+ image);
	}
	public void sendaudio(int i){ //id
		send("/as " + i);
		//au = new audio();
	}


	@SuppressWarnings({ "unused", "restriction" })
	
	private void playSound( String sound) 
	{
		String gongFile = sound;
	  try
	  {
	    // get the sound file as a resource out of my jar file;
	    // the sound file must be in the same directory as this class file.
	    // the input stream portion of this recipe comes from a javaworld.com article.
	    InputStream inputStream = new FileInputStream(gongFile);
	    AudioStream audioStream = new AudioStream(inputStream);
	    AudioPlayer.player.start(audioStream);
	  }
	  catch (Exception e)
	  {
	    // a special way i'm handling logging in this application
	     e.printStackTrace();
	  }
	}
	
}