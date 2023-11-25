package AutresThreads;

import Controleurs.Singleton;
import Protocoles.TCP;

import java.net.Socket;

public class ThValiderPanier extends Thread {
    @Override
    public void run() {

        Singleton singleton = Singleton.getInstance();
        Socket maSocket = singleton.getMaSocket();


        if (maSocket == null) {
            System.out.println("La socket n'est pas initialisée dans le Singleton.");
            return;
        }

        try {

            String requete = "VALIDERPANIER";
            System.out.println("Envoi de la requête : " + requete);
            byte[] requeteBytes = requete.getBytes();
            int sentBytes = TCP.send(maSocket, requeteBytes, requeteBytes.length);

            if (sentBytes == -1) {
                System.out.println("Erreur lors de l'envoi de la requête.");
                return;
            }

            byte[] buffer = new byte[1500];
            int bytesRead = TCP.receive(maSocket, buffer);
            Singleton.getInstance().ViderPanier();


        } catch (Exception e) {

            e.printStackTrace();
        }


        this.interrupt();
    }
}
