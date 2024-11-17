package Entites;

public class Livre {
    // Attributs
    public int isbn;
    public String titre;
    public String auteur;
    public String date_parution;
    public String domaine;
    public int nb_exemplaires;

    // Constructeur par défault
    public Livre() {
    }

    // Constructeur surchargé
    public Livre(int isbn, String titre, String auteur, String date_parution, String domaine, int nb_exemplaires) {
        this.isbn = isbn;
        this.titre = titre;
        this.auteur = auteur;
        this.date_parution = date_parution;
        this.domaine = domaine;
        this.nb_exemplaires = nb_exemplaires;
    }

    // La méthode toString()
    @Override
    public String toString() {
        return "Livre{" +
                "isbn=" + isbn +
                ", titre='" + titre + '\'' +
                ", auteur='" + auteur + '\'' +
                ", date_parution='" + date_parution + '\'' +
                ", domaine='" + domaine + '\'' +
                ", nb_exemplaires=" + nb_exemplaires +
                '}';
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getDate_parution() {
        return date_parution;
    }

    public void setDate_parution(String date_parution) {
        this.date_parution = date_parution;
    }

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    public int getNb_exemplaires() {
        return nb_exemplaires;
    }

    public void setNb_exemplaires(int nb_exemplaires) {
        this.nb_exemplaires = nb_exemplaires;
    }
}
