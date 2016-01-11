package fr.esigelec.quiz.dto;
import java.sql.Timestamp;


/**Projet d'integration
 * Le jeu de TF8
 * @author GSI-IR
 * BOSSO BOSSO Ghyslaine and NGANE Pascale
 * Classe Quiz
 * */

public class Quiz {

	/*Attributs de la classe Quiz*/

	/**
	 * identifiant du quiz
	 */
	private int id; 

	/**
	 * libelle du quiz;
	 */
	private String libelle; 

	/**
	 * Date et heure de début du quiz
	 */
	private Timestamp dateDebutQuiz; 

	/**
	 * Date et heure de fin du quiz
	 * */
	private Timestamp dateFinQuiz; 

	/**
	 * Identifiant de la question en cours du quiz
	 * */
	private int noQuestionCourante; 

	/**
	 * date et heure de début de la question
	 */
	private Timestamp dateDebutQuestion;

	/**
	 * étape 
	 * 1 : en cours
	 *  2 : affichage de statistiques
	 *   3 : affichage réponse + classement
	 */
	private int etape;



	/*Constructeurs*/

	/**
	 * Constructeur sans paramètres
	 */

	public Quiz() {}

	/**
	 * Constructeur avec paramètres
	 * @param id
	 * @param libelle
	 * @param dateDebutQuiz
	 * @param dateFinQuiz
	 * @param noQuestionCourante
	 * @param etape
	 */

	public Quiz(String libelle, Timestamp dateDebutQuiz,
			Timestamp dateFinQuiz,Timestamp dateDebutQuestion, int noQuestionCourante, int etape) {
		this.libelle = libelle;
		this.dateDebutQuiz = dateDebutQuiz;
		this.dateFinQuiz = dateFinQuiz;
		this.noQuestionCourante = noQuestionCourante;
		this.dateDebutQuestion=dateDebutQuestion;
		this.etape = etape;
	}

	/**
	 * Constructeur par recopie
	 * @param Quiz z
	 */

	public Quiz(Quiz q) {
		this.id = q.id;
		this.libelle = q.libelle;
		this.dateDebutQuiz = q.dateDebutQuiz;
		this.dateFinQuiz = q.dateFinQuiz;
		this.dateDebutQuestion=q.dateDebutQuestion;
		this.noQuestionCourante = q.noQuestionCourante;
		this.etape = q.etape;
	}


	/*Getters et setters*/

	/**
	 * méthode getId()
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * méthode: setId()
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * méthode: getLibelle()
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * méthode: setLibelle()
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	/**
	 * méthode:getDateDebutQuiz()
	 * @return the dateDebutQuiz
	 */
	public Timestamp getDateDebutQuiz() {
		return dateDebutQuiz;
	}

	/**
	 * méthode: setDateDebutQuiz()
	 * @param dateDebutQuiz the dateDebutQuiz to set
	 */
	public void setDateDebutQuiz(Timestamp dateDebutQuiz) {
		this.dateDebutQuiz = dateDebutQuiz;
	}

	/**
	 * méthode:getDateFinQuiz()
	 * @return the dateFinQuiz
	 */
	public Timestamp getDateFinQuiz() {
		return dateFinQuiz;
	}

	/**
	 * méthode: setDateFinQuiz
	 * @param dateFinQuiz the dateFinQuiz to set
	 */
	public void setDateFinQuiz(Timestamp dateFinQuiz) {
		this.dateFinQuiz = dateFinQuiz;
	}

	/**
	 * méthode: getNoQuestionCourante
	 * @return the noQuestionCourante
	 */
	public int getNoQuestionCourante() {
		return noQuestionCourante;
	}

	/**
	 * méthode: setNoQuestionCourante
	 * @param noQuestionCourante the noQuestionCourante to set
	 */
	public void setNoQuestionCourante(int noQuestionCourante) {
		this.noQuestionCourante = noQuestionCourante;
	}

	/**
	 * méthode: getDateDebutQuestion()
	 * @return the dateDebutQuestion
	 */
	public Timestamp getDateDebutQuestion() {
		return dateDebutQuestion;
	}

	/**
	 * méthode: setDateDebutQuestion()
	 * @param dateDebutQuestion the dateDebutQuestion to set
	 */
	public void setDateDebutQuestion(Timestamp dateDebutQuestion) {
		this.dateDebutQuestion = dateDebutQuestion;
	}

	/**
	 * méthode: getEtape()
	 * @return the etape
	 */
	public int getEtape() {
		return etape;
	}

	/**
	 * méthode: setEtape()
	 * @param etape the etape to set
	 */
	public void setEtape(int etape) {
		this.etape = etape;
	}

}

