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
import javax.swing.JButton;

public class ForgetPassword extends JFrame {
	private JPanel contentPane;
	private JTextField txtEmail;
	private JButton btnSend;
        private JButton btnBack;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ForgetPassword frame = new ForgetPassword();
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
	public ForgetPassword() {
            initComponent();
            
            btnSend.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnSendActionPerformed(evt);
                }
            });
             
            btnBack.addActionListener(new java.awt.event.ActionListener() {
              public void actionPerformed(java.awt.event.ActionEvent evt) {
                  btnBackActionPerformed(evt);
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
		
		btnBack = new JButton("Quay về");
		btnBack.setForeground(new Color(0, 0, 0));
		btnBack.setBackground(new Color(255, 255, 255));
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnBack.setBounds(263, 400, 153, 39);
		contentPane.add(btnBack);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setForeground(Color.LIGHT_GRAY);
		lblLogo.setBackground(Color.LIGHT_GRAY);
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setIcon(new ImageIcon(Login.class.getResource("/uichatcomponent/chat.png")));
		lblLogo.setBounds(304, 67, 226, 117);
		contentPane.add(lblLogo);
		
		JLabel lblChangePass = new JLabel("Khởi tạo lại mật khẩu");
		lblChangePass.setHorizontalAlignment(SwingConstants.CENTER);
		lblChangePass.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblChangePass.setBounds(304, 226, 226, 28);
		contentPane.add(lblChangePass);
		
		JLabel lblRequire = new JLabel("Hãy nhập vào Email để nhận mật khẩu mới");
		lblRequire.setForeground(new Color(128, 128, 128));
		lblRequire.setHorizontalAlignment(SwingConstants.CENTER);
		lblRequire.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRequire.setBounds(259, 276, 329, 26);
		lblRequire.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(lblRequire);
		
		txtEmail = new JTextField();
		txtEmail.setText("   Email");
		txtEmail.setHorizontalAlignment(SwingConstants.LEFT);
		txtEmail.setForeground(Color.GRAY);
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtEmail.setColumns(10);
		txtEmail.setBounds(263, 322, 313, 48);
		contentPane.add(txtEmail);
		
		btnSend = new JButton("Gửi");
		btnSend.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSend.setBackground(Color.BLACK);
		btnSend.setBounds(423, 400, 153, 39);
		contentPane.add(btnSend);
        }

        
         private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {                                         
            MainFormUser menuForm = new MainFormUser();
            menuForm.setVisible(true);
            this.dispose();
        }  
         
          private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {                                         
            Login menuForm = new Login();
            menuForm.setVisible(true);
            this.dispose();
        }  
}
