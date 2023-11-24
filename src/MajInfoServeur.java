import java.io.*;

public class MajInfoServeur {
    public static void main(String[] args) {
        File fichier = new File("InfoServeur.dat");

        try {
            // Vérifier si le fichier existe
            if (!fichier.exists()) {
                fichier.createNewFile();
            }

            // Demander à l'utilisateur d'introduire l'IP du serveur
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Entrez l'IP du serveur : ");
            String ip = reader.readLine();

            // Demander à l'utilisateur d'introduire le port d'écoute
            System.out.print("Entrez le port d'écoute : ");
            String port = reader.readLine();

            // Écrire les informations dans le fichier
            BufferedWriter writer = new BufferedWriter(new FileWriter(fichier));
            writer.write("IP du serveur : " + ip);
            writer.newLine();
            writer.write("Port d'écoute : " + port);
            writer.close();

            System.out.println("Les informations ont été enregistrées avec succès.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
