package fr.esigelec.gsi.quizintegration.objects;

import android.util.Log;

import org.json.JSONObject;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
    private int nbQuestion;
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

    public int getNbQuestion() {
        return nbQuestion;
    }

    public void setNbQuestion(int nbQuestion) {
        this.nbQuestion = nbQuestion;
    }

    //Only for the Statistique Result
    public HashMap<String,String> QuizToHashMap(){
        HashMap<String,String> returnHashMap = new HashMap<> ();
        if(this.id != -1){
            returnHashMap.put ("idQuiz",String.valueOf (this.id));
        }
        return returnHashMap;
    }
    /* Convertion methods */
    public void JSONObjectToQuiz(JSONObject obj){
        if(null != obj){
            try{

                //Parsing datestring
                Date fin = null, debut, debutQuestion = null;
                SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
                debut = formater.parse(obj.getString("dateDebutQuiz"));
                if(!obj.isNull("dateFinQuiz"))
                    fin = formater.parse(obj.getString("dateFinQuiz"));
                if(!obj.isNull("dateDebutQuestion"))
                    debutQuestion = formater.parse(obj.getString("dateDebutQuestion"));

                this.setId(obj.getInt("id"));
                this.setLibelle(obj.getString("libelle"));
                this.setDateDebutQuiz(new Timestamp(debut.getTime()));
                if(fin != null)
                    this.setDateFinQuiz(new Timestamp(fin.getTime()));
                this.setEtape(obj.getInt("etape"));
                this.setNoQuestionCourante(obj.getInt("noQuestionCourante") + 1);
                if(debutQuestion != null)
                    this.setDateDebutQuestion(new Timestamp(debutQuestion.getTime()));
                if(!obj.isNull("nbQuestions"))
                    this.setNbQuestion(obj.getInt("nbQuestions"));
            }catch(Exception jsonE){
                Log.e("ERREUR", jsonE.getMessage());
            }
        }
    }
}
