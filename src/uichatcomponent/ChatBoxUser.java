
package uichatcomponent;



/**
 *
 * @author HIEN
 */
public class ChatBoxUser extends ChatBoxLayout{

    public ChatBoxUser(String name,Boolean isOnline) {
        nameJLabel.setText(name);
        setStatusHeader(isOnline);
        
        javax.swing.GroupLayout headerChatLayoutLayout = new javax.swing.GroupLayout(headerJPanel);
        headerJPanel.setLayout(headerChatLayoutLayout);
        headerChatLayoutLayout.setHorizontalGroup(
            headerChatLayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerChatLayoutLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(nameJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(statusJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93)
                .addComponent(deleteHistoryJLabel)
                .addGap(32, 32, 32)
                .addComponent(searchBarChat, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );
        headerChatLayoutLayout.setVerticalGroup(
            headerChatLayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerChatLayoutLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(headerChatLayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(statusJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteHistoryJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchBarChat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        
        createInput(this);
        
//        inputChatTextArea.setColumns(20);
//        inputChatTextArea.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
//        inputChatTextArea.setLineWrap(true);
//        inputChatTextArea.setRows(5);
//        inputChatTextArea.setWrapStyleWord(true);
//        JScrollPane inputChatScollPane = new JScrollPane();
//        inputChatScollPane.setViewportView(inputChatTextArea);
//
//        sendButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
//        sendButton.setText("Gửi");
//        
//        javax.swing.GroupLayout chatBoxUserLayout = new javax.swing.GroupLayout(this);
//        this.setLayout(chatBoxUserLayout);
//        chatBoxUserLayout.setHorizontalGroup(
//            chatBoxUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addComponent(headerJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//            .addGroup(chatBoxUserLayout.createSequentialGroup()
//                .addGap(19, 19, 19)
//                .addGroup(chatBoxUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
//                    .addComponent(displayChat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                    .addGroup(chatBoxUserLayout.createSequentialGroup()
//                        .addComponent(inputChatScollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 618, javax.swing.GroupLayout.PREFERRED_SIZE)
//                        .addGap(36, 36, 36)
//                        .addComponent(sendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
//                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
//        );
//        chatBoxUserLayout.setVerticalGroup(
//            chatBoxUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGroup(chatBoxUserLayout.createSequentialGroup()
//                .addComponent(headerJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//                .addGap(35, 35, 35)
//                .addComponent(displayChat, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
//                .addGap(38, 38, 38)
//                .addGroup(chatBoxUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                    .addGroup(chatBoxUserLayout.createSequentialGroup()
//                        .addComponent(sendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
//                        .addGap(0, 156, Short.MAX_VALUE))
//                    .addGroup(chatBoxUserLayout.createSequentialGroup()
//                        .addComponent(inputChatScollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
//                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
//        );

        
    }

    public static String createChatBoxUserID(int firstID,int secondID){
        if(firstID > secondID){
            firstID = firstID ^ secondID;
            secondID = firstID ^ secondID;
            firstID = firstID ^ secondID;
        }

        String ID1 = String.valueOf(firstID);
        String ID2 = String.valueOf(secondID);

        return ID1 + "-" + ID2;
    }
    
   
    
    
    
}
