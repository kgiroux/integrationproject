package fr.esigelec.gsi.quizintegration.Objects;

import java.sql.Timestamp;

/**
 * Created by Kevin PACE
 */
public class Choisir
{
    /* Attributes */
    private Timestamp date;
    private Personne personne;
    private Proposition proposition;
    private Quiz quiz;

    /* Getters & Setters */
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public Proposition getProposition() {
        return proposition;
    }

    public void setProposition(Proposition proposition) {
        this.proposition = proposition;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
}
