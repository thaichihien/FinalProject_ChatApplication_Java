
package test;

import java.awt.Color;

import javax.swing.JPanel;

import adminchatapp.MenuAccountManager;
import userchatapp.MenuChat;


public class MainFrameTest extends javax.swing.JFrame {

   //Test your code here
    public MainFrameTest() {
        initComponents();
        
        //set menu here
        MenuAccountManager manager = new MenuAccountManager();
        setMenu(manager);

        




        
    }
    
    private final Color activeTabColor = new Color(239,239,239);
    private final Color unactiveTabColor = new Color(217,217,217);

    
    private void initComponents() {
        background = new javax.swing.JPanel();
        navbar = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTabButton1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTabButton2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTabButton3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jTabbedPane = new javax.swing.JTabbedPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Admin");
        setMinimumSize(new java.awt.Dimension(1440, 750));

        background.setMinimumSize(new java.awt.Dimension(1440, 750));
        background.setLayout(null);

        navbar.setBackground(new java.awt.Color(217, 217, 217));
        navbar.setPreferredSize(new java.awt.Dimension(165, 750));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Admin");

        jTabButton1.setBackground(new java.awt.Color(239, 239, 239));
        jTabButton1.setPreferredSize(new java.awt.Dimension(165, 110));
        jTabButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabButton1MouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons8-user-60.png"))); // NOI18N

        javax.swing.GroupLayout jTabButton1Layout = new javax.swing.GroupLayout(jTabButton1);
        jTabButton1.setLayout(jTabButton1Layout);
        jTabButton1Layout.setHorizontalGroup(
            jTabButton1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jTabButton1Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
        );
        jTabButton1Layout.setVerticalGroup(
            jTabButton1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jTabButton1Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        jTabButton2.setBackground(new java.awt.Color(217, 217, 217));
        jTabButton2.setPreferredSize(new java.awt.Dimension(165, 110));
        jTabButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabButton2MouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons8-time-machine-48.png"))); // NOI18N

        javax.swing.GroupLayout jTabButton2Layout = new javax.swing.GroupLayout(jTabButton2);
        jTabButton2.setLayout(jTabButton2Layout);
        jTabButton2Layout.setHorizontalGroup(
            jTabButton2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jTabButton2Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jTabButton2Layout.setVerticalGroup(
            jTabButton2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jTabButton2Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        jTabButton3.setBackground(new java.awt.Color(217, 217, 217));
        jTabButton3.setPreferredSize(new java.awt.Dimension(165, 110));
        jTabButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabButton3MouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons8-user-groups-48.png"))); // NOI18N

        javax.swing.GroupLayout jTabButton3Layout = new javax.swing.GroupLayout(jTabButton3);
        jTabButton3.setLayout(jTabButton3Layout);
        jTabButton3Layout.setHorizontalGroup(
            jTabButton3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jTabButton3Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jTabButton3Layout.setVerticalGroup(
            jTabButton3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jTabButton3Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout navbarLayout = new javax.swing.GroupLayout(navbar);
        navbar.setLayout(navbarLayout);
        navbarLayout.setHorizontalGroup(
            navbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
            .addComponent(jTabButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
            .addComponent(jTabButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, navbarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );
        navbarLayout.setVerticalGroup(
            navbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(navbarLayout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77)
                .addComponent(jTabButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jTabButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jTabButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(221, Short.MAX_VALUE))
        );

        background.add(navbar);
        navbar.setBounds(0, 0, 170, 750);
        background.add(jTabbedPane);
        jTabbedPane.setBounds(170, -33, 1270, 950);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTabButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabButton1MouseClicked
        jTabbedPane.setSelectedIndex(0);
        jTabButton1.setBackground(activeTabColor);
        jTabButton2.setBackground(unactiveTabColor);
        jTabButton3.setBackground(unactiveTabColor);
    }

    private void jTabButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabButton2MouseClicked
        // jTabbedPane.setSelectedIndex(1);
        // jTabButton1.setBackground(unactiveTabColor);
        // jTabButton2.setBackground(activeTabColor);
        // jTabButton3.setBackground(unactiveTabColor);
    }

    private void jTabButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabButton3MouseClicked
        // jTabbedPane.setSelectedIndex(2);
        // jTabButton1.setBackground(unactiveTabColor);
        // jTabButton2.setBackground(unactiveTabColor);
        // jTabButton3.setBackground(activeTabColor);
    }

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
            java.util.logging.Logger.getLogger(MainFrameTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrameTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrameTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrameTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrameTest().setVisible(true);
            }
        });
    }

    private void setMenu(JPanel menu){
        jTabbedPane.addTab("testMenu",menu);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel background;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jTabButton1;
    private javax.swing.JPanel jTabButton2;
    private javax.swing.JPanel jTabButton3;
    private javax.swing.JTabbedPane jTabbedPane;
    private javax.swing.JPanel navbar;
    // End of variables declaration//GEN-END:variables
}