package fr.esigelec.quiz.dto;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**Projet d'integration
 * Le jeu de TF8
 * GSI-IR
 * @author BOSSO BOSSO Ghyslaine
 * @author  CHOUAKRIA Farid
 * @author DELAUNAY Brice
 * @author NGANE Pascale
 * Classe Quiz
 * */

public class Quiz {

	/**Attributs de la classe Quiz*/


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
	
	//private List<Question> listeQuestions = new LinkedList<Question>();
	private Set<Question> questions;


	/*Constructeurs*/

	/**
	 * Constructeur sans paramétres
	 */

	public Quiz() {
		this.id = 0;
		this.libelle = "";
		this.dateDebutQuiz = null;
		this.dateFinQuiz = null;
		this.noQuestionCourante = 0;
		this.dateDebutQuestion = null;
		this.etape = 0;
		this.questions = new HashSet<Question>();
	}

	/**
	 * Constructeur avec paramétres
	 * @param id
	 * @param libelle
	 * @param noQuestionCourante
	 * @param etape
	 */

	public Quiz(String libelle, int noQuestionCourante, int etape) {
		this.id = 0;
		this.libelle = libelle;
		this.dateDebutQuiz = null;
		this.dateFinQuiz = null;
		this.noQuestionCourante = noQuestionCourante;
		this.dateDebutQuestion = null;
		this.etape = etape;
		this.questions = new HashSet<Question>();
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
		this.questions = q.questions;
	}


	/*Getters et setters*/
	
	public Set<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}
	
	public void setListeQuestions(List<Question> questions) {
		this.questions = new HashSet<Question> (questions);
	}

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
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateDebutQuestion == null) ? 0 : dateDebutQuestion.hashCode());
		result = prime * result + ((dateDebutQuiz == null) ? 0 : dateDebutQuiz.hashCode());
		result = prime * result + ((dateFinQuiz == null) ? 0 : dateFinQuiz.hashCode());
		result = prime * result + etape;
		result = prime * result + id;
		result = prime * result + ((libelle == null) ? 0 : libelle.hashCode());
		result = prime * result + noQuestionCourante;
		result = prime * result + ((questions == null) ? 0 : questions.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Quiz other = (Quiz) obj;
		if (dateDebutQuestion == null) {
			if (other.dateDebutQuestion != null)
				return false;
		} else if (!dateDebutQuestion.equals(other.dateDebutQuestion))
			return false;
		if (dateDebutQuiz == null) {
			if (other.dateDebutQuiz != null)
				return false;
		} else if (!dateDebutQuiz.equals(other.dateDebutQuiz))
			return false;
		if (dateFinQuiz == null) {
			if (other.dateFinQuiz != null)
				return false;
		} else if (!dateFinQuiz.equals(other.dateFinQuiz))
			return false;
		if (etape != other.etape)
			return false;
		if (id != other.id)
			return false;
		if (libelle == null) {
			if (other.libelle != null)
				return false;
		} else if (!libelle.equals(other.libelle))
			return false;
		if (noQuestionCourante != other.noQuestionCourante)
			return false;
		if (questions == null) {
			if (other.questions != null)
				return false;
		} else if (!questions.equals(other.questions))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Quiz [id=" + id + ", libelle=" + libelle + ", dateDebutQuiz=" + dateDebutQuiz + ", dateFinQuiz="
				+ dateFinQuiz + ", noQuestionCourante=" + noQuestionCourante + ", dateDebutQuestion="
				+ dateDebutQuestion + ", etape=" + etape + ", questions=" + questions + "]";
	}
	
	
	public void addQuestion(Question q) {
		questions.add(q);
	}
}
