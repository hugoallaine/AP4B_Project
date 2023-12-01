package src.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;


public class GameWindow extends JFrame{

    protected final class MainMenu extends Menu{
        public final JLabel mainLabel;
        public final JTextField textField;
        public final JTextArea textArea;
        public final JPanel mainPanel;
        public final MKButton addPlayerButton;
        public final MKButton startGameButton;
        private static final Color BACKGROUND_COLOR = new Color(0xAAAAAA);
    
        public MainMenu(){
            mainPanel = new JPanel();
            mainPanel.setLayout(new GridBagLayout());
            mainPanel.setBackground(BACKGROUND_COLOR);
            // mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            mainPanel.setPreferredSize(new Dimension(900, 600));
            mainPanel.setBorder(BorderFactory.createLineBorder(Color.RED, 10));

            mainLabel = new JLabel("Enter your name");
            mainLabel.setHorizontalAlignment(SwingConstants.CENTER);
            mainLabel.setFont(new Font(null, Font.BOLD, 36));
    
            textField = new JTextField();
            textField.setToolTipText(" Name input ");
            textField.setFont(new Font("Liberation Mono", Font.PLAIN, 24));
            textField.setColumns(20);
            textField.setPreferredSize(new Dimension(this.mainPanel.getWidth(),50));
    
            textArea = new JTextArea();
            textArea.setEditable(false);
            textArea.setMargin(new Insets(10, 10, 10, 10));
            // textArea.setPreferredSize(new Dimension(400, 200));
    
            addPlayerButton = new MKButton("Add player");
            addPlayerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            startGameButton = new MKButton("Start game");
            startGameButton.setEnabled(false);
            startGameButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.weightx = 1.0f;
            gbc.weighty = 1.0f;
            gbc.gridx = 1;
            gbc.insets = new Insets(5,5,5,5);
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            mainPanel.add(mainLabel,gbc);
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridy++;
            gbc.insets = new Insets(5, 40, 5, 40);
            mainPanel.add(textField, gbc);
            gbc.fill = GridBagConstraints.BOTH;
            gbc.gridy++;
            mainPanel.add(textArea, gbc);
            gbc.insets = new Insets(5,5,5,5);
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
    
    private final JPanel panel;
    protected final MainMenu mainMenu;
    // TODO:
    // protected final Menu playingMenu;

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
        super.setVisible(true);
    }

    private void addMenu(Menu menu){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0f;
        gbc.weighty = 1.0f;
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
