package Modele;

public class Article {
    private int idArticle;
    private String denomination;
    private float prix;
    private int quantiteDisponible;
    private int quantiteDemandee;
    private String image;

    public Article(int idArticle, String denomination, float prix, int quantiteDisponible, int quantiteDemandee, String image) {
        this.idArticle = idArticle;
        this.denomination = denomination;
        this.prix = prix;
        this.quantiteDisponible = quantiteDisponible;
        this.quantiteDemandee = quantiteDemandee;
        this.image = image;
    }

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getQuantiteDisponible() {
        return quantiteDisponible;
    }

    public void setQuantiteDisponible(int quantiteDisponible) {
        this.quantiteDisponible = quantiteDisponible;
    }

    public int getQuantiteDemandee() {
        return quantiteDemandee;
    }

    public void setQuantiteDemandee(int quantiteDemandee) {
        this.quantiteDemandee = quantiteDemandee;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String toString() {
        return "Article{" +
                "idArticle=" + idArticle +
                ", denomination='" + denomination + '\'' +
                ", prix=" + prix +
                ", quantiteDisponible=" + quantiteDisponible +
                ", quantiteDemandee=" + quantiteDemandee +
                ", image='" + image + '\'' +
                '}';
    }
}
