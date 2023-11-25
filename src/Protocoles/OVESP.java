package Protocoles;

import Controleurs.*;
import Modele.*;

public class OVESP {

    private static String TypeReponse;
    private static String JeReponds;

    public static String processResponse(String response) {
        // Logique de traitement de la réponse OVESP ici
        // Pour l'instant, définissons TypeReponse comme la première partie de la réponse jusqu'au premier "#"
        String[] parts = response.split("#");

        if (parts.length > 0) {
            TypeReponse = parts[0];
        } else {
            // En cas de réponse invalide, définissons TypeReponse comme "ERREUR"
            TypeReponse = "ERREUR";
        }

        // Condition spécifique pour LOGIN2
        if (TypeReponse.equals("LOGIN2")) {
            // Récupérer la partie du string après "LOGIN2#"
            if (response.startsWith("LOGIN2#")) {
                System.out.println();
                JeReponds = response.substring("LOGIN2#".length());

                return JeReponds;
            } else {
                // En cas de réponse invalide pour LOGIN2, définissons JeReponds comme "ERREUR"
                JeReponds = "ERREUR";
                return JeReponds;
            }
        }
        else if (TypeReponse.equals("ARTSUIVANT2")) {
            // Divisez la chaîne de caractères en sous-parties en utilisant le délimiteur "#"
            String[] artParts = response.split("#");


            // Assurez-vous que nous avons suffisamment de parties pour extraire les données
            if (artParts.length >= 6) {
                try {

                    // Extract IdArt (partie 1)
                    int IdArt = Integer.parseInt(artParts[1]);

                    // Extract Denomination (partie 2)
                    String Denomination = artParts[2];

                    // Extract Image (partie 3)
                    String Image = artParts[3];

                    // Extract QuDispo (partie 4)
                    int QuDispo = Integer.parseInt(artParts[4]);

                    // Extract Prix (partie 5) et convertissez-le en float
                    float Prix = Float.parseFloat(artParts[5]) / 100.0f;

                    Article monArticleEnCours = new Article(IdArt, Denomination, Prix, QuDispo, 0, Image);

                    Singleton.getInstance().setArticleEnCours(monArticleEnCours);
                    Singleton.getInstance().setIdArticleEnCours(IdArt);

                    // Affichez les valeurs extraites
                    System.out.println("Mon Singleton contient : " + Singleton.getInstance().getArticleEnCours().toString());

                } catch (NumberFormatException e) {
                    // Gérer les erreurs de conversion ici
                    System.out.println("Erreur de conversion de données.");
                }
            }
            else {
                // La réponse ne contient pas suffisamment de parties
                System.out.println("Réponse invalide : Insuffisamment de parties.");
            }
        }
        else
        {
            return "MERDE";
        }


        return TypeReponse;
    }

    public static String getTypeReponse() {
        return TypeReponse;
    }

    public static String getJeReponds() {
        return JeReponds;
    }
}
