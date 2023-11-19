package GUI;

import Controleurs.Controleur;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VuePrincipale extends JFrame {
    private Controleur controleur;

    public VuePrincipale(Controleur controleur) {
        super("Boutique en ligne");
        this.controleur = controleur;
        setSize(850, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComposants();
        setVisible(true);
    }

    private void initComposants() {
        JMenuBar menuBar = new JMenuBar();

        // Menu Fichier
        JMenu menuFichier = new JMenu("Fichier");

        JMenuItem itemConnection = new JMenuItem("Connection");
        JMenuItem itemDeconnection = new JMenuItem("Déconnection");
        JMenuItem itemQuitter = new JMenuItem("Quitter");

        itemConnection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controleur.connection();
            }
        });

        itemDeconnection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controleur.deconnection();
            }
        });

        itemQuitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controleur.quitter();
            }
        });

        menuFichier.add(itemConnection);
        menuFichier.add(itemDeconnection);
        menuFichier.addSeparator();
        menuFichier.add(itemQuitter);

        menuBar.add(menuFichier);

        // Menu Navigation
        JMenu menuNavigation = new JMenu("Navigation");

        JMenuItem itemSuivant = new JMenuItem("Suivant");
        JMenuItem itemPrecedent = new JMenuItem("Précédent");

        itemSuivant.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action pour le menu Suivant
                JOptionPane.showMessageDialog(VuePrincipale.this, "Action Suivant");
            }
        });

        itemPrecedent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action pour le menu Précédent
                JOptionPane.showMessageDialog(VuePrincipale.this, "Action Précédent");
            }
        });

        menuNavigation.add(itemSuivant);
        menuNavigation.add(itemPrecedent);

        menuBar.add(menuNavigation);

        // Menu Panier
        JMenu menuPanier = new JMenu("Panier");

        JMenuItem itemAjouterPanier = new JMenuItem("Ajouter");
        JMenuItem itemSupprimerPanier = new JMenuItem("Supprimer");
        JMenuItem itemViderPanier = new JMenuItem("Vider");

        itemAjouterPanier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action pour le menu Ajouter Panier
                JOptionPane.showMessageDialog(VuePrincipale.this, "Action Ajouter Panier");
            }
        });

        itemSupprimerPanier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action pour le menu Supprimer Panier
                JOptionPane.showMessageDialog(VuePrincipale.this, "Action Supprimer Panier");
            }
        });

        itemViderPanier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action pour le menu Vider Panier
                JOptionPane.showMessageDialog(VuePrincipale.this, "Action Vider Panier");
            }
        });

        menuPanier.add(itemAjouterPanier);
        menuPanier.add(itemSupprimerPanier);
        menuPanier.add(itemViderPanier);

        menuBar.add(menuPanier);

        // Menu Achat
        JMenu menuAchat = new JMenu("Achat");

        JMenuItem itemValiderPanier = new JMenuItem("Valider Panier");

        itemValiderPanier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action pour le menu Valider Panier
                JOptionPane.showMessageDialog(VuePrincipale.this, "Action Valider Panier");
            }
        });

        menuAchat.add(itemValiderPanier);

        menuBar.add(menuAchat);

        // Menu ?
        JMenu menuQuestion = new JMenu("?");

        JMenuItem itemAide = new JMenuItem("Aide");
        JMenuItem itemApropos = new JMenuItem("A propos");

        itemAide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action pour le menu Aide
                JOptionPane.showMessageDialog(VuePrincipale.this, "Action Aide");
            }
        });

        itemApropos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action pour le menu A propos
                JOptionPane.showMessageDialog(VuePrincipale.this, "Action A propos");
            }
        });

        menuQuestion.add(itemAide);
        menuQuestion.add(itemApropos);

        menuBar.add(menuQuestion);

        setJMenuBar(menuBar);
    }

    public static void main(String[] args) {
        // Création de la vue de lancement
        VueLancement vueLancement = new VueLancement();

        // Définir une minuterie pour fermer la vue de lancement après 5 secondes
        Timer timer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Fermer la vue de lancement après 5 secondes
                vueLancement.dispose();

                // Création du contrôleur et de la vue principale
                Controleur controleur = new Controleur();
                new VuePrincipale(controleur);
            }
        });

        // Démarrer la minuterie
        timer.setRepeats(false); // Ne répète pas la minuterie
        timer.start();
    }
}
