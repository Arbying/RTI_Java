package GUI;

import Controleurs.Controleur;
import AutresThreads.*;

import javax.swing.*;
import javax.swing.border.Border;
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
        JLabel label1 = new JLabel("User");
        label1.setHorizontalAlignment(JLabel.CENTER);
        textFieldLogin = new JTextField();
        textFieldLogin.setHorizontalAlignment(JTextField.CENTER);
        cell1.add(label1, BorderLayout.NORTH);
        cell1.add(textFieldLogin, BorderLayout.CENTER);

        // Cellule 2 (Mot de passe)
        JPanel cell2 = new JPanel(new BorderLayout());
        cell2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JLabel label2 = new JLabel("Mot de passe");
        label2.setHorizontalAlignment(JLabel.CENTER);
        textFieldMDP = new JTextField();
        textFieldMDP.setHorizontalAlignment(JTextField.CENTER);
        cell2.add(label2, BorderLayout.NORTH);
        cell2.add(textFieldMDP, BorderLayout.CENTER);

        // Cellule 3 (Login button)
        JPanel cell3 = new JPanel(new BorderLayout());
        cell3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JBoutonLogin = new JButton("Login");
        JBoutonLogin.setBackground(Color.GREEN);
        cell3.add(JBoutonLogin, BorderLayout.CENTER);

        // Cellule 4 (Logout button)
        JPanel cell4 = new JPanel(new BorderLayout());
        cell4.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JBoutonLogout = new JButton("LogOUT");
        JBoutonLogout.setBackground(Color.ORANGE);
        JBoutonLogout.setEnabled(false);
        cell4.add(JBoutonLogout, BorderLayout.CENTER);

        // Cellule 5 (Nouveau client)
        JPanel cell5 = new JPanel(new BorderLayout());
        cell5.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JLabel label5 = new JLabel("Nouveau client");
        label5.setHorizontalAlignment(JLabel.CENTER);
        EstNouveau = new JCheckBox();
        cell5.add(label5, BorderLayout.NORTH);
        cell5.add(EstNouveau, BorderLayout.CENTER);

        // Ajouter les cellules au identificationPanel
        identificationPanel.add(cell1);
        identificationPanel.add(cell2);
        identificationPanel.add(cell3);
        identificationPanel.add(cell4);
        identificationPanel.add(cell5);

        add(identificationPanel, BorderLayout.NORTH);

        // Création d'un panneau pour Magasin et Panier
        JPanel centerSouthPanel = new JPanel(new GridLayout(2, 1));

        // JPanel de Magasin (au milieu)
        JPanel magasinPanel = new JPanel();
        magasinPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        magasinPanel.add(new JLabel("Contenu du Magasin"));
        centerSouthPanel.add(magasinPanel);

        // JPanel de Panier (en bas)
        JPanel panierPanel = new JPanel();
        panierPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panierPanel.add(new JLabel("Contenu du Panier"));
        centerSouthPanel.add(panierPanel);

        add(centerSouthPanel, BorderLayout.CENTER);

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
            VueLancement vueLancement = new VueLancement();
            vueLancement.setVisible(true);

            Timer timer = new Timer(5000, e -> {
                vueLancement.dispose();
                VuePrincipale vuePrincipale = new VuePrincipale();
                Controleur controleur = new Controleur(vuePrincipale);
                vuePrincipale.setControleur(controleur);
                vuePrincipale.setVisible(true);
            });

            timer.setRepeats(false);
            timer.start();
        });
    }
}
