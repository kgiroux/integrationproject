package fr.esigelec.gsi.quizintegration.objects;

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
    private int numQuestion;
    private String libelle;
    private List<Proposition> listePropositions;

    //Constructeur pour test
    public Question(int id, String libelle) {
        this.id = id;
        this.libelle = libelle;
        this.listePropositions = new ArrayList<> ();
    }
    public Question() {
        this.listePropositions = new ArrayList<> ();
    }

    /* Getters & Setters */
    public List<Proposition> getListePropositions() {
        return listePropositions;
    }

    public void setListePropositions(List<Proposition> listePropositions) {
        this.listePropositions = listePropositions;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getNumQuestion() {
        return numQuestion;
    }

    public void setNumQuestion(int numQuestion) {
        this.numQuestion = numQuestion;
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

                this.id = obj.getInt("id");
                this.libelle = obj.getString ("libelle");
                this.numQuestion = obj.getInt("numQuestion");

                //Remplissage de la liste des propositions
                JSONArray array = obj.getJSONArray ("propositions");
                listePropositions.clear();
                for(int i=0; i<array.length(); i++){
                    JSONObject item = array.getJSONObject(i);
                    Proposition p = new Proposition(item.getInt("id"),item.getString("libelle"));
                    listePropositions.add(p);
                }
            }catch(JSONException jsonE){
                Log.e ("ERREUR",jsonE.getMessage ());
            }
        }
    }

}
