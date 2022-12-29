
package uichatcomponent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


import database.DatabaseManagment;
import datastructure.GroupChat;
import datastructure.UserAccount;
import utils.Utils;

public class AddFriendToGroup extends javax.swing.JFrame {

    private GroupChat groupChat;
    private int userID;
    private javax.swing.JButton addToGroupButton;
    private javax.swing.JLabel jLabelGroupName;
    private uichatcomponent.SearchBar searchBarFriend;
    private javax.swing.JTable tabelFriendList;
    private DetailGroupChatForm parentFrame;


    private void filltabelFriendList(){
        Utils.clearTable(tabelFriendList);
        DatabaseManagment database = DatabaseManagment.getInstance();
        String searchFriend = searchBarFriend.getText().trim();
        ArrayList<UserAccount> friendList = null;
        if(!searchFriend.isBlank()){
            friendList = database.getFriendArrayListNotInGroup(userID,groupChat.getID(),searchFriend);
        }
        else{
            friendList = database.getFriendArrayListNotInGroup(userID,groupChat.getID());
        }
        DefaultTableModel tableModel = (DefaultTableModel) tabelFriendList.getModel();
        for(UserAccount friend : friendList){
            String ID = String.valueOf(friend.getID());
            String username = friend.getUsername();
            String fullname = friend.getFullname();
            String online = String.valueOf(friend.getOnline());
            String[] row ={ID,username,fullname,online};
            tableModel.addRow(row);
        }
    }


    public void addMemberToGroup(){
        int row = tabelFriendList.getSelectedRow();
        if(row < 0){
            JOptionPane.showMessageDialog(null, "Please select an account", "Not selected", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String ID = tabelFriendList.getModel().getValueAt(0,0).toString();
        DatabaseManagment database = DatabaseManagment.getInstance();
        database.addNewMemberToGroup(groupChat.getID(), Integer.parseInt(ID));
        JOptionPane.showMessageDialog(null, "Add friend to group successfully", "Successful", JOptionPane.INFORMATION_MESSAGE);
        filltabelFriendList();
        parentFrame.fillTableMember();
    }



    public AddFriendToGroup(DetailGroupChatForm parentFrame,GroupChat groupChat,int userID) {
        initComponents();
        this.parentFrame = parentFrame;
        this.groupChat = groupChat;
        this.userID = userID;
        jLabelGroupName.setText(groupChat.getGroupname());
        searchBarFriend.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               filltabelFriendList();
            }
        });
        addToGroupButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addMemberToGroup();
            }
            
        });

        filltabelFriendList();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddFriendToGroup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddFriendToGroup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddFriendToGroup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddFriendToGroup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        jPanel1 = new javax.swing.JPanel();
        searchBarFriend = new uichatcomponent.SearchBar();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelFriendList = new javax.swing.JTable();
        addToGroupButton = new javax.swing.JButton();
        jLabelGroupName = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        
        searchBarFriend.setAutoscrolls(false);
        searchBarFriend.setBackgroundColor(new java.awt.Color(204, 204, 204));
        searchBarFriend.setPlaceHolder("Nhập tên bạn bè...");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Thêm bạn bè vào nhóm");

        tabelFriendList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                
            },
            new String [] {
                "ID", "username", "fullname", "online"
            }
        ));
        jScrollPane1.setViewportView(tabelFriendList);

        addToGroupButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        addToGroupButton.setText("Thêm vào nhóm");

        jLabelGroupName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelGroupName.setForeground(new java.awt.Color(0, 0, 0));
        jLabelGroupName.setText("<tên nhóm>");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(searchBarFriend, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addToGroupButton, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 702, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelGroupName, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(24, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelGroupName))
                        .addGap(10, 10, 10)
                        .addComponent(searchBarFriend, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(addToGroupButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
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
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       

        /* Create and display the form */
        // java.awt.EventQueue.invokeLater(new Runnable() {
        //     public void run() {
        //         new AddFriendToGroup().setVisible(true);
        //     }
        // });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;

    // End of variables declaration//GEN-END:variables
}
