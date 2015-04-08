package server;
import java.io.*;
import java.net.*;
import java.util.Vector;

import server.clientfile;

public class server{
	private ServerSocket ss;
	private ServerSocket fs;
	private final int sPort = 8010;
	private final int fPort = 9988;
	Vector<clientfile> clientList;
	Vector<room> roomlist;
	Vector<String> roomnameList;
	//Vector<String> roomRealList;
	Vector<String> nameList;
	Vector<String> pswordList;
	private int id;
	private int roomid;
	private ServerDisplay gui;
	DataInputStream in;
	DataOutputStream out;
	private boolean newuser;
	
	public server(){
		clientList = new Vector<clientfile>();
		nameList = new Vector<String>();
		roomlist = new Vector<room>();
		roomnameList = new Vector<String>();
		//roomRealList = new Vector<String>();
		gui = new ServerDisplay(this);
		gui.setVisible(true);
		Thread t;
		int uid = 0;
		try{
			ss = new ServerSocket(sPort);
			fs = new ServerSocket(fPort);
			while(true){
				Socket s = null;
				synchronized(this){
					s = ss.accept();
					in = new DataInputStream(s.getInputStream());
					out = new DataOutputStream(s.getOutputStream());
					String cname = in.readUTF();
//					String pword = in.readUTF();	
					if(nameList.contains(cname)){
						while(true){						
							out.writeUTF("Name used!");
//							uid = nameList.indexOf(cname);
//							if(pword == pswordList.get(uid))
//								newuser = false;
							cname = in.readUTF();
							if(nameList.contains(cname)) continue;
							else break;
						}						
					}
					newuser = true;
					System.out.println("new user : " + cname + " in!");
					if(newuser==true)
						clientList.add(new clientfile(this,fs,s,id++,cname));
//					clientList.add(new clientfile(this,s,id++));
				}
				if(newuser==true)
					t = new Thread(clientList.lastElement());
				else 
					t = new Thread(clientList.get(uid));
				t.start();
				System.out.println("connected!");
			}
		}
		catch(IOException e){
			System.out.println("err"+e.toString());
		}		
	}
	private void interrupt() {
		// TODO Auto-generated method stub
		System.out.println("interrupt");
	}
	public void sendAll(String msg){
		for(clientfile c: clientList)
			if(c.isonleave()==false) 
				c.send(msg);		
		gui.showLog("broadcasts: " + msg);
	}
	public boolean sendPrivate(int id, int rid, String msg){
		clientfile c = clientList.get(id);
		if(c.isonleave()==true) return false;
		else {
			c.send(msg);
			gui.showLog("whisper to " + c.getname() + " in Room: " + rid + msg);
			return true;
		}
	}
	public void adduser(String name, int id){
		nameList.add(name);
		gui.adduser(name, id);
		gui.showLog(name + " entered.");
	}
	public void addroom(String roomname, int consid){
		++roomid;
		clientfile c = clientList.get(consid);
		roomlist.add(new room(roomname ,roomid));
		roomnameList.add(roomname + " | " + c.getname());
		gui.addroom(roomname + " | " + c.getname() ,roomid);
		gui.showLog("Add Room " + roomid + " :" + roomname + " | " + c.getname() );
	}
	public void addtoroom(int rID, int userID){
		clientfile c = clientList.get(userID);
		String username = c.getname();
		room r = roomlist.get(rID-1);
		r.adduser(c);
		for(clientfile cl:(r.roomclientlist)){
			if(cl!=c && cl.isonleave()==false) 
				c.send("/aru " + roomid + " " + cl.getname() + " " + cl.getid());
		}
		gui.addroomuser(rID, username);
		gui.showLog("Add " + username + " into Room " + rID);
	}
	public void sendroom(int rID, String msg){
		room r = roomlist.get(rID-1);
		if(r.emtpy==true) return;
		r.sendroom(msg);
		gui.showLog("Send to room " + rID + " : " + msg);
	}
	public void leaveroom(int rID, int uID){
		room r = roomlist.get(rID-1);
		clientfile leave = clientList.get(uID);
		if(r.emtpy==true) return;
			r.userleave(leave);
		gui.showLog(leave.getname() + " left Room " + rID );
		gui.deleteroomuser(rID,leave.getname());
		r.sendroom("/p " + leave.getname() + " left Room ");
	}
	public void leave(int id){
		sendAll("/du " + id);
		sendAll("/p 0 " + nameList.get(id) + " left");
		gui.offlineUser(id);
		gui.showLog(nameList.get(id) + " left");
	}
	public void sendfile(int id){
		clientfile dest = clientList.get(id);
//		dest.recvfile();
		gui.showLog("Send file to " + dest.getname());
	}
	public void kick(int id){
		clientfile c = clientList.get(id);
		try {
			c.closesocket();
			gui.showLog("Bye " + c.getname());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}