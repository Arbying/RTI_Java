package GUI;

import Controleurs.*;
import AutresThreads.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VuePrincipale extends JFrame {

    private Controleur controleur; // Référence au contrôleur

    private JMenuItem quitterItem;
    private JMenuItem loginItem;
    private JMenuItem logoutItem;
    private JMenuItem changerMdpItem;

    private JTextField textFieldLogin;
    private JTextField textFieldMDP;
    private JButton JBoutonLogin;
    private JButton JBoutonLogout;
    private JCheckBox EstNouveau;

    public VuePrincipale() {
        // Configuration de la fenêtre principale
        setTitle("Application de Vente de Légumes");
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Barre de menus
        JMenuBar menuBar = new JMenuBar();
        JMenu fichierMenu = new JMenu("Fichier");
        quitterItem = new JMenuItem("Quitter");

        quitterItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        fichierMenu.add(quitterItem);
        menuBar.add(fichierMenu);

        // Menu Utilisateur
        JMenu utilisateurMenu = new JMenu("Utilisateur");
        loginItem = new JMenuItem("Login");
        loginItem.setVisible(true);
        logoutItem = new JMenuItem("Logout");
        logoutItem.setVisible(false);
        changerMdpItem = new JMenuItem("Changer mot de passe");
        changerMdpItem.setVisible(false);

        utilisateurMenu.add(loginItem);
        utilisateurMenu.add(logoutItem);
        utilisateurMenu.add(changerMdpItem);
        menuBar.add(utilisateurMenu);

        setJMenuBar(menuBar);

        // JPanel d'Identification (en haut)
        JPanel identificationPanel = new JPanel();
        identificationPanel.setBackground(Color.RED);
        identificationPanel.setPreferredSize(new Dimension(800, 50));
        identificationPanel.setLayout(new GridLayout(1, 5, 0, 0));

        // Cellule 1 (User)
        JPanel cell1 = new JPanel(new BorderLayout());
        cell1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        cell1.setPreferredSize(new Dimension(200, 50));

        JLabel label1 = new JLabel("User");
        label1.setHorizontalAlignment(JLabel.CENTER);

        textFieldLogin = new JTextField();
        textFieldLogin.setName("Login");
        textFieldLogin.setHorizontalAlignment(JTextField.CENTER);
        textFieldLogin.setPreferredSize(new Dimension(100, 22));

        cell1.add(label1, BorderLayout.NORTH);
        cell1.add(textFieldLogin, BorderLayout.CENTER);

        // Cellule 2 (Mot de passe)
        JPanel cell2 = new JPanel(new BorderLayout());
        cell2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        cell2.setPreferredSize(new Dimension(200, 50));

        JLabel label2 = new JLabel("Mot de passe");
        label2.setHorizontalAlignment(JLabel.CENTER);

        textFieldMDP = new JTextField();
        textFieldMDP.setName("MDP");
        textFieldMDP.setHorizontalAlignment(JTextField.CENTER);
        textFieldMDP.setPreferredSize(new Dimension(100, 22));

        cell2.add(label2, BorderLayout.NORTH);
        cell2.add(textFieldMDP, BorderLayout.CENTER);

        // Cellule 3 (Login button)
        JPanel cell3 = new JPanel(new BorderLayout());
        cell3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        cell3.setPreferredSize(new Dimension(200, 50));

        JBoutonLogin = new JButton("Login");
        JBoutonLogin.setBackground(Color.GREEN);
        JBoutonLogin.setHorizontalAlignment(JButton.CENTER);

        cell3.add(JBoutonLogin, BorderLayout.CENTER);

        // Cellule 4 (Logout button)
        JPanel cell4 = new JPanel(new BorderLayout());
        cell4.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        cell4.setPreferredSize(new Dimension(200, 50));

        JBoutonLogout = new JButton("LogOUT");
        JBoutonLogout.setBackground(Color.ORANGE);
        JBoutonLogout.setHorizontalAlignment(JButton.CENTER);
        JBoutonLogout.setEnabled(false);

        cell4.add(JBoutonLogout, BorderLayout.CENTER);

        // Cellule 5 (Nouveau client)
        JPanel cell5 = new JPanel(new BorderLayout());
        cell5.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        cell5.setPreferredSize(new Dimension(200, 50));

        JLabel label5 = new JLabel("Nouveau client");
        label5.setHorizontalAlignment(JLabel.CENTER);

        EstNouveau = new JCheckBox();
        EstNouveau.setName("JeSuisLePetitNouveau");
        EstNouveau.setHorizontalAlignment(JCheckBox.CENTER);

        cell5.add(label5, BorderLayout.NORTH);
        cell5.add(EstNouveau, BorderLayout.CENTER);

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



    public void setModificationEtat(boolean etat) {
        textFieldLogin.setEditable(etat);
        textFieldMDP.setEditable(etat);
        JBoutonLogin.setEnabled(etat);
        JBoutonLogout.setEnabled(!etat);
        EstNouveau.setEnabled(etat);
        loginItem.setVisible(etat);
        logoutItem.setVisible(!etat);
        changerMdpItem.setVisible(!etat);
    }

    // Méthodes pour obtenir les éléments du menu
    public JMenuItem getQuitterItem() {
        return quitterItem;
    }

    public JMenuItem getLoginItem() {
        return loginItem;
    }

    public JMenuItem getLogoutItem() {
        return logoutItem;
    }

    public JMenuItem getChangerMdpItem() {
        return changerMdpItem;
    }

    public JButton getJBoutonLogin() {
        return JBoutonLogin;
    }

    public JTextField getTextFieldLogin() {
        return textFieldLogin;
    }

    public JTextField getTextFieldMDP() {
        return textFieldMDP;
    }

    public JCheckBox getEstNouveau() {
        return EstNouveau;
    }

    public JButton getJBoutonLogout() {
        return JBoutonLogout;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VuePrincipale vue = new VuePrincipale();
            Controleur controleur = new Controleur(vue);
            vue.setControleur(controleur);
        });
    }
}
