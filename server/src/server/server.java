package server;
import java.io.*;
import java.net.*;
import java.util.Vector;

import server.clientfile;

public class server{
	private ServerSocket ss;
	private final int sPort = 8010;
	Vector<clientfile> clientList;
	Vector<String> nameList;
	private int id;
	public server(){
		clientList = new Vector<clientfile>();
		nameList = new Vector<String>();
		try{
			ss = new ServerSocket(sPort);
			while(true){
				Socket s = null;
				synchronized(this){
					s = ss.accept();
					clientList.add(new clientfile(this,s,id++));
				}
				Thread t = new Thread(clientList.lastElement());
				t.start();
				System.out.println("connected!");
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
	public void sendAll(String msg){
		for(clientfile c: clientList){
			if(c.onleave==true) c.send(msg);			
		}
	}
	public boolean sendPrivate(int id, String msg){
		clientfile c = clientList.get(id);
		if(c.onleave==false) return false;
		else {
			c.send(msg);
			return true;
		}
	}
	public void adduser(String name,int id){
		nameList.add(name);
	}
	public void leave(int id){
		sendAll("/du " + id);
		sendAll("/p " + nameList.get(id) + " left");		
	}
}