package server;
import java.io.*;
import java.net.*;
import java.util.*;

public class clientfile implements Runnable{
	private Socket s;
	private server mainserver;
	private ServerSocket fileserver;
	private DataInputStream in;
	private DataOutputStream out;
	private String msg,name,psword;
	private int id;
	private boolean onleave=false;
	private FileRecv filer;
	String TransferLine;
	String fileName;
	
	public clientfile(server ms, ServerSocket fs, Socket ss, int cid,String cname){
//	public clientfile(server ms, Socket ss, int cid){
		try{
			s = ss;
			mainserver = ms;
			fileserver = fs;
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
				if(c!=this)
					send("/ul " + c.name + " " + c.id);
			}
			for(clientfile c:(mainserver.clientList)){
				if(c!=this && c.isonleave()==false) 
					send("/au " + c.name + " " + c.id);
			}
			mainserver.sendAll("/ul " + name + " " + id);
			mainserver.sendAll("/au " + name + " " + id);
			mainserver.sendAll("/p 0 " + name + " joined!");
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
			mainserver.sendAll("/p 0 "+ name + " says: " + msgsent);
		}
		else if(msg.startsWith("/sw")){
			int destid = Integer.parseInt(msg.split(" ", 4)[1]);
			int roomid = Integer.parseInt(msg.split(" ", 4)[2]);
			String msgsent = msg.split(" ", 4)[3];
			boolean err = mainserver.sendPrivate(destid, roomid, "/p " + roomid + " " + name + " whispers: " + msgsent);
			if(mainserver.clientList.get(destid)!=this)
				send("/p " + roomid + " " + name + " whispers: " + msgsent);
			if(err==false) send("No such user!");
		}
		else if(msg.startsWith("/i")){
			int roomid = Integer.parseInt(msg.split(" ", 3)[1]);
			String msgsent = msg.split(" ", 3)[2];
			if(roomid == 0) mainserver.sendAll("/i " + roomid + " " + name + " " + msgsent);
			else mainserver.sendroom(roomid, "/i " + roomid + " " + name + " " + msgsent);
		}
		else if(msg.startsWith("/ar")){
			String roomname = msg.split(" ", 2)[1];
			mainserver.addroom(roomname,id);
			int roomid = mainserver.roomnameList.indexOf(roomname + " | " + name) + 1;
			send("/r " + roomid);
			mainserver.addtoroom(roomid, id);
			send("/aru " + roomid + " " + name + " " + id);
		}
		else if(msg.startsWith("/sr")){
			int roomid = Integer.parseInt(msg.split(" ", 3)[1]);
			String msgsent = msg.split(" ", 3)[2];
			mainserver.sendroom(roomid,"/p " + roomid + " " + name + " says: " + msgsent);
		}
		else if(msg.startsWith("/lr")){
			int roomid = Integer.parseInt(msg.split(" ", 3)[1]);
			int userid = Integer.parseInt(msg.split(" ", 3)[2]);
			mainserver.leaveroom(roomid, userid);
			mainserver.sendroom(roomid, "/dru " + roomid + userid);
		}
		else if(msg.startsWith("/aur")){
			int roomid = Integer.parseInt(msg.split(" ", 3)[1]);
			int userid = Integer.parseInt(msg.split(" ", 3)[2]);
			String uname = mainserver.nameList.get(userid);
			room destr = mainserver.roomlist.get(roomid-1);
			String sendrname = destr.getname();
			clientfile goal = mainserver.clientList.get(userid);
			if(destr.isinroom(goal)) {
				send("What are you doing(unsure)");
				return;
			}			
			goal.send("/at " + roomid + " " + sendrname);
			mainserver.addtoroom(roomid, userid);
			mainserver.sendroom(roomid, "/aru " + roomid + " " + uname + " " + userid);
		}
		else if(msg.startsWith("/f")){
			clientfile dest= this;
			Socket fr = null;
			Socket fs = null;
			try {				
				fr = fileserver.accept();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Thread tr = new Thread(new FileRecv(fr,dest,name,mainserver));
			tr.start();
			
            //mainserver.sendfile(destid);
			System.out.println("After start");
		}
		else if(msg.startsWith("/ok")){
			recvfile();
			System.out.println("Start sending");
		}
	}
	public void recvfile(){
		Socket fs = null;
		try {
			fs = fileserver.accept();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Thread ts = new Thread(new FileSend(fs,fileName));
		ts.start();
	}
	public int getid(){
		return id;
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