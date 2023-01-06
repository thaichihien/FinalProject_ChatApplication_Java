
package uichatcomponent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import chatservice.ChatService;
import database.DatabaseManagment;
import datastructure.GroupChat;
import datastructure.Message;
import datastructure.UserAccount;


public class ChatBoxGroup extends ChatBoxLayout{

    private JButton viewGroupInforButton;
    private GroupChat groupChat;
    

    private void sendMessage(){
        String message = inputChatTextArea.getText();
        //ZonedDateTime  myDateObj = ZonedDateTime.now( ZoneId.of("Asia/Ho_Chi_Minh")); 
        Timestamp sendTime =new Timestamp(new Date().getTime());
        String formattedsendTime = new SimpleDateFormat("HH:mm dd-MM-yyyy").format(sendTime);
        addMessage(new ChatMessageBlock(user.getUsername(), formattedsendTime, ChatMessageBlock.MINE, message));
        inputChatTextArea.setText("");
        
        String packetSend = ChatService.createPacket(ChatService.CHATGROUP, groupChat.getID(), message,formattedsendTime);
        user.sendPacket(packetSend);
    }

    public void addMessage(Message message){
        this.addMessage(new ChatMessageBlock(message.getUserName(), message.getDateSend(), ChatMessageBlock.OTHER, message.getContent()));
    }

    public void viewGroupDetail(){
        DatabaseManagment database = DatabaseManagment.getInstance();
        boolean isAdmin = database.checkAdmin(this.user.getID(), groupChat.getID());
        DetailGroupChatForm detailGroupChatForm = new DetailGroupChatForm(groupChat,isAdmin,this.user.getID());
        detailGroupChatForm.setVisible(true);
    }


    private void findMessageGroup(){
        String keyword = searchBarChat.getText();
        if(keyword.isBlank()){
            return;
        }

        DatabaseManagment database = DatabaseManagment.getInstance();
        ArrayList<Message> messageFound = database.searchMessageGroup(groupChat.getID(), keyword);
        if(messageFound.isEmpty()){
            JOptionPane.showMessageDialog(null, "Message not found", "Not found", JOptionPane.WARNING_MESSAGE);
            return;
        }else{
            MessageHistory displayHistory = new MessageHistory(messageFound,user.getUsername(),keyword);
            JOptionPane.showMessageDialog(null, displayHistory, "All messages found", JOptionPane.PLAIN_MESSAGE);
        }
    }


    public ChatBoxGroup(UserAccount me,GroupChat groupChat) {
        super(me);
        this.groupChat = groupChat;
        nameJLabel.setText(groupChat.getGroupname());
        setStatusHeader(groupChat.getOnline());
        deleteHistoryJLabel.setText("");
        deleteHistoryJLabel.setEnabled(false);
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

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
                
            }
       });
       viewGroupInforButton.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            viewGroupDetail();
        }
        
       });

       searchBarChat.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            findMessageGroup();
        }
        
       });
    }
    
}
