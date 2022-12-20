
package userchatapp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import database.DatabaseManagment;
import datastructure.UserAccount;
import uichatcomponent.SearchBar;


public class MenuAddFriend extends JPanel{

    SearchBar searchBarFindFriendinList;
    SearchBar searchBarFindFriend;
    JButton unfriendButton;
    JButton addfriendButton;
    JTable tableFindFriend;
    JTable tableListFriend;
    UserAccount user;
    

    // TODO 1: lấy dữ liệu từ searchBarFindFriend, thực hiện truy vấn tài khoản theo tên tìm kiếm
    // nạp vào bảng tableFindFriend nếu thanh tìm kiếm rỗng thì nạp tất cả vào
    // Hiển thị danh sách tài khoản chưa kết bạn
    public void filltableFindFriend(){
        

    }

    // TODO 2: Tạo kết bạn với tài khoản với id
    public void addNewFriend(int id){

    }

    //TODO 3: lấy dữ liệu từ searchBarFindFriend, thực hiện truy vấn tài khoản theo tên tìm kiếm
    // nạp vào bảng tableFindFriend nếu thanh tìm kiếm rỗng thì nạp tất cả vào
    // Hiển thị danh sách tài khoản đã kết bạn
    public void filltableListFriend(){



    }

    //TODO 4: Thực hiện hủy kết bạn
    public void unfriend(int id){
        
    }

   


    public MenuAddFriend(UserAccount account) {
        this.user = account;
        initComponent();

        //filltableFindFriend();
    }





    private void initComponent(){
       
        this.setBackground(new java.awt.Color(255, 255, 255));
        JLabel jLabel_danhsachbanbe = new JLabel();
        JLabel jLabel_thembanbe = new JLabel();
        searchBarFindFriendinList = new SearchBar();
        searchBarFindFriend = new SearchBar();
        unfriendButton = new JButton();
        addfriendButton = new JButton();
        tableFindFriend = new JTable();
        tableListFriend = new JTable();

        searchBarFindFriend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filltableFindFriend();
                
            }
        });
        searchBarFindFriendinList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              filltableListFriend();
            }
        });


        
        //for view only
        tableFindFriend.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                
            },
            new String [] {
                "username", "fullname", "email", "online"
            }
        ));
        
        tableListFriend.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
              
            },
            new String [] {
                "username", "fullname", "email", "online"
            }
        ));
        
        jLabel_danhsachbanbe.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel_danhsachbanbe.setForeground(new java.awt.Color(51, 51, 51));
        jLabel_danhsachbanbe.setText("Danh sách bạn bè");
        jLabel_thembanbe.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel_thembanbe.setForeground(new java.awt.Color(51, 51, 51));
        jLabel_thembanbe.setText("Thêm bạn bè");
        searchBarFindFriendinList.setForeground(new java.awt.Color(51, 51, 51));
        searchBarFindFriendinList.setBackgroundColor(new java.awt.Color(204, 204, 204));
        searchBarFindFriendinList.setPlaceHolder("Tìm bạn bè bằng tên đăng nhập");
        searchBarFindFriend.setForeground(new java.awt.Color(51, 51, 51));
        searchBarFindFriend.setBackgroundColor(new java.awt.Color(204, 204, 204));
        searchBarFindFriend.setPlaceHolder("Thêm bạn bè bằng tên đăng nhập");
        unfriendButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        unfriendButton.setText("Hủy kết bạn");
        addfriendButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        addfriendButton.setText("Kết bạn");
        
        
        
        JScrollPane jScrollPane_tableFindFriend = new JScrollPane();
        JScrollPane jScrollPane_tableListFriend = new JScrollPane();
        jScrollPane_tableFindFriend.setViewportView(tableFindFriend);
        jScrollPane_tableListFriend.setViewportView(tableListFriend);
        
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
                            .addComponent(searchBarFindFriendinList, javax.swing.GroupLayout.PREFERRED_SIZE, 831, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                        .addComponent(unfriendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane_tableFindFriend)
                    .addComponent(jScrollPane_tableListFriend)
                    .addGroup(menuAddfriendsLayout.createSequentialGroup()
                        .addComponent(searchBarFindFriend, javax.swing.GroupLayout.PREFERRED_SIZE, 823, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(searchBarFindFriend, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addfriendButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane_tableFindFriend, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel_danhsachbanbe, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(menuAddfriendsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchBarFindFriendinList, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(unfriendButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane_tableListFriend, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(61, Short.MAX_VALUE))
        
        // javax.swing.GroupLayout menuAddfriendLayout = new javax.swing.GroupLayout(this);
        // this.setLayout(menuAddfriendLayout);
        // menuAddfriendLayout.setHorizontalGroup(
        //     menuAddfriendLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        //     .addGroup(menuAddfriendLayout.createSequentialGroup()
        //         .addGap(83, 83, 83)
        //         .addGroup(menuAddfriendLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
        //             .addGroup(menuAddfriendLayout.createSequentialGroup()
        //                 .addGroup(menuAddfriendLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        //                     .addComponent(jLabel_danhsachbanbe, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
        //                     .addComponent(jLabel_thembanbe, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
        //                     .addComponent(searchBarFindFriendinList, javax.swing.GroupLayout.PREFERRED_SIZE, 831, javax.swing.GroupLayout.PREFERRED_SIZE))
        //                 .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
        //                 .addComponent(unfriendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
        //             .addComponent(searchBarFindFriend, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        //             .addComponent(jScrollPane_tableFindFriend)
        //             .addComponent(jScrollPane_tableListFriend))
        //         .addContainerGap(76, Short.MAX_VALUE))
        // );
        // menuAddfriendLayout.setVerticalGroup(
        //     menuAddfriendLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        //     .addGroup(menuAddfriendLayout.createSequentialGroup()
        //         .addGap(36, 36, 36)
        //         .addComponent(jLabel_thembanbe, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
        //         .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        //         .addComponent(searchBarFindFriend, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
        //         .addGap(18, 18, 18)
        //         .addComponent(jScrollPane_tableFindFriend, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
        //         .addGap(18, 18, 18)
        //         .addComponent(jLabel_danhsachbanbe, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
        //         .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        //         .addGroup(menuAddfriendLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
        //             .addComponent(searchBarFindFriendinList, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
        //             .addComponent(unfriendButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        //         .addGap(18, 18, 18)
        //         .addComponent(jScrollPane_tableListFriend, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
        //         .addContainerGap(61, Short.MAX_VALUE))
        );
    }
    
}
