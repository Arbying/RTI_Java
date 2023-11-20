package Tests;

import Protocoles.TCP;

import java.io.IOException;
import java.net.Socket;

public class TestTCP {

    public static void main(String[] args) {
        // Paramètres du serveur
        String serverIP = "192.168.100.8";
        int serverPort = 50406;

        // Paramètres du client
        Socket clientSocket = TCP.createClientSocket(serverIP, serverPort);

        if (clientSocket != null) {
            // Données à envoyer pour LOGIN
            String loginRequestData = "LOGIN#arber#arber#0";
            byte[] loginRequestDataBytes = loginRequestData.getBytes();
            int loginRequestDataSize = loginRequestDataBytes.length;

            // Envoi des données pour LOGIN
            int sentLoginBytes = TCP.send(clientSocket, loginRequestDataBytes, loginRequestDataSize);

            if (sentLoginBytes != -1) {
                System.out.println("Données de LOGIN envoyées avec succès.");

                // Réception de la réponse du serveur pour LOGIN
                byte[] loginResponseData = new byte[1024];
                int receivedLoginBytes = TCP.receive(clientSocket, loginResponseData);

                if (receivedLoginBytes != -1) {
                    String loginResponse = new String(loginResponseData, 0, receivedLoginBytes);
                    System.out.println("Réponse du serveur pour LOGIN : " + loginResponse);

                    // Données à envoyer pour LOGOUT
                    String logoutRequestData = "LOGOUT";
                    byte[] logoutRequestDataBytes = logoutRequestData.getBytes();
                    int logoutRequestDataSize = logoutRequestDataBytes.length;

                    // Envoi des données pour LOGOUT
                    int sentLogoutBytes = TCP.send(clientSocket, logoutRequestDataBytes, logoutRequestDataSize);

                    if (sentLogoutBytes != -1) {
                        System.out.println("Données de LOGOUT envoyées avec succès.");
                    } else {
                        System.err.println("Erreur lors de l'envoi des données pour LOGOUT.");
                    }
                } else {
                    System.err.println("Erreur lors de la réception de la réponse pour LOGIN.");
                }

                // Fermer le socket client
                try {
                    clientSocket.close();
                    System.out.println("Socket client fermé.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.err.println("Erreur lors de l'envoi des données pour LOGIN.");
            }
        } else {
            System.err.println("Erreur lors de la création du socket client.");
        }
    }
}
