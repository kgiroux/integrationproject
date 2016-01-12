package fr.esigelec.gsi.quizintegration.Objects;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Kevin PACE
 */
public class StatPersonne implements Comparable<StatPersonne>
{
    /* Attribute */
    private String nom;
    private String prenom;
    private int score;

    /* Constructor */
    public StatPersonne() {
    }

    /* Getters & Setters */
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    /* Compare methode */
    @Override
    public int compareTo(StatPersonne stat)
    {
        if(this.score < stat.getScore())
            return 0;
        else if(this.score == stat.getScore())
            return 0;
        else
            return 1;
    }
}
