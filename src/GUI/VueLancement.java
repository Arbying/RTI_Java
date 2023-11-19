package GUI;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;

public class VueLancement extends JFrame {
    public VueLancement() {
        super("Bienvenue");

        setSize(500, 500);
        setLocationRelativeTo(null);

        JLabel labelBienvenue = new JLabel("<html><center>Bienvenue,<br/>découvrez aussi nos autres magasins :)<br/>Vous pourriez y acheter :<br/>Des balais<br/>Des tisanes<br/>Des cafés<br/>Des blabla</center></html>");
        labelBienvenue.setHorizontalAlignment(SwingConstants.CENTER);

        // Augmenter la police
        Font police = new Font("Arial", Font.BOLD, 16);
        labelBienvenue.setFont(police);

        // Changer la couleur du texte en BLEU
        labelBienvenue.setForeground(Color.BLUE);

        add(labelBienvenue);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
