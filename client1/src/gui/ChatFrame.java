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
	private Vector<String> userList;
	
	//
	private JFrame temp = new JFrame();
	public JTextField input; //textpane
	public JTextArea output; 
	public JTextArea users;
	public String message;
	
	
	public void setClientObject (Client co) {
		ClientObject = co;
	}
	public ChatFrame( Client co) {
		initComponents();
		
		ClientObject = co;
		userList  = new Vector<String>();
	}
	
	public void printonGUI (String msg) {
		output.append(msg + "\n"); 
		//System.out.println("get message " + msg);
	}
	
	public void userList(String name, int id) {
		System.out.println("add in user list!");
		userList.add(id,name);	
		users.append(name + "\n");
	}
	
	public void addUser(String name, int id) {
		System.out.println(name + " " + id);
		userList.add(id,name);
		users.append(name + "\n");
		output.append(name + " joined! \n");
	}

	private void prepareMsg(String msg) {
		// TODO Auto-generated method stub
		ClientObject.send(msg);
	}

	private void initComponents() {
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
				// TODO Auto-generated method stub
				//typing();
				message = input.getText();
				//output.append(message + "\n"); 
				input.setText(null);
				//System.out.println("send for frame: " + message);
				prepareMsg(message);	
			
			}
        	
        });
/*        input.addKeyListener(new java.awt.event.KeyAdapter(){
        	public void keyPressed(java.awt.event.KeyEvent evt) {
        		if (evt.getKeyChar() == '\n') {
        			typing();
        			ClientObject.send(message);
        		}
        	}
        	public void keyReleased(java.awt.event.KeyEvent evt) {
        		if (evt.getKeyChar() == '\n') {
        			input.setText(null);
        			//ClientObject.send(message);
        		}
        	}
        }); */       
        
		temp.pack();
		temp.setVisible(true);
	}
	
	
}
