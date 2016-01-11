package fr.esigelec.quiz.dto;


/**Projet d'integration
 * Le jeu de TF8
 * @author GSI-IR
 * BOSSO BOSSO Ghyslaine and NGANE Pascale
 * Classe Question
 * */

public class Proposition {


	/*Attributs de la classe Proposition*/

	/**
	 * identifiant de la proposition
	 */
	private int id; 

	/**
	 * libelle de la proposition
	 */
	private String libelle; 


	/*Constructeurs*/

	/**
	 * Constructeur sans paramètres
	 */
	public Proposition() {
		super();
	}


	/**
	 * Constructeur avec paramètres
	 * @param id
	 * @param libelle
	 */
	public Proposition(int id, String libelle) {
		super();
		this.id = id;
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
	 * méthode: getId()
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

}
