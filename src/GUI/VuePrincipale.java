package GUI;

import Controleurs.*;
import AutresThreads.*;
import Modele.*;


import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import java.util.List;



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

    private JTextField textFieldArticle;
    private JTextField textFieldPrix;
    private JTextField textFieldStock;
    private JTextField textFieldJachete;

    private JButton boutonPrecedent;
    private JButton boutonSuivant;
    private JButton boutonAjoutPanier;


    private JLabel imageLabel = new JLabel();

    private JPanel panierPanel;
    private DefaultTableModel modelePanier;
    private JTable tableArticles;
    private JButton boutonSupprimer;
    private JButton boutonVider;
    private JButton boutonConfirmer;
    private JTextField totalField;


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
        magasinPanel.setLayout(new BoxLayout(magasinPanel, BoxLayout.X_AXIS));
        magasinPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

// Panneau gauche
        JPanel gauchePanel = new JPanel(new GridBagLayout());
        gauchePanel.setPreferredSize(new Dimension(250, 100));
        gauchePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        gauchePanel.add(imageLabel);


// Panneau central
        // Panneau central avec GridBagLayout
        JPanel centrePanel = new JPanel(new GridBagLayout());
        centrePanel.setPreferredSize(new Dimension(300, 100));
        centrePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5); // Marge entre les composants

// Labels
        JLabel labelArticle = new JLabel("Article:");
        JLabel labelPrix = new JLabel("Prix:");
        JLabel labelStock = new JLabel("Stock:");
        JLabel labelJachete = new JLabel("J'achète:");

// TextFields
        textFieldArticle = new JTextField(20); // Vous pouvez spécifier la largeur ici
        textFieldPrix = new JTextField(20);
        textFieldStock = new JTextField(20);
        textFieldJachete = new JTextField(20);

// Ajout des composants avec GridBagConstraints
        constraints.gridx = 0; // Colonne 0
        constraints.gridy = 0; // Ligne 0
        centrePanel.add(labelArticle, constraints);

        constraints.gridx = 1; // Colonne 1
        centrePanel.add(textFieldArticle, constraints);

        constraints.gridx = 0; // Colonne 0
        constraints.gridy = 1; // Ligne 1
        centrePanel.add(labelPrix, constraints);

        constraints.gridx = 1; // Colonne 1
        centrePanel.add(textFieldPrix, constraints);

        constraints.gridx = 0; // Colonne 0
        constraints.gridy = 2; // Ligne 2
        centrePanel.add(labelStock, constraints);

        constraints.gridx = 1; // Colonne 1
        centrePanel.add(textFieldStock, constraints);

        constraints.gridx = 0; // Colonne 0
        constraints.gridy = 3; // Ligne 3
        centrePanel.add(labelJachete, constraints);

        constraints.gridx = 1; // Colonne 1
        centrePanel.add(textFieldJachete, constraints);




// Panneau droit
        JPanel droitPanel = new JPanel(new GridLayout(2, 1)); // Utilisation d'un GridLayout à 2 lignes et 1 colonne

// Créez le panneau pour les boutons de navigation
        JPanel boutonsNavigationPanel = new JPanel(new GridLayout(1, 2)); // Utilisation d'un GridLayout à 1 ligne et 2 colonnes

// Bouton <<
        boutonPrecedent = new JButton(" << ");
        boutonPrecedent.setEnabled(false);
        boutonsNavigationPanel.add(boutonPrecedent);

// Bouton >>
        boutonSuivant = new JButton(" >> ");
        boutonSuivant.setEnabled(false);
        boutonsNavigationPanel.add(boutonSuivant);

// Créez le panneau pour le bouton "Ajout panier"
        JPanel boutonAjoutPanierPanel = new JPanel();

// Bouton "Ajout panier"
        boutonAjoutPanier = new JButton("Ajout panier");
        boutonAjoutPanier.setEnabled(false);
        boutonAjoutPanierPanel.add(boutonAjoutPanier);

// Ajoutez les panneaux de boutons au panneau droit
        droitPanel.add(boutonsNavigationPanel);
        droitPanel.add(boutonAjoutPanierPanel);

// Ajout du panneau droit à votre magasinPanel existant
        magasinPanel.add(droitPanel);

// Ajout des sous-panneaux au magasinPanel
        magasinPanel.add(gauchePanel);
        magasinPanel.add(centrePanel);
        magasinPanel.add(droitPanel);

// Ajout du magasinPanel au panel principal
        centerSouthPanel.add(magasinPanel);


        // JPanel de Panier (en bas)
        panierPanel = new JPanel();
        panierPanel.setLayout(new GridLayout(1, 2)); // Grille avec 2 colonnes
        panierPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

// Modèle de la table pour le panier
        modelePanier = new DefaultTableModel(new Object[]{"Article", "Prix Unitaire", "Quantité"}, 0);

// Créer la JTable pour les articles
        tableArticles = new JTable(modelePanier);
        JScrollPane scrollPane = new JScrollPane(tableArticles); // Permet le défilement

// Panneau de gauche pour la JTable
        JPanel gauchePanierPanel = new JPanel(new BorderLayout());
        gauchePanierPanel.setPreferredSize(new Dimension(500, 100)); // Largeur 500
        gauchePanierPanel.add(scrollPane, BorderLayout.CENTER);

// Panneau de droite pour les boutons et le total
        JPanel droitPanierPanel = new JPanel();
        droitPanierPanel.setLayout(new BoxLayout(droitPanierPanel, BoxLayout.Y_AXIS));
        droitPanierPanel.setPreferredSize(new Dimension(300, 100)); // Largeur 300

// Boutons
        boutonSupprimer = new JButton("Supprimer Article");
        boutonSupprimer.setEnabled(false);

        boutonVider = new JButton("Vider Panier");
        boutonVider.setEnabled(false);

        boutonConfirmer = new JButton("Confirmer achat");
        boutonConfirmer.setEnabled(false);

        droitPanierPanel.add(boutonSupprimer);
        droitPanierPanel.add(boutonVider);
        droitPanierPanel.add(boutonConfirmer);

// Panel pour le total
        JPanel totalPanel = new JPanel();
        totalPanel.setLayout(new FlowLayout());
        JLabel labelTotal = new JLabel("Total : ");
        totalField = new JTextField(10); // Zone de texte pour le total
        totalPanel.add(labelTotal);
        totalPanel.add(totalField);

        droitPanierPanel.add(totalPanel);

// Ajout des sous-panneaux au panierPanel
        panierPanel.add(gauchePanierPanel);
        panierPanel.add(droitPanierPanel);

// Ajout du panierPanel au panel principal
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
        boutonSuivant.setEnabled(!etat);
        boutonPrecedent.setEnabled(!etat);
        boutonAjoutPanier.setEnabled(!etat);
        boutonSupprimer.setEnabled(!etat);
        boutonConfirmer.setEnabled(!etat);
        boutonVider.setEnabled(!etat);
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

    public String getTextFieldJacheteText() {
        return textFieldJachete.getText();
    }

    public JButton getBoutonPrecedent() {
        return boutonPrecedent;
    }

    public JButton getBoutonSuivant() {
        return boutonSuivant;
    }

    public JButton getBoutonAjoutPanier() {
        return boutonAjoutPanier;
    }

    public JButton getBoutonSupprimer() {
        return boutonSupprimer;
    }

    public JButton getBoutonVider() {
        return boutonVider;
    }

    public JButton getBoutonConfirmer() {
        return boutonConfirmer;
    }



    // --------------------------- SET
    public void setImageInGauchePanel(ImageIcon imageIcon) {
        imageLabel.setIcon(imageIcon);
    }
    public void setTextFieldArticleText(String text) {
        textFieldArticle.setText(text);
    }

    public void setTextFieldPrixText(String text) {
        textFieldPrix.setText(text);
    }

    public void setTextFieldStockText(String text) {
        textFieldStock.setText(text);
    }
    public void setTotal(String total) {
        totalField.setText(total);
    }
    public void mettreAJourPanier() {
        List<Article> articles = Singleton.getInstance().getPanier();
        DefaultTableModel modele = (DefaultTableModel) tableArticles.getModel();
        modele.setRowCount(0); // Efface les données existantes

        for (Article article : articles) {
            Object[] ligne = {
                    article.getDenomination(),
                    article.getPrix(),
                    article.getQuantiteDemandee()
            };
            modele.addRow(ligne); // Ajoute chaque article comme une ligne dans le modèle
        }

        // Mettre à jour le total
        setTotal(String.valueOf(Singleton.getInstance().getTotal()));
    }



    // ----------------------- MAIN
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VueLancement vueLancement = new VueLancement();
            vueLancement.setVisible(true);

            Timer timer = new Timer(500, e -> {
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
