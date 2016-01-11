package fr.esigelec.gsi.quizintegration.Objects;

/**
 * Created by Kevin PACE
 */
public class Personne
{
    /* Attribute */
    private int id;
    private String nom;
    private String prenom;
    private String mail;
    private String mdp;
    private int droits;

    /* Getters & Setters */
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public int getDroits() {
        return droits;
    }

    public void setDroits(int droits) {
        this.droits = droits;
    }


}
