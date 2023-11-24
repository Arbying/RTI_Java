package AutresThreads;

import Controleurs.Singleton;
import Protocoles.TCP;

import java.net.Socket;

public class ThChangeArtcile extends Thread {
    @Override
    public void run() {
        System.out.println("JE SUIS LE THREAD ARTICLE");

        // Récupérez la socket du Singleton
        Singleton singleton = Singleton.getInstance();
        Socket maSocket = singleton.getMaSocket();
        System.out.println("BLABLABLABLABLABLA");

        // Vérifiez si la socket existe
        if (maSocket == null) {
            System.out.println("La socket n'est pas initialisée dans le Singleton.");
            return;
        }

        try {
            // Envoyez la requête "ARTSUIVANT#0" au serveur
            String requete = "ARTSUIVANT#0";
            byte[] requeteBytes = requete.getBytes();
            int sentBytes = TCP.send(maSocket, requeteBytes, requeteBytes.length);

            if (sentBytes == -1) {
                System.out.println("Erreur lors de l'envoi de la requête.");
                return;
            }

            // Attendez la réponse du serveur
            byte[] buffer = new byte[1500]; // Définissez la taille du tampon selon vos besoins
            int bytesRead = TCP.receive(maSocket, buffer);

            if (bytesRead > 0) {
                String reponse = new String(buffer, 0, bytesRead);

                // Affichez la réponse
                System.out.println("Réponse du serveur : " + reponse);
            }
        } catch (Exception e) {
            // Gérer l'exception ici
            e.printStackTrace();
        }
        this.interrupt();
    }
}
