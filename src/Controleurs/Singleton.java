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
        client = null;
        articleEnCours = null;
        panier = new ArrayList<>();
        total = 0.0f;
        estConnecte = false;
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public void majTotal() {
        total = 0.0f;
        for (Article article : panier) {
            total += article.getPrix() * (float) article.getQuantiteDemandee();
        }
    }

    // Getters et setters pour les attributs

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

    public boolean isEstConnecte() {
        return estConnecte;
    }

    public void setEstConnecte(boolean estConnecte) {
        this.estConnecte = estConnecte;
    }
}
