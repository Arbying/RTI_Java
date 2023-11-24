package Controleurs;

import GUI.VuePrincipale;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Controleur {

    private VuePrincipale vue;
    private Singleton modele;

    public Controleur() {
        this.vue = null; // Initialisez la vue à null par défaut
        this.modele = Singleton.getInstance();

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
