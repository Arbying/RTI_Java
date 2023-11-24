package Tests;

import Controleurs.Singleton;
import Modele.Article;
import Modele.Client;

import java.util.ArrayList;

public class TestSingleton {

    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance();

        // Test pour s'assurer que Singleton n'est pas null
        if (singleton == null) {
            System.out.println("Erreur : Singleton est null");
        } else {
            System.out.println("Test Singleton instance : Réussi");
        }

        // Test pour s'assurer que Singleton retourne la même instance
        if (singleton != Singleton.getInstance()) {
            System.out.println("Erreur : Les instances de Singleton ne sont pas les mêmes");
        } else {
            System.out.println("Test Singleton unique instance : Réussi");
        }

        // Réinitialiser l'état du singleton pour le test des setters et getters
        singleton.setClient(null);
        singleton.setArticleEnCours(null);
        singleton.setPanier(new ArrayList<>());
        singleton.setTotal(0.0f);
        singleton.setEstConnecte(false);

        // Test des setters et getters
        Client client = new Client("username", "password", true);
        singleton.setClient(client);
        Article article = new Article(1, "Légume", 3.99f, 10, 5, "imagePath");
        singleton.setArticleEnCours(article);
        singleton.setPanier(new ArrayList<>());
        singleton.setTotal(100.0f);
        singleton.setEstConnecte(true);

        if (singleton.getClient() != client ||
                singleton.getArticleEnCours() != article ||
                singleton.getPanier().size() != 0 ||
                singleton.getTotal() != 100.0f ||
                !singleton.isEstConnecte()) {
            System.out.println("Erreur : Les getters et setters de Singleton ne fonctionnent pas correctement");
        } else {
            System.out.println("Test getters et setters : Réussi");
        }

        // Test de la méthode majTotal
        ArrayList<Article> panier = new ArrayList<>();
        panier.add(new Article(1, "Tomate", 1.50f, 20, 3, "imagePath1"));
        panier.add(new Article(2, "Carotte", 2.00f, 15, 2, "imagePath2"));
        singleton.setPanier(panier);

        singleton.majTotal();

        float totalAttendu = 1.50f * 3 + 2.00f * 2; // Calcul du total attendu
        if (singleton.getTotal() != totalAttendu) {
            System.out.println("Erreur : La méthode majTotal ne calcule pas correctement le total");
        } else {
            System.out.println("Test de la méthode majTotal : Réussi");
        }
    }
}
