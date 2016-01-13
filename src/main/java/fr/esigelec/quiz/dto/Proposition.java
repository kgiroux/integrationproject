package fr.esigelec.quiz.dto;

/**Projet d'integration
 * Le jeu de TF8
 * @author GSI-IR
 * BOSSO BOSSO Ghyslaine and NGANE Pascale
 * Classe Question
 * */

public class Proposition {


	/**Attributs de la classe Proposition*/

	/**
	 * identifiant de la proposition
	 */
	private int id; 

	/**
	 * libelle de la proposition
	 */
	private String libelle; 

	private Question question;
	
	/*Constructeurs*/

	/**
	 * Constructeur sans param�tres
	 */
	public Proposition() {
		super();
		this.id = 0;
		this.libelle = "";
	}

	/**
	 * Constructeur avec param�tres
	 * @param id
	 * @param libelle
	 */
	public Proposition(String libelle, Question question) {
		super();
		this.id = 0;
		this.question = question;
		this.libelle = libelle;
	}
	
	

	/**
	 * Constructeur par recopie
	 * @param Proposition p
	 */
	public Proposition(Proposition p) {
		super();
		this.id = p.id;
		this.libelle = p.libelle;
	}

	/*Getters et setters*/

	/**
	 * m�thode: getId()
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
	 * methode getQIdQUestion
	 * @return the id of the question
	 */
	
	public Question getquestion() {
		return question;
	}
	
	/**
	 * methode setIdQuestion
	 * @param the id to set
	 */
	public void setIdQuestion(Question question) {
		this.question = question;
	}

}
