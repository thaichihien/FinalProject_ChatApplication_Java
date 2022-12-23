package userchatapp;

import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.xml.crypto.Data;

import datastructure.UserAccount;

import database.DatabaseManagment;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Register extends JFrame {
	private JPanel contentPane;
	private JTextField txtUser;
	private JTextField txtRePass;
	private JTextField txtPass;
	private JTextField txtEmail;
	private JButton btnRegister;
	private JLabel lblHavingAcc;

	private int getLargestID(){
		DatabaseManagment db=new DatabaseManagment();
		Connection conn=db.getConnection();
		try{
		Statement stmt=conn.createStatement();
		String query="Select MAX(ID) as LargestID from USER_ACCOUNT;";
		ResultSet rs=stmt.executeQuery(query);
		if(rs.next())
			return rs.getInt("LargestID");
		}
		catch (SQLException e){}
		return -1;
	}

	// TODO 1: viết hàm đăng ký, kiểm tra các field, thêm vào database

	private UserAccount registerAccount(){
	// Ok thì trả về account vừa đăng ký ngược lại null rồi làm hiện lỗi tại hàm btnRegisterActionPerformed()
		UserAccount result=new UserAccount();
	
		String username, password, email, repass;
		username=new String(txtUser.getText());
		password=new String(txtPass.getText());
		repass=new String(txtRePass.getText());
		email=new String(txtEmail.getText());
		if(username.isBlank()||password.isBlank()||email.isBlank()||repass.isBlank())
			return null;
		else if(repass.equals(password))
		{
			result=new UserAccount();
			result.setID(getLargestID()+1);
			result.setUsername(username);
			result.setPassword(password);
			result.setEmail(email);
			DatabaseManagment db=new DatabaseManagment();
			db.addNewAccount(result);
			return result;
		}

		return null;
	}

	private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {                                         
		
		UserAccount account = registerAccount();
		if(account != null){
		 MainFormUser menuForm = new MainFormUser(account);
		 menuForm.setVisible(true);
		 this.dispose();
		 
		}
		else{
		 //TODO 2: Hiện lỗi tại đây cho người dùng, recommend dùng JOptionPane;
			JFrame frame = new JFrame("Error");
			frame.setSize(200, 200);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
			

			String username, password, email, repass;
			username=new String(txtUser.getText());
			password=new String(txtPass.getText());
			repass=new String(txtRePass.getText());
			email=new String(txtEmail.getText());
			if(username.isBlank()||password.isBlank()||email.isBlank()||repass.isBlank())
				JOptionPane.showMessageDialog(frame,"Please enter all required fields");

			if(!repass.equals(password))
			 	JOptionPane.showMessageDialog(frame,"Your repassword is incorrect");
		}

		txtUser.setText("");
		txtEmail.setText("");
		txtPass.setText("");
		txtRePass.setText("");
	 }  





	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Register() {
            initComponent();
                
            lblHavingAcc.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lblHavingAccMouseClicked(evt);
                }
            });
            
            btnRegister.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnRegisterActionPerformed(evt);
                }
            });
                
                
	}

        
        private void initComponent(){
            setFont(null);
		setTitle("Đăng ký tài khoản");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 810, 564);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

                setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnRegister = new JButton("Đăng ký");
		btnRegister.setBackground(Color.BLACK);
		btnRegister.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnRegister.setBounds(273, 431, 297, 39);
		contentPane.add(btnRegister);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setForeground(Color.LIGHT_GRAY);
		lblLogo.setBackground(Color.LIGHT_GRAY);
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setIcon(new ImageIcon(Login.class.getResource("/uichatcomponent/chat.png")));
		lblLogo.setBounds(313, 10, 226, 117);
		contentPane.add(lblLogo);
		
		JLabel lblRegister = new JLabel("Đăng ký tài khoản");
		lblRegister.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegister.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblRegister.setBounds(314, 137, 226, 28);
		contentPane.add(lblRegister);
		
		txtUser = new JTextField();
		txtUser.setBackground(Color.WHITE);
		txtUser.setForeground(Color.GRAY);
		txtUser.setHorizontalAlignment(SwingConstants.LEFT);
		txtUser.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtUser.setText("   Tên tài khoản");
		txtUser.setBounds(273, 186, 297, 48);
		contentPane.add(txtUser);
		txtUser.setColumns(10);
		
		txtRePass = new JTextField();
		txtRePass.setText("   Nhập lại mật khẩu");
		txtRePass.setHorizontalAlignment(SwingConstants.LEFT);
		txtRePass.setForeground(Color.GRAY);
		txtRePass.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtRePass.setColumns(10);
		txtRePass.setBounds(273, 362, 297, 48);
		contentPane.add(txtRePass);
		
		lblHavingAcc = new JLabel("Đã có tài khoản");
		lblHavingAcc.setHorizontalAlignment(SwingConstants.CENTER);
		lblHavingAcc.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblHavingAcc.setBounds(273, 480, 297, 26);
		lblHavingAcc.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(lblHavingAcc);
		
		txtPass = new JTextField();
		txtPass.setText("   Mật khẩu");
		txtPass.setHorizontalAlignment(SwingConstants.LEFT);
		txtPass.setForeground(Color.GRAY);
		txtPass.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtPass.setColumns(10);
		txtPass.setBounds(273, 304, 297, 48);
		contentPane.add(txtPass);
		
		txtEmail = new JTextField();
		txtEmail.setText("   Email");
		txtEmail.setHorizontalAlignment(SwingConstants.LEFT);
		txtEmail.setForeground(Color.GRAY);
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtEmail.setColumns(10);
		txtEmail.setBounds(273, 244, 297, 48);
		contentPane.add(txtEmail);
        }
        
        
        private void lblHavingAccMouseClicked(java.awt.event.MouseEvent evt) {                                     
             Login registerForm = new Login();
             registerForm.setVisible(true);
             this.dispose(); 
        }                                    

       
}
