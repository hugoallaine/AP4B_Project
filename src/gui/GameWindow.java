package src.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import src.gui.Menus.*;

public class GameWindow extends JFrame{
    
    private final JPanel panel;
    protected final MainMenu mainMenu;
    // TODO:
    protected final PlayingMenu playingMenu;

    public GameWindow(String title, int width, int height){
        super(title);
        this.setLNF();
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
        super.setVisible(true);
    }

    private void addMenu(MKMenu menu){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0f;
        gbc.weighty = 1.0f;
        if(menu instanceof PlayingMenu){
            gbc.fill= GridBagConstraints.BOTH;
        }
        this.panel.add(menu.getPanel(), gbc);
    }

    public void setLNF(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch (UnsupportedLookAndFeelException lnfex){
            System.out.println("[WARNING] Defaulting to Cross Platform Look and Feel");
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

    private void centerWindow(){
        this.setLocationRelativeTo(null);
    }

    /**
     * Shows a popup window containing the {@code message}
     * @param message
     */
    public void announce(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void quit() {
        int answer = JOptionPane.showConfirmDialog(this, "Are you sure you want to quit?", "Quit", JOptionPane.YES_NO_OPTION);
        if (answer == JOptionPane.YES_OPTION) {
            this.dispose();
            System.exit(0);
        }
    }
}
