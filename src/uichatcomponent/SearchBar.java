
package uichatcomponent;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author HIEN
 */
public class SearchBar extends JTextField{
    
    private Color backgroundColor = Color.WHITE;
    private Color buttonColor;
    private String placeHolder;

    public String getPlaceHolder() {
        return placeHolder;
    }

    public void setPlaceHolder(String placeHolder) {
        this.placeHolder = placeHolder;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Color getButtonColor() {
        return buttonColor;
    }

    public void setButtonColor(Color buttonColor) {
        this.buttonColor = buttonColor;
    }
    
    
    private final Icon iconSearch;

    public SearchBar() {
        placeHolder = "Text ...";
        buttonColor = new Color(38, 38, 38);

        setBackground(new Color(255,255,255,0));
        setOpaque(false);
        setBorder(new EmptyBorder(10,10,10,50));
        iconSearch = new ImageIcon(getClass().getResource("/uichatcomponent/icons8-search-more-30.png"));
        
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if(checkMouseOver(e.getPoint())){
                    setCursor(new Cursor(Cursor.HAND_CURSOR));
                }
                else{
                    setCursor(new Cursor(Cursor.TEXT_CURSOR));
                }
            }
            
        });
        
        addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e) {
                if(SwingUtilities.isLeftMouseButton(e)){
                    if(checkMouseOver(e.getPoint())){
                        System.out.println("Pressed");
                    }
                }
            }
            
        });
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        int width = getWidth();
        int height = getHeight();
        Graphics2D g2D = (Graphics2D) g;
        
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON );
        g2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2D.setColor(backgroundColor);
        g2D.fillRoundRect(0, 0, width, height, height, height);
        
        
        super.paintComponent(g); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        
        //Create button
        int marginButton = 5;
        int buttonSize = height - marginButton*2;
        GradientPaint gra = new GradientPaint(0,0, new Color(255,255,255), width,0,buttonColor);
        g2D.setPaint(gra);
        g2D.fillOval(width - height + 3, marginButton, buttonSize, buttonSize);

        //Create icon on button
        int marginImage = 5;
        int imageSize = buttonSize - marginImage * 2;
        Image image = ((ImageIcon) iconSearch).getImage();
        g2D.drawImage(image, width - height + marginImage + 3,marginButton + marginImage,imageSize,imageSize,null);
        g2D.dispose();
    
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (getText().length() == 0) {
            int h = getHeight();
            ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            Insets ins = getInsets();
            FontMetrics fm = g.getFontMetrics();
            int c0 = getBackground().getRGB();
            int c1 = getForeground().getRGB();
            int m = 0xfefefefe;
            int c2 = ((c0 & m) >>> 1) + ((c1 & m) >>> 1);
            g.setColor(new Color(c2, true));
            g.drawString(placeHolder, ins.left, h / 2 + fm.getAscent() / 2 - 2);
        }
    }
    
    private boolean checkMouseOver(Point mouse){
        int width = getWidth();
        int height = getHeight();
        int marginButton = 5;
        int buttonSize = height - marginButton*2;
        Point point = new Point(width - height + 3,marginButton);
        Ellipse2D.Double circle = new Ellipse2D.Double(point.x,point.y,buttonSize,buttonSize);
        return circle.contains(mouse);
        
            
    }
    
}
