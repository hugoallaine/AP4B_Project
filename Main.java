import javax.swing.SwingUtilities;

import src.gui.*;

public class Main{
    public static void main(String args[]) {
        App app = new App();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                app.launch();
            }
        });
        
    }
}