package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;

import client.Client;


public class ChatFrame extends JFrame{

	private Client ClientObject;
	private ChatTab GuiObject;
	public  Vector<String> userList;
	private Vector<String> roomList;
	private int action; // the motion
	private int whisID;
	private int roomID;
	private int iuID;
	
	//
	private JFrame temp = new JFrame();
	//public JTextField input; //textpane
	//public JTextArea output; 
	public JTextArea users;
	public String message;
	
	
	/* public void setClientObject (Client co) {
		ClientObject = co;
	} */
	
	public ChatFrame( Client co) {
		//initComponents();
		
		ClientObject = co;
		GuiObject = new ChatTab();
		userList = new Vector<String>();
		roomList = new Vector<String>();
		action = 0;
		roomID = 0;
	}
	
	public void printonGUI (String msg) {
		//output.append(msg + "\n"); 
		//System.out.println("get message " + msg);
		GuiObject.set_Text(msg);
	}
	
	public void userList(String name, int id) {
		System.out.println("add in user list!");
		userList.add(id,name);	
		//users.append(name + "\n");
	}
	
	public void addUser(String name, int id) {
		System.out.println(name + " " + id);
		userList.add(id,name);
		//users.append(name + "\n");
		//output.append(name + " joined! \n");
	}
	public void addroomlist(String roomID) {
		roomList.add(roomID);
	}

	private void prepareMsg(String msg) {
		
		if (action == 1) { //whisper
			ClientObject.sendWhis(whisID,roomID,msg);
		}
		else if (action == 2) { // send room message to all
			ClientObject.sendroom(roomID,msg);
		}
		else if (action == 3) {
			ClientObject.addroom("roomname");
		}
		else if (action == 4) {
			ClientObject.addutroom(roomID, iuID); //userID who be invited
		}
		ClientObject.sendall(msg);
	}

/*	private void initComponents() {
		// TODO Auto-generated method stub
		
		temp.setSize(400, 300);
        temp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //temp.setLayout(new GridLayout(2, 2));
        temp.setLayout(new BorderLayout());
        message = null;
        input = new JTextField(20);
        output = new JTextArea(5,20);
        output.setEditable(false);
        users = new JTextArea(6,5);
        users.setEditable(false);
        
        temp.add(output,BorderLayout.CENTER);
		temp.add(input,BorderLayout.SOUTH);
		temp.add(users,BorderLayout.EAST);
        
        input.addActionListener(new java.awt.event.ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				message = input.getText();
				input.setText(null);
				prepareMsg(message);	
			
			}
        	
        });

		temp.pack();
		temp.setVisible(true);
	}*/ 
	
	
}
