package userchatapp;


import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;


import datastructure.UserAccount;
import utils.PasswordService;
import database.DatabaseManagment;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Register extends JFrame {
	private JPanel contentPane;
	private JTextField txtUser;
	private JPasswordField txtRePass;
	private JPasswordField txtPass;
	private JTextField txtEmail;
	private JLabel labelUser;
	private JLabel labelRePass;
	private JLabel labelEmail;
	private JLabel labelPass;
	private JButton btnRegister;
	private JLabel lblHavingAcc;

	//socket
	private UserAccount socketTemp;
	



	private UserAccount registerAccount(){
	// Ok thì trả về account vừa đăng ký ngược lại null rồi làm hiện lỗi tại hàm btnRegisterActionPerformed()
		UserAccount newAccount;
	
		String username, password, email, repass;
		username=new String(txtUser.getText().trim());
		password=new String(txtPass.getPassword());
		repass=new String(txtRePass.getPassword());
		email=new String(txtEmail.getText().trim());
		if(username.isBlank()||password.isBlank()||email.isBlank()||repass.isBlank()){
			JOptionPane.showMessageDialog(null,"Please enter all required fields");
			return null;
		}
		else if(!repass.equals(password)){
			JOptionPane.showMessageDialog(null,"Your repassword is incorrect");
			return null;
		}
		else if(repass.equals(password))
		{
			DatabaseManagment db=DatabaseManagment.getInstance();

			if(db.checkAccount(email) > 0){
				JOptionPane.showMessageDialog(null,"This e-mail is already taken");
				return null;
			}

			newAccount=new UserAccount();

			String encryptPassword = PasswordService.encryptPassword(password);
			
			newAccount.setUsername(username);
			newAccount.setPassword(encryptPassword);
			newAccount.setEmail(email);

			
			int newID = db.registerNewAccount(newAccount);
			if(newID == -1) return null;
			newAccount.setID(newID);
			return newAccount;
		}

		return null;
	}

	private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {                                         
		
		UserAccount account = registerAccount();
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
				}
			}
			MainFormUser menuForm = new MainFormUser(account);
			menuForm.setVisible(true);
			this.dispose();
		 
		}
		

		txtPass.setText("");
		txtRePass.setText("");
	 }  

	 
	 private void lblHavingAccMouseClicked(java.awt.event.MouseEvent evt) {                                     
		 Login registerForm = new Login(socketTemp.clienSocket,socketTemp.pw,socketTemp.br);
		 registerForm.setVisible(true);
		 this.dispose(); 
	}      
	
	
	// public static void main(String[] args) {
	// 	EventQueue.invokeLater(new Runnable() {
	// 		public void run() {
	// 			try {
	// 				Register frame = new Register();
	// 				frame.setVisible(true);
	// 			} catch (Exception e) {
	// 				e.printStackTrace();
	// 			}
	// 		}
	// 	});
	// }

	/**
	 * Create the frame.
	 */
	public Register(UserAccount socketTemp) {
            initComponent();
            this.socketTemp= socketTemp;
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
		setBounds(100, 100, 810, 650);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

 		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnRegister = new JButton("Đăng ký");
		btnRegister.setBackground(Color.BLACK);
		btnRegister.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnRegister.setBounds(273, 490, 297, 39);
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

		labelUser = new JLabel("Tên đăng nhập");
		labelUser.setFont(new Font("Tahoma", Font.ITALIC, 12));
		labelUser.setBounds(273, 170, 226, 28);
		contentPane.add(labelUser);
		
		txtUser = new JTextField();
		txtUser.setMargin(new Insets(10, 15, 10, 10));
		txtUser.setBackground(Color.WHITE);
		txtUser.setForeground(Color.GRAY);
		txtUser.setHorizontalAlignment(SwingConstants.LEFT);
		txtUser.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtUser.setBounds(273, 195, 297, 48);
		contentPane.add(txtUser);
		txtUser.setColumns(10);

		labelRePass = new JLabel("Nhập lại mật khẩu");
		labelRePass.setFont(new Font("Tahoma", Font.ITALIC, 12));
		labelRePass.setBounds(273, 395, 226, 28);
		contentPane.add(labelRePass);
		
		txtRePass = new JPasswordField();
		txtRePass.setMargin(new Insets(10, 15, 10, 10));
		txtRePass.setHorizontalAlignment(SwingConstants.LEFT);
		txtRePass.setForeground(Color.GRAY);
		txtRePass.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtRePass.setColumns(10);
		txtRePass.setBounds(273, 420, 297, 48);
		contentPane.add(txtRePass);
		
		lblHavingAcc = new JLabel("Đã có tài khoản");
		lblHavingAcc.setHorizontalAlignment(SwingConstants.CENTER);
		lblHavingAcc.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblHavingAcc.setBounds(273, 540, 297, 26);
		lblHavingAcc.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(lblHavingAcc);

		labelPass = new JLabel("Mật khẩu");
		labelPass.setFont(new Font("Tahoma", Font.ITALIC, 12));
		labelPass.setBounds(273, 320, 226, 28);
		contentPane.add(labelPass);
		
		txtPass = new JPasswordField();
		txtPass.setMargin(new Insets(10, 15, 10, 10));
		txtPass.setHorizontalAlignment(SwingConstants.LEFT);
		txtPass.setForeground(Color.GRAY);
		txtPass.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtPass.setColumns(10);
		txtPass.setBounds(273, 345, 297, 48);
		contentPane.add(txtPass);

		labelEmail = new JLabel("Email");
		labelEmail.setFont(new Font("Tahoma", Font.ITALIC, 12));
		labelEmail.setBounds(273, 245, 226, 28);
		contentPane.add(labelEmail);
		
		txtEmail = new JTextField();
		txtEmail.setMargin(new Insets(10, 15, 10, 10));
		txtEmail.setHorizontalAlignment(SwingConstants.LEFT);
		txtEmail.setForeground(Color.GRAY);
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtEmail.setColumns(10);
		txtEmail.setBounds(273, 270, 297, 48);
		contentPane.add(txtEmail);
        }
        
                                      

       
}
