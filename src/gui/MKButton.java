package src.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * @brief Classe permettant de créer un bouton avec une image de fond
 * @details Cette classe hérite de JButton et permet de créer un bouton avec une
 * image de fond. Elle implémente l'interface EasyBkgImg qui permet de mettre
 * en place l'image de fond.
 * 
 * @param img L'image de fond du bouton
 */
public class MKButton extends JButton implements EasyBkgImg{
    public Image img;
    private static final Cursor HAND_CURSOR = new Cursor(Cursor.HAND_CURSOR);

    /**
     * @brief Constructeur de la classe MKButton
     * @param text Le texte du bouton
     */
    public MKButton(String text){
        super(text);
        img = null;
        super.setCursor(HAND_CURSOR);
        super.setBackground(Color.GRAY);
        super.setForeground(Color.white);
    }

    /**
     * @brief Affiche le bouton
     */
    public void MKShow(){
        this.setVisible(true);
    }

    /**
     * @brief Cache le bouton
     */
    public void MKHide(){
        this.setVisible(false);
    }

    /**
     * @brief Défini l'image de fond du bouton
     * @param backgroundImage L'image de fond du bouton
     */
    public void setBackgroundImage(ImageIcon backgroundImage){
        this.img = backgroundImage.getImage();   
    }

    /**
     * @brief Repaint le bouton
     * @param g Le Graphics du bouton
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(img, 0, 0, this);
    }
}