
package uichatcomponent;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import javax.swing.JButton;
import javax.swing.JOptionPane;

import chatservice.ChatService;
import database.DatabaseManagment;
import datastructure.Message;
import datastructure.UserAccount;

/**
 *
 * @author HIEN
 */
public class ChatBoxUser extends ChatBoxLayout{

    private JButton unfriendButton;
    UserAccount other;



    private void sendMessage(){
        String message = inputChatTextArea.getText();
        if(message.isBlank())return;
        //ZonedDateTime  myDateObj = ZonedDateTime.now( ZoneId.of("Asia/Ho_Chi_Minh")); 
        Timestamp sendTime =new Timestamp(new Date().getTime());
        String formattedsendTime = new SimpleDateFormat("HH:mm dd-MM-yyyy").format(sendTime);
        addMessage(new ChatMessageBlock(user.getUsername(), formattedsendTime, ChatMessageBlock.MINE, message));
        inputChatTextArea.setText("");
        
        String packetSend = ChatService.createPacket(ChatService.CHAT, other.getID(), message,formattedsendTime);
        user.sendPacket(packetSend);
    }

    private void unfriend(){
        String waringMessage = "Are you sure you want to unfriend " + other.getUsername();
        if(JOptionPane.showConfirmDialog(null, waringMessage,"Confirm unfriend",JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION){
            DatabaseManagment database = DatabaseManagment.getInstance();
            database.unfriendUsers(user.getID(), other.getID());
            String packet = ChatService.createPacket(ChatService.CHANGES, other.getID(), ChatService.MENUCHAT, "0");
            user.sendPacket(packet);
        }
    }

    private void findMessageUser(){
        String keyword = searchBarChat.getText();
        if(keyword.isBlank()){
            return;
        }

        DatabaseManagment database = DatabaseManagment.getInstance();
        String chatBoxID = createChatBoxUserID(user.getID(), other.getID());
        ArrayList<Message> messageFound = database.searchMessageUser(chatBoxID, keyword,user.getID());
        if(messageFound.isEmpty()){
            JOptionPane.showMessageDialog(null, "Message not found", "Not found", JOptionPane.WARNING_MESSAGE);
            return;
        }else{
            MessageHistory displayHistory = new MessageHistory(messageFound,user.getUsername(),keyword);
            JOptionPane.showMessageDialog(null, displayHistory, "All messages found", JOptionPane.PLAIN_MESSAGE);
        }

    }


    private void deleteMessage(){
        if(JOptionPane.showConfirmDialog(null, "Are you sure you want to delete all messages with this person (messages can still be visible to the other person) ?", "Confirm delete message", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            String chatBoxUser = createChatBoxUserID(user.getID(), other.getID());
            DatabaseManagment database = DatabaseManagment.getInstance();
            database.deleteMessageUser(chatBoxUser, user.getID(), other.getID());
            JOptionPane.showMessageDialog(null, "Delete message successfully", "Successful", JOptionPane.INFORMATION_MESSAGE);
            String packet = ChatService.createPacket(ChatService.CHANGES, other.getID(), ChatService.MENUCHAT, "0");
            user.sendPacket(packet);
        }
    }
    

    public ChatBoxUser(UserAccount me,UserAccount other) {
        super(me);
        this.other = other;
        nameJLabel.setText(other.getUsername());
        setStatusHeader(other.isOnline());
        
        unfriendButton = new JButton();
        unfriendButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        unfriendButton.setText("Hủy kết bạn");
        
        javax.swing.GroupLayout headerGroupLayout = new javax.swing.GroupLayout(headerJPanel);
        headerJPanel.setLayout(headerGroupLayout);
        headerGroupLayout.setHorizontalGroup(
            headerGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerGroupLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(headerGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(unfriendButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(unfriendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        
        
        createInput(this);
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
                
            }
       });

       unfriendButton.addActionListener(new ActionListener(){

        @Override
        public void actionPerformed(ActionEvent e) {
            unfriend();
            
        }

       });

       searchBarChat.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            findMessageUser();
        }
        
       });
        
       deleteHistoryJLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       deleteHistoryJLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                deleteMessage();
            }
       });

       inputChatTextArea.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			sendMessage();
		}
            
       });
      
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

   public void addMessage(Message message){
        this.addMessage(new ChatMessageBlock(other.getUsername(), message.getDateSend(), ChatMessageBlock.OTHER, message.getContent()));
   }
    
   
    
    
    
}
