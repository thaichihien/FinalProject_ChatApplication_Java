package userchatapp;


import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import database.DatabaseManagment;
import datastructure.UserAccount;
import utils.PasswordService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtUser;		// chứa tên đăng nhập
	private JPasswordField txtPass;		// chứa mật khẩu
	private JButton btnLogin;		// nút đăng nhập

	private JLabel lblCreateAcc;	// nút tạo acc
	private JLabel lblForgetPass;	// nút quên mật khẩu

	private JLabel lblUser;
	// private JLabel lblPass;
	private UserAccount socketTemp;


	// ! WARNING: KHÔNG CHỈNH SỬA FILE NÀY, ĐANG LÀM VIỆC SOCKET

	private UserAccount loginAccount(){
		String username,password;
		username=new String(txtUser.getText());
		password=new String(txtPass.getPassword());
		if(username.isBlank()||password.isBlank()){
			JOptionPane.showMessageDialog(null,"Please enter all required fields!");
			return null;
		}
		
		DatabaseManagment db=DatabaseManagment.getInstance();
		UserAccount account = db.getDetailAccount(username);
		if(account == null){
			JOptionPane.showMessageDialog(null,"This account does not exist!");
			return null;
		}

		if(db.checkAccountIsBanned(account.getID())){
			JOptionPane.showMessageDialog(null,"This account is banned!");
			return null;
		}

		Pattern pattern = Pattern.compile("^USER", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(username);
		boolean matchFound = matcher.find();
		if(matchFound) {	// For data inserted vie Sql
			if(password.equals(account.getPassword())){
				return account;
			}else{
				JOptionPane.showMessageDialog(null,"Your password is incorrect!");
				return null;
			}
		} else {
			String encryptPassword = account.getPassword();
			if(PasswordService.verifyPassword(password, encryptPassword)){
				return account;
			}
			else{
				JOptionPane.showMessageDialog(null,"Your password is incorrect!");
				return null;
			}
		}

	}

	private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {

		UserAccount account = loginAccount();
		if(account != null){
			account.setClienSocket(socketTemp.getClienSocket());
			account.setPw(socketTemp.getPw());
			account.setBr(socketTemp.getBr());

			socketTemp.sendPacket(String.valueOf(account.getID()));
			while (true) {
				try {
					String validateRespone = socketTemp.receivePacket();
					if(validateRespone.equals("IDEXIST")){
						JOptionPane.showMessageDialog(null, 
						"This account is online on the system", "Login failed", 
						JOptionPane.WARNING_MESSAGE);
						txtUser.setText("");
						txtPass.setText("");
						return;
					}
					else{
						break;
					}
				} catch (HeadlessException e) {
					e.printStackTrace();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}


			DatabaseManagment database = DatabaseManagment.getInstance();
			database.addToLoginHistory(account.getID());
			MainFormUser menuForm = new MainFormUser(account);
			menuForm.setVisible(true);
			this.dispose();
		}
		
	
		txtPass.setText("");
	}

	private void lblCreateAccMouseClicked(java.awt.event.MouseEvent evt) {
		Register registerForm = new Register(this.socketTemp);
		registerForm.setVisible(true);
		this.dispose();
	}

	private void lblForgetPassMouseClicked(java.awt.event.MouseEvent evt) {
		ForgetPassword registerForm = new ForgetPassword(socketTemp);
		registerForm.setVisible(true);
		this.dispose();
	}


	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// 	EventQueue.invokeLater(new Runnable() {
	// 		public void run() {
	// 			try {
	// 				Login frame = new Login();
	// 				frame.setVisible(true);
	// 			} catch (Exception e) {
	// 				e.printStackTrace();
	// 			}
	// 		}
	// 	});
	// }

	
	public Login(Socket clienSocket,PrintWriter pw,BufferedReader br) {
		initComponent();
		socketTemp = new UserAccount();
		socketTemp.setClienSocket(clienSocket);
		socketTemp.setPw(pw);
		socketTemp.setBr(br);

		lblCreateAcc.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				lblCreateAccMouseClicked(evt);
			}
		});

		lblForgetPass.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				lblForgetPassMouseClicked(evt);
			}
		});

		btnLogin.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnLoginActionPerformed(evt);
			}
		});

	}

	private void initComponent() {
		setTitle("Đăng nhập tài khoản");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 810, 564);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnLogin = new JButton("Đăng nhập");
		btnLogin.setBackground(Color.BLACK);
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnLogin.setBounds(272, 380, 297, 39);
		contentPane.add(btnLogin);

		JLabel lblLogo = new JLabel("");
		lblLogo.setForeground(Color.LIGHT_GRAY);
		lblLogo.setBackground(Color.LIGHT_GRAY);
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setIcon(new ImageIcon(Login.class.getResource("/uichatcomponent/chat.png")));
		lblLogo.setBounds(313, 20, 226, 117);
		contentPane.add(lblLogo);

		JLabel lblLogin = new JLabel("Đăng nhập tài khoản");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblLogin.setBounds(313, 156, 226, 28);
		contentPane.add(lblLogin);

		lblUser = new JLabel("Tên đăng nhập");
		lblUser.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblUser.setBounds(272, 190, 226, 28);
		contentPane.add(lblUser);

		txtUser = new JTextField();
		txtUser.setMargin(new Insets(10, 15, 10, 10));
		txtUser.setBackground(Color.WHITE);
		txtUser.setForeground(Color.GRAY);
		txtUser.setHorizontalAlignment(SwingConstants.LEFT);
		txtUser.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtUser.setBounds(272, 215, 297, 48);
		contentPane.add(txtUser);
		txtUser.setColumns(10);

		JCheckBox chckbxRememberPass = new JCheckBox("   Ghi nhớ tài khoản");
		chckbxRememberPass.setForeground(Color.GRAY);
		chckbxRememberPass.setBackground(Color.WHITE);
		chckbxRememberPass.setFont(new Font("Tahoma", Font.PLAIN, 16));
		chckbxRememberPass.setBounds(272, 340, 297, 39);
		contentPane.add(chckbxRememberPass);

		lblUser = new JLabel("Mật khẩu");
		lblUser.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblUser.setBounds(270, 265, 226, 28);
		contentPane.add(lblUser);

		txtPass = new JPasswordField();
		txtPass.setMargin(new Insets(10, 15, 10, 10));
		txtPass.setHorizontalAlignment(SwingConstants.LEFT);
		txtPass.setForeground(Color.GRAY);
		txtPass.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtPass.setColumns(10);
		txtPass.setBounds(272, 290, 297, 48);
		contentPane.add(txtPass);

		lblCreateAcc = new JLabel("Tạo tài khoản mới");
		lblCreateAcc.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreateAcc.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCreateAcc.setBounds(272, 471, 297, 26);
		lblCreateAcc.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(lblCreateAcc);

		lblForgetPass = new JLabel("Quên mật khẩu?");
		lblForgetPass.setForeground(new Color(128, 128, 128));
		lblForgetPass.setHorizontalAlignment(SwingConstants.CENTER);
		lblForgetPass.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblForgetPass.setBounds(272, 435, 297, 26);
		lblForgetPass.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(lblForgetPass);
	}

	

}
