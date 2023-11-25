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
    private JButton boutonSupprimer;

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
        boutonSupprimer = vue.getBoutonSupprimer();

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

        boutonSupprimer.addActionListener(e -> handleBoutonSupprimerClick());
    }

    private void quitterApplication() {
        if (Singleton.getInstance().isEstConnecte()) {
            ThLogout thLogout = new ThLogout();
            thLogout.start();
        }
        System.exit(0);
    }

    private void handleLoginButtonClick() {
        String login = vue.getTextFieldLogin().getText();
        String mdp = vue.getTextFieldMDP().getText();
        boolean NouveauClient = vue.getEstNouveau().isSelected();
        ThLogin thLogin = new ThLogin(login, mdp, NouveauClient);
        thLogin.start();
    }

    private void handleLogoutButtonClick() {
        modele.setEstConnecte(false);
        ThLogout thLogout = new ThLogout();
        thLogout.start();
        Singleton.getInstance().ViderPanier();
    }

    private void afficherBoiteDialogue() {
        JOptionPane.showMessageDialog(vue, "Cette fonctionnalité est réservée à la version payante :P", "DEMO", JOptionPane.INFORMATION_MESSAGE);
    }

    private void handleBoutonPrecedentClick() {
        if(Singleton.getInstance().getArtSuivantPrecedent() < 2) {
            JOptionPane.showMessageDialog(vue, "Vous êtes au bout du rouleau !", "Information", JOptionPane.INFORMATION_MESSAGE);
        } else {
            Singleton.getInstance().setArtSuivantPrecedent(Singleton.getInstance().getIdArticleEnCours()-2);
            ThChangeArtcile thArt = new ThChangeArtcile();
            thArt.start();
        }
    }

    private void handleBoutonSuivantClick() {
        ThChangeArtcile thArt = new ThChangeArtcile();
        thArt.start();
    }

    private void handleBoutonAjoutPanierClick() {
        // Logique pour ajouter un article au panier
        System.out.println("J'ai clické sur ajouter au panier");
        String quantiteSaisie = vue.getTextFieldJacheteText();
        try {
            int quantite = Integer.parseInt(quantiteSaisie);
            if(quantite > 0)
            {
                Singleton.getInstance().setQuDemande(quantite);
                ThAchat thachat = new ThAchat();
                thachat.start();
            }
            else
            {
                JOptionPane.showMessageDialog(vue, "Veuillez entrer un nombre valide pour la quantité", "Erreur de Quantité", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vue, "Veuillez entrer un nombre valide pour la quantité", "Erreur de Quantité", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleBoutonViderClick() {
        // Logique pour vider le panier
        System.out.println("Bouton Vider le panier cliqué");
        ThVidePanier thVidePanier = new ThVidePanier();
        thVidePanier.start();
    }

    private void handleBoutonConfirmerClick() {
        // Logique pour confirmer le panier
        System.out.println("Bouton Confirmer le panier cliqué");
        ThValiderPanier thValiderPanier = new ThValiderPanier();
        thValiderPanier.start();
    }

    private void handleBoutonSupprimerClick() {
        int indice = vue.getIndiceTableau();
        if (indice != -1) {
            ThSupprimerLigne thSupprimerLigne = new ThSupprimerLigne(indice);
            thSupprimerLigne.start();
        } else {
            JOptionPane.showMessageDialog(vue, "Aucun article sélectionné", "Erreur de Suppression", JOptionPane.ERROR_MESSAGE);
        }
    }



}
