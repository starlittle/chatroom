package server;
import java.io.*;
import java.net.*;
import java.util.*;

public class clientfile implements Runnable{
	private Socket s;
	private server mainserver;
	private DataInputStream in;
	private DataOutputStream out;
	private String msg,name;
	private int id;
	String TransferLine;
	
	public clientfile(server ms, Socket ss, int cid){
		try{
			s = ss;
			mainserver = ms;
			in = new DataInputStream(s.getInputStream());
			out = new DataOutputStream(s.getOutputStream());
			id = cid;
			
		}catch(IOException e){
			System.out.println(e.toString());
		}   
	}
	public void send(String s){
		try{
			out.writeUTF(s);			
		}catch(IOException e){
			System.out.println(e.toString());
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			while(true){
				TransferLine = in.readUTF();
				System.out.println("Recv: " + TransferLine);
				out.writeUTF("Recvname");
			}
		}catch(IOException e){
			System.out.println(e.toString());
		}
	}
	
	
	
	
}