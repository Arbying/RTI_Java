package AutresThreads;

import Controleurs.Singleton;
import Protocoles.TCP;

import java.net.Socket;

public class ThLogout extends Thread {
    @Override
    public void run() {
        // Récupérez la socket du Singleton
        Singleton singleton = Singleton.getInstance();
        Socket maSocket = singleton.getMaSocket();

        // Vérifiez si la socket existe
        if (maSocket == null) {
            System.out.println("La socket n'est pas initialisée dans le Singleton.");
            return;
        }

        try {
            // Envoyez la requête "LOGOUT" au serveur
            String requete = "LOGOUT";
            byte[] requeteBytes = requete.getBytes();
            int sentBytes = TCP.send(maSocket, requeteBytes, requeteBytes.length);

            if (sentBytes == -1) {
                System.out.println("Erreur lors de l'envoi de la requête LOGOUT.");
            }

            // Mettez à jour le modèle (Singleton) pour marquer l'utilisateur comme déconnecté
            singleton.setEstConnecte(false);
            this.interrupt();
        } catch (Exception e) {
            // Gérer l'exception ici
            e.printStackTrace();
        }
    }
}
