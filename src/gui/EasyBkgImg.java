package src.gui;

import javax.swing.ImageIcon;
import java.awt.Graphics;

/**
 * @brief Interface permettant de mettre une image de fond à un JPanel
 */
interface EasyBkgImg {
    public void setBackgroundImage(ImageIcon imageIcon);
    public void paintComponent(Graphics g);
}