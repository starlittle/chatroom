package server;
import java.io.*;
import java.net.*;
import java.util.Vector;

import server.clientfile;

public class server{
	private ServerSocket ss;
	private final int sPort = 8010;
	Vector<clientfile> clientList;
	private int id;
//	private DataInputStream in;
//	private DataOutputStream out;
	public server(){
		clientList = new Vector<clientfile>();
		try{
			ss = new ServerSocket(sPort);
			while(true){
				Socket s = null;
				synchronized(this){
					s = ss.accept();
					clientList.add(new clientfile(this,s,id++));
				}
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
	/*	public void run(){
		
	}*/
	private void parseMsg(String msg) {
		// TODO Auto-generated method stub
		String[] splitedLine = msg.split(" ", 3);
        System.out.println("User joined:" + splitedLine[1]);
	}
	
}