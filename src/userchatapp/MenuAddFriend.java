
package userchatapp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import chatservice.ChatService;
import database.DatabaseManagment;
import datastructure.UserAccount;
import uichatcomponent.SearchBar;

import utils.Utils;


public class MenuAddFriend extends JPanel{

    SearchBar searchBarFindFriendRequest;
    SearchBar searchBarFindNewFriend;
    JButton responeRequestButton;
    JButton addfriendButton;
    JTable tableFindFriend;
    JTable tableListFriendRequest;
    
    UserAccount user;
    ArrayList<UserAccount> listAccountFindFriends;
    ArrayList<UserAccount> listAccountRequestFriends;

    
    public void filltableFindFriend(){
        String searchName;

        Utils.clearTable(tableFindFriend);

        DatabaseManagment database = DatabaseManagment.getInstance();

        if(searchBarFindNewFriend.getText().toString().trim().length() > 0){
            searchName = searchBarFindNewFriend.getText().toString().trim();
        }
        else{
            searchName = "";
        }  
        try {
            ArrayList<UserAccount> listAccounts = database.searchAccountsNotFriend(user.getID(), searchName);
            DefaultTableModel tableModel = (DefaultTableModel) tableFindFriend.getModel();
            listAccountFindFriends = listAccounts;

            for (UserAccount account : listAccounts){
                String ID = String.valueOf(account.getID());
                String username = account.getUsername();
                String fullname = account.getFullname();
                String email = account.getEmail();
                String online = "";
                if(account.isOnline()) online = "online";
                else online = "offline";
                String row[] = {ID,username,fullname,email,online};
                tableModel.addRow(row);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void filltableListFriendRequest(){

        String searchName;

        Utils.clearTable(tableListFriendRequest);

        DatabaseManagment database = DatabaseManagment.getInstance();

        if(searchBarFindFriendRequest.getText().toString().trim().length() > 0){
            searchName = searchBarFindFriendRequest.getText().toString().trim();
            try {
                ArrayList<UserAccount> listFriendRequests = database.getAllFriendRequest(user.getID(), searchName);

                DefaultTableModel tableModel = (DefaultTableModel) tableListFriendRequest.getModel();
    
                for (UserAccount account : listFriendRequests){
                    String ID = String.valueOf(account.getID());
                    String username = account.getUsername();
                    String fullname = account.getFullname();
                    String email = account.getEmail();
                    String online = "";
                    if(account.isOnline()) online = "online";
                    else online = "offline";
                    String row[] = {ID,username,fullname,email,online};
                    tableModel.addRow(row);
                }
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
        else{
            try {
                ArrayList<UserAccount> listFriendRequests;
                listFriendRequests = database.getAllFriendRequest(user.getID());
                DefaultTableModel tableModel = (DefaultTableModel) tableListFriendRequest.getModel();
                listAccountRequestFriends = listFriendRequests;
                for (UserAccount account : listFriendRequests){
                    String ID = String.valueOf(account.getID());
                    String username = account.getUsername();
                    String fullname = account.getFullname();
                    String email = account.getEmail();
                    String online = "";
                    if(account.isOnline()) online = "online";
                    else online = "offline";
                    String row[] = {ID,username,fullname,email,online};
                    tableModel.addRow(row);
                }
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
        
    }


    //TODO 1: Thực hiện gửi lời mời kết bạn
    
    private void sendFriendRequest(){
        int row = tableFindFriend.getSelectedRow();
        if(row < 0){    // Cảnh báo chưa chọn dòng nào trong bảng
             JOptionPane.showMessageDialog(null, "Please select an account", "Not selected", JOptionPane.WARNING_MESSAGE);
             return;
        }
        String IDChoosed = tableFindFriend.getModel().getValueAt(row, 0).toString();
        DatabaseManagment database = DatabaseManagment.getInstance();
        int IDRequest = Integer.parseInt(IDChoosed);
        database.createFriendRequest(user.getID(), IDRequest);
        JOptionPane.showMessageDialog(null, "Send friend request successfully", "Successful", JOptionPane.INFORMATION_MESSAGE);
        String packket = ChatService.createPacket(ChatService.CHANGES, IDRequest, ChatService.MENUCHAT, "0");
        user.sendPacket(packket);
        filltableFindFriend();
    }


    // TODO 2: xử lý lời mời kết bạn
    private void handleFriendRequest(){
        int row = tableListFriendRequest.getSelectedRow();
        if(row < 0){    // Cảnh báo chưa chọn dòng nào trong bảng
             JOptionPane.showMessageDialog(null, "Please select an account", "Not selected", JOptionPane.WARNING_MESSAGE);
             return;
        }
        int idFriendFrom;
        String IDChoosed = tableListFriendRequest.getModel().getValueAt(row, 0).toString();
        DatabaseManagment database = DatabaseManagment.getInstance();
        idFriendFrom = Integer.parseInt(IDChoosed);
        int response = JOptionPane.showConfirmDialog(this, "Would you like to accept this friend request?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION){
            database.setResponeToRequest(idFriendFrom,user.getID(), "ACCEPTED");
            database.addFriendToUser(user.getID(), idFriendFrom);
            String packet = ChatService.createPacket(ChatService.CHANGES, idFriendFrom, ChatService.MENUCHAT, "0");
            user.sendPacket(packet);
        }
        else{
            database.setResponeToRequest(idFriendFrom,user.getID() , "DENIED");
        }

        //send packet changes to server
        
        filltableFindFriend();
        filltableListFriendRequest();
    }


    public MenuAddFriend(UserAccount account) {
        this.user = account;
        initComponent();

        searchBarFindNewFriend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filltableFindFriend();
            }
        });
        searchBarFindFriendRequest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              filltableListFriendRequest();
            }
        });

        addfriendButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                
                sendFriendRequest();

            }

        });

        responeRequestButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                
                handleFriendRequest();
            }

        });

        filltableFindFriend();
        filltableListFriendRequest();
    }





    private void initComponent(){
       
        this.setBackground(new java.awt.Color(255, 255, 255));
        JLabel jLabel_danhsachbanbe = new JLabel();
        JLabel jLabel_thembanbe = new JLabel();
        searchBarFindFriendRequest = new SearchBar();
        searchBarFindNewFriend = new SearchBar();
        responeRequestButton = new JButton();
        addfriendButton = new JButton();
        tableFindFriend = new JTable();
        tableListFriendRequest = new JTable();

        
        //for view only
        tableFindFriend.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                
            },
            new String [] {
                "ID","username", "fullname", "email", "online"
            }
        ));
        
        tableListFriendRequest.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
              
            },
            new String [] {
                "ID","username", "fullname", "email", "online"
            }
        ));
        
        jLabel_danhsachbanbe.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel_danhsachbanbe.setForeground(new java.awt.Color(51, 51, 51));
        jLabel_danhsachbanbe.setText("Lời mời kết bạn");
        jLabel_thembanbe.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel_thembanbe.setForeground(new java.awt.Color(51, 51, 51));
        jLabel_thembanbe.setText("Thêm bạn bè");
        searchBarFindFriendRequest.setForeground(new java.awt.Color(51, 51, 51));
        searchBarFindFriendRequest.setBackgroundColor(new java.awt.Color(204, 204, 204));
        searchBarFindFriendRequest.setPlaceHolder("Tìm lời mời bằng tên đăng nhập");
        searchBarFindNewFriend.setForeground(new java.awt.Color(51, 51, 51));
        searchBarFindNewFriend.setBackgroundColor(new java.awt.Color(204, 204, 204));
        searchBarFindNewFriend.setPlaceHolder("Thêm bạn bè bằng tên đăng nhập");
        responeRequestButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        responeRequestButton.setText("Trả lời lời mời");
        addfriendButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        addfriendButton.setText("Kết bạn");
        
        
        
        JScrollPane jScrollPane_tableFindFriend = new JScrollPane();
        JScrollPane jScrollPane_tableListFriend = new JScrollPane();
        jScrollPane_tableFindFriend.setViewportView(tableFindFriend);
        jScrollPane_tableListFriend.setViewportView(tableListFriendRequest);
        
        javax.swing.GroupLayout menuAddfriendsLayout = new javax.swing.GroupLayout(this);
        this.setLayout(menuAddfriendsLayout);
        menuAddfriendsLayout.setHorizontalGroup(
            menuAddfriendsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuAddfriendsLayout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addGroup(menuAddfriendsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(menuAddfriendsLayout.createSequentialGroup()
                        .addGroup(menuAddfriendsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_danhsachbanbe, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_thembanbe, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(searchBarFindFriendRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 831, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                        .addComponent(responeRequestButton, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane_tableFindFriend)
                    .addComponent(jScrollPane_tableListFriend)
                    .addGroup(menuAddfriendsLayout.createSequentialGroup()
                        .addComponent(searchBarFindNewFriend, javax.swing.GroupLayout.PREFERRED_SIZE, 823, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addfriendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(76, Short.MAX_VALUE))
        );
        menuAddfriendsLayout.setVerticalGroup(
            menuAddfriendsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuAddfriendsLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel_thembanbe, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(menuAddfriendsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchBarFindNewFriend, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addfriendButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane_tableFindFriend, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel_danhsachbanbe, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(menuAddfriendsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchBarFindFriendRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(responeRequestButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane_tableListFriend, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(61, Short.MAX_VALUE))
       
        );
    }
    
}
