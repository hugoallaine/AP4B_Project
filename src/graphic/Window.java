package src.graphic;

import javax.swing.JFrame;

public class Window extends JFrame{
    public Window(String title, int width, int height){
        super(title);
        this.setSize(width, height);
    }
}
