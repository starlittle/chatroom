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
		gui.showLog("broadcast: " + msg);
	}
	public boolean sendPrivate(int id, int roomid, String msg){
		clientfile c = clientList.get(id);
		if(c.isonleave()==true) return false;
		else {
			c.send(msg);
			gui.showLog("whisper: " + msg);
			return true;
		}
	}
	public void adduser(String name, int id){
		nameList.add(name);
		gui.adduser(name, id);
	}
	public void addroom(String roomname){
		roomlist.add(new room(roomname,roomid++));
		roomnameList.add(roomname);
		gui.addroom(roomname);	
	}
	public void addtoroom(int roomID, int userID){
		clientfile c = clientList.get(userID);
		String username = c.getname();
		room r = roomlist.get(roomID);
		r.adduser(c);
		gui.addroomuser(roomID, username);
	}
	public void sendroom(int roomID, String msg){
		room r = roomlist.get(roomID);
		if(r.emtpy==true) return;
		r.sendroom(msg);
		gui.showLog("Send to room " + roomID + " : " + msg);
	}
	public void leave(int id){
		sendAll("/du " + id);
		sendAll("/p " + nameList.get(id) + " left");
		gui.offlineUser(id);
	}
	public void kick(int id){
		clientfile c = clientList.get(id);
		try {
			c.closesocket();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}