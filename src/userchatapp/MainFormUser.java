
package userchatapp;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import chatservice.ChatService;
import datastructure.UserAccount;
import uichatcomponent.ChangeInforForm;



public class MainFormUser extends javax.swing.JFrame implements Runnable {

    private final Color activeTabColor = new Color(239,239,239);
    private final Color unactiveTabColor = new Color(217,217,217);
    private UserAccount user;
    MenuChat menuChatLayout;


    @Override
    public void run() {
       while(true){
            String receiveMessage = user.receivePacket();
            handleMessage(receiveMessage);
       }
    }

    public void handleMessage(String packet){
        System.out.println(packet);
        String[] allMessage = ChatService.packetAnalysis(packet);

       
        if(allMessage[0].equals(ChatService.CHAT)){ // chat#id#time#content
            menuChatLayout.addMessageToChatboxUser(allMessage);
        }else if(allMessage[0].equals(ChatService.CHANGES)){ // change#id#time#menuchat
           if(allMessage[3].equals(ChatService.MENUCHAT)){
            menuChatLayout.resetData();
           }
        }else if(allMessage[0].equals(ChatService.GROUP_RECEIVED)){
            menuChatLayout.addMessageToChatboxGroup(allMessage);
        }

    }

    private void changeInfor(){
        ChangeInforForm changeInforForm = new ChangeInforForm(user);
        changeInforForm.setVisible(true);
    }

    
    public MainFormUser(UserAccount user) {
        initComponents();
        this.user = user;
        userNameLabel.setText(this.user.getUsername());

        userNameLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                changeInfor();
            }
        });

        menuChatLayout = new MenuChat(this.user);
        jTabbedPaneLayput.addTab("chat", menuChatLayout);
    
        MenuAddFriend menuFriendLayout = new MenuAddFriend(this.user);
        jTabbedPaneLayput.addTab("addfriend", menuFriendLayout);
        
        MenuGroup menuGroupLayout = new MenuGroup(this.user);
        jTabbedPaneLayput.addTab("group", menuGroupLayout);
        
        Thread receiveMessageProcess = new Thread(this);
        receiveMessageProcess.start();
        


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
            java.util.logging.Logger.getLogger(MainFormUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFormUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFormUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFormUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        jPanel1 = new javax.swing.JPanel();
        navbar = new javax.swing.JPanel();
        userNameLabel = new javax.swing.JLabel();
        jTabButton1 = new javax.swing.JPanel();
        jTabButton1_icon = new javax.swing.JLabel();
        jTabButton2 = new javax.swing.JPanel();
        jTabButton2_icon = new javax.swing.JLabel();
        jTabButton3 = new javax.swing.JPanel();
        jTabButton_icon = new javax.swing.JLabel();
        jTabbedPaneLayput = new javax.swing.JTabbedPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ứng dụng chat");
        setMinimumSize(new java.awt.Dimension(1440, 750));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        navbar.setBackground(new java.awt.Color(217, 217, 217));
        navbar.setPreferredSize(new java.awt.Dimension(170, 200));

        userNameLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        userNameLabel.setForeground(new java.awt.Color(0, 0, 0));
        userNameLabel.setText("User");

        jTabButton1.setBackground(new java.awt.Color(239, 239, 239));
        jTabButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabButton1MouseClicked(evt);
            }
        });

        jTabButton1_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uichatcomponent/chat. (1).png"))); // NOI18N

        javax.swing.GroupLayout jTabButton1Layout = new javax.swing.GroupLayout(jTabButton1);
        jTabButton1.setLayout(jTabButton1Layout);
        jTabButton1Layout.setHorizontalGroup(
            jTabButton1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jTabButton1Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jTabButton1_icon)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jTabButton1Layout.setVerticalGroup(
            jTabButton1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jTabButton1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jTabButton1_icon)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jTabButton2.setBackground(new java.awt.Color(217, 217, 217));
        jTabButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabButton2MouseClicked(evt);
            }
        });

        jTabButton2_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uichatcomponent/chat. (2).png"))); // NOI18N

        javax.swing.GroupLayout jTabButton2Layout = new javax.swing.GroupLayout(jTabButton2);
        jTabButton2.setLayout(jTabButton2Layout);
        jTabButton2Layout.setHorizontalGroup(
            jTabButton2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jTabButton2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabButton2_icon)
                .addGap(48, 48, 48))
        );
        jTabButton2Layout.setVerticalGroup(
            jTabButton2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jTabButton2Layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(jTabButton2_icon)
                .addGap(16, 16, 16))
        );

        jTabButton3.setBackground(new java.awt.Color(217, 217, 217));
        jTabButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabButton3MouseClicked(evt);
            }
        });

        jTabButton_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uichatcomponent/chat. (3).png"))); // NOI18N

        javax.swing.GroupLayout jTabButton3Layout = new javax.swing.GroupLayout(jTabButton3);
        jTabButton3.setLayout(jTabButton3Layout);
        jTabButton3Layout.setHorizontalGroup(
            jTabButton3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jTabButton3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabButton_icon)
                .addGap(52, 52, 52))
        );
        jTabButton3Layout.setVerticalGroup(
            jTabButton3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jTabButton3Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jTabButton_icon)
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout navbarLayout = new javax.swing.GroupLayout(navbar);
        navbar.setLayout(navbarLayout);
        navbarLayout.setHorizontalGroup(
            navbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(navbarLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(userNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        navbarLayout.setVerticalGroup(
            navbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(navbarLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(userNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66)
                .addComponent(jTabButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jTabButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jTabButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(269, Short.MAX_VALUE))
        );

        jPanel1.add(navbar);
        navbar.setBounds(0, 0, 170, 750);

        jPanel1.add(jTabbedPaneLayput);
        jTabbedPaneLayput.setBounds(170, -30, 1270, 780);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTabButton1MouseClicked(java.awt.event.MouseEvent evt) {
        jTabbedPaneLayput.setSelectedIndex(0);
        jTabButton1.setBackground(activeTabColor);
        jTabButton2.setBackground(unactiveTabColor);
        jTabButton3.setBackground(unactiveTabColor);

    }

    private void jTabButton2MouseClicked(java.awt.event.MouseEvent evt) {
         jTabbedPaneLayput.setSelectedIndex(1);
        jTabButton1.setBackground(unactiveTabColor);
        jTabButton2.setBackground(activeTabColor);
        jTabButton3.setBackground(unactiveTabColor);
    }

    private void jTabButton3MouseClicked(java.awt.event.MouseEvent evt) {
         jTabbedPaneLayput.setSelectedIndex(2);
        jTabButton1.setBackground(unactiveTabColor);
        jTabButton2.setBackground(unactiveTabColor);
        jTabButton3.setBackground(activeTabColor);
    }

    //MAIN
    public static void main(String args[]) {
       
        
        

    //     /* Create and display the form */
        // java.awt.EventQueue.invokeLater(new Runnable() {
        //     public void run() {
        //         new MainFormUser(new UserAccount()).setVisible(true);
        //     }
        // });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jTabButton1;
    private javax.swing.JLabel jTabButton1_icon;
    private javax.swing.JPanel jTabButton2;
    private javax.swing.JLabel jTabButton2_icon;
    private javax.swing.JPanel jTabButton3;
    private javax.swing.JLabel jTabButton_icon;
    private javax.swing.JTabbedPane jTabbedPaneLayput;
    private javax.swing.JPanel navbar;
    private javax.swing.JLabel userNameLabel;
    // End of variables declaration//GEN-END:variables

   
}
