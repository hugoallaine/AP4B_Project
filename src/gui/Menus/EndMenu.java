package src.gui.Menus;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import src.gui.GameWindow;
import src.gui.MKMenu;

/**
 * @brief Classe du menu de fin de partie
 * @details Affiche le gagnant et les statistiques de fin de partie
 * 
 * @param mainPanel JPanel du menu
 * @param winnerText JLabel du gagnant
 * @param textArea JTextArea des statistiques
 * @param box JPanel contenant le JLabel et le JTextArea
 */
public class EndMenu extends MKMenu {
    private static final int LAYOUT_GAP = 20;

    private final JPanel mainPanel;
    private final JLabel winnerText;
    private final JTextArea textArea;
    private final JPanel box;

    /**
     * @brief Constructeur du menu de fin de partie
     * @details Initialise les composants du menu
     */
    public EndMenu() {
        this.mainPanel = new JPanel();
        this.mainPanel.setLayout(new GridBagLayout());
        this.mainPanel.setBackground(Color.BLACK);

        this.textArea = new JTextArea();
        this.textArea.setBackground(MKMenu.BACKGROUND_COLOR);
        this.textArea.setFont(GameWindow.DEFAULT_FONT);
        this.textArea.setForeground(Color.WHITE);
        this.textArea.setEditable(false);

        this.winnerText = new JLabel();
        this.winnerText.setFont(MKMenu.TITLE_FONT);
        this.winnerText.setForeground(Color.WHITE);
        this.winnerText.setBackground(MKMenu.BACKGROUND_COLOR);
        this.winnerText.setBorder(BorderFactory.createEmptyBorder());
        this.winnerText.setHorizontalAlignment(SwingConstants.CENTER);

        this.box = new JPanel();
        this.box.setBackground(MKMenu.BACKGROUND_COLOR);
        this.box.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        this.box.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(LAYOUT_GAP, LAYOUT_GAP, LAYOUT_GAP, LAYOUT_GAP);
        gbc.weightx = 1.0f;
        gbc.weighty = 1.0f;
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.box.add(winnerText, gbc);
        gbc.gridy++;
        this.box.add(textArea, gbc);
        this.mainPanel.add(box, gbc);
        this.hide();
    }

    /**
     * @brief Affiche le gagnant et les statistiques de fin de partie
     * @param winnerName Nom du gagnant
     * @param endStats Statistiques de fin de partie
     */
    public void setText(String winnerName, String endStats) {
        String[] lines = endStats.split("\n");
        this.winnerText.setText(winnerName + " won the game");
        this.textArea.setRows(lines.length - 1);
        this.textArea.setText(endStats);
    }

    /**
     * @brief Affiche le menu de fin de partie
     */
    @Override
    public void show() {
        this.mainPanel.setVisible(true);
        this.textArea.setVisible(true);
    }

    /**
     * @brief Cache le menu de fin de partie
     */
    @Override
    public void hide() {
        this.mainPanel.setVisible(false);
        this.textArea.setVisible(false);
    }

    /**
     * @brief Retourne le JPanel du menu de fin de partie
     * @return JPanel du menu de fin de partie
     */
    @Override
    public JPanel getPanel() {
        return this.mainPanel;
    }
}