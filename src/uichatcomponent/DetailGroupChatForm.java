
package uichatcomponent;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Group;
import javax.swing.table.DefaultTableModel;

import database.DatabaseManagment;
import datastructure.GroupChat;
import datastructure.UserAccount;
import utils.Utils;


public class DetailGroupChatForm extends javax.swing.JFrame {

    private javax.swing.JButton addMemberButton;
    private javax.swing.JButton changeGroupNameButton;
    private javax.swing.JButton enableAdminButton;
    private javax.swing.JLabel jLabelGroupName;

    private javax.swing.JButton removeMemberButton;
    private uichatcomponent.SearchBar searchBarFriendGroup;
    private javax.swing.JTable tableMemberGroup;

   
    private GroupChat groupChat;
    private boolean isAdmin;


    public void fillTableMember(){
        Utils.clearTable(tableMemberGroup);
        DatabaseManagment database = DatabaseManagment.getInstance();
        ArrayList<UserAccount> members = database.getAllGroupMembers(groupChat.getID());
        DefaultTableModel tableModel = (DefaultTableModel) tableMemberGroup.getModel();
        for (UserAccount member : members) {
            String id = String.valueOf(member.getID());
            String username = member.getUsername();
            String fullname = member.getFullname();
            String online = String.valueOf(member.getOnline());
            String position = member.getPosition();
            String row[] = { id, username, fullname, position,online };
            tableModel.addRow(row);
        }
    }

    // TEST Ở FILE NÀY (RUN FILE NÀY)
    // GROUP CHAT ĐANG TEST LÀ GROUP1 VỚI ID = 1 (Ở HÀM MAIN)

    // TODO 1: Gán quyền admin
    // - Kiểm tra xem có account đang xem có phải admin (kiểm tra biến isAdmin)
    // - lấy ID của row đang được chọn trong tableMemberGroup (Gợi ý tìm hiểu getValueAt của Jtable model 
    //         hoặc xem ví dụ hàm addToGroup của MenuGroup)
    // - Dùng JOptionPane để hỏi người dùng có chắc chắn k, không thì return
    // Chia 2 TH : + Nếu account chọn đang là member thì gán quyền admin:
    //             + Sử dụng  database.assignAdminToUser(int ID,int groupID)
    //              + Trong đó ID là ID vừa lấy của row selected, groupId là groupChat.getID()
    //             + Nếu account chọn đang là admin thì gán quyền member:
    //              + Sử dụng assignMemberToUser(int ID,int groupID) tham số tương tự trên
    // - Gọi hàm fillTableMember()
    public void enableAdmin(){

    }

    public void addMemberToGroup(){

    }

    // TODO 2: Thay đổi tên nhóm
    //      + Kiểm tra biến IsAdmin, nếu false thì return
    //      + kiểm tra Jtextfield, nếu trống thì hiện JOptionPane cảnh báo
    //      +Thay đổi tên nhóm theo Jtextfield sử dụng setNewGroupName(String name,int groupID)
    //      + Với name là tên nhóm mới, groupID là groupChat.getID()
    //      +Thay đổi tên nhóm ở jLabelGroupName
    
    public void changeGroupName(){
        JPanel changeGroupPanel = new JPanel();
        JLabel changeLabel = new JLabel("Tên nhóm mới :");
        JTextField newGroupNameField = new JTextField(20);
       

        changeGroupPanel.setLayout(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();

        cs.fill = GridBagConstraints.HORIZONTAL;
        cs.insets = new Insets(10, 5, 5, 5);
        cs.gridx = 0;
        cs.gridy = 0;
        changeGroupPanel.add(changeLabel,cs);
        cs.gridx = 1;
        changeGroupPanel.add(newGroupNameField,cs);
       
        if(JOptionPane.showConfirmDialog(null,changeGroupPanel,"Change group name",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION){

            // Code here

        }

    }


    // TODO 3: // TODO 1: Gán quyền admin
    // - Kiểm tra xem có account đang xem có phải admin (kiểm tra biến isAdmin)
    // - lấy ID của row đang được chọn trong tableMemberGroup (Gợi ý tìm hiểu getValueAt của Jtable model 
    //         hoặc xem ví dụ hàm addToGroup của MenuGroup)
    // - Dùng JOptionPane để hỏi người dùng có chắc chắn k, không thì return
    // Nếu có thì thực hiện xóa account được chọn sử dụng removeMemberFromGroup(int groupID,int ID)
    // - Gọi hàm fillTableMember()
    public void removeMember(){

    }



    public DetailGroupChatForm(GroupChat groupChat,boolean isAdmin) {
        initComponents();
       
        this.groupChat = groupChat;
        this.isAdmin = isAdmin;

        changeGroupNameButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                changeGroupName();
            }
            
        });
        removeMemberButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeMember();
            }
        });

        jLabelGroupName.setText(groupChat.getGroupname());
        fillTableMember();
    }

                            
    private void initComponents() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DetailGroupChatForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DetailGroupChatForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DetailGroupChatForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DetailGroupChatForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableMemberGroup = new javax.swing.JTable();
        jLabel_danhsachthanhvien = new javax.swing.JLabel();
        searchBarFriendGroup = new uichatcomponent.SearchBar();
        addMemberButton = new javax.swing.JButton();
        removeMemberButton = new javax.swing.JButton();
        changeGroupNameButton = new javax.swing.JButton();
        jLabelGroupName = new javax.swing.JLabel();
        enableAdminButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Thông tin nhóm");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tableMemberGroup.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "ID", "username", "fullname", "position","online"
            }
        ));
        jScrollPane1.setViewportView(tableMemberGroup);

        jLabel_danhsachthanhvien.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel_danhsachthanhvien.setForeground(new java.awt.Color(51, 51, 51));
        jLabel_danhsachthanhvien.setText("Danh sách thành viên nhóm");

        searchBarFriendGroup.setForeground(new java.awt.Color(51, 51, 51));
        searchBarFriendGroup.setBackgroundColor(new java.awt.Color(204, 204, 204));
        searchBarFriendGroup.setPlaceHolder("Tìm kiếm theo tên...");

        addMemberButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        addMemberButton.setText("Thêm thành viên");

        removeMemberButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        removeMemberButton.setText("Xóa thành viên");
       

        changeGroupNameButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        changeGroupNameButton.setText("Đổi tên nhóm");

        jLabelGroupName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelGroupName.setForeground(new java.awt.Color(0, 0, 0));
        jLabelGroupName.setText("<tên nhóm>");

        enableAdminButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        enableAdminButton.setText("Chỉnh quyền admin");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel_danhsachthanhvien)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelGroupName, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(searchBarFriendGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(removeMemberButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(addMemberButton, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(changeGroupNameButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(enableAdminButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(33, 33, 33))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_danhsachthanhvien, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelGroupName, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addMemberButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(changeGroupNameButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchBarFriendGroup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(removeMemberButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(enableAdminButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>                        

                                             

    private void formWindowClosing(java.awt.event.WindowEvent evt) {                                   
        //parentFrame.setEnabled(true);
    }                                  

    
    public static void main(String args[]) {
       
       java.awt.EventQueue.invokeLater(new Runnable() {
           public void run() {
            DatabaseManagment database = DatabaseManagment.getInstance();
            GroupChat testGroup = database.getDetailGroupChat(1);

               new DetailGroupChatForm(testGroup,true).setVisible(true);
           }
       });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jLabel_danhsachthanhvien;
    // End of variables declaration                   
}
