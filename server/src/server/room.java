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
		roomclientlist = new Vector<clientfile>();
	    System.out.println("in room");
	}
	public void adduser(clientfile c){
		roomclientlist.add(c);
	}
	public void userleave(clientfile c){
		roomclientlist.remove(c);
		if(roomclientlist.isEmpty()) emtpy = true;
	}
	public void sendroom(String msg){
		for(clientfile c:roomclientlist){
			if(c.isonleave()==false)
				c.send(msg);
		}			
	}	
	public String getname(){
		return roomname;
	}
	public boolean isinroom(clientfile c){
		if(roomclientlist.contains(c)) return true;
		else return false;
	}
	public int getidxof(clientfile c){
		return roomclientlist.indexOf(c);
	}
	public int getid(){
		return roomID;
	}
	
}