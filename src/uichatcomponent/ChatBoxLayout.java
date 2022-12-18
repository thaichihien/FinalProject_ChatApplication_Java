
package uichatcomponent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author HIEN
 */
public class ChatBoxLayout extends JPanel{
    protected JPanel headerJPanel;
    protected JLabel nameJLabel;
    protected JLabel statusJLabel;
    protected JLabel deleteHistoryJLabel;
    protected SearchBar searchBarChat;
    public ChatMessageDisplay displayChat;
    protected JTextArea inputChatTextArea;
    protected JButton sendButton;

    private void sendButtonActionPerformed(ActionEvent e){

    }

    public void addMessage(String message){

        displayChat.addMessage(null);
    }




    public ChatBoxLayout() {
        this.setBackground(new java.awt.Color(255, 255, 255));
        nameJLabel = new JLabel();
        statusJLabel = new JLabel();
        deleteHistoryJLabel = new JLabel();
        searchBarChat = new SearchBar();
        displayChat = new ChatMessageDisplay();
        inputChatTextArea = new JTextArea();
        sendButton = new JButton();
        
        nameJLabel.setFont(new java.awt.Font("Segoe UI", 1, 24));
        statusJLabel.setFont(new java.awt.Font("Segoe UI", 0, 18));
        
        deleteHistoryJLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        deleteHistoryJLabel.setForeground(new java.awt.Color(204, 0, 0));
        deleteHistoryJLabel.setText("Xóa cuộc hội thoại");
        searchBarChat.setPlaceHolder("Tìm kiếm tin nhắn");
        
        headerJPanel = new JPanel();
        headerJPanel.setBackground(new java.awt.Color(217, 217, 217));
    }
    
    
    
    
    public void createInput(JPanel layout){
        inputChatTextArea.setColumns(20);
        inputChatTextArea.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        inputChatTextArea.setLineWrap(true);
        inputChatTextArea.setRows(5);
        inputChatTextArea.setWrapStyleWord(true);
        JScrollPane inputChatScollPane = new JScrollPane();
        inputChatScollPane.setViewportView(inputChatTextArea);

        sendButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        sendButton.setText("Gửi");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendButtonActionPerformed(e);
                
            }
       });
        
        javax.swing.GroupLayout chatBoxUserLayout = new javax.swing.GroupLayout(layout);
        layout.setLayout(chatBoxUserLayout);
        chatBoxUserLayout.setHorizontalGroup(
            chatBoxUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(headerJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(chatBoxUserLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(chatBoxUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(displayChat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(chatBoxUserLayout.createSequentialGroup()
                        .addComponent(inputChatScollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 618, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(sendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        chatBoxUserLayout.setVerticalGroup(
            chatBoxUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chatBoxUserLayout.createSequentialGroup()
                .addComponent(headerJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(displayChat, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(chatBoxUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(chatBoxUserLayout.createSequentialGroup()
                        .addComponent(sendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 156, Short.MAX_VALUE))
                    .addGroup(chatBoxUserLayout.createSequentialGroup()
                        .addComponent(inputChatScollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
    }
    
    public void setStatusHeader(boolean isOnline){
        if(isOnline){
            statusJLabel.setForeground(new java.awt.Color(0, 204, 0));
            statusJLabel.setText("Online");
        }
        else {
            statusJLabel.setForeground(new java.awt.Color(204, 51, 0));
            statusJLabel.setText("Offline");
        }
    }

    
}
