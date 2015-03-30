package server;
import java.io.*;
import java.net.*;
import java.util.Vector;

import server.clientfile;

public class server{
	private ServerSocket ss;
	private final int sPort = 8010;
	Vector<clientfile> clientList;
	Vector<room> roomlist;
	Vector<String> nameList;
	Vector<String> pswordList;
	private int id;
	private int roomid;
	private ServerDisplay gui;
	DataInputStream in;
	
	public server(){
		clientList = new Vector<clientfile>();
		nameList = new Vector<String>();
		gui = new ServerDisplay(this);
		gui.setVisible(true);
		try{
			ss = new ServerSocket(sPort);
			while(true){
				Socket s = null;
				synchronized(this){
					s = ss.accept();
		//			if(nameList.contains())
		//			in = new DataInputStream(s.getInputStream());
		//			String cname = in.readUTF();
		//			clientList.add(new clientfile(this,s,id++/*,cname*/));
					clientList.add(new clientfile(this,s,id++));
				}
				Thread t = new Thread(clientList.lastElement());
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
	public boolean sendPrivate(int id, String msg){
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
		gui.showLog("Send to room " + id + " : " + msg);
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