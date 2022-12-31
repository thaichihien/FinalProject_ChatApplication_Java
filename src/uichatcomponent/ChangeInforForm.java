
package uichatcomponent;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import datastructure.UserAccount;
import database.DatabaseManagment;


public class ChangeInforForm extends javax.swing.JFrame {

    private javax.swing.JTextField addressField;
    private com.toedter.calendar.JDateChooser birthDayChooser;
    private javax.swing.JButton updateAccButton;
    private javax.swing.JButton deleteFieldsButton;
    private javax.swing.JTextField emailField;
    private javax.swing.JRadioButton femaleRadio;
    private javax.swing.ButtonGroup genderGroup;
    private javax.swing.JRadioButton maleRadio;
    private javax.swing.JTextField nameField;
    private javax.swing.JTextField usernameField;
    private javax.swing.JButton changePasswordButton;
    
    private UserAccount user;


    // TODO 1: Nạp thông tin account
   
    private void fillAccountInfor(){

        DatabaseManagment dbm=DatabaseManagment.getInstance();
        user =dbm.getDetailAccount(user.getID());

        usernameField.setText(user.getUsername());
        nameField.setText(user.getFullname());
        addressField.setText(user.getAddress());
        emailField.setText(user.getEmail());

        if(user.getGender().equals("Nam")){
            maleRadio.setSelected(true);
        }
        else{
            femaleRadio.setSelected(true);
        }

         // truyền ngày sinh vào datechooser
         SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH); 
         try {
             birthDayChooser.setDate(new Date(formatter.parse(user.getBirthDay()).getTime()));
         } catch (ParseException ex) {
             birthDayChooser.setDate(new Date());
         }

    }


    // TODO 2 : Cập nhật tài khoản:
   
    public void updateThisAccount(){
        String username =usernameField.getText();
        String name=nameField.getText();
        String address=addressField.getText();
        String email =emailField.getText();
        String gender="";
        if(maleRadio.isSelected())
            gender="Nam";
        if(femaleRadio.isSelected())
            gender="Nữ";

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String birthDay = df.format(birthDayChooser.getDate());

        user.setUsername(username);
        user.setFullname(name);
        user.setAddress(address);
        user.setEmail(email);
        user.setGender(gender);
        user.setBirthDay(birthDay);

        DatabaseManagment db=DatabaseManagment.getInstance();
        db.updateAccount(user);
    }


    //  xóa dữ liệu đã nhập ở tất cả các field
    private void clearFields(){

        usernameField.setText("");
        nameField.setText("");
        addressField.setText("");
        emailField.setText("");
        genderGroup.clearSelection();

    }

    // TODO 3: thay đổi mật khẩu 
    // tham khảo DetailAccountForm
    // copy qua cũng được =)

    private void changePassword(){


        DatabaseManagment database = DatabaseManagment.getInstance();
        JPanel changePasswordPanel = new JPanel();
        JLabel oldLabel = new JLabel("Mật khẩu cũ :");
        JTextField oldPasswordField = new JTextField(20);
        JLabel newLabel = new JLabel("Mật khẩu mới :");
        JTextField newPasswordField = new JTextField(20);

        changePasswordPanel.setLayout(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();

        cs.fill = GridBagConstraints.HORIZONTAL;
        cs.insets = new Insets(10, 5, 5, 5);
        cs.gridx = 0;
        cs.gridy = 0;
        changePasswordPanel.add(oldLabel,cs);
        cs.gridx = 1;
        changePasswordPanel.add(oldPasswordField,cs);
        cs.gridx = 0;
        cs.gridy = 1;
        changePasswordPanel.add(newLabel,cs);
        cs.gridx = 1;
        changePasswordPanel.add(newPasswordField,cs);

        if(JOptionPane.showConfirmDialog(null,changePasswordPanel,"Change password",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION){

            // Code here
            String oldPass = oldPasswordField.getText().toString().trim();
            String newPass = newPasswordField.getText().toString().trim();

            if(database.checkPassword(user.getID(), oldPass)){
                database.changePasswordUser(user.getID(), newPass);
                JOptionPane.showMessageDialog(null, "Completed!", "Change password", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            else{
                JOptionPane.showMessageDialog(null, "Old password is incorrect!", "Change password", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }
    }
    
    
    public ChangeInforForm(UserAccount account) {
        initComponents();

        this.user = account;
        updateAccButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateThisAccount();
            }
        });
        deleteFieldsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearFields();
            }
        });

        changePasswordButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                changePassword();
            }
            
        });
        fillAccountInfor();

        
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
            java.util.logging.Logger.getLogger(CreateAccountForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreateAccountForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreateAccountForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreateAccountForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        genderGroup = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jlabel_tendangnhap = new javax.swing.JLabel();
        usernameField = new javax.swing.JTextField();
        jLabel_hotvaten = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        jLabel_diachi = new javax.swing.JLabel();
        addressField = new javax.swing.JTextField();
        jLabel_ngaysinh = new javax.swing.JLabel();
        jLabel_gioitinh = new javax.swing.JLabel();
        jLabel_email = new javax.swing.JLabel();
        emailField = new javax.swing.JTextField();
        jLabel_matkhau = new javax.swing.JLabel();
        femaleRadio = new javax.swing.JRadioButton();
        maleRadio = new javax.swing.JRadioButton();
        updateAccButton = new javax.swing.JButton();
        deleteFieldsButton = new javax.swing.JButton();
        birthDayChooser = new com.toedter.calendar.JDateChooser();
        changePasswordButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cập nhật tài khoản");
        setMinimumSize(new java.awt.Dimension(700, 500));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(700, 500));

        jlabel_tendangnhap.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jlabel_tendangnhap.setForeground(new java.awt.Color(0, 0, 0));
        jlabel_tendangnhap.setText("Tên đăng nhập :");

        jLabel_hotvaten.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel_hotvaten.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_hotvaten.setText("Họ và tên :");

        jLabel_diachi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel_diachi.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_diachi.setText("Địa chỉ :");

        jLabel_ngaysinh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel_ngaysinh.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_ngaysinh.setText("Ngày sinh:");

        jLabel_gioitinh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel_gioitinh.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_gioitinh.setText("Giới tính :");

        jLabel_email.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel_email.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_email.setText("Email :");

        jLabel_matkhau.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel_matkhau.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_matkhau.setText("Mật khẩu :");

        genderGroup.add(femaleRadio);
        femaleRadio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        femaleRadio.setForeground(new java.awt.Color(0, 0, 0));
        femaleRadio.setText("Nữ");

        genderGroup.add(maleRadio);
        maleRadio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        maleRadio.setForeground(new java.awt.Color(0, 0, 0));
        maleRadio.setText("Nam");

        updateAccButton.setText("Hoàn tất");
        

        deleteFieldsButton.setText("Xóa dữ liệu ô");

        changePasswordButton.setText("Thay đổi");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(updateAccButton, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(deleteFieldsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_hotvaten, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlabel_tendangnhap, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(usernameField)
                            .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel_gioitinh, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(maleRadio, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61)
                        .addComponent(femaleRadio, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_diachi, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_ngaysinh, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addressField)
                            .addComponent(birthDayChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_email, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_matkhau, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(emailField)
                            .addComponent(changePasswordButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(117, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlabel_tendangnhap, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_hotvaten, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_diachi, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addressField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel_ngaysinh, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(birthDayChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_gioitinh, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(femaleRadio)
                    .addComponent(maleRadio))
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_email, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_matkhau, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(changePasswordButton))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteFieldsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateAccButton, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(76, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

  

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        
    }//GEN-LAST:event_formWindowClosing

   
    // public static void main(String args[]) {
       
    //    java.awt.EventQueue.invokeLater(new Runnable() {
    //        public void run() {
    //             DatabaseManagment database = DatabaseManagment.getInstance();
    //             UserAccount testAccount = database.getDetailAccount(1);

    //            new ChangeInforForm(testAccount).setVisible(true);
    //        }
    //    });
    // }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel_diachi;
    private javax.swing.JLabel jLabel_email;
    private javax.swing.JLabel jLabel_gioitinh;
    private javax.swing.JLabel jLabel_hotvaten;
    private javax.swing.JLabel jLabel_matkhau;
    private javax.swing.JLabel jLabel_ngaysinh;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jlabel_tendangnhap;
    
    // End of variables declaration//GEN-END:variables
}
