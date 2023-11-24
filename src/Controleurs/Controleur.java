package Controleurs;

import GUI.VuePrincipale;
import AutresThreads.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Controleur {

    private VuePrincipale vue;
    private Singleton modele;

    private JButton JBoutonLogin;
    private JButton JBoutonLogout;

    public Controleur(VuePrincipale vue) {
        this.vue = vue;
        this.modele = Singleton.getInstance();

        JBoutonLogin = vue.getJBoutonLogin();
        JBoutonLogout = vue.getJBoutonLogout();

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

        //PARTIE CHANGER MOT DE PASSE
        vue.getChangerMdpItem().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                afficherBoiteDialogue();
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
                }
                // Ajoutez d'autres gestionnaires de propriété au besoin
            }
        });

        // PARTIE LOGIN (élément de menu)
        vue.getLoginItem().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleLoginButtonClick();
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
}
