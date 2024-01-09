package src.gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;

/**
 * @brief Classe abstraite représentant un menu
 * @details Cette classe permet de représenter un menu dans l'interface graphique.
 * Elle contient les constantes de couleurs et de polices utilisées dans les menus.
 */
public abstract class MKMenu {
    public static final Color BACKGROUND_COLOR = new Color(0x202020);
    public static final Font TITLE_FONT = new Font(null, Font.BOLD, 36);
    public abstract void hide();
    public abstract void show();
    public abstract JPanel getPanel();
}