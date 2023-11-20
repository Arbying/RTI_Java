package Tests;

import Protocoles.TCP;
import Protocoles.OVESP;

import java.io.IOException;
import java.net.Socket;

public class TestOVESP {

    public static void main(String[] args) {
        // Paramètres du serveur
        String serverIP = "192.168.100.8";
        int serverPort = 50406;

        // Paramètres du client
        Socket clientSocket = TCP.createClientSocket(serverIP, serverPort);

        if (clientSocket != null) {
            // Données à envoyer
            String requestData = "LOGIN#test#test#0";
            byte[] requestDataBytes = requestData.getBytes();
            int requestDataSize = requestDataBytes.length;

            // Envoi des données
            int sentBytes = TCP.send(clientSocket, requestDataBytes, requestDataSize);

            if (sentBytes != -1) {
                System.out.println("Données OVESP envoyées avec succès.");

                // Réception de la réponse du serveur
                byte[] responseData = new byte[1024]; // Ajuste la taille du tableau en fonction de la taille attendue de la réponse
                int receivedBytes = TCP.receive(clientSocket, responseData);

                if (receivedBytes != -1) {
                    String response = new String(responseData, 0, receivedBytes);
                    System.out.println("Réponse du serveur OVESP : " + response);

                    // Appel de la méthode OVESP avec la réponse du serveur
                    String requete = OVESP.processResponse(response);

                    // ON TEST LA CONNECTION :

                    if (requete.equals("0"))
                    {
                        System.out.println("ERREUR DE LOGIN");
                    }
                    else
                    {
                        System.out.println("SUPER - ON VA ENVOYER UNE REQUETE ARTICLE SUIVANT ART 0");
                    }

                    // Affichage de la requete générée
                    System.out.println("----------------Requête générée par OVESP : " + requete);
                } else {
                    System.err.println("Erreur lors de la réception de la réponse OVESP.");
                }

                // Fermer le socket client
                try {
                    clientSocket.close();
                    System.out.println("Socket client fermé.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.err.println("Erreur lors de l'envoi des données OVESP.");
            }
        } else {
            System.err.println("Erreur lors de la création du socket client.");
        }
    }
}
