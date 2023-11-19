package Tests;

import Modele.Client;

public class TestClient {
    public static void main(String[] args) {
        // Création d'un nouveau client
        Client client = new Client("utilisateur1", "motDePasse123", true);

        // Affichage des informations initiales du client
        System.out.println("Login: " + client.getLogin());
        System.out.println("Mot de passe: " + client.getMotDePasse());
        System.out.println("Nouveau client: " + client.isEstNouveau());

        // Modification du mot de passe
        client.setMotDePasse("nouveauMotDePasse456");

        // Affichage des informations mises à jour du client
        System.out.println("Nouveau mot de passe: " + client.getMotDePasse());
    }
}
