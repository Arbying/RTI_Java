package Controleurs;

import Modele.Article;
import Modele.Client;

import java.util.ArrayList;
import java.util.List;

public class Singleton {
    private static Singleton instance;

    private Client client;
    private Article articleEnCours;
    private List<Article> panier;
    private float total;
    private boolean estConnecte;

    private Singleton() {
        client = null; // Aucun client connecté au début
        articleEnCours = null; // Aucun article sélectionné au début
        panier = new ArrayList<>(); // Initialisation du panier
        total = 0.0f; // Initialisation du total à 0
        estConnecte = false; // Le client n'est pas connecté au début
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    // Getters et Setters pour chaque attribut

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Article getArticleEnCours() {
        return articleEnCours;
    }

    public void setArticleEnCours(Article articleEnCours) {
        this.articleEnCours = articleEnCours;
    }

    public List<Article> getPanier() {
        return panier;
    }

    public void setPanier(List<Article> panier) {
        this.panier = panier;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public void majTotal() {
        total = 0.0f; // Réinitialiser le total à 0

        for (Article article : panier) {
            total += article.getPrix() * (float) article.getQuantiteDemandee();
        }
    }

    public boolean isEstConnecte() {
        return estConnecte;
    }

    public void setEstConnecte(boolean estConnecte) {
        this.estConnecte = estConnecte;
    }

    // Vous pouvez ajouter d'autres méthodes utiles ici, comme pour ajouter/supprimer des articles du panier, etc.
}
