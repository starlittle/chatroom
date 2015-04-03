package server;

import java.util.*;


public class room{
	private int roomID;
	private String roomname;
	Vector<clientfile> roomclientlist;
	public boolean emtpy;
	
	public room(String name,int id){
		roomID = id;
		roomname = name;		
	}
	public void adduser(clientfile c){
		roomclientlist.add(c);
	}
	public void userleave(clientfile c){
		roomclientlist.remove(c);
		if(roomclientlist.isEmpty()) emtpy = true;
	}
	public void sendroom(String msg){
		for(clientfile c:roomclientlist) c.send(msg);
	}	
	
	
}