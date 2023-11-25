package Controleurs;

import Modele.*;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Singleton {
    private static Singleton instance;

    private Client client;
    private int IdArticleEnCours = 0;

    private int artSuivantPrecedent = 0;  // Nouvel attribut
    private Article articleEnCours;
    private List<Article> panier;
    private float total;
    private boolean estConnecte;
    private Socket maSocket;

    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private PropertyChangeSupport idArticleChangeSupport = new PropertyChangeSupport(this);

    private Singleton() {
        client = null;
        articleEnCours = null;
        panier = new ArrayList<>();
        total = 0.0f;
        estConnecte = false;
        maSocket = null;
    }

    public static synchronized Singleton getInstance() {
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

    public int getIdArticleEnCours() {
        return IdArticleEnCours;
    }

    public void setIdArticleEnCours(int IdArticleEnCours) {
        int oldIdArticleEnCours = this.IdArticleEnCours;
        this.IdArticleEnCours = IdArticleEnCours;
        idArticleChangeSupport.firePropertyChange("IdArticleEnCours", oldIdArticleEnCours, IdArticleEnCours);
        System.out.println("Changement de IdArticleEnCours : " + IdArticleEnCours);
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
        boolean oldEstConnecte = this.estConnecte;
        this.estConnecte = estConnecte;
        changeSupport.firePropertyChange("estConnecte", oldEstConnecte, estConnecte);
    }

    public Socket getMaSocket() {
        return maSocket;
    }

    public void setMaSocket(Socket maSocket) {
        this.maSocket = maSocket;
    }

    public int getArtSuivantPrecedent() {
        return artSuivantPrecedent;
    }

    public void setArtSuivantPrecedent(int artSuivantPrecedent) {
        this.artSuivantPrecedent = artSuivantPrecedent;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

    public void addIdArticleChangeListener(PropertyChangeListener listener) {
        idArticleChangeSupport.addPropertyChangeListener(listener);
    }

    public void removeIdArticleChangeListener(PropertyChangeListener listener) {
        idArticleChangeSupport.removePropertyChangeListener(listener);
    }
}
