package userchatapp;

import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtUser;
	private JTextField txtPass;
        private JButton btnLogin;
        private JLabel lblLogin;
        private JLabel lblCreateAcc;
        private JLabel lblForgetPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
            initComponent();
            
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
        
        
        private void initComponent(){
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
		
		lblLogin = new JLabel("Đăng nhập tài khoản");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblLogin.setBounds(313, 156, 226, 28);
		contentPane.add(lblLogin);
		
		txtUser = new JTextField();
		txtUser.setBackground(Color.WHITE);
		txtUser.setForeground(Color.GRAY);
		txtUser.setHorizontalAlignment(SwingConstants.LEFT);
		txtUser.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtUser.setText("   Tên tài khoản");
		txtUser.setBounds(272, 209, 297, 48);
		contentPane.add(txtUser);
		txtUser.setColumns(10);
		
		JCheckBox chckbxRememberPass = new JCheckBox("   Ghi nhớ tài khoản");
		chckbxRememberPass.setForeground(Color.GRAY);
		chckbxRememberPass.setBackground(Color.WHITE);
		chckbxRememberPass.setFont(new Font("Tahoma", Font.PLAIN, 16));
		chckbxRememberPass.setBounds(272, 335, 297, 39);
		contentPane.add(chckbxRememberPass);
		
		txtPass = new JTextField();
		txtPass.setText("   Mật khẩu");
		txtPass.setHorizontalAlignment(SwingConstants.LEFT);
		txtPass.setForeground(Color.GRAY);
		txtPass.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtPass.setColumns(10);
		txtPass.setBounds(272, 281, 297, 48);
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
        
        
        private void lblCreateAccMouseClicked(java.awt.event.MouseEvent evt) {                                     
             Register registerForm = new Register();
             registerForm.setVisible(true);
             this.dispose(); 
        }            
        
        private void lblForgetPassMouseClicked(java.awt.event.MouseEvent evt) {                                     
             ForgetPassword registerForm = new ForgetPassword();
             registerForm.setVisible(true);
             this.dispose(); 
        }                                    


        private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {                                         
            MainFormUser menuForm = new MainFormUser();
            menuForm.setVisible(true);
            this.dispose();
        }  
        
        
}
