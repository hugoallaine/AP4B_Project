package src.gui;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Window extends JFrame{
    private JTextField textField;
    private JTextArea textArea;

    public Window(String title, int width, int height){
        super(title);
        textField = new JTextField("Name");
        textArea = new JTextArea();
        this.add(textField);
        this.add(textArea);
        this.setSize(width, height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
