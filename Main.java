import src.gui.*;
import src.jeu.*;

public class Main{
    public static void main(String args[]) {
        System.out.println("Hello world!");
        // Window w = new Window("Test", 640, 480);
        Game g = new Game();
        g.readFromConsole();

    }

}