package fr.esigelec.gsi.quizintegration.Objects;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Kevin PACE
 */
public class Quiz
{
    /* Attributes */
    private int id;
    private String libelle;
    private Timestamp dateDebutQuiz;
    private Timestamp dateFinQuiz;
    private int noQuestionCourante;
    private Timestamp dateDebutQuestion;
    private int etape;
    private List<Question> listeQuestions;

    /* Constructor */
    public Quiz() {
    }

    /* Getters & Setters */
    public int getId() {
        return id;
    }

    //Constructor de test


    public Quiz(int id, String libelle, Timestamp dateDebutQuiz, Timestamp dateFinQuiz, int noQuestionCourante, Timestamp dateDebutQuestion, int etape) {
        this.id = id;
        this.libelle = libelle;
        this.dateDebutQuiz = dateDebutQuiz;
        this.dateFinQuiz = dateFinQuiz;
        this.noQuestionCourante = noQuestionCourante;
        this.dateDebutQuestion = dateDebutQuestion;
        this.etape = etape;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getEtape() {
        return etape;
    }

    public void setEtape(int etape) {
        this.etape = etape;
    }

    public Timestamp getDateDebutQuiz() {
        return dateDebutQuiz;
    }

    public void setDateDebutQuiz(Timestamp dateDebutQuiz) {
        this.dateDebutQuiz = dateDebutQuiz;
    }

    public Timestamp getDateFinQuiz() {
        return dateFinQuiz;
    }

    public void setDateFinQuiz(Timestamp dateFinQuiz) {
        this.dateFinQuiz = dateFinQuiz;
    }

    public int getNoQuestionCourante() {
        return noQuestionCourante;
    }

    public void setNoQuestionCourante(int noQuestionCourante) {
        this.noQuestionCourante = noQuestionCourante;
    }

    public Timestamp getDateDebutQuestion() {
        return dateDebutQuestion;
    }

    public void setDateDebutQuestion(Timestamp dateDebutQuestion) {
        this.dateDebutQuestion = dateDebutQuestion;
    }

    public List<Question> getListeQuestions() {
        return listeQuestions;
    }

    public void setListeQuestions(List<Question> listeQuestions) {
        this.listeQuestions = listeQuestions;
    }
}
