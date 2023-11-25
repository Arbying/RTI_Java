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

    public Controleur(VuePrincipale vue) {
        this.vue = vue;
        this.modele = Singleton.getInstance();

        JBoutonLogin = vue.getJBoutonLogin();
        JBoutonLogout = vue.getJBoutonLogout();
        boutonPrecedent = vue.getBoutonPrecedent();
        boutonSuivant = vue.getBoutonSuivant();
        boutonAjoutPanier = vue.getBoutonAjoutPanier();

        // MENUS
        // FICHIER
        vue.getQuitterItem().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                quitterApplication();
            }
        });

        // PARTIE LOGIN
        JBoutonLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleLoginButtonClick();
            }
        });

        // PARTIE LOGOUT
        vue.getLogoutItem().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleLogoutButtonClick();
            }
        });

        // PARTIE CHANGER MOT DE PASSE
        vue.getChangerMdpItem().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                afficherBoiteDialogue();
            }
        });

        // Ajoutez un écouteur de changement de propriété au modèle pour IdArticleEnCours
        modele.addIdArticleChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                // Mettez à jour la vue en fonction du nouvel IDArticleEnCours ici
                System.out.println("Changement de IdArticleEnCours ************** : " + evt.getNewValue());

                String AdresseImage = Singleton.getInstance().getArticleEnCours().getImage();
                String cheminImages = "images/"; // Le chemin vers le dossier images

                // Combiner le chemin du dossier images avec le nom de l'image
                String adresseImageComplete = cheminImages + AdresseImage;

                // Charger l'image en tant qu'ImageIcon
                ImageIcon imageIcon = new ImageIcon(adresseImageComplete); // Assurez-vous que AdresseImage est un chemin valide
                System.out.println(adresseImageComplete);

                // Redimensionner l'image si nécessaire
                Image image = imageIcon.getImage(); // Transformer l'ImageIcon en Image pour redimensionnement
                Image newimg = image.getScaledInstance(220, 220, java.awt.Image.SCALE_SMOOTH); // Redimensionner
                imageIcon = new ImageIcon(newimg); // Re-transformer en ImageIcon

                // Mettre à jour la vue avec la nouvelle image
                vue.setImageInGauchePanel(imageIcon);
                System.out.println("Vue mise à jour ... ");

                vue.setTextFieldArticleText(Singleton.getInstance().getArticleEnCours().getDenomination());
                vue.setTextFieldStockText(String.valueOf(Singleton.getInstance().getArticleEnCours().getQuantiteDisponible()));
                vue.setTextFieldPrixText(String.valueOf(Singleton.getInstance().getArticleEnCours().getPrix()));
            }
        });

        // Ajoutez un écouteur de changement de propriété au modèle
        modele.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                // Gérez ici les changements de propriété du modèle
                String propertyName = evt.getPropertyName();
                if ("estConnecte".equals(propertyName)) {
                    boolean nouvelleValeur = (boolean) evt.getNewValue();
                    // Mettez à jour la vue en fonction de la nouvelle valeur
                    vue.setModificationEtat(!nouvelleValeur);
                } else if ("IdArticleEnCours".equals(propertyName)) {
                    System.out.println("J AI ETE PREVENU !!!!!!!!!!!!!!!!");
                    // Récupération de l'adresse de l'image de l'article en cours
                } else {
                    System.out.println("RIEN");
                }
            }
        });

        // PARTIE LOGIN (élément de menu)
        vue.getLoginItem().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleLoginButtonClick();
            }
        });

        // PARTIE BOUTON PRECEDENT
        boutonPrecedent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleBoutonPrecedentClick();
            }
        });

        // PARTIE BOUTON SUIVANT
        boutonSuivant.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleBoutonSuivantClick();
            }
        });

        // PARTIE BOUTON AJOUT PANIER
        boutonAjoutPanier.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleBoutonAjoutPanierClick();
            }
        });
    }

    // Méthode pour définir la référence à la vue
    public void setVue(VuePrincipale vue) {
        this.vue = vue;
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

        // Affichez les valeurs en console
        System.out.println("Login : " + login);
        System.out.println("Mot de passe : " + mdp);

        ThLogin thLogin = new ThLogin(login, mdp, NouveauClient);
        thLogin.start();
        System.out.println("------------------------------------");
    }

    private void handleLogoutButtonClick() {
        modele.setEstConnecte(false);
        ThLogout thLogout = new ThLogout();
        thLogout.start();
    }

    // Méthode pour afficher la boîte de dialogue
    private void afficherBoiteDialogue() {
        JOptionPane.showMessageDialog(vue, "Cette fonctionnalité est réservée à la version payante :P", "DEMO", JOptionPane.INFORMATION_MESSAGE);
    }

    // Méthodes pour gérer les clics sur les boutons

    private void handleBoutonPrecedentClick() {
        System.out.println("Précédent");
        if(Singleton.getInstance().getIdArticleEnCours() < 2)
        {
            JOptionPane.showMessageDialog(vue, "Vous êtes au bout du rouleau !", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {

        }
    }

    private void handleBoutonSuivantClick() {
        System.out.println("Suivant");
    }

    private void handleBoutonAjoutPanierClick() {
        System.out.println("Ajouté au panier");
    }
}
