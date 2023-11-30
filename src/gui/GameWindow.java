package src.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.*;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameWindow extends JFrame{

    protected final class MainMenu extends Menu{
        public final JLabel mainLabel;
        public final JTextField textField;
        public final JTextArea textArea;
        public final JPanel mainPanel;
        public final MKButton addPlayerButton;
        public final MKButton startGameButton;
    
        public MainMenu(){
            mainPanel = new JPanel();
            mainPanel.setLayout(new GridBagLayout());
            mainPanel.setBackground(Color.BLACK);
            mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            mainLabel = new JLabel("Enter your name");
            mainLabel.setHorizontalAlignment(SwingConstants.CENTER);
            mainLabel.setFont(new Font(null, Font.BOLD, 36));
    
            textField = new JTextField();
            textField.setToolTipText(" Name input ");
            textField.setFont(new Font("Liberation Mono", Font.PLAIN, 24));
            textField.setColumns(20);
            textField.setPreferredSize(new Dimension(1920,100));
    
            textArea = new JTextArea();
            textArea.setEditable(false);
            textArea.setMargin(new Insets(10, 10, 10, 10));
            textArea.setPreferredSize(new Dimension(400, 200));
    
            addPlayerButton = new MKButton("Add player");
            addPlayerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            startGameButton = new MKButton("Start game");
            startGameButton.setEnabled(false);
            startGameButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.weightx = 1.0f;
            gbc.weighty = 1.0f;
            gbc.gridx = 1;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.insets = new Insets(5,5,5,5);
            
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            mainPanel.add(mainLabel,gbc);
            gbc.gridy++;
            mainPanel.add(textField, gbc);
            gbc.gridy++;
            mainPanel.add(textArea, gbc);
            gbc.gridy++;
            gbc.gridwidth = 1;
            mainPanel.add(startGameButton, gbc);
            gbc.gridx++;
            mainPanel.add(addPlayerButton, gbc);
        }

        @Override
        public JPanel getPanel(){
            return this.mainPanel;
        }
    
        @Override
        public void hide(){
            this.addPlayerButton.MKHide();
            this.mainLabel.setVisible(false);
            this.mainPanel.setVisible(false);
            this.startGameButton.MKHide();
            this.textArea.setVisible(false);
            this.textField.setVisible(false);
        }
    
        @Override
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
    // TODO:
    // protected final Menu playingMenu;

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
        this.mainMenu = new MainMenu();
        this.addMenu(this.mainMenu);
        this.setVisible(true);
    }

    private void addMenu(Menu menu){
        this.add(menu.getPanel());
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
