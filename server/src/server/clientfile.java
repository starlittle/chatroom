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
			System.out.println("constructed err: "+e.toString());
		}   
	}
	public void send(String s){
		try{
			out.writeUTF(s);			
		}catch(IOException e){
			System.out.println("write err: "+e.toString());
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			setname();
			while(true){
				TransferLine = in.readUTF();
				System.out.println("Recv: " + TransferLine);
				parseMsg(TransferLine);
				//out.writeUTF("server sent back to id"+id + ":"+TransferLine);
			}
		}catch(IOException e){
			System.out.println("in run(): "+e.toString());
		}
	}
	public void parseMsg(String msg){
		mainserver.sendAll("id"+id+": "+msg);
	}
	public void setname()throws IOException{
		String buf;		
		while(true){
			buf = in.readUTF();
			if(mainserver.nameList.contains(buf)){
				out.writeUTF("Name used!");
				continue;
			}
			else{
				name = buf;
				mainserver.adduser(name,id);
				out.writeUTF("Recvname");
				mainserver.sendAll(name+" joined!");
				break;
			}
		}
	}
	
	
	
	
}