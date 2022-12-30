
package userchatapp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ListSelectionEvent;


import database.DatabaseManagment;
import datastructure.GroupChat;
import datastructure.Message;
import datastructure.UserAccount;
import uichatcomponent.ChatBoxGroup;
import uichatcomponent.ChatBoxUser;
import uichatcomponent.ChatMessageBlock;
import uichatcomponent.ItemChatAccount;
import uichatcomponent.ListItemChatAccount;


public class MenuChat extends JPanel{
    

    public JButton refreshButton;
    public ListItemChatAccount<String> listFriendJlist;
    public JTabbedPane chatLayout;
    private DatabaseManagment database;
    private UserAccount user;
    private HashMap<String,ChatBoxUser> chatUser;
    private HashMap<Integer,ChatBoxGroup> chatGroup;


    
    public void fillFriendList(){
        
        ArrayList<UserAccount> onlineUser = database.getFriendArrayListByOnline(user.getID());
        ArrayList<Message> allChat = database.getAllMessageFromUser(user.getID());
        ArrayList<GroupChat> onlineGroup = database.getAllGroupChatOnline(user.getID());
        ArrayList<Message> allGroupChat = database.getAllMessageGroupFromUser(user.getID());
        listFriendJlist.clearList();

        // put user online first
        int index = 0;
        for(;index < onlineUser.size();index++){
            if(!onlineUser.get(index).getOnline()) break;
            ItemChatAccount chatAccount = new ItemChatAccount(onlineUser.get(index).getID(),onlineUser.get(index).getUsername(),onlineUser.get(index).getOnline());
            ChatBoxUser chatBoxUser = new ChatBoxUser(user,onlineUser.get(index));
            listFriendJlist.addItem(chatAccount);
            chatLayout.addTab(chatAccount.getName(), chatBoxUser);
            String chatBoxID = ChatBoxUser.createChatBoxUserID(user.getID(), onlineUser.get(index).getID());
            chatUser.put(chatBoxID, chatBoxUser);
        }

        // put group online first
        int indexGroup = 0;
        for(;indexGroup < onlineGroup.size();indexGroup++){
            if(!onlineGroup.get(indexGroup).getOnline()) break;
            ItemChatAccount chatGroupItem = new ItemChatAccount(onlineGroup.get(indexGroup).getID(),onlineGroup.get(indexGroup).getGroupname(),onlineGroup.get(indexGroup).getOnline());
            ChatBoxGroup chatBoxGroup = new ChatBoxGroup(user,onlineGroup.get(indexGroup));
            listFriendJlist.addItem(chatGroupItem);
            chatLayout.addTab(chatGroupItem.getName(), chatBoxGroup);
            chatGroup.put(onlineGroup.get(indexGroup).getID(), chatBoxGroup);
        }

        //put all friend and group remain
        for(;index < onlineUser.size();index++){
            
            ItemChatAccount chatAccount = new ItemChatAccount(onlineUser.get(index).getID(),onlineUser.get(index).getUsername(),onlineUser.get(index).getOnline());
            ChatBoxUser chatBoxUser = new ChatBoxUser(user,onlineUser.get(index));
            listFriendJlist.addItem(chatAccount);
            chatLayout.addTab(chatAccount.getName(), chatBoxUser);
            String chatBoxID = ChatBoxUser.createChatBoxUserID(user.getID(), onlineUser.get(index).getID());
            chatUser.put(chatBoxID, chatBoxUser);
        }

        for(;indexGroup < onlineGroup.size();indexGroup++){
          
            ItemChatAccount chatGroupItem = new ItemChatAccount(onlineGroup.get(indexGroup).getID(),onlineGroup.get(indexGroup).getGroupname(),onlineGroup.get(indexGroup).getOnline());
            ChatBoxGroup chatBoxGroup = new ChatBoxGroup(user,onlineGroup.get(indexGroup));
            listFriendJlist.addItem(chatGroupItem);
            chatLayout.addTab(chatGroupItem.getName(), chatBoxGroup);
            chatGroup.put(onlineGroup.get(indexGroup).getID(), chatBoxGroup);
        }


        // message for user
        for(Message message: allChat){
            ChatMessageBlock messageBlock;
            if(message.getUserName().equals(user.getUsername())){
                messageBlock = new ChatMessageBlock(message.getUserName(), message.getDateSend(), ChatMessageBlock.MINE, message.getContent());
            }
            else{
                messageBlock = new ChatMessageBlock(message.getUserName(), message.getDateSend(), ChatMessageBlock.OTHER, message.getContent());
            }
            if(chatUser.containsKey(message.getChatboxID())){
                chatUser.get(message.getChatboxID()).addMessage(messageBlock);
            }
        }

        for(Message message : allGroupChat){
            ChatMessageBlock messageBlock;
            if(message.getUserName().equals(user.getUsername())){
                messageBlock = new ChatMessageBlock(message.getUserName(), message.getDateSend(), ChatMessageBlock.MINE, message.getContent());
            }
            else{
                messageBlock = new ChatMessageBlock(message.getUserName(), message.getDateSend(), ChatMessageBlock.OTHER, message.getContent());
            }
            if(chatGroup.containsKey(message.getGroupID())){
                chatGroup.get(message.getGroupID()).addMessage(messageBlock);
            }
        }

        if(listFriendJlist.getModel().getSize() > 0){
            chatLayout.setSelectedIndex(0);
        }
    }

   

    public void addMessageToChatboxUser(String[] allMessage){
        String chatBoxID = ChatBoxUser.createChatBoxUserID(user.getID(), Integer.parseInt(allMessage[1]));
        ChatBoxUser chatBoxToDisplay = chatUser.get(chatBoxID);
        Message newMessage = new Message();
        newMessage.setDateSend(allMessage[2]);
        newMessage.setContent(allMessage[3]);
        chatBoxToDisplay.addMessage(newMessage);
    }

    public void addMessageToChatboxGroup(String[] allMessage){
        int chatBoxID = Integer.parseInt(allMessage[1]);
        ChatBoxGroup chatBoxToDisplay = chatGroup.get(chatBoxID);
        Message newMessage = new Message();
        newMessage.setUserName(allMessage[2]);
        newMessage.setDateSend(allMessage[3]);
        newMessage.setContent(allMessage[4]);
        chatBoxToDisplay.addMessage(newMessage);
    }


    public void resetData(){
        System.out.println("reset");
        chatUser.clear();
        chatGroup.clear();
        fillFriendList();
    }

    
    public MenuChat(UserAccount account) {
        initComponents();
        user = account;
        database = DatabaseManagment.getInstance();
        chatUser = new HashMap<>();
        chatGroup = new HashMap<>();
        fillFriendList();

        refreshButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                fillFriendList();
            }
            
        });

        
    }

    private void initComponents(){
        this.setBackground(new java.awt.Color(255, 255, 255));
        this.setLayout(null);
        refreshButton = new JButton();
        chatLayout = new JTabbedPane();
        chatLayout.setBounds(410, -30, 880, 880);   
        
        JPanel sidePanel = new JPanel();
        sidePanel.setBackground(new java.awt.Color(235, 235, 235));
        refreshButton.setFont(new java.awt.Font("Segoe UI", 1, 18));
        refreshButton.setText("Làm mới danh sách bạn bè");
        JScrollPane jScrollPaneListFriend = new JScrollPane();
        listFriendJlist = new ListItemChatAccount<>();
        listFriendJlist.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent evt) {
                listFriendJlistValueChanged(evt);
            }
        });
        jScrollPaneListFriend.setViewportView(listFriendJlist);
        
        
        javax.swing.GroupLayout sidePanelLayout = new javax.swing.GroupLayout(sidePanel);
        sidePanel.setLayout(sidePanelLayout);
        sidePanelLayout.setHorizontalGroup(
            sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidePanelLayout.createSequentialGroup()
                .addGroup(sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sidePanelLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(refreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(sidePanelLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jScrollPaneListFriend, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        sidePanelLayout.setVerticalGroup(
            sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidePanelLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(refreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(jScrollPaneListFriend, javax.swing.GroupLayout.PREFERRED_SIZE, 534, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(176, Short.MAX_VALUE))
        );
        
        
        sidePanel.setBounds(0, 0, 408, 846);
        this.add(sidePanel);
        this.add(chatLayout);
    }
    
    public void addToListFriendJlist(ItemChatAccount item){
        listFriendJlist.addItem(item);
    }
    
    public void listFriendJlistValueChanged(ListSelectionEvent e){
        int index = listFriendJlist.getSelectedIndex();
        chatLayout.setSelectedIndex(index);
        
    }
    
    
    
    
    
}
