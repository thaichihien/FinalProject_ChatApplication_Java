
package uichatcomponent;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ArrayList;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javax.swing.table.DefaultTableModel;

import adminchatapp.MenuAccountManager;
import database.DatabaseManagment;
import datastructure.UserAccount;


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
    private MenuAccountManager menu;
    private boolean isEdit;

    
    // TEST NGAY TẠI FILE NÀY (RUN FILE NÀY)
    // Nạp dữ liệu account
    private void fillAccountInfor(){
        DatabaseManagment databaseManagment = DatabaseManagment.getInstance();

        user = databaseManagment.getDetailAccount(user.getID());

        userNameField.setText(user.getUsername());
        nameField.setText(user.getFullname());
        addressField.setText(user.getAddress());
        emailField.setText(user.getEmail());

        if(user.getGender().equals("Nam")){
            maleRadioButton.setSelected(true);
        }
        else{
            femaleRadioButton.setSelected(true);
        }


        // truyền ngày sinh vào datechooser
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH); 
        try {
            birthDayChooser.setDate(new Date(formatter.parse(user.getBirthDay()).getTime()));
        } catch (ParseException ex) {
            birthDayChooser.setDate(new Date());
        }


    }


    
    private void fillfriendListTable(){

        DatabaseManagment database = DatabaseManagment.getInstance();
        
        try {
            ArrayList<UserAccount> listAccounts = database.getFriendArrayListByOnline(user.getID());

            DefaultTableModel tableModel = (DefaultTableModel) friendListTable.getModel();

            // tableModel.setRowCount(0);

            for (UserAccount account : listAccounts){
                String username = account.getUsername();
                String fullname = account.getFullname();
                String email = account.getEmail();
                String gender = account.getGender();
                String row[] = {username,fullname,gender,email};
                tableModel.addRow(row);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

    }


    // TODO 3: thay đổi mật khẩu (done)
   
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

    

    // TODO 1: Khóa tài khoản
   
    private void lockAccount(){
        DatabaseManagment database = DatabaseManagment.getInstance();
        if(database.checkAccountIsBanned(user.getID())){
            if(JOptionPane.showConfirmDialog(this, "Are you sure you want to unban this account?", "Confirm unban", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                database.setLockUserAccount(user.getID(), false);
                menu.filltableUserAccount();
                return;
            }
        }else{
            if(JOptionPane.showConfirmDialog(this, "Are you sure you want to ban this account?", "Confirm ban", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                database.setLockUserAccount(user.getID(), true);
                menu.filltableUserAccount();
                return;
            }
        }
    }


    // TODO2: Xóa tài khoản:
    
    private void deleteAccount(){
        if(JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this account \n(The account will not be recoverable) ?", "Confirm delete", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            DatabaseManagment database = DatabaseManagment.getInstance();
            database.deleteAnAccount(user.getID());
        }else{
            return;
        }

        menu.filltableUserAccount();
        this.dispose(); // đóng Jframe này
    }

    // TEST NGAY TẠI FILE NÀY, SỬA ID TẠI HÀM MAIN USER TEST 

   // TODO 3: Sửa thông tin tài khoản:
  
    private void editAccount(){
        if(!isEdit){
            isEdit = true;
        }
        else{
            isEdit = false;
           
            // code here
            String username =userNameField.getText();
            String name=nameField.getText();
            String address=addressField.getText();
            String email =emailField.getText();
            String gender="";
            if(maleRadioButton.isSelected())
                gender="Nam";
            if(femaleRadioButton.isSelected())
                gender="Nữ";

             // Lấy dữ liệu ngày sinh
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String birthDay = df.format(birthDayChooser.getDate());

            user.setUsername(username);
            user.setFullname(name);
            user.setEmail(email);
            user.setAddress(address);
            user.setGender(gender);
            user.setBirthDay(birthDay);

            DatabaseManagment database = DatabaseManagment.getInstance();
            database.updateAccount(user);
            menu.filltableUserAccount();
            fillAccountInfor();
        }

        birthDayChooser.setEnabled(isEdit);
        nameField.setEditable(isEdit);
        addressField.setEditable(isEdit);
        emailField.setEditable(isEdit);
        userNameField.setEditable(isEdit);
        maleRadioButton.setEnabled(isEdit);
        femaleRadioButton.setEnabled(isEdit);
    }

    // SỬA ID USER TEST TẠI ĐÂY
    // public static void main(String args[]) {
        

    //     /* Create and display the form */
    //    java.awt.EventQueue.invokeLater(new Runnable() {
    //        public void run() {
    //         DatabaseManagment database = DatabaseManagment.getInstance();
    //         UserAccount testAccount = database.getDetailAccount(1);


    //            new DetailAccountForm(testAccount).setVisible(true);
    //        }
    //    });
    // }




    private void viewLoginHistoryUser(){
        ViewLoginHistory viewLoginHistory = new ViewLoginHistory(user);
        viewLoginHistory.setVisible(true);
    }





    public DetailAccountForm(UserAccount account,MenuAccountManager menu) {
        initComponents();
        this.user = account;
        this.menu = menu;

        changePasswordButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                changePassword();
            }
            
        });

        viewHistoryButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               viewLoginHistoryUser();
            }
            
        });

        lockAccountButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                lockAccount();
            }
            
        });

        deleteAccountButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                deleteAccount();
                
            }
            
        });

        editorButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                editAccount();
                
            }
            
        });

        isEdit = false;
        birthDayChooser.setEnabled(false);
        nameField.setEditable(false);
        addressField.setEditable(false);
        emailField.setEditable(false);
        userNameField.setEditable(false);
        maleRadioButton.setEnabled(false);
        femaleRadioButton.setEnabled(false);
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


      


        pack();
        setLocationRelativeTo(null);
    }

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        //parentFrame.setEnabled(true);
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
