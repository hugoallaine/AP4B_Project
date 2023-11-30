package src.gui;

import javax.swing.ImageIcon;
import java.awt.Graphics;

interface EasyBkgImg {
    public void setBackgroundImage(ImageIcon imageIcon);
    public void paintComponent(Graphics g);
}