package GUI;

import Controleurs.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VuePrincipale extends JFrame {

    private Controleur controleur; // Référence au contrôleur

    public VuePrincipale() {
        // Configuration de la fenêtre principale
        setTitle("Application de Vente de Légumes");
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Barre de menus
        JMenuBar menuBar = new JMenuBar();
        JMenu fichierMenu = new JMenu("Fichier");
        JMenuItem quitterItem = new JMenuItem("Quitter");

        quitterItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        fichierMenu.add(quitterItem);
        menuBar.add(fichierMenu);

        setJMenuBar(menuBar);

        // JPanel d'Identification (en haut)
        JPanel identificationPanel = new JPanel();
        identificationPanel.setBackground(Color.RED);
        identificationPanel.setPreferredSize(new Dimension(800, 50));
        identificationPanel.setLayout(new GridLayout(1, 5, 0, 0));

// Cellule 1
        JPanel cell1 = new JPanel(new BorderLayout());
        cell1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        cell1.setPreferredSize(new Dimension(200, 50)); // Taille de la cellule 1

        JLabel label1 = new JLabel("User");
        label1.setHorizontalAlignment(JLabel.CENTER); // Centrer le texte

        JTextField textField1 = new JTextField();
        textField1.setName("Login"); // Identifiant "Login"
        textField1.setHorizontalAlignment(JTextField.CENTER); // Centrer le texte
        textField1.setPreferredSize(new Dimension(100, 22)); // Définir la taille du champ texte (100x22 pixels)
        textField1.setEditable(!Singleton.getInstance().isEstConnecte());
        cell1.add(label1, BorderLayout.NORTH);
        cell1.add(textField1, BorderLayout.CENTER);

// Cellule 2
        JPanel cell2 = new JPanel(new BorderLayout());
        cell2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        cell2.setPreferredSize(new Dimension(200, 50)); // Taille de la cellule 2

        JLabel label2 = new JLabel("Mot de passe");
        label2.setHorizontalAlignment(JLabel.CENTER); // Centrer le texte

        JTextField textField2 = new JTextField();
        textField2.setName("MDP"); // Identifiant "MDP"
        textField2.setHorizontalAlignment(JTextField.CENTER); // Centrer le texte
        textField2.setPreferredSize(new Dimension(100, 22)); // Définir la taille du champ texte (100x22 pixels)

        cell2.add(label2, BorderLayout.NORTH);
        cell2.add(textField2, BorderLayout.CENTER);

// Cellule 3
        JPanel cell3 = new JPanel(new BorderLayout());
        cell3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        cell3.setPreferredSize(new Dimension(200, 50)); // Taille de la cellule 3

        JButton button3 = new JButton("Login");
        button3.setBackground(Color.GREEN); // Fond vert pomme
        button3.setHorizontalAlignment(JButton.CENTER); // Centrer le bouton

        cell3.add(button3, BorderLayout.CENTER);

// Cellule 4
        JPanel cell4 = new JPanel(new BorderLayout());
        cell4.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        cell4.setPreferredSize(new Dimension(200, 50)); // Taille de la cellule 4

        JButton button4 = new JButton("LogOUT");
        button4.setBackground(Color.ORANGE); // Fond orange
        button4.setHorizontalAlignment(JButton.CENTER); // Centrer le bouton

        cell4.add(button4, BorderLayout.CENTER);

// Cellule 5
        JPanel cell5 = new JPanel(new BorderLayout());
        cell5.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        cell5.setPreferredSize(new Dimension(200, 50)); // Taille de la cellule 5

        JLabel label5 = new JLabel("Nouveau client");
        label5.setHorizontalAlignment(JLabel.CENTER); // Centrer le texte

        JCheckBox checkBox5 = new JCheckBox();
        checkBox5.setName("JeSuisLePetitNouveau"); // Identifiant "JeSuisLePetitNouveau"
        checkBox5.setHorizontalAlignment(JCheckBox.CENTER); // Centrer la case à cocher

        cell5.add(label5, BorderLayout.NORTH);
        cell5.add(checkBox5, BorderLayout.CENTER);

// Ajouter les cellules au identificationPanel
        identificationPanel.add(cell1);
        identificationPanel.add(cell2);
        identificationPanel.add(cell3);
        identificationPanel.add(cell4);
        identificationPanel.add(cell5);

        add(identificationPanel, BorderLayout.NORTH);












        // JPanel de Magasin (au milieu)
        JPanel magasinPanel = new JPanel();
        // Ajoutez les composants GUI du magasin ici
        // magasinPanel.add(new JLabel("Liste des produits : "));
        // magasinPanel.add(new JList<>(listeDesProduits));
        add(magasinPanel, BorderLayout.CENTER);

        // JPanel de Panier (en bas)
        JPanel panierPanel = new JPanel();
        // Ajoutez les composants GUI du panier ici
        // panierPanel.add(new JLabel("Panier : "));
        // panierPanel.add(new JList<>(contenuDuPanier));
        add(panierPanel, BorderLayout.SOUTH);

        // Centrer la fenêtre sur l'écran
        setLocationRelativeTo(null);

        // Rendre la fenêtre visible
        setVisible(true);
    }

    // Méthode pour définir la référence au contrôleur
    public void setControleur(Controleur controleur) {
        this.controleur = controleur;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VuePrincipale vue = new VuePrincipale();
            Controleur controleur = new Controleur();
            vue.setControleur(controleur); // Définir la référence au contrôleur dans la vue
            controleur.setVue(vue); // Définir la référence à la vue dans le contrôleur
        });
    }
}
