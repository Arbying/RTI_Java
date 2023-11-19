package Modele;

import java.util.ArrayList;
import java.util.List;

public class Panier {
    private List<Article> articles;

    public Panier() {
        this.articles = new ArrayList<>();
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void ajouterArticle(Article article) {
        articles.add(article);
    }

    public void supprimerArticle(Article article) {
        articles.remove(article);
    }

}
