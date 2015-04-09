package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.Rectangle;
import java.awt.event.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;

import client.Client;
import gui.MainChatTab;
import gui.AudioInvite;


public class ChatFrame extends JFrame{

	private Client ClientObject;
	private Map<Integer,ChatTab> map;
	private Vector<String> tabs;
	//private ChatTab GuiObject;
	public  Vector<String> ouserList;
	//public  Vector<String> tuserList;
	private Vector<String> roomList;
	private int action; // the motion
	private int whisID;
	private int roomID;
	private int lastRoomID;
	private int iuID;
	private int onlineNum;
	private int tabID;
	private JDialog rename;
	private JTextField forname;
	private String whisperName;
	private ChatTab testCT;
	private int testNum;
	private String roomName;
	private String userName;
	
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
		MCTab.setFrame(this);
		MCTab.setClientObject(ClientObject);
		//MCTab.setuList(ouserList);
		
		ouserList = new Vector<String>();
		roomList = new Vector<String>();
		map = new HashMap<Integer, ChatTab>();
		action = 0;
		roomID = 0;
		lastRoomID = 0;
		onlineNum = 0;
		testNum = 0;
		nameLabel.setText(userName);
		System.out.println("name: "+userName);
	}
	public void setID(String ID){
		roomID = Integer.parseInt(ID);
	}
	
	
	public void printonGUI (String msg) {
        if(roomID == 0)
            MCTab.set_Text(msg);
       
        else{
            ChatTab tab = map.get(roomID);
            tab.set_Text(msg);
             }
	}
	
	public void userList(String name, int id) {
		System.out.println("add in user list!");
		ouserList.add(name);
		MCTab.getuserList(name,id);
			
	}
	
	//for MainTab
	public void addUser(String name, int id) {
		MCTab.addUser(name, id);            
	}
	
	//for MainTab
        public void delUser(int id){        
            MCTab.delUser(id);
    }
        
        public void addrUser(String rID, String name,String id){
        	
        	int roID = Integer.parseInt(rID);
        	int iid = Integer.parseInt(id);
        	ChatTab tab = map.get(roID);
            tab.addUser(name,iid);	
        }
        
        public void delrUser(String rID, String id){
        	int roID = Integer.parseInt(rID);
        	int iid = Integer.parseInt(id);
        	ChatTab tab = map.get(roID);
            tab.delUser(iid);	
        	
        }
    
	
	public void addroomlist(String roomID) {
		roomList.add(roomID);
	}
	
     public void addTab(String ID){
     	roomID = Integer.parseInt(ID);
     	System.out.println("in roomID: "+roomID);
     	testCT = new ChatTab(roomID,MCTab);
     	
     	//System.out.println("here");
     	testCT.setFrame(this);     	
     	testCT.setClientObject(ClientObject);
     	map.put(roomID,testCT);    
     	System.out.println("mapSize: "+map.size());
     	
         jTabbedPane2.add(roomName,testCT);
     }
     
     public void invitedtoRoom(String ID,String rName){
         roomID = Integer.parseInt(ID);
     	System.out.println("roomID: "+roomID);
        testCT = new ChatTab(roomID,MCTab);
     	
     	System.out.println("here");
     	testCT.setFrame(this);     	
     	testCT.setClientObject(ClientObject);
     	map.put(roomID,testCT);    	
     	
         jTabbedPane2.add(rName,testCT);
     }
     
     public void delTab(String ID){
    	 roomID = Integer.parseInt(ID);
      	System.out.println("out roomID: "+roomID);
      	testCT = new ChatTab(roomID,MCTab);
      	
      	//System.out.println("here");
      	testCT.setFrame(this);     	
      	testCT.setClientObject(ClientObject);
      	map.remove(roomID); 
      	System.out.println("mapSize: "+map.size());
      	  if(tabID !=0)
             jTabbedPane2.remove(tabID);
     }
     
     public void printimage(String msg, String name){
    	 if(roomID == 0)
    		 MCTab.printImage(msg,name);
    	 else{
    		 ChatTab tab = map.get(roomID);
    		 tab.printImage(msg,name);
    	 }
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

	public void setprofile(ImageIcon profilepic) {
		// TODO Auto-generated method stub
		if (profilepic == null) {
			System.out.println("no profile picture");
		}
		else {
			Image img = profilepic.getImage() ;  
			Image newimg = img.getScaledInstance( pic.getWidth() -10 , pic.getHeight() -5,  java.awt.Image.SCALE_SMOOTH ) ;  
			profilepic = new ImageIcon( newimg );
			
			pic.setIcon(profilepic);
		    //jButton1.setText(null);
		}
		
	}
	
	public void setName(String name){nameLabel.setText(name);}
	
	private void choosepic() {
		// TODO Auto-generated method stub
    	JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new java.io.File("."));
		fc.setDialogTitle("choose file");
        fc.setMultiSelectionEnabled(false);
        
        FileFilter type2 = new ExtensionFilter("Image files",
                new String[] { ".jpg", ".gif", "jpeg", "xbm", ".png" });
        
        fc.addChoosableFileFilter(type2);
        fc.setFileFilter(type2);
        
        //FileView view = new IconView();
        //fc.setFileView(view);
        ImageIcon image = null;
		int result = fc.showOpenDialog(new JFrame());
		File sendfile;
		if (result == JFileChooser.APPROVE_OPTION) {
			sendfile = fc.getSelectedFile();
						
			//String n = sendfile.getName();
			String p = sendfile.getAbsolutePath();
			System.out.println("getfile: "+ p);
			
			image = new ImageIcon(p);			
			setprofile(image);
		}
		else 
			System.out.println("no get");	
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

        pPanel = new javax.swing.JPanel();
        newRoom = new javax.swing.JButton();
        roomNameField = new javax.swing.JTextField();
        pic = new javax.swing.JButton();
        nameLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        MCTab = new gui.MainChatTab();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(532, 634));
        setMinimumSize(new java.awt.Dimension(532, 634));
        setResizable(false);

        newRoom.setFont(new java.awt.Font("微軟正黑體", 0, 12)); // NOI18N
        newRoom.setText("new Room");
        newRoom.setActionCommand("jbutton");
        newRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newRoomActionPerformed(evt);
            }
        });

        roomNameField.setFont(new java.awt.Font("微軟正黑體", 0, 12)); // NOI18N

        pic.setFont(new java.awt.Font("微軟正黑體", 0, 12)); // NOI18N
        pic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                picActionPerformed(evt);
            }
        });

        nameLabel.setFont(new java.awt.Font("微軟正黑體", 0, 12)); // NOI18N
        nameLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("微軟正黑體", 0, 12)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/star1.png"))); // NOI18N

        javax.swing.GroupLayout pPanelLayout = new javax.swing.GroupLayout(pPanel);
        pPanel.setLayout(pPanelLayout);
        pPanelLayout.setHorizontalGroup(
            pPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pic, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 135, Short.MAX_VALUE)
                        .addComponent(roomNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(newRoom)
                        .addContainerGap())
                    .addGroup(pPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        pPanelLayout.setVerticalGroup(
            pPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pPanelLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(pic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pPanelLayout.createSequentialGroup()
                        .addGroup(pPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(newRoom)
                            .addComponent(roomNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                        .addGroup(pPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))))
                .addContainerGap())
        );

        jTabbedPane2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane2StateChanged(evt);
            }
        });
        jTabbedPane2.addTab("Lobby", MCTab);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane2))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
	 
    private void jTabbedPane2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane2StateChanged
        JTabbedPane tb = (JTabbedPane)evt.getSource();
        tabID = tb.getSelectedIndex();//???
        
        System.out.println("statechange "+ roomID);
        
    }//GEN-LAST:event_jTabbedPane2StateChanged

    private void newRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newRoomActionPerformed
    	//newName nName = new newName(ClientObject);
    	//nName.setVisible(true);
    	//nName.setModal(true);
/////
    	//ClientObject.addroom("newRoom"+(testNum+1));
    	//testNum++;
        roomName = roomNameField.getText();
        System.out.println("rname "+roomName);
        
        if(roomName == ""){
            ClientObject.addroom("newRoom"+(testNum+1));
            testNum++;
        }
        else{
            ClientObject.addroom(roomName);
        }
        roomNameField.setText("");
    }//GEN-LAST:event_newRoomActionPerformed

    private void picActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_picActionPerformed
    	choosepic();
    }//GEN-LAST:event_picActionPerformed
    
	 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.MainChatTab MCTab;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JButton newRoom;
    private javax.swing.JPanel pPanel;
    private javax.swing.JButton pic;
    private javax.swing.JTextField roomNameField;
    // End of variables declaration//GEN-END:variables
    
    public class ExtensionFilter extends FileFilter {
        private String extensions[];
        private String description;

        public ExtensionFilter(String description, String extension) {
          this(description, new String[] { extension });
        }
        
        public ExtensionFilter(String description, String extensions[]) {
          this.description = description;
          this.extensions = (String[]) extensions.clone();
        }

        public boolean accept(File file) {
          if (file.isDirectory()) {
            return true;
          }
          int count = extensions.length;
          String path = file.getAbsolutePath();
          for (int i = 0; i < count; i++) {
            String ext = extensions[i];
            if (path.endsWith(ext)
                && (path.charAt(path.length() - ext.length()) == '.')) {
              return true;
            }
          }
          return false;
        }

        public String getDescription() {
          return (description == null ? extensions[0] : description);
        }
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