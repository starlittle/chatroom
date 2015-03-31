package server;
import java.io.*;
import java.net.*;
import java.util.*;

public class clientfile implements Runnable{
	private Socket s;
	private server mainserver;
	private DataInputStream in;
	private DataOutputStream out;
	private String msg,name,psword;
	private int id;
	private boolean onleave=false;
	String TransferLine;
	
	public clientfile(server ms, Socket ss, int cid,String cname){
//	public clientfile(server ms, Socket ss, int cid){
		try{
			s = ss;
			mainserver = ms;
			in = new DataInputStream(s.getInputStream());
			out = new DataOutputStream(s.getOutputStream());
			id = cid;
			onleave = false;
			name = cname;
			mainserver.adduser(name,id);
			out.writeUTF("Recvname");
//			psword = in.readUTF();			

		}catch(IOException e){
			System.out.println("constructed err: "+e.toString());
		}   
	}
	public void send(String s){
		try{
			out.writeUTF(s);			
			System.out.println(s);
		}catch(IOException e){
			System.out.println("write err: "+e.toString());
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			for(clientfile c:(mainserver.clientList)){	
				send("/ul " + c.name + " " + c.id);
			}
			for(clientfile c:(mainserver.clientList)){
				if(c!=this && c.isonleave()==false) 
					send("/au " + c.name + " " + c.id);
			}
			mainserver.sendAll("/au " + name + " " + id);
			mainserver.sendAll("/p " + name + " joined!");
			System.out.println("in run");
			while(true){
				TransferLine = in.readUTF();
				System.out.println("Recv: " + TransferLine);
				parseMsg(TransferLine);
			}
		}catch(IOException e){
//			if(e instanceof SocketException){
				onleave = true;
				mainserver.leave(id);
//			}
//			else System.out.println("in run(): "+e.toString());
		}
	}
	public void parseMsg(String msg){
		if(msg.startsWith("/sa")){
			String msgsent = msg.split(" ", 2)[1];
			mainserver.sendAll("/p "+ name + " says: " + msgsent);
		}
		else if(msg.startsWith("/sw")){
			int destid = Integer.parseInt(msg.split(" ", 3)[1]);
			int roomid = Integer.parseInt(msg.split(" ", 3)[2]);
			String msgsent = msg.split(" ", 3)[2];
			boolean err = mainserver.sendPrivate(destid, "/w "+ roomid + name + " whispers: " + msgsent);
			if(err==false) send("No such user!");
		}
		else if(msg.startsWith("/ar")){
			
		}
	}
	public String getname(){
		return name;
	}
	public boolean isonleave(){
		return onleave;
	}
	public void closesocket() throws IOException{
		s.close();
	}
	
	
	
	
}