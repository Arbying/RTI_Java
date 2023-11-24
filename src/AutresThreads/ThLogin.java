package AutresThreads;

import Protocoles.*;
import Controleurs.Singleton;
import Modele.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JOptionPane;


public class ThLogin extends Thread {
    private String adresseIP;
    private String portEcoute;
    private String login;
    private String mdp;
    private boolean estNouveau;
    private String Reponse;
    private String temp;

    public ThLogin(String login, String mdp, boolean estNouveau) {
        this.login = login;
        this.mdp = mdp;
        this.estNouveau = estNouveau;
        chargerInfoServeur();
    }

    @Override
    public void run() {


        // Créez la socket en utilisant les informations chargées
        Socket maSocket = TCP.createClientSocket(adresseIP, Integer.parseInt(portEcoute));

        // Stockez la socket dans le singleton
        Singleton.getInstance().setMaSocket(maSocket);

        String trame = "LOGIN#" + login + "#" + mdp + "#" + (estNouveau ? "1" : "0");

        // Envoyer la trame sur le réseau
        byte[] trameBytes = trame.getBytes();
        TCP.send(maSocket, trameBytes, trameBytes.length);


        byte[] buffer = new byte[1500]; // Définissez la taille du tampon selon vos besoins
        int bytesRead = TCP.receive(maSocket, buffer);
        if (bytesRead > 0) {
            String reponse = new String(buffer, 0, bytesRead);

            // Maintenant, la variable `reponse` contient la réponse du serveur
            System.out.println("Réponse du serveur : " + reponse);

            OVESP ovesp = new OVESP();

// Appelez la fonction OVESP avec les paramètres nécessaires
            String temp = ovesp.processResponse(reponse);

            if (temp.equals("Erreur"))
            {
                System.out.println("Erreur Réseau !!!!");
                JOptionPane.showMessageDialog(null, "Erreur réseau", "ERREUR", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                if (temp.equals("0"))
                {
                    System.out.println("Erreur ID ou MDP !!!!");
                    JOptionPane.showMessageDialog(null, "Vérifie tes identifiants, fais pas le biesse :D ", "ERREUR LOGIN", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    int idClient = Integer.parseInt(temp) ;
                    Client ceClient = new Client(idClient, login, mdp, estNouveau);
                    Singleton.getInstance().setClient(ceClient);
                    Singleton.getInstance().setEstConnecte(true);
                    System.out.println(Singleton.getInstance().getClient().toString());

                    ThChangeArtcile thArt = new ThChangeArtcile();
                    thArt.start();
                    this.interrupt();

                }

            }

        }
        this.interrupt();
    }

    public String getAdresseIP() {
        return adresseIP;
    }

    public String getPortEcoute() {
        return portEcoute;
    }

    public String getLogin() {
        return login;
    }

    public String getMdp() {
        return mdp;
    }

    public boolean isEstNouveau() {
        return estNouveau;
    }

    private void chargerInfoServeur() {
        try (BufferedReader br = new BufferedReader(new FileReader("InfoServeur.dat"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    String value = parts[1].trim();
                    if ("IP du serveur".equals(key)) {
                        adresseIP = value;
                    } else if ("Port d'écoute".equals(key)) {
                        portEcoute = value;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
