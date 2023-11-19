package Modele;

public class Client {
    private String login;
    private String motDePasse;
    private boolean estNouveau;

    public Client(String login, String motDePasse, boolean estNouveau) {
        this.login = login;
        this.motDePasse = motDePasse;
        this.estNouveau = estNouveau;
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
}
