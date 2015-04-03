package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Rectangle;
import java.awt.event.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;

import client.Client;
//import gui.ChatTab;
import fileexchange.FileSend;


public class ChatFrame extends JFrame{

	private Client ClientObject;
	//private ChatTab GuiObject;
	public  Vector<String> ouserList;
	public  Vector<String> tuserList;
	private Vector<String> roomList;
	private int action; // the motion
	private int whisID;
	private int roomID;
	private int iuID;
	private int onlineNum;
	private JDialog rename;
	private JTextField forname;
	private String whisperName;
	
	//
	//private JFrame temp = new JFrame();
	//public JTextField input; //textpane
	//public JTextArea output; 
	//xpublic JTextArea users;
	public String message;
	
	
	/* public void setClientObject (Client co) {
		ClientObject = co;
	} */
	
	public ChatFrame( Client co) {
		initComponents();
		
		ClientObject = co;
		ouserList = new Vector<String>();
		//tuserList = new Vector<String>();
		roomList = new Vector<String>();
		action = 0;
		roomID = 0;
		onlineNum = 0;
	}
	
	public void printonGUI (String msg) {
		set_Text(msg);
	}
	
	public void userList(String name, int id) {
		System.out.println("add in user list!");
		ouserList.add(id,name);	
	}
	
	public void addUser(String name, int id) {
		System.out.println("addU " + name + " " + id);
		ListModel.addElement(name);
		onlineNum++;
	}
	public void addroomlist(String roomID) {
		roomList.add(roomID);
	}
	
	public void rename() {
		rename = new JDialog(this,"Please rename!!");
		rename.setSize(320, 200);
		JPanel JP = new JPanel();
		rename.add(JP);
		
		JP.add(new Label("名稱已被使用，請重新輸入一個名字！"));
		
		forname = new JTextField(20);
		JP.add(forname);
		
		JButton enter = new JButton("enter");
		JP.add(enter);
		
		enter.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(ActionEvent e) {
				sendnewname(e);		
				rename.setVisible(false); 
				rename.dispose();
			}
        });
		
		rename.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        rename.setModal(true);	
		rename.setVisible(true);
	}
	
	public void sendnewname(ActionEvent e) {
		ClientObject.username = forname.getText();
		forname.setText(null);	
	}

	public void prepareMsg(String msg) {
		
		if (action == 1) { //whisper
			ClientObject.sendWhis(whisID,roomID,msg);
			action = 0;
		}
		else if (action == 2) { // send room message to all
			ClientObject.sendroom(roomID,msg);
			action = 0;
		}
		else if (action == 3) {
			ClientObject.addroom("roomname");
			action = 0;
		}
		else if (action == 4) {
			ClientObject.addutroom(roomID, iuID); //userID who be invited
			action = 0;
		}
		else
			ClientObject.sendall(msg);
	}


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

    	ListModel = new DefaultListModel<String>();
    	pPanel = new javax.swing.JPanel();
        pic = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        textPanel = new javax.swing.JTextPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        userList = new javax.swing.JList<String>(ListModel);
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        textField = new javax.swing.JTextField();
        
        

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout picLayout = new javax.swing.GroupLayout(pic);
        pic.setLayout(picLayout);
        picLayout.setHorizontalGroup(
            picLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 113, Short.MAX_VALUE)
        );
        picLayout.setVerticalGroup(
            picLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 115, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pPanelLayout = new javax.swing.GroupLayout(pPanel);
        pPanel.setLayout(pPanelLayout);
        pPanelLayout.setHorizontalGroup(
            pPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pPanelLayout.setVerticalGroup(
            pPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        textPanel.setFont(new java.awt.Font("微軟正黑體", 0, 12)); // NOI18N
        jScrollPane2.setViewportView(textPanel);
        textPanel.setEditable(false);

        jTabbedPane1.addTab("tab1", jScrollPane2);

        userList.setFont(new java.awt.Font("微軟正黑體", 0, 12)); // NOI18N
        userList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                userListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(userList);

        jButton1.setFont(new java.awt.Font("微軟正黑體", 0, 12)); // NOI18N
        jButton1.setText("for test");
        jButton1.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(ActionEvent e) {
				ClientObject.sendFile("filename");
			}
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(33, 33, 33))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jButton1)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        textField.setFont(new java.awt.Font("微軟正黑體", 0, 12)); // NOI18N
        textField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
                            .addComponent(textField))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textField, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
	 
	 private void textFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldActionPerformed
	     message = textField.getText();
	     textField.setText(null);
	     prepareMsg(message);
	    }//GEN-LAST:event_textFieldActionPerformed
	 
	 private void userListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userListMouseClicked
	        if(evt.getClickCount() == 2){
	        	//userList.clearSelection();
	        	Rectangle r = userList.getCellBounds(0, onlineNum -1);
	        	System.out.println("onlineNum "+onlineNum);
	        	if(r!=null && r.contains(evt.getPoint())){
	        		String sname = (String) userList.getSelectedValue();
	        		whisID = ouserList.indexOf(sname); 
	        		System.out.println(whisID + sname);
	        		
	        		action = 1;
	        	}
	        	
	        }
	    }//GEN-LAST:event_userListMouseClicked
	 
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
	    
	    public void delUser(int id){
	    	String name = ouserList.get(id);
	   	 	ListModel.removeElement(name);
	   	 	//tuserList.remove(id);
	   	 	//userList.setListData(tuserList);	
	   	 	onlineNum--; 	 
	    }
	 
	    // Variables declaration - do not modify//GEN-BEGIN:variables
	    private javax.swing.JButton jButton1;
	    private javax.swing.JPanel jPanel2;
	    private javax.swing.JScrollPane jScrollPane1;
	    private javax.swing.JScrollPane jScrollPane2;
	    private javax.swing.JTabbedPane jTabbedPane1;
	    private javax.swing.JPanel pPanel;
	    private javax.swing.JPanel pic;
	    private javax.swing.JTextField textField;
	    private javax.swing.JTextPane textPanel;
	    private javax.swing.JList<String> userList;
	    
	    public DefaultListModel<String> ListModel;
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