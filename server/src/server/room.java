package server;

import java.util.*;


public class room{
	private int roomID;
	private String roomname;
	private Vector<clientfile> clientlist;
	public boolean emtpy;
	
	public room(String name,int id){
		roomID = id;
		roomname = name;		
	}
	public void adduser(clientfile c){
		clientlist.add(c);
	}
	public void userleave(clientfile c){
		clientlist.remove(c);
		if(clientlist.isEmpty()) emtpy = true;
	}
	public void sendroom(String msg){
		for(clientfile c:clientlist) c.send(msg);
	}	
	
	
}