package fr.esigelec.quiz.dto;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


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
	 * Date et heure de d�but du quiz
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
	 * date et heure de d�but de la question
	 */
	private Timestamp dateDebutQuestion;

	/**
	 * �tape 
	 * 1 : en cours
	 *  2 : affichage de statistiques
	 *   3 : affichage r�ponse + classement
	 */
	private int etape;
	
	private Set<Question> listeQuestions = new HashSet<Question>();


	/*Constructeurs*/

	/**
	 * Constructeur sans param�tres
	 */

	public Quiz() {}

	/**
	 * Constructeur avec param�tres
	 * @param id
	 * @param libelle
	 * @param noQuestionCourante
	 * @param etape
	 */

	public Quiz(String libelle, int noQuestionCourante, int etape) {
		this.libelle = libelle;
		this.dateDebutQuiz = null;
		this.dateFinQuiz = null;
		this.noQuestionCourante = noQuestionCourante;
		this.dateDebutQuestion=null;
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
	 * m�thode getId()
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * m�thode: setId()
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * m�thode: getLibelle()
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * m�thode: setLibelle()
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	/**
	 * m�thode:getDateDebutQuiz()
	 * @return the dateDebutQuiz
	 */
	public Timestamp getDateDebutQuiz() {
		return dateDebutQuiz;
	}

	/**
	 * m�thode: setDateDebutQuiz()
	 * @param dateDebutQuiz the dateDebutQuiz to set
	 */
	public void setDateDebutQuiz(Timestamp dateDebutQuiz) {
		this.dateDebutQuiz = dateDebutQuiz;
	}

	/**
	 * m�thode:getDateFinQuiz()
	 * @return the dateFinQuiz
	 */
	public Timestamp getDateFinQuiz() {
		return dateFinQuiz;
	}

	/**
	 * m�thode: setDateFinQuiz
	 * @param dateFinQuiz the dateFinQuiz to set
	 */
	public void setDateFinQuiz(Timestamp dateFinQuiz) {
		this.dateFinQuiz = dateFinQuiz;
	}

	/**
	 * m�thode: getNoQuestionCourante
	 * @return the noQuestionCourante
	 */
	public int getNoQuestionCourante() {
		return noQuestionCourante;
	}

	/**
	 * m�thode: setNoQuestionCourante
	 * @param noQuestionCourante the noQuestionCourante to set
	 */
	public void setNoQuestionCourante(int noQuestionCourante) {
		this.noQuestionCourante = noQuestionCourante;
	}

	/**
	 * m�thode: getDateDebutQuestion()
	 * @return the dateDebutQuestion
	 */
	public Timestamp getDateDebutQuestion() {
		return dateDebutQuestion;
	}

	/**
	 * m�thode: setDateDebutQuestion()
	 * @param dateDebutQuestion the dateDebutQuestion to set
	 */
	public void setDateDebutQuestion(Timestamp dateDebutQuestion) {
		this.dateDebutQuestion = dateDebutQuestion;
	}

	/**
	 * m�thode: getEtape()
	 * @return the etape
	 */
	public int getEtape() {
		return etape;
	}

	/**
	 * m�thode: setEtape()
	 * @param etape the etape to set
	 */
	public void setEtape(int etape) {
		this.etape = etape;
	}
	/**
	public List<Question> getListeQuestions() {
		return listeQuestions;
	}*/

	/**public void setListeQuestions(List<Question> listeQuestions) {
		this.listeQuestions = listeQuestions;
	}*/

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
		if (listeQuestions == null) {
			if (other.listeQuestions != null)
				return false;
		} else if (!listeQuestions.equals(other.listeQuestions))
			return false;
		if (noQuestionCourante != other.noQuestionCourante)
			return false;
		return true;
	}

	public Set<Question> getListeQuestions() {
		return listeQuestions;
	}

	public void setListeQuestions(Set<Question> listeQuestions) {
		this.listeQuestions = listeQuestions;
	}
}

