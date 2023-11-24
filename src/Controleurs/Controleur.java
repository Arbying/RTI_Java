package Controleurs;

import GUI.VuePrincipale;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;



public class Controleur {

    private VuePrincipale vue;
    private Singleton modele;
    private JButton JBoutonLogin;


    public Controleur(VuePrincipale vue) {
        this.vue = null; // Initialisez la vue à null par défaut
        this.modele = Singleton.getInstance();

        JBoutonLogin = vue.getJBoutonLogin();
        JBoutonLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Gestion de l'événement du bouton JBoutonLogin
                String login = vue.getTextFieldLogin().getText();
                String mdp = vue.getTextFieldMDP().getText();

                // Affichez les valeurs en console
                System.out.println("Login : " + login);
                System.out.println("Mot de passe : " + mdp);
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
    }

    // Méthode pour définir la référence à la vue
    public void setVue(VuePrincipale vue) {
        this.vue = vue;
    }


    // Configurer les interactions entre la vue et le modèle ici
}
