package src.gui;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;

public class Window extends JFrame{
    private JTextField textField;
    private JTextArea textArea;
    private JPanel mainPanel;

    public Window(String title, int width, int height){
        super(title);
        textField = new JTextField("Name");
        textField.setBackground(Color.BLACK);
        textArea = new JTextArea();
        mainPanel = new JPanel();
        this.setSize(width, height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.add(mainPanel);
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(textField,JLabel.CENTER);
        mainPanel.add(textArea, JLabel.SOUTH);
    }
}
