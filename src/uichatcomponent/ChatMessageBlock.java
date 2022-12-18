
package uichatcomponent;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author HIEN
 */
public class ChatMessageBlock extends JPanel{
    private Color backgroundColor = Color.WHITE;
    private JPanel container;
    private JPanel chatJPanel;
    private JTextArea messagArea;
    private JLabel nameJLabel;
    private JLabel timeJLabel;
    private int align;
    final static public int OTHER = 0;
    final static public int MINE = 1;
    
    public ChatMessageBlock(String name,String time, int align,String message) {
       
       this.align = align;
       if(this.align == OTHER) this.setLayout(new FlowLayout(FlowLayout.LEADING));
        else this.setLayout(new FlowLayout(FlowLayout.RIGHT));
        nameJLabel = new JLabel(name);
        timeJLabel = new JLabel(time);
        messagArea = new JTextArea();
        nameJLabel.setFont(new Font("Segoe UI", 1, 18));
        timeJLabel.setFont(new Font("Segoe UI", 0, 14));
        messagArea.setFont(new Font("Segoe UI", 0, 18));
        messagArea.setEditable(false);
        container = new JPanel(new GridBagLayout());
        paintUI(message);
        
    }

    
    public void setBackgroundColor(Color color) {
        backgroundColor = color;

    }

    public void setText(String message) {
        messagArea.setText(message);
    }

    public String getText() {
        return messagArea.getText();
    }
    
    public void paintUI(String message) {
        // container.setBackground(Color.BLUE);
        container.setOpaque(false);
        chatJPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Dimension arcs = new Dimension(15, 15); // Border corners arcs {width,height}, change this to whatever
                                                        // you want
                int width = getWidth();
                int height = getHeight();
                Graphics2D graphics = (Graphics2D) g;
                graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Draws the rounded panel with borders.
                graphics.setColor(backgroundColor);
                graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);// paint background
                graphics.setColor(getForeground());
                graphics.drawRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);// paint border
            }
        };
        chatJPanel.setOpaque(false);
        messagArea.setLineWrap(true);
        messagArea.setWrapStyleWord(true);
        if (message.length() >= 40)
            messagArea.setColumns(20);
        else if (message.length() >= 15)
            messagArea.setColumns(15);

        GridBagConstraints cs = new GridBagConstraints();
        // cs.fill = GridBagConstraints.HORIZONTAL;
        cs.anchor = this.align == MINE ? GridBagConstraints.EAST : GridBagConstraints.WEST;
        cs.gridx = 0;
        cs.gridy = 0;

        container.add(nameJLabel, cs);
        cs.insets = new Insets(10, 0, 0, 0);
        cs.gridy = 1;
        if (this.align == MINE)
            messagArea.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        messagArea.setText(message);
        chatJPanel.add(messagArea);
        container.add(chatJPanel, cs);
        cs.gridy = 2;
        cs.insets = new Insets(0, 0, 10, 0);
        container.add(timeJLabel,cs);
        
        this.add(container);
        this.setSize(220,100);
        this.setSize(220,this.getPreferredSize().height);
    }
    
    public int getAlign(){
        return this.align;
    }
}
