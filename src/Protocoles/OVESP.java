package Protocoles;

import Controleurs.*;

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
                JeReponds = response.substring("LOGIN2#".length());

                return JeReponds;
            } else {
                // En cas de réponse invalide pour LOGIN2, définissons JeReponds comme "ERREUR"
                JeReponds = "ERREUR";
                return JeReponds;
            }
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
