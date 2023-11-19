package Tests;

import Modele.Article;
import Modele.Panier;

public class TestPanier {
    public static void main(String[] args) {
        // Création d'un panier
        Panier panier = new Panier();

        // Création de quelques articles
        Article article1 = new Article(1, "Ordinateur portable", 999.99f, 10, 0, "image_ordinateur.jpg");
        Article article2 = new Article(2, "Smartphone", 599.99f, 20, 0, "image_smartphone.jpg");

        // Ajout des articles au panier
        panier.ajouterArticle(article1);
        panier.ajouterArticle(article2);

        // Affichage des articles dans le panier
        System.out.println("Articles dans le panier:");
        for (Article article : panier.getArticles()) {
            System.out.println("ID de l'article: " + article.getIdArticle());
            System.out.println("Dénomination: " + article.getDenomination());
            System.out.println("Prix: " + article.getPrix());
            System.out.println("Quantité disponible: " + article.getQuantiteDisponible());
            System.out.println("Quantité demandée: " + article.getQuantiteDemandee());
            System.out.println("Image: " + article.getImage());
            System.out.println("-------------------------");
        }

        // Suppression d'un article du panier
        panier.supprimerArticle(article1);

        // Affichage des articles restants dans le panier
        System.out.println("Articles restants dans le panier:");
        for (Article article : panier.getArticles()) {
            System.out.println("ID de l'article: " + article.getIdArticle());
            System.out.println("Dénomination: " + article.getDenomination());
            System.out.println("Prix: " + article.getPrix());
            System.out.println("Quantité disponible: " + article.getQuantiteDisponible());
            System.out.println("Quantité demandée: " + article.getQuantiteDemandee());
            System.out.println("Image: " + article.getImage());
            System.out.println("-------------------------");
        }
    }
}
