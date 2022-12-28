/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package uichatcomponent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import javax.swing.JFrame;
import database.DatabaseManagment;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import datastructure.UserAccount;
import datastructure.GroupChat;

import utils.Utils;

/**
 *
 * @author HIEN
 */
public class DetailGroupForm extends javax.swing.JFrame {

    private javax.swing.JComboBox<String> filterCombobox;
    private javax.swing.JTable tableListAccount;
    private javax.swing.JLabel tennhomLabel;
    private GroupChat groupChat;

    // TODO 2: nạp vào tất cả thành viên của một nhóm dựa vào ID 
    // sử dụng getAllMemberGroup(int groupID,String where)
    // trong đó groupID lấy từ groupChat.getID()
    // + where chỉ chấp nhận các tham số "admin","member",null (giá trị null không phải chuỗi "null")
    // giá trị của where thì kiểm tra từ filterCombobox
    // Tất cả thành viên thì where = null
    private void filltableListAccount(String selected){
        Utils.clearTable(tableListAccount);
        DatabaseManagment database = DatabaseManagment.getInstance();

        ArrayList<UserAccount> listAccounts = database.getAllMemberGroup(groupChat.getID(), selected);

        DefaultTableModel tableModel = (DefaultTableModel) tableListAccount.getModel();

        for (UserAccount account : listAccounts){
            String username = account.getUsername();
            String fullname = account.getFullname();
            String chucvu = account.getPosition();
            String online = "";
            if(account.isOnline()) online = "online";
            else online = "offline";
            String row[] = {username,fullname,chucvu,online};
            tableModel.addRow(row);
        }
    }



    public DetailGroupForm(GroupChat groupChat) {
        initComponents();
        this.groupChat = groupChat;

        tennhomLabel.setText(groupChat.getGroupname());

        filterCombobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String selected = (String) filterCombobox.getSelectedItem();
                switch (selected) {//check for a match
                    case "Thành viên":
                        filltableListAccount(selected);
                        break;
                    case "Admin":
                        filltableListAccount(selected);
                        break;
                    default:
                        selected = null;
                        filltableListAccount(selected);
                        break;
                }
            }
        });
    }

    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane_tabelListAccount = new javax.swing.JScrollPane();
        tableListAccount = new javax.swing.JTable();
        jLabel_tennhom = new javax.swing.JLabel();
        tennhomLabel = new javax.swing.JLabel();
        filterCombobox = new javax.swing.JComboBox<>();
        jLabel_xemdanhsach = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Chi tiết nhóm");
        setMinimumSize(new java.awt.Dimension(900, 500));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                //formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(900, 500));
        jPanel1.setLayout(null);

        tableListAccount.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                
            },
            new String [] {
                "Tên đăng nhập", "Họ và tên", "Chức vụ","online"
            }
        ));
        jScrollPane_tabelListAccount.setViewportView(tableListAccount);

        jPanel1.add(jScrollPane_tabelListAccount);
        jScrollPane_tabelListAccount.setBounds(20, 90, 840, 440);

        jLabel_tennhom.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel_tennhom.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_tennhom.setText("Tên Nhóm :");
        jPanel1.add(jLabel_tennhom);
        jLabel_tennhom.setBounds(30, 30, 120, 30);

        tennhomLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tennhomLabel.setForeground(new java.awt.Color(0, 0, 0));
        tennhomLabel.setText("<Group Name>");
        jPanel1.add(tennhomLabel);
        tennhomLabel.setBounds(140, 30, 200, 30);

        filterCombobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả thành viên", "Thành viên", "Admin" }));
        jPanel1.add(filterCombobox);
        filterCombobox.setBounds(640, 30, 220, 40);

        jLabel_xemdanhsach.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel_xemdanhsach.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_xemdanhsach.setText("Xem danh sách");
        jPanel1.add(jLabel_xemdanhsach);
        jLabel_xemdanhsach.setBounds(490, 30, 140, 30);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
        // filltableListAccount();
    }// </editor-fold>//GEN-END:initComponents

    // private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
    //    parentFrame.setEnabled(true);
    // }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DetailGroupForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DetailGroupForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DetailGroupForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DetailGroupForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
    //    java.awt.EventQueue.invokeLater(new Runnable() {
    //        public void run() {
    //            new DetailGroupForm().setVisible(true);
    //        }
    //    });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel_xemdanhsach;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jLabel_tennhom;
    private javax.swing.JScrollPane jScrollPane_tabelListAccount;
    
    // End of variables declaration//GEN-END:variables
}
