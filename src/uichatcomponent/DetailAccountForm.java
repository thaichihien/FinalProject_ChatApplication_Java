/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package uichatcomponent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.JFrame;

import database.DatabaseManagment;
import datastructure.UserAccount;

/**
 *
 * @author HIEN
 */
public class DetailAccountForm extends javax.swing.JFrame {

    private javax.swing.JButton changePasswordButton;
    private javax.swing.JLabel friendListLabel;
    private javax.swing.JButton deleteAccountButton;
    private javax.swing.JToggleButton editorButton;
    private javax.swing.JTextField emailField;
    private javax.swing.JTextField addressField;
    private com.toedter.calendar.JDateChooser birthDayChooser;
    private javax.swing.JRadioButton femaleRadioButton;
    private javax.swing.JTable friendListTable;
    private javax.swing.ButtonGroup genderGroup;
    private javax.swing.JButton lockAccountButton;
    private javax.swing.JRadioButton maleRadioButton;
    private javax.swing.JTextField nameField;
    private javax.swing.JTextField userNameField;
    private javax.swing.JButton viewHistoryButton;

    private UserAccount user;



    // TODO 1: Nạp tất cả dữ liệu người dùng từ user vào các field
    // Sử dụng hàm getDetailAccount(int ID)
    // trong đó ID lấy từ user.getID()
    // TEST NGAY TẠI FILE NÀY (RUN FILE NÀY)
    private void fillAccountInfor(){



        // truyền ngày sinh vào datechooser
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH); 
        try {
            birthDayChooser.setDate(new Date(formatter.parse(user.getBirthDay()).getTime()));
        } catch (ParseException ex) {
            birthDayChooser.setDate(new Date());
        }


    }


    // TODO 2: Nạp vào bảng friendListTable dữ liệu tất cả bạn bè của user
    // Sử dụng hàm getFriendArrayListByOnline(int ID)
    // trong đó ID là user.getID()
    private void fillfriendListTable(){

    }

    private void lockAccount(){

    }

    private void deleteAccount(){
        
    }





    public DetailAccountForm(UserAccount account) {
        initComponents();
        
        this.user = account;
        fillAccountInfor();
        fillfriendListTable();
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
            java.util.logging.Logger.getLogger(DetailAccountForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DetailAccountForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DetailAccountForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DetailAccountForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        genderGroup = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jlabel_tendangnhap = new javax.swing.JLabel();
        userNameField = new javax.swing.JTextField();
        jLabel_hovaten = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        jlabel_diachi = new javax.swing.JLabel();
        addressField = new javax.swing.JTextField();
        jLabel_ngaysinh = new javax.swing.JLabel();
        jLabel_email = new javax.swing.JLabel();
        emailField = new javax.swing.JTextField();
        jLabel_gioitinh = new javax.swing.JLabel();
        femaleRadioButton = new javax.swing.JRadioButton();
        maleRadioButton = new javax.swing.JRadioButton();
        changePasswordButton = new javax.swing.JButton();
        editorButton = new javax.swing.JToggleButton();
        viewHistoryButton = new javax.swing.JButton();
        lockAccountButton = new javax.swing.JButton();
        deleteAccountButton = new javax.swing.JButton();
        birthDayChooser = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        friendListTable = new javax.swing.JTable();
        friendListLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Thông tin chi tiết");
        setMinimumSize(new java.awt.Dimension(900, 500));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(900, 500));
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(235, 235, 235));

        jlabel_tendangnhap.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jlabel_tendangnhap.setForeground(new java.awt.Color(0, 0, 0));
        jlabel_tendangnhap.setText("Tên đăng nhập :");

        jLabel_hovaten.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel_hovaten.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_hovaten.setText("Họ và tên :");

        jlabel_diachi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jlabel_diachi.setForeground(new java.awt.Color(0, 0, 0));
        jlabel_diachi.setText("Địa chỉ :");

        jLabel_ngaysinh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel_ngaysinh.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_ngaysinh.setText("Ngày sinh :");

        jLabel_email.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel_email.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_email.setText("Email :");

        jLabel_gioitinh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel_gioitinh.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_gioitinh.setText("Giới tính :");

        genderGroup.add(femaleRadioButton);
        femaleRadioButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        femaleRadioButton.setForeground(new java.awt.Color(0, 0, 0));
        femaleRadioButton.setText("Nữ");

        genderGroup.add(maleRadioButton);
        maleRadioButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        maleRadioButton.setForeground(new java.awt.Color(0, 0, 0));
        maleRadioButton.setText("Nam");

        changePasswordButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        changePasswordButton.setText("Cập nhật mật khẩu");

        editorButton.setText("Chế độ chỉnh sửa");

        viewHistoryButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        viewHistoryButton.setText(" Xem lịch sử đăng nhập");

        lockAccountButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lockAccountButton.setText("Khóa tài khoản");

        deleteAccountButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        deleteAccountButton.setForeground(new java.awt.Color(204, 0, 0));
        deleteAccountButton.setText("Xóa tài khoản");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jlabel_diachi, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(addressField, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel_hovaten, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jlabel_tendangnhap, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(userNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel_ngaysinh, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel_gioitinh, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(40, 40, 40)
                                        .addComponent(maleRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(51, 51, 51)
                                        .addComponent(femaleRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                        .addGap(30, 30, 30)
                                        .addComponent(birthDayChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel_email, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lockAccountButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(viewHistoryButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(30, 30, 30)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(changePasswordButton, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(deleteAccountButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(133, 133, 133)
                        .addComponent(editorButton, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlabel_tendangnhap, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_hovaten, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlabel_diachi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addressField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel_ngaysinh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(birthDayChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_gioitinh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(femaleRadioButton)
                    .addComponent(maleRadioButton))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_email, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(editorButton, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(viewHistoryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(changePasswordButton, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lockAccountButton, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteAccountButton, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 0, 433, 500);

        friendListTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
              
            },
            new String [] {
                "Tên đăng nhập", "Họ tên", "Giới tính", "Email"
            }
        ));
        jScrollPane1.setViewportView(friendListTable);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(460, 50, 420, 430);

        friendListLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        friendListLabel.setForeground(new java.awt.Color(0, 0, 0));
        friendListLabel.setLabelFor(friendListTable);
        friendListLabel.setText("Danh sách bạn bè");
        jPanel1.add(friendListLabel);
        friendListLabel.setBounds(480, 20, 160, 20);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );


        nameField.setEditable(false);
        addressField.setEditable(false);
        emailField.setEditable(false);
        userNameField.setEditable(false);
        maleRadioButton.setEnabled(false);
        femaleRadioButton.setEnabled(false);


        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        //parentFrame.setEnabled(true);
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        

        /* Create and display the form */
       java.awt.EventQueue.invokeLater(new Runnable() {
           public void run() {
            DatabaseManagment database = DatabaseManagment.getInstance();
            UserAccount testAccount = database.getDetailAccount(1);


               new DetailAccountForm(testAccount).setVisible(true);
           }
       });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel_email;
    private javax.swing.JLabel jLabel_gioitinh;
    private javax.swing.JLabel jLabel_hovaten;
    private javax.swing.JLabel jLabel_ngaysinh;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlabel_diachi;
    private javax.swing.JLabel jlabel_tendangnhap;
    
    // End of variables declaration//GEN-END:variables
}
