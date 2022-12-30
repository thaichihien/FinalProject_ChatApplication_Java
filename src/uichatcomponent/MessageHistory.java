
package uichatcomponent;

import java.util.ArrayList;

import datastructure.Message;

public class MessageHistory extends javax.swing.JPanel {

    
    private uichatcomponent.ChatMessageDisplay chatMessageDisplay1;
    private javax.swing.JLabel jLabelKeyword;
    private String username;
    

    private void fillDisplay(ArrayList<Message> messages){
        for(Message message: messages){
            ChatMessageBlock messageBlock;
            if(message.getUserName().equals(username)){
                messageBlock = new ChatMessageBlock(message.getUserName(), message.getDateSend(), ChatMessageBlock.MINE, message.getContent());
            }
            else{
                messageBlock = new ChatMessageBlock(message.getUserName(), message.getDateSend(), ChatMessageBlock.OTHER, message.getContent());
            }
           chatMessageDisplay1.addMessage(messageBlock);
        }
    }

    public MessageHistory(ArrayList<Message> messages,String username,String keyword) {
        initComponents();
        this.username = username;
        jLabelKeyword.setText(keyword);
        fillDisplay(messages);

    }

                         
    private void initComponents() {

        chatMessageDisplay1 = new uichatcomponent.ChatMessageDisplay();
        jLabel1 = new javax.swing.JLabel();
        jLabelKeyword = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Tất cả tin nhắn tìm được theo:");

        jLabelKeyword.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelKeyword.setForeground(new java.awt.Color(0, 0, 0));
        jLabelKeyword.setText("<keyword>");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chatMessageDisplay1, javax.swing.GroupLayout.PREFERRED_SIZE, 681, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelKeyword, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelKeyword, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chatMessageDisplay1, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
    }                      


    // public static void main(String[] args) {
    //     SwingUtilities.invokeLater(new Runnable() {

    //         @Override
    //         public void run() {
    //             MessageHistory pHistory = new MessageHistory();
    //             JOptionPane.showMessageDialog(null, pHistory, "test", JOptionPane.PLAIN_MESSAGE);
                
    //         }
            
    //     });
    // }

                        
    
    private javax.swing.JLabel jLabel1;
                 
}
