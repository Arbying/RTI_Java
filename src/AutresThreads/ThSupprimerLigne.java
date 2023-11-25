package AutresThreads;

import Controleurs.Singleton;
import Protocoles.TCP;

import java.net.Socket;

public class ThSupprimerLigne extends Thread {
    private int ligne;

    public ThSupprimerLigne(int ligne) {
        this.ligne = ligne;
    }

    @Override
    public void run() {
        Singleton singleton = Singleton.getInstance();
        Socket maSocket = singleton.getMaSocket();

        if (maSocket == null) {
            System.out.println("La socket n'est pas initialisée dans le Singleton.");
            return;
        }

        try {
            String requete = "SUPPRESSION#" + ligne;
            System.out.println("Envoi de la requête : " + requete);
            byte[] requeteBytes = requete.getBytes();
            int sentBytes = TCP.send(maSocket, requeteBytes, requeteBytes.length);

            if (sentBytes == -1) {
                System.out.println("Erreur lors de l'envoi de la requête.");
                return;
            }

            byte[] buffer = new byte[1500];
            int bytesRead = TCP.receive(maSocket, buffer);

            if (bytesRead > 0) {
                // Traiter la réponse si nécessaire
                String reponse = new String(buffer, 0, bytesRead);
                System.out.println("Réponse du serveur : " + reponse);

                // Supprimer la ligne du panier dans le Singleton
                singleton.EnleveDuPanier(ligne);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        this.interrupt();
    }
}
