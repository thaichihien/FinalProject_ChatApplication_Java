
package uichatcomponent;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import database.DatabaseManagment;
import datastructure.LoginHistory;
import datastructure.UserAccount;
import utils.Utils;

public class ViewLoginHistory extends javax.swing.JFrame {

    
    private UserAccount user;


    private void filltableLoginHistory(){
        Utils.clearTable(tableLoginHistory);
        DatabaseManagment database = DatabaseManagment.getInstance();
        ArrayList<LoginHistory> histories = database.getAllLoginHistoryUser(user.getID());
        DefaultTableModel tableModel = (DefaultTableModel) tableLoginHistory.getModel();
        int index = 1;
        for(LoginHistory history : histories){
            String stt = String.valueOf(index);
            String username = this.user.getUsername();
            String time = history.getLoginTime();
            String[] row = {stt,username,time};
            tableModel.addRow(row);
            index += 1;
        }

    }

    public ViewLoginHistory(UserAccount user) {
        initComponents();
        this.user = user;
        jLabelUsername.setText(user.getUsername());
        filltableLoginHistory();
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
            java.util.logging.Logger.getLogger(ViewLoginHistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewLoginHistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewLoginHistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewLoginHistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableLoginHistory = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabelUsername = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Xem lịch sử đăng nhập");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tableLoginHistory.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tableLoginHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "STT", "username", "thời gian đăng nhập"
            }
        ));
        jScrollPane1.setViewportView(tableLoginHistory);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Lịch sử đăng nhập của tài khoản");

        jLabelUsername.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelUsername.setForeground(new java.awt.Color(0, 0, 0));
        jLabelUsername.setText("<tên tài khoản>");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 575, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabelUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
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

   
    // public static void main(String args[]) {
       
    //     java.awt.EventQueue.invokeLater(new Runnable() {
    //         public void run() {
    //             DatabaseManagment database = DatabaseManagment.getInstance();
    //             UserAccount testAccount = database.getDetailAccount(1);

    //             new ViewLoginHistory(testAccount).setVisible(true);
    //         }
    //     });
    // }

    // Variables declaration - do not modify                     
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelUsername;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableLoginHistory;
    // End of variables declaration                   
}
