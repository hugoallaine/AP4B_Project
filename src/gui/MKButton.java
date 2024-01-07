package src.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MKButton extends JButton implements EasyBkgImg{
    public Image img;
    private static final Cursor HAND_CURSOR = new Cursor(Cursor.HAND_CURSOR);
    public MKButton(String text){
        super(text);
        img = null;
        super.setCursor(HAND_CURSOR);
        super.setBackground(Color.GRAY);
        super.setForeground(Color.white);
    }

    public void MKShow(){
        this.setVisible(true);
    }

    public void MKHide(){
        this.setVisible(false);
    }

    public void setBackgroundImage(ImageIcon backgroundImage){
        this.img = backgroundImage.getImage();   
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(img, 0, 0, this);
    }
}
