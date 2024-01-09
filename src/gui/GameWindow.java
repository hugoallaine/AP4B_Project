package src.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import src.gui.Menus.*;

/**
 * @brief Classe représentant la fenêtre principale du jeu
 * @details Cette classe hérite de JFrame et permet de représenter la fenêtre
 * principale du jeu. Elle contient les différents menus du jeu et permet de
 * les afficher.
 * 
 * @param panel Le JPanel contenant les différents menus
 * @param mainMenu Le menu principal
 * @param playingMenu Le menu de jeu
 * @param endMenu Le menu de fin de partie
 */
public class GameWindow extends JFrame{
    private final JPanel panel;
    protected final MainMenu mainMenu;
    protected final PlayingMenu playingMenu;
    protected final EndMenu endMenu;

    public final static Font DEFAULT_FONT = new Font(null, Font.PLAIN, 24);

    /**
     * @brief Constructeur de la classe GameWindow
     * @param title Le titre de la fenêtre
     * @param width La largeur de la fenêtre
     * @param height La hauteur de la fenêtre
     */
    public GameWindow(final String title, final int width, final int height){
        super(title);
        super.setMinimumSize(new Dimension(width, height));
        this.centerWindow();
        super.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        super.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e){
                quit();
            }
        });
        this.panel = new JPanel();
        this.panel.setLayout(new GridBagLayout());
        this.panel.setBackground(Color.BLACK);
        super.add(this.panel);
        this.mainMenu = new MainMenu();
        this.addMenu(this.mainMenu);
        this.playingMenu = new PlayingMenu();
        this.addMenu(this.playingMenu);
        this.endMenu = new EndMenu();
        this.addMenu(endMenu);
        super.setVisible(true);
    }

    /**
     * @brief Affiche le menu entré en paramètre
     * @param menu Le menu à afficher
     */
    private void addMenu(final MKMenu menu){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        if(menu instanceof PlayingMenu){
            gbc.fill= GridBagConstraints.BOTH;
        }
        if(menu instanceof EndMenu) {
            gbc.fill = GridBagConstraints.BOTH;
        }
        this.panel.add(menu.getPanel(), gbc);
    }

    /**
     * @brief 
     */
    public void setLNF(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch (UnsupportedLookAndFeelException ex){
            System.err.println("[WARNING] Defaulting to Cross Platform Look and Feel");
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            }catch (Exception ex1){
                ex1.printStackTrace();
                System.err.println("[ERROR] Unsupported Look and Feel");
            }
        }catch (Exception ex){
            ex.printStackTrace();
            System.err.println("[ERROR] Unsupported Look and Feel");
        }
    }

    /**
     * @brief Centre la fenêtre sur l'écran
     */
    private void centerWindow(){
        this.setLocationRelativeTo(null);
    }

    /**
     * @brief Affiche une boite de dialogue avec le message entré en paramètre
     * @param message Le message à afficher
     */
    public void announce(final String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    /**
     * @brief Ferme la fenêtre après avoir demandé confirmation à l'utilisateur
     */
    public void quit() {
        int answer = JOptionPane.showConfirmDialog(this, "Êtes vous sur de vouloir quitter ?", "Quitter", JOptionPane.YES_NO_OPTION);
        if (answer == JOptionPane.YES_OPTION) {
            this.dispose();
            System.exit(0);
        }
    }
}