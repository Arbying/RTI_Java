package Controleurs;

import Modele.*;

import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class Singleton {
    private static Singleton instance;
    private final PropertyChangeSupport pcs;

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
        pcs = new PropertyChangeSupport(this);
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
        Client oldClient = this.client;
        this.client = client;
        pcs.firePropertyChange("client", oldClient, client);
    }

    public Article getArticleEnCours() {
        return articleEnCours;
    }

    public void setArticleEnCours(Article articleEnCours) {
        Article oldArticleEnCours = this.articleEnCours;
        this.articleEnCours = articleEnCours;
        pcs.firePropertyChange("articleEnCours", oldArticleEnCours, articleEnCours);
    }

    public List<Article> getPanier() {
        return panier;
    }

    public void setPanier(List<Article> panier) {
        List<Article> oldPanier = this.panier;
        this.panier = panier;
        pcs.firePropertyChange("panier", oldPanier, panier);
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        float oldTotal = this.total;
        this.total = total;
        pcs.firePropertyChange("total", oldTotal, total);
    }

    public boolean isEstConnecte() {
        return estConnecte;
    }

    public void setEstConnecte(boolean estConnecte) {
        boolean oldEstConnecte = this.estConnecte;
        this.estConnecte = estConnecte;
        pcs.firePropertyChange("estConnecte", oldEstConnecte, estConnecte);
    }

    // Méthodes pour gérer les auditeurs
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }
}
