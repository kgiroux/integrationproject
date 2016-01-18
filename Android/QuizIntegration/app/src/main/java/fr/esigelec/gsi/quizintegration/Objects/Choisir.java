package fr.esigelec.gsi.quizintegration.Objects;

import java.sql.Timestamp;
import java.util.HashMap;

/**
 * Created by Kevin PACE
 */
public class Choisir
{
    /* Attributes */
    private int id_personne;
    private int id_proposition;
    private int id_quiz;

    /* Constructor */
    public Choisir ()
    {
        id_proposition = -1;
        id_quiz = -1;
        id_personne = -1;
    }

    /* Getters & Setters */
    public int getPersonne() {
        return id_personne;
    }

    public void setPersonne(int personne) {
        this.id_personne = personne;
    }

    public int getProposition() {
        return id_proposition;
    }

    public void setProposition(int proposition) {
        this.id_proposition = proposition;
    }

    public int getQuiz() {
        return id_quiz;
    }

    public void setQuiz(int quiz) {
        this.id_quiz = quiz;
    }

    /* Convertion methods */
    public HashMap<String,String> ChoiceToHashMap(){
        HashMap<String, String> returnHashMap = new HashMap<> ();
        if(id_personne != -1){
            returnHashMap.put ("idPersonne",String.valueOf (id_personne));
        }
        if(id_quiz !=-1){
            returnHashMap.put ("idQuiz",String.valueOf (id_quiz));
        }
        if(id_proposition !=-1){
            returnHashMap.put ("idProposition",String.valueOf (id_proposition));
        }
        return returnHashMap;
    }

}
