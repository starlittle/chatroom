package fileexchange;

import java.awt.Label;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;

import client.Client;

public class RecvGUI extends JDialog{
	
	private Client clientobject;
	private String sendname;
	

	public RecvGUI (Client cl, String name) {
		clientobject = cl;
		sendname = name;
	
		this.setSize(220, 100);
		JPanel JP = new JPanel();
		this.add(JP);
		
		JP.add(new Label("There is a file from " + sendname ));
		JP.add(new Label("  Do you want to accept it? "));
		JP.add(new Label("				"));
	
	//forname = new JTextField(20);
	//JP.add(forname);
	
		JButton yes = new JButton("Yes");
		JP.add(yes);
		
		JButton no = new JButton("No");
		JP.add(no);
	
		yes.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(ActionEvent e) {
				clientobject.receive = 1;
				setVisible(false); 
				dispose();
			}
		});
		
		no.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(ActionEvent e) {
				clientobject.receive = 0;
				setVisible(false); 
				dispose();
			}
		});
	
		this.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		this.setModal(true);	
		this.setVisible(true);
	}

}