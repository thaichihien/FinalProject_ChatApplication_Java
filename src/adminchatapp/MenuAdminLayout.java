package adminchatapp;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MenuAdminLayout extends JPanel {
    
    protected JFrame parentFrame;
    
    MenuAdminLayout(JFrame parentFrame){
        this.parentFrame = parentFrame;
        this.setBackground(new java.awt.Color(255, 255, 255));
        this.setMinimumSize(new java.awt.Dimension(1275, 750));
        this.setPreferredSize(new java.awt.Dimension(1275, 750));
        this.setLayout(null);
    }
}
