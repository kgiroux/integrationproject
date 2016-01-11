package fr.esigelec.gsi.quizintegration.Objects;

import java.util.List;

/**
 * Created by Kevin PACE
 */
public class Question
{
    /* Attributes */
    private int id;
    private String libelle;
    private int idBonneReponse;
    private List<Proposition> listePropositions;

    /* Getters & Setters */
    public List<Proposition> getListePropositions() {
        return listePropositions;
    }

    public void setListePropositions(List<Proposition> listePropositions) {
        this.listePropositions = listePropositions;
    }

    public int getIdBonneReponse() {
        return idBonneReponse;
    }

    public void setIdBonneReponse(int idBonneReponse) {
        this.idBonneReponse = idBonneReponse;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
