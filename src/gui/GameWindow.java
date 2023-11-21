package src.gui;

import javax.management.loading.MLet;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameWindow extends JFrame{
    protected final JTextField textField;
    protected final JTextArea textArea;
    protected final JPanel mainPanel;
    protected final MKButton addPlayerButton;
    protected final MKButton startGameButton;

    public GameWindow(String title, int width, int height){
        super(title);
        this.setLNF();
        textField = new JTextField();
        textField.setToolTipText(" Name input ");
        textField.setFont(new Font("Liberation Mono", Font.PLAIN, 24));
        textArea = new JTextArea();
        textArea.setEditable(false);
        mainPanel = new JPanel();
        addPlayerButton = new MKButton("Add player");
        startGameButton = new MKButton("Start game");
        startGameButton.MKHide();
        this.setMinimumSize(new Dimension(width, height));
        this.centerWindow();
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e){
                quit();
            }
        });
        this.setVisible(true);
        this.add(textField,BorderLayout.NORTH);
        this.add(textArea, BorderLayout.CENTER);
        this.add(addPlayerButton, BorderLayout.SOUTH);
        this.add(startGameButton, BorderLayout.EAST);
    }
    
    public void setLNF(){
        // pas sur que le try-catch soit utile ici
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

    /**
     * Displays the {@code Window} object at the center of the screen
     */
    private void centerWindow(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int xWinPos = (int) ((screenSize.getWidth() - this.getSize().getWidth())/2);
        int yWinPos = (int) ((screenSize.getHeight() - this.getSize().getHeight())/2);
        this.setLocation(xWinPos, yWinPos);
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
