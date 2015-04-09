/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import client.Client;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;

/**
 *
 * @author HP
 */
public class testChatTab extends javax.swing.JPanel {
    private DefaultListModel<String> ListModel;
        public Vector<String> uList;
        public Vector<String> existList;
        
        private ChatFrame cf;
        private Client ClientObject;
        private MainChatTab mctab;
        
        private int onlineNum;
        private int action;
        public String message;
        private int whisID;
        public int invID;
        public int croomID;
        private boolean whis;

    public testChatTab(int roomID, MainChatTab Mtab) {
        initComponents();
        
        ListModel = new DefaultListModel<String>();
        mctab = Mtab;
        onlineNum = 0;
        croomID = roomID;
        whis = false;
    }
    
     public void setFrame(gui.ChatFrame chatF){cf = chatF;}
    public void setClientObject( Client co ) { ClientObject = co; }

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
    
    public void addUser(String name, int id){
    	System.out.println("addU " + name + " " + id);
		ListModel.addElement(name);
                existList.add(name);
		onlineNum++;
    }
    
    public void delUser(int id){
    	String name = cf.ouserList.get(id);
   	 	ListModel.removeElement(name);
                //existList.remove(id);
   	 	onlineNum--; 	 
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        textPanel = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        userList = new javax.swing.JList<String>();
        inviteBu = new javax.swing.JButton();
        textField = new javax.swing.JTextField();
        jToolBar1 = new javax.swing.JToolBar();
        sendFile = new javax.swing.JButton();
        eicon = new javax.swing.JButton();

        textPanel.setFont(new java.awt.Font("微軟正黑體", 0, 12)); // NOI18N
        jScrollPane1.setViewportView(textPanel);

        userList.setFont(new java.awt.Font("微軟正黑體", 0, 12)); // NOI18N
        jScrollPane2.setViewportView(userList);

        inviteBu.setFont(new java.awt.Font("微軟正黑體", 0, 12)); // NOI18N
        inviteBu.setText("invite");
        inviteBu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inviteBuActionPerformed(evt);
            }
        });

        textField.setFont(new java.awt.Font("微軟正黑體", 0, 12)); // NOI18N
        textField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldActionPerformed(evt);
            }
        });

        jToolBar1.setRollover(true);

        sendFile.setFont(new java.awt.Font("微軟正黑體", 0, 12)); // NOI18N
        sendFile.setText("send File");
        sendFile.setFocusable(false);
        sendFile.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        sendFile.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(sendFile);

        eicon.setFont(new java.awt.Font("微軟正黑體", 0, 12)); // NOI18N
        eicon.setText("eicon");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(inviteBu, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(0, 11, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(textField, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(eicon)
                        .addGap(46, 46, 46))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(inviteBu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textField)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                        .addComponent(eicon)
                        .addGap(50, 50, 50))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void textFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldActionPerformed
         String message = textField.getText();
        textField.setText(null);
        if (whis){
            ClientObject.sendWhis(whisID,croomID,message);
            whis = false;
        }
        else
            ClientObject.sendall(message);

    }//GEN-LAST:event_textFieldActionPerformed

    private void inviteBuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inviteBuActionPerformed
        System.out.println("invite!");
    	//InviteFrame iFrame = new InviteFrame(mctab.existList,this);
    	//iFrame.setVisible(true);
    	//iFrame.setModal(true);
    }//GEN-LAST:event_inviteBuActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton eicon;
    private javax.swing.JButton inviteBu;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton sendFile;
    private javax.swing.JTextField textField;
    private javax.swing.JTextPane textPanel;
    private javax.swing.JList<String> userList;
    // End of variables declaration//GEN-END:variables
}
