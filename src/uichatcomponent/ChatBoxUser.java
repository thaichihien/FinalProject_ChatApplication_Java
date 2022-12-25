
package uichatcomponent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import chatservice.ChatService;
import datastructure.UserAccount;

/**
 *
 * @author HIEN
 */
public class ChatBoxUser extends ChatBoxLayout implements Runnable{

    UserAccount other;

    private void sendButtonActionPerformed(ActionEvent e){
        String message = inputChatTextArea.getText();
        //ZonedDateTime  myDateObj = ZonedDateTime.now( ZoneId.of("Asia/Ho_Chi_Minh")); 
        Timestamp sendTime =new Timestamp(new Date().getTime());
        String formattedsendTime = new SimpleDateFormat("HH:mm dd-MM-yyyy").format(sendTime);
        addMessage(new ChatMessageBlock(user.getUsername(), formattedsendTime, ChatMessageBlock.MINE, message));
        inputChatTextArea.setText("");
        
        String packetSend = ChatService.createPacket(ChatService.CHAT, other.getID(), message,formattedsendTime);
        user.sendPacket(packetSend);
    }

    @Override
    public void run() {
       while (true) {
            String receiveMessage = user.receivePacket();
            System.out.println(receiveMessage);
            String[] allMessage = ChatService.packetAnalysis(receiveMessage);
            // ID#time#message
            addMessage(new ChatMessageBlock(other.getUsername(), allMessage[2], ChatMessageBlock.OTHER, allMessage[3]));
       }
    }

    public ChatBoxUser(UserAccount me,UserAccount other) {
        super(me);
        this.other = other;
        nameJLabel.setText(other.getUsername());
        setStatusHeader(other.isOnline());
        
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
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendButtonActionPerformed(e);
                
            }
       });
        
       Thread receiveMessageProcess = new Thread(this);
       receiveMessageProcess.start();
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
