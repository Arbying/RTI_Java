package Controleurs;

import GUI.VuePrincipale;
import AutresThreads.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Controleur {

    private VuePrincipale vue;
    private Singleton modele;

    private JButton JBoutonLogin;
    private JButton JBoutonLogout;
    private JButton boutonPrecedent;
    private JButton boutonSuivant;
    private JButton boutonAjoutPanier;
    private JButton boutonVider;
    private JButton boutonConfirmer;

    public Controleur(VuePrincipale vue) {
        this.vue = vue;
        this.modele = Singleton.getInstance();

        JBoutonLogin = vue.getJBoutonLogin();
        JBoutonLogout = vue.getJBoutonLogout();
        boutonPrecedent = vue.getBoutonPrecedent();
        boutonSuivant = vue.getBoutonSuivant();
        boutonAjoutPanier = vue.getBoutonAjoutPanier();
        boutonVider = vue.getBoutonVider();
        boutonConfirmer = vue.getBoutonConfirmer();

        // MENUS
        // FICHIER
        vue.getQuitterItem().addActionListener(e -> quitterApplication());

        // PARTIE LOGIN
        JBoutonLogin.addActionListener(e -> handleLoginButtonClick());

        // PARTIE LOGOUT
        vue.getLogoutItem().addActionListener(e -> handleLogoutButtonClick());

        // PARTIE CHANGER MOT DE PASSE
        vue.getChangerMdpItem().addActionListener(e -> afficherBoiteDialogue());

        // Écouteur pour IdArticleEnCours
        modele.addIdArticleChangeListener(evt -> {
            String adresseImage = "images/" + Singleton.getInstance().getArticleEnCours().getImage();
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(adresseImage).getImage().getScaledInstance(220, 220, Image.SCALE_SMOOTH));
            vue.setImageInGauchePanel(imageIcon);

            vue.setTextFieldArticleText(Singleton.getInstance().getArticleEnCours().getDenomination());
            vue.setTextFieldStockText(String.valueOf(Singleton.getInstance().getArticleEnCours().getQuantiteDisponible()));
            vue.setTextFieldPrixText(String.valueOf(Singleton.getInstance().getArticleEnCours().getPrix()));
        });

        // Écouteur pour les changements de propriété généraux
        modele.addPropertyChangeListener(evt -> {
            if ("estConnecte".equals(evt.getPropertyName())) {
                vue.setModificationEtat(!(boolean) evt.getNewValue());
            }
        });

        // Écouteur pour le changement de total
        modele.addTotalChangeListener(evt -> {
            System.out.println("*******+++++++++++++++***** Nouveau Total: " + evt.getNewValue());
            // 1. MAJ TOTAL INTERFACE
            vue.setTotal(String.valueOf(Singleton.getInstance().getTotal()));
            // 2. MISE A JOUR TABLEAU
            vue.mettreAJourPanier();
        });

        // PARTIE LOGIN (élément de menu)
        vue.getLoginItem().addActionListener(e -> handleLoginButtonClick());

        // PARTIE BOUTON PRECEDENT
        boutonPrecedent.addActionListener(e -> handleBoutonPrecedentClick());

        // PARTIE BOUTON SUIVANT
        boutonSuivant.addActionListener(e -> handleBoutonSuivantClick());

        // PARTIE BOUTON AJOUT PANIER
        boutonAjoutPanier.addActionListener(e -> handleBoutonAjoutPanierClick());

        // PARTIE BOUTON VIDER
        boutonVider.addActionListener(e -> handleBoutonViderClick());

        // PARTIE BOUTON CONFIRMER
        boutonConfirmer.addActionListener(e -> handleBoutonConfirmerClick());
    }

    // ... autres méthodes privées (quitterApplication, handleLoginButtonClick, etc.) ...

    private void handleBoutonConfirmerClick() {
        // Logique pour confirmer le panier
        System.out.println("Bouton Confirmer le panier cliqué");
        ThValiderPanier thValiderPanier = new ThValiderPanier();
        thValiderPanier.start();
    }

    // Autres méthodes et logique du contrôleur
}
