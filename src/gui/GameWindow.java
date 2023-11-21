package src.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameWindow extends JFrame{

    protected static final class MainMenu implements Menu{
        public final JLabel mainLabel;
        public final JTextField textField;
        public final JTextArea textArea;
        public final JPanel mainPanel;
        public final MKButton addPlayerButton;
        public final MKButton startGameButton;
    
        public MainMenu(){
            mainLabel = new JLabel("Enter your name");
            mainLabel.setHorizontalAlignment(SwingConstants.CENTER);
    
            textField = new JTextField();
            textField.setToolTipText(" Name input ");
            textField.setFont(new Font("Liberation Mono", Font.PLAIN, 24));
            textField.setColumns(20);
    
            textArea = new JTextArea();
            textArea.setEditable(false);
            textArea.setMargin(new Insets(10, 10, 10, 10));
            textArea.setPreferredSize(new Dimension(400, 200));
    
            addPlayerButton = new MKButton("Add player");
            startGameButton = new MKButton("Start game");
            startGameButton.MKHide();
            
            mainPanel = new JPanel();
            mainPanel.setLayout(new GridBagLayout());
            mainPanel.setBackground(Color.BLACK);
    
            GridBagConstraints gbc = new GridBagConstraints();
            // gbc.gridheight = 3;
            // gbc.gridwidth = 3;
            gbc.weightx = 1.0f;
            gbc.weighty = 1.0f;
            gbc.gridx = 1;
            
            gbc.gridy = 0;
            mainPanel.add(mainLabel,gbc);
            gbc.gridy++;
            mainPanel.add(textField, gbc);
            gbc.gridy++;
            mainPanel.add(textArea, gbc);
            gbc.gridy++;
            mainPanel.add(addPlayerButton, gbc);
            mainPanel.add(startGameButton, gbc);
        }
        
        public JPanel getPanel(){
            return this.mainPanel;
        }
    
        public void hide(){
            this.addPlayerButton.MKHide();
            this.mainLabel.setVisible(false);
            this.mainPanel.setVisible(false);
            this.startGameButton.MKHide();
            this.textArea.setVisible(false);
            this.textField.setVisible(false);
        }
    
        public void show(){
            this.addPlayerButton.MKShow();
            this.mainLabel.setVisible(true);
            this.mainPanel.setVisible(true);
            this.startGameButton.MKShow();
            this.textArea.setVisible(true);
            this.textField.setVisible(true);
        }
    }
    
    protected final MainMenu mainMenu;

    public GameWindow(String title, int width, int height){
        super(title);
        this.setLNF();
        this.setMinimumSize(new Dimension(width, height));
        this.centerWindow();
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e){
                quit();
            }
        });
        mainMenu = new MainMenu();
        this.add(mainMenu.getPanel());
        this.setVisible(true);
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
