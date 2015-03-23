package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import client.Client;


public class ChatFrame extends JFrame{

	private Client ClientObject;
	private JFrame temp = new JFrame();
	public JTextField input; //textpane
	public JTextArea output; 
	public String message;
	
	public void setClientObject (Client co) {
		ClientObject = co;
	}
	public ChatFrame( Client co) {
		initComponents();
		ClientObject = co;
	}
	
	public void printonGUI (String msg) {
		output.append(msg + "\n"); 
	}

	private void prepareMsg(String msg) {
		// TODO Auto-generated method stub
		ClientObject.send(msg);
	}

	private void initComponents() {
		// TODO Auto-generated method stub
		
		temp.setSize(400, 300);
        temp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        temp.setLayout(new GridLayout(2, 2));
        message = null;
        input = new JTextField(20);
        output = new JTextArea(5,20);
        output.setEditable(false);
        
        temp.add(output);
		temp.add(input);
        
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
