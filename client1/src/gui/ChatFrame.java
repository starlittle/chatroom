package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;

import client.Client;
//import gui.ChatTab;


public class ChatFrame extends JFrame{

	private Client ClientObject;
	//private ChatTab GuiObject;
	public  Vector<String> ouserList;
	private Vector<String> roomList;
	private int action; // the motion
	private int whisID;
	private int roomID;
	private int iuID;
	
	//
	//private JFrame temp = new JFrame();
	//public JTextField input; //textpane
	//public JTextArea output; 
	public JTextArea users;
	public String message;
	
	
	/* public void setClientObject (Client co) {
		ClientObject = co;
	} */
	
	public ChatFrame( Client co) {
		initComponents();
		
		ClientObject = co;
		//GuiObject = new ChatTab(co);
		//GuiObject.setVisible(true);
		ouserList = new Vector<String>();
		roomList = new Vector<String>();
		action = 0;
		roomID = 0;
	}
	
	public void printonGUI (String msg) {
		//output.append(msg + "\n"); 
		//System.out.println("get message " + msg);
		//GuiObject.set_Text(msg);
		set_Text(msg);
	}
	
	public void userList(String name, int id) {
		System.out.println("add in user list!");
		ouserList.add(id,name);	
		//users.append(name + "\n");
	}
	
	public void addUser(String name, int id) {
		System.out.println(name + " " + id);
		ouserList.add(id,name);
		//users.append(name + "\n");
		//output.append(name + " joined! \n");
	}
	public void addroomlist(String roomID) {
		roomList.add(roomID);
	}

	public void prepareMsg(String msg) {
		
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

	 private void initComponents() {

	        pPanel = new javax.swing.JPanel();
	        textField = new javax.swing.JTextField();
	        jTabbedPane1 = new javax.swing.JTabbedPane();
	        jScrollPane1 = new javax.swing.JScrollPane();
	        textPanel = new javax.swing.JTextPane();
	        jScrollPane2 = new javax.swing.JScrollPane();
	        userList = new javax.swing.JList();
	        Eicon = new javax.swing.JButton();
	        toolBar = new javax.swing.JToolBar();

	        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

	        javax.swing.GroupLayout pPanelLayout = new javax.swing.GroupLayout(pPanel);
	        pPanel.setLayout(pPanelLayout);
	        pPanelLayout.setHorizontalGroup(
	            pPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGap(0, 0, Short.MAX_VALUE)
	        );
	        pPanelLayout.setVerticalGroup(
	            pPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGap(0, 143, Short.MAX_VALUE)
	        );

	        textField.setFont(new java.awt.Font("微軟正黑體", 0, 12)); // NOI18N
	        textField.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                textFieldActionPerformed(evt);
	            }
	        });

	        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

	        textPanel.setFont(new java.awt.Font("微軟正黑體", 0, 12)); // NOI18N
	        jScrollPane1.setViewportView(textPanel);

	        jTabbedPane1.addTab("tab1", jScrollPane1);

	        userList.setFont(new java.awt.Font("微軟正黑體", 0, 12)); // NOI18N
	        userList.setForeground(new java.awt.Color(255, 255, 255));
	        jScrollPane2.setViewportView(userList);

	        Eicon.setFont(new java.awt.Font("微軟正黑體", 0, 12)); // NOI18N
	        Eicon.setText("jButton1");

	        toolBar.setRollover(true);

	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(pPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                    .addGroup(layout.createSequentialGroup()
	                        .addContainerGap()
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addGroup(layout.createSequentialGroup()
	                                .addComponent(textField)
	                                .addGap(50, 50, 50)
	                                .addComponent(Eicon)
	                                .addGap(46, 46, 46))
	                            .addGroup(layout.createSequentialGroup()
	                                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)))))
	                .addContainerGap())
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(toolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(layout.createSequentialGroup()
	                        .addComponent(pPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE))
	                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
	                        .addGap(174, 174, 174)
	                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(textField, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(Eicon))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                .addComponent(toolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
	        );
	        //setVisible(true);
	        pack();
	    }// </editor-fold>//GEN-END:initComponents
	 
	 private void textFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldActionPerformed
	     message = textField.getText();
	     textField.setText(null);
	     prepareMsg(message);
	    }//GEN-LAST:event_textFieldActionPerformed
	 
	    public void set_Text(String mg){
	        SimpleAttributeSet attrset=new SimpleAttributeSet();
	         Document docs=textPanel.getDocument();
	         
	          try{
	                docs.insertString(docs.getLength(),mg+"\n",attrset); 	    	
	             }
	          catch(BadLocationException ble){
	            System.out.println("BadLocationException:"+ble);	
	         }	
	    }
	 
	    // Variables declaration - do not modify//GEN-BEGIN:variables
	    private javax.swing.JButton Eicon;
	    private javax.swing.JScrollPane jScrollPane1;
	    private javax.swing.JScrollPane jScrollPane2;
	    private javax.swing.JTabbedPane jTabbedPane1;
	    private javax.swing.JPanel pPanel;
	    private javax.swing.JTextField textField;
	    private javax.swing.JTextPane textPanel;
	    private javax.swing.JToolBar toolBar;
	    private javax.swing.JList userList;
	    // End of variables declaration//GEN-END:variables
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