
package userchatapp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import database.DatabaseManagment;
import datastructure.UserAccount;
import uichatcomponent.ListItemChatAccount;
import uichatcomponent.SearchBar;


public class MenuGroup extends JPanel{

    JTextField inputGroupName;
    JTable tableListFriend;
    public ListItemChatAccount<String> listFriendChoosen;
    JButton cancelButton;
    JButton createGroupButton;
    JButton addToGroupButton;
    SearchBar searchBarFriend;  // khi đang nhập nhấn enter để tìm
    UserAccount user;

    
    // TODO 2: cải tiến hàm dựa vào searchBarFriend
    // searchBarFriend trả về chuỗi rỗng thì nạp hết như bình thường
    // sử dụng database.searchFriendList(int ID,String name)
    // trong đó: + ID là user.getID() (ID của người dùng hiện tại)
    //             + name là tên người bạn muôn tìm để thêm vào nhóm

    public void filltableListFriend(){
    	DatabaseManagment database = DatabaseManagment.getInstance();
    	ArrayList<UserAccount> allFriend = database.getFriendArrayList(user.getID());
    	// tableFindFriend is JTable
    	DefaultTableModel tableModel = (DefaultTableModel) tableListFriend.getModel();
    	for(UserAccount friend : allFriend){
    		String username = String.valueOf(friend.getUsername());
    	    String email = friend.getEmail();
    	    String online = String.valueOf(friend.getOnline());  
    	    String row[] = {username,email, online};
    	    tableModel.addRow(row);

    	}
    }

    //TODO 2: tạo nhóm chat
    public void createNewGroupChat(){
        
    }


    
    public MenuGroup(UserAccount account) {
       initComponent();
       this.user = account;

       searchBarFriend.addActionListener(new ActionListener(){

        @Override
        public void actionPerformed(ActionEvent e) {
            filltableListFriend();
            
        }

       });

       filltableListFriend();

        
    }

    private void initComponent(){
        this.setBackground(new java.awt.Color(255, 255, 255));
        inputGroupName = new JTextField();
        tableListFriend = new JTable();
        cancelButton = new JButton();
        createGroupButton = new JButton();
        addToGroupButton = new JButton();
        searchBarFriend = new SearchBar();
        listFriendChoosen = new ListItemChatAccount<>();
//        listFriendChoosen.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
//            @Override
//            public void valueChanged(ListSelectionEvent evt) {
//                listFriendJlistValueChanged(evt);
//            }
//        });
        
        JLabel jLabel_taonhom = new JLabel();
        JLabel jlabel_banbedachon = new JLabel();
        JLabel jlabel_nhaptenhom = new JLabel();
        JLabel jLabel_timkiem = new JLabel();
        
        jLabel_taonhom.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel_taonhom.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_taonhom.setText("Tạo nhóm");
        inputGroupName.setBackground(new java.awt.Color(204, 204, 204));
        inputGroupName.setForeground(new java.awt.Color(51, 51, 51));
        inputGroupName.setText("..");

        jlabel_banbedachon.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlabel_banbedachon.setForeground(new java.awt.Color(51, 51, 51));
        jlabel_banbedachon.setText("Bạn bè đã chọn :");

        jlabel_nhaptenhom.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlabel_nhaptenhom.setForeground(new java.awt.Color(51, 51, 51));
        jlabel_nhaptenhom.setText("Nhập tên nhóm :");

        tableListFriend.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tableListFriend.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                
            },
            new String [] {
                "Username", "Email", "Online"
            }
        ));
        JScrollPane jScrollPane_tableListFriend = new JScrollPane();
        jScrollPane_tableListFriend.setViewportView(tableListFriend);

        jLabel_timkiem.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel_timkiem.setForeground(new java.awt.Color(51, 51, 51));
        jLabel_timkiem.setText("Tìm kiếm bạn vào nhóm :");

        
        
        JScrollPane jScrollPane_listfriendChoosen = new JScrollPane();
        jScrollPane_listfriendChoosen.setViewportView(listFriendChoosen);

        cancelButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        cancelButton.setText("Hủy");
//        cancelButton.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                cancelButtonActionPerformed(evt);
//            }
//        });

        searchBarFriend.setForeground(new java.awt.Color(51, 51, 51));
        searchBarFriend.setBackgroundColor(new java.awt.Color(204, 204, 204));
        searchBarFriend.setPlaceHolder("Tìm kiếm bằng tên...");

        createGroupButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        createGroupButton.setText("Tạo nhóm");
        addToGroupButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        addToGroupButton.setText("Thêm vào nhóm");
//        createGroupButton.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                createGroupButtonActionPerformed(evt);
//            }
//        });
        
        javax.swing.GroupLayout menuGroupLayout = new javax.swing.GroupLayout(this);
        this.setLayout(menuGroupLayout);
        menuGroupLayout.setHorizontalGroup(
            menuGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuGroupLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(menuGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlabel_nhaptenhom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(menuGroupLayout.createSequentialGroup()
                        .addGroup(menuGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane_tableListFriend, javax.swing.GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
                            .addComponent(jLabel_timkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(searchBarFriend, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(67, 67, 67)
                        .addGroup(menuGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(menuGroupLayout.createSequentialGroup()
                                .addComponent(jScrollPane_listfriendChoosen, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addGroup(menuGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(createGroupButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cancelButton, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)))
                            .addComponent(jlabel_banbedachon, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel_taonhom, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(menuGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(addToGroupButton, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(inputGroupName, javax.swing.GroupLayout.PREFERRED_SIZE, 1159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(77, Short.MAX_VALUE))
        );
        menuGroupLayout.setVerticalGroup(
            menuGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuGroupLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel_taonhom, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlabel_nhaptenhom, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(inputGroupName, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(menuGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(menuGroupLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel_timkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(menuGroupLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jlabel_banbedachon, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(menuGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane_listfriendChoosen)
                    .addGroup(menuGroupLayout.createSequentialGroup()
                        .addComponent(searchBarFriend, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jScrollPane_tableListFriend, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuGroupLayout.createSequentialGroup()
                        .addComponent(addToGroupButton, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(createGroupButton, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
    }
    
}
