
package userchatapp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import chatservice.ChatService;
import database.DatabaseManagment;
import datastructure.GroupChat;
import datastructure.UserAccount;
import uichatcomponent.ItemChatAccount;
import uichatcomponent.ListItemChatAccount;
import uichatcomponent.SearchBar;
import utils.Utils;

public class MenuGroup extends JPanel {

    JTextField inputGroupName;
    JTable tableListFriend;
    public ListItemChatAccount<String> listFriendChoosen;
    JButton cancelButton;
    JButton createGroupButton;
    JButton addToGroupButton;
    SearchBar searchBarFriend; // khi đang nhập nhấn enter để tìm
    UserAccount user;

    public void filltableListFriend() {
        Utils.clearTable(tableListFriend);
        DatabaseManagment database = DatabaseManagment.getInstance();
        ArrayList<UserAccount> allFriend;
        String name = searchBarFriend.getText();
        if (!name.isEmpty()) {
            allFriend = database.searchFriendList(user.getID(), name);
        } else {
            allFriend = database.getFriendArrayList(user.getID());
        }

        DefaultTableModel tableModel = (DefaultTableModel) tableListFriend.getModel();
        for (UserAccount friend : allFriend) {
            String id = String.valueOf(friend.getID());
            String username = String.valueOf(friend.getUsername());
            String fullname = String.valueOf(friend.getFullname());
            String online = String.valueOf(friend.getOnline());
            String row[] = { id, username, fullname, online,"" };
            tableModel.addRow(row);
        }
        searchBarFriend.setText("");

    }

    public void addToGroup() {
        int row = tableListFriend.getSelectedRow();
        if(row < 0){    // Cảnh báo chưa chọn dòng nào trong bảng
             JOptionPane.showMessageDialog(null, "Please select an account", "Not selected", JOptionPane.WARNING_MESSAGE);
             return;
        }

        String isChoosen = tableListFriend.getModel().getValueAt(row, 4).toString();
        if(isChoosen.equals("Đã chọn")){
            JOptionPane.showMessageDialog(null, "This account has been selected to the group", "Already selected", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String friendIDString = tableListFriend.getModel().getValueAt(row, 0).toString();
        String username = tableListFriend.getModel().getValueAt(row, 1).toString();
        String status = tableListFriend.getModel().getValueAt(row, 3).toString();
        boolean isonline = false;
        int friendID = Integer.parseInt(friendIDString);
        if (status.equals("true"))
            isonline = true;
        listFriendChoosen.addItem(new ItemChatAccount(friendID, username, isonline));
        tableListFriend.getModel().setValueAt("Đã chọn", row, 4);
    }

    public void createNewGroupChat() {
        String groupName = inputGroupName.getText();
        if (groupName.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Group name is empty", "Input a group name",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        ArrayList<UserAccount> member = listFriendChoosen.getAllItem();
        ArrayList<UserAccount> admin = new ArrayList<>();
        admin.add(this.user);
        DatabaseManagment database = DatabaseManagment.getInstance();
        GroupChat groupChat = new GroupChat();
        groupChat.setMembers(member);
        groupChat.setGroupname(groupName);
        groupChat.setAdmins(admin);
        database.addNewGroup(groupChat);
        String packet = ChatService.createPacket(ChatService.CHANGES, 0, ChatService.MENUCHAT, "0");
        user.sendPacket(packet);
        JOptionPane.showMessageDialog(null, "Created chat group successfully", "Created chat group",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public MenuGroup(UserAccount account) {
        initComponent();
        this.user = account;

        searchBarFriend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filltableListFriend();

            }

        });

        addToGroupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addToGroup();
            }

        });

        createGroupButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createNewGroupChat();
            }
        });

        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listFriendChoosen.clearList();
            }
        });

        filltableListFriend();

    }

    private void initComponent() {
        this.setBackground(new java.awt.Color(255, 255, 255));
        inputGroupName = new JTextField();
        tableListFriend = new JTable();
        cancelButton = new JButton();
        createGroupButton = new JButton();
        addToGroupButton = new JButton();
        searchBarFriend = new SearchBar();
        listFriendChoosen = new ListItemChatAccount<>();
        // listFriendChoosen.addListSelectionListener(new
        // javax.swing.event.ListSelectionListener() {
        // @Override
        // public void valueChanged(ListSelectionEvent evt) {
        // listFriendJlistValueChanged(evt);
        // }
        // });

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
                new Object[][] {

                },
                new String[] {
                        "id", "Username", "Email", "Online","Đã chọn"
                }));
        JScrollPane jScrollPane_tableListFriend = new JScrollPane();
        jScrollPane_tableListFriend.setViewportView(tableListFriend);

        jLabel_timkiem.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel_timkiem.setForeground(new java.awt.Color(51, 51, 51));
        jLabel_timkiem.setText("Tìm kiếm bạn vào nhóm :");

        JScrollPane jScrollPane_listfriendChoosen = new JScrollPane();
        jScrollPane_listfriendChoosen.setViewportView(listFriendChoosen);

        cancelButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        cancelButton.setText("Hủy");

        searchBarFriend.setForeground(new java.awt.Color(51, 51, 51));
        searchBarFriend.setBackgroundColor(new java.awt.Color(204, 204, 204));
        searchBarFriend.setPlaceHolder("Tìm kiếm bằng tên...");

        createGroupButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        createGroupButton.setText("Tạo nhóm");
        addToGroupButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        addToGroupButton.setText("Thêm vào nhóm");

        javax.swing.GroupLayout menuGroupLayout = new javax.swing.GroupLayout(this);
        this.setLayout(menuGroupLayout);
        menuGroupLayout.setHorizontalGroup(
                menuGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(menuGroupLayout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(menuGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jlabel_nhaptenhom, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(menuGroupLayout.createSequentialGroup()
                                                .addGroup(menuGroupLayout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                false)
                                                        .addComponent(jScrollPane_tableListFriend,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, 554,
                                                                Short.MAX_VALUE)
                                                        .addComponent(jLabel_timkiem,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 238,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(searchBarFriend,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(67, 67, 67)
                                                .addGroup(menuGroupLayout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(menuGroupLayout.createSequentialGroup()
                                                                .addComponent(jScrollPane_listfriendChoosen,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 287,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(26, 26, 26)
                                                                .addGroup(menuGroupLayout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING,
                                                                        false)
                                                                        .addComponent(createGroupButton,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                        .addComponent(cancelButton,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                225, Short.MAX_VALUE)))
                                                        .addComponent(jlabel_banbedachon,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 163,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(jLabel_taonhom, javax.swing.GroupLayout.PREFERRED_SIZE, 205,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(menuGroupLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(addToGroupButton, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(inputGroupName, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        1159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(77, Short.MAX_VALUE)));
        menuGroupLayout.setVerticalGroup(
                menuGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(menuGroupLayout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel_taonhom, javax.swing.GroupLayout.PREFERRED_SIZE, 42,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jlabel_nhaptenhom, javax.swing.GroupLayout.PREFERRED_SIZE, 22,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(inputGroupName, javax.swing.GroupLayout.PREFERRED_SIZE, 41,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(menuGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(menuGroupLayout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel_timkiem, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(menuGroupLayout.createSequentialGroup()
                                                .addGap(26, 26, 26)
                                                .addComponent(jlabel_banbedachon,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 22,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(menuGroupLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jScrollPane_listfriendChoosen)
                                        .addGroup(menuGroupLayout.createSequentialGroup()
                                                .addComponent(searchBarFriend, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(28, 28, 28)
                                                .addComponent(jScrollPane_tableListFriend,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 417,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuGroupLayout
                                                .createSequentialGroup()
                                                .addComponent(addToGroupButton, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(createGroupButton, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 58,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(40, Short.MAX_VALUE)));
    }

}
