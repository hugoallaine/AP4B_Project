package src.gui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MKButton extends JButton implements EasyBkgImg{
    public Image img;
    public MKButton(String text){
        super(text);
        img = null;
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
