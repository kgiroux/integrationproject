package fr.esigelec.gsi.quizintegration.Objects;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
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

    //Constructeur pour test
    public Question(int id, String libelle, int idBonneReponse) {
        this.id = id;
        this.libelle = libelle;
        this.idBonneReponse = idBonneReponse;
    }

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

    /* Convertion methods */
    public void JSONObjectToQuestion(JSONObject obj){
        if(null != obj){
            try{

                this.id = obj.getInt ("id");
                this.libelle = obj.getString ("libelle");
                this.listePropositions = new ArrayList<> ();
                Proposition p = new Proposition ();
                JSONArray array = obj.getJSONArray ("propositions");
                for (int i = 0; i<array.length (); i++){
                    JSONObject item = array.getJSONObject (i);
                    p.setId (item.getInt ("id"));
                    p.setLibelle (item.getString ("libelle"));
                    listePropositions.add (p);
                    p = new Proposition ();
                }
            }catch(JSONException jsonE){
                Log.e ("ERREUR",jsonE.getMessage ());
            }
        }
    }

}
