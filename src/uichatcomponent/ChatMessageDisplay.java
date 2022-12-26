
package uichatcomponent;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author HIEN
 */
public class ChatMessageDisplay extends JScrollPane{

    private Color backgroundColor;
    private int marginTopBottom = 10;
    private int marginLeftRight = 20;
    private int row;
    private JPanel messagePanel;

    public int getMarginTopBottom() {
        return marginTopBottom;
    }

    public void setMarginTopBottom(int marginTopBottom) {
        this.marginTopBottom = marginTopBottom;
    }

    public int getMarginLeftRight() {
        return marginLeftRight;
    }

    public void setMarginLeftRight(int marginLeftRight) {
        this.marginLeftRight = marginLeftRight;
    }
    

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
    
    
    public ChatMessageDisplay() {
        marginTopBottom = 10;
        marginLeftRight = 60;
        initComponent();
        
    }
    
    private void initComponent(){
        messagePanel = new JPanel(new GridBagLayout());
        messagePanel.setBackground(backgroundColor);
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setViewportView(messagePanel);
       
    }
    
    public void addMessage(ChatMessageBlock newMessageBlock){
        GridBagConstraints cs = new GridBagConstraints();
        cs.fill = GridBagConstraints.HORIZONTAL;
        int x = newMessageBlock.getAlign();
        cs.gridx = x;
        cs.gridy = row;
        cs.insets = new Insets(marginTopBottom,marginLeftRight *x , marginTopBottom, (1-x) * marginLeftRight);
        messagePanel.add(newMessageBlock,cs);
        messagePanel.revalidate();
        int height = (int)messagePanel.getPreferredSize().getHeight();
        Rectangle rect = new Rectangle(0,height,10,10);
        messagePanel.scrollRectToVisible(rect);
        row += 1;
    }
    
}
