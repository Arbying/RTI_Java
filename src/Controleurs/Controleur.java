package Controleurs;

import GUI.VuePrincipale;
import Modele.*;

public class Controleur {

    private VuePrincipale vue;
    private Singleton modele;

    public Controleur() {
        this.vue = null; // Initialisez la vue à null par défaut
        this.modele = Singleton.getInstance();
    }

    // Méthode pour définir la référence à la vue
    public void setVue(VuePrincipale vue) {
        this.vue = vue;
    }

    // Configurer les interactions entre la vue et le modèle ici
}
