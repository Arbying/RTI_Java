package Modele;

public class Client {
    private int idClient;
    private String login;
    private String motDePasse;
    private boolean estNouveau;

    public Client(int idClient, String login, String motDePasse, boolean estNouveau) {
        this.idClient = idClient;
        this.login = login;
        this.motDePasse = motDePasse;
        this.estNouveau = estNouveau;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public boolean isEstNouveau() {
        return estNouveau;
    }

    public void setEstNouveau(boolean estNouveau) {
        this.estNouveau = estNouveau;
    }

    public String toString() {
        return "Client{" +
                "idClient=" + idClient +
                ", login='" + login + '\'' +
                ", motDePasse='" + motDePasse + '\'' +
                ", estNouveau=" + estNouveau +
                '}';
    }
}
