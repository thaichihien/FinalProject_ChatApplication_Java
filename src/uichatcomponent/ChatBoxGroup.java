/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uichatcomponent;

import javax.swing.JButton;

import datastructure.GroupChat;
import datastructure.UserAccount;

/**
 *
 * @author HIEN
 */
public class ChatBoxGroup extends ChatBoxLayout{

    private JButton viewGroupInforButton;
    private GroupChat groupChat;
    
    public ChatBoxGroup(UserAccount me,GroupChat groupChat) {
        super(me);
        this.groupChat = groupChat;
        nameJLabel.setText(groupChat.getGroupname());
        setStatusHeader(groupChat.getOnline());
        viewGroupInforButton = new JButton();
        viewGroupInforButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        viewGroupInforButton.setText("Thông tin nhóm");
        
        javax.swing.GroupLayout headerGroupLayout = new javax.swing.GroupLayout(headerJPanel);
        headerJPanel.setLayout(headerGroupLayout);
        headerGroupLayout.setHorizontalGroup(
            headerGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerGroupLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(headerGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(viewGroupInforButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nameJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(deleteHistoryJLabel)
                .addGap(41, 41, 41)
                .addComponent(searchBarChat, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67))
        );
        headerGroupLayout.setVerticalGroup(
            headerGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerGroupLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(headerGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(statusJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteHistoryJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchBarChat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(viewGroupInforButton, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        
        createInput(this);
    }
    
}
