
package userchatapp;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;


import uichatcomponent.ListItemChatAccount;
import uichatcomponent.SearchBar;


public class MenuGroup extends JPanel{

    JTextField inputGroupName;
    JTable tableListFriend;
    public ListItemChatAccount<String> listFriendChoosen;
    JButton cancelButton;
    JButton createGroupButton;
    SearchBar searchBarFriend;

    // TODO 1: nạp dữ liệu bảng ListFriend dựa vào searchBarFriend

    public void filltableListFriend(){

    }


    
    public MenuGroup() {
        this.setBackground(new java.awt.Color(255, 255, 255));
        inputGroupName = new JTextField();
        tableListFriend = new JTable();
        cancelButton = new JButton();
        createGroupButton = new JButton();
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
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
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
                        .addGroup(menuGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_taonhom, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                            .addComponent(createGroupButton, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                                            .addComponent(cancelButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addComponent(jlabel_banbedachon, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(inputGroupName, javax.swing.GroupLayout.PREFERRED_SIZE, 1159, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    .addGroup(menuGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(menuGroupLayout.createSequentialGroup()
                            .addComponent(searchBarFriend, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(28, 28, 28)
                            .addComponent(jScrollPane_tableListFriend, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(menuGroupLayout.createSequentialGroup()
                            .addGap(318, 318, 318)
                            .addComponent(createGroupButton, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        
    }
    
}
