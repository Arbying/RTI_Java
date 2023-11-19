package Tests;

import Modele.Article;

public class TestArticle {
    public static void main(String[] args) {
        // Création d'un nouvel article
        Article article = new Article(1, "Ordinateur portable", 999.99f, 10, 0, "image_ordinateur.jpg");

        // Affichage des informations de l'article
        System.out.println("ID de l'article: " + article.getIdArticle());
        System.out.println("Dénomination: " + article.getDenomination());
        System.out.println("Prix: " + article.getPrix());
        System.out.println("Quantité disponible: " + article.getQuantiteDisponible());
        System.out.println("Quantité demandée: " + article.getQuantiteDemandee());
        System.out.println("Image: " + article.getImage());

        // Modification du prix
        article.setPrix(899.99f);

        // Affichage des informations mises à jour de l'article
        System.out.println("Nouveau prix: " + article.getPrix());
    }
}
