package fr.esigelec.quiz.dto;

import java.sql.Timestamp;
import java.util.Date;

/**Projet d'integration
 * Le jeu de TF8
 * GSI-IR
 * @author BOSSO BOSSO Ghyslaine
 * @author  CHOUAKRIA Farid
 * @author DELAUNAY Brice
 * @author NGANE Pascale
 * Classe Choisir
 */

public class Choisir {

	/**
	 * Attributs de la classe Choisir
	 */
	
	/**date*/
	private Timestamp date;
	/** the proposition's id*/
	private int idProposition;
	/** the quiz's id*/
	private int idQuiz;
	/** the person's id*/
	private int idPersonne;
	
	/**
	 * Constructeurs
	 */
	
	
	/**
	 *Constructeur sans paramètres
	 */
	public Choisir() {
		super();

	}

	/**
	 * Constructeur avec paramètres
	 * @param date
	 * @param idProposition
	 * @param idQuiz
	 * @param idPersonne
	 */
	public Choisir(Timestamp date, int id_proposition, int id_quiz, int id_personne) {
		super();
		this.date = date;
		this.idProposition = id_proposition;
		this.idQuiz = id_quiz;
		this.idPersonne = id_personne;
	}

	
	/** getters et setters*/
	
	/**
	 * @return the date
	 */
	public Timestamp getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Timestamp date) {
		this.date = date;
	}

	/**
	 * @return the idProposition
	 */
	public int getIdProposition() {
		return idProposition;
	}

	/**
	 * @param idProposition the idProposition to set
	 */
	public void setIdProposition(int idProposition) {
		this.idProposition = idProposition;
	}

	/**
	 * @return the idQuiz
	 */
	public int getIdQuiz() {
		return idQuiz;
	}

	/**
	 * @param idQuiz the idQuiz to set
	 */
	public void setIdQuiz(int idQuiz) {
		this.idQuiz = idQuiz;
	}

	/**
	 * @return the idPersonne
	 */
	public int getIdPersonne() {
		return idPersonne;
	}

	/**
	 * @param idPersonne the idPersonne to set
	 */
	public void setIdPersonne(int idPersonne) {
		this.idPersonne = idPersonne;
	}

	
	
	
	
	
	
}
