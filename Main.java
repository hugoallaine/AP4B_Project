import javax.swing.SwingUtilities;

import src.gui.*;

/**
 * @brief Classe principale du jeu
 * @details Cette classe contient la méthode main qui permet de lancer le jeu.
 */
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