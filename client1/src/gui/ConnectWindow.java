/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/**
 *
 * @author HP
 */
public class ConnectWindow extends javax.swing.JDialog {

    /**
     * Creates new form ConnectWindow
     */
    public int port;
    public String IP;
    public String name;
    public ConnectWindow(ChatFrame f) {
        super(f);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pic = new javax.swing.JPanel();
        input_port = new javax.swing.JTextField();
        portLabel = new javax.swing.JLabel();
        ipLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        input_name = new javax.swing.JTextField();
        goButton = new javax.swing.JButton();
        name_remind = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        input_IP = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setModal(true);
        
        javax.swing.GroupLayout picLayout = new javax.swing.GroupLayout(pic);
        pic.setLayout(picLayout);
        picLayout.setHorizontalGroup(
            picLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 183, Short.MAX_VALUE)
        );
        picLayout.setVerticalGroup(
            picLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
        );

        input_port.setFont(new java.awt.Font("微軟正黑體", 0, 12)); // NOI18N
        input_port.setText("8010");

        portLabel.setFont(new java.awt.Font("微軟正黑體", 0, 12)); // NOI18N
        portLabel.setText("port");

        ipLabel.setFont(new java.awt.Font("微軟正黑體", 0, 12)); // NOI18N
        ipLabel.setText("IP");

        nameLabel.setFont(new java.awt.Font("微軟正黑體", 0, 12)); // NOI18N
        nameLabel.setText("Name");

        input_name.setFont(new java.awt.Font("微軟正黑體", 0, 12)); // NOI18N

        goButton.setFont(new java.awt.Font("微軟正黑體", 0, 12)); // NOI18N
        goButton.setText("GO!");
        goButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goButtonActionPerformed(evt);
            }
        });

        name_remind.setFont(new java.awt.Font("微軟正黑體", 0, 12)); // NOI18N

        jButton2.setFont(new java.awt.Font("微軟正黑體", 0, 12)); // NOI18N
        jButton2.setText("Cancel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        input_IP.setFont(new java.awt.Font("微軟正黑體", 0, 12)); // NOI18N
        input_IP.setText("140.112.18.219");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(name_remind, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 25, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(goButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(nameLabel))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(input_IP)
                            .addComponent(input_name)
                            .addComponent(input_port, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGap(86, 86, 86)
                                        .addComponent(ipLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGap(77, 77, 77)
                                        .addComponent(portLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(pic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ipLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(input_IP, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(portLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(input_port, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(nameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(input_name, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(name_remind, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(goButton)
                    .addComponent(jButton2))
                .addGap(41, 41, 41))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void goButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goButtonActionPerformed
        IP = input_IP.getText();
        String port_text = input_port.getText();
        port = new java.lang.Integer(port_text).intValue();

        if( input_name.getText().equals("") ) {
            JOptionPane.showMessageDialog(this, "請輸入名稱", "", JOptionPane.ERROR_MESSAGE);
        } else if( input_name.getText().contains(" ") ) {
            JOptionPane.showMessageDialog(this, "名稱請不要有空白", "", JOptionPane.ERROR_MESSAGE);
            input_name.setText("");
        } else {
            name = input_name.getText();
            setVisible(false);
       
    }//GEN-LAST:event_goButtonActionPerformed
    }
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton goButton;
    private javax.swing.JTextField input_IP;
    private javax.swing.JTextField input_name;
    private javax.swing.JTextField input_port;
    private javax.swing.JLabel ipLabel;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel name_remind;
    private javax.swing.JPanel pic;
    private javax.swing.JLabel portLabel;
    // End of variables declaration//GEN-END:variables
}
