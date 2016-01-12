package fr.esigelec.quiz.dto;


import java.util.List;


/**Projet d'integration
 * Le jeu de TF8
 * @author GSI-IR
 * BOSSO BOSSO Ghyslaine and NGANE Pascale
 * Classe Question
 * */

public class Question {

	/*Attributs de la classe Question*/

	/**
	 *identfiant de la question 
	 */
	private int id; 

	/**
	 * intitul� de la question
	 */
	private String libelle; 

	/**
	 *Proposition Bonne r�ponse
	 */
	private Proposition bonneReponse; 

	/**
	 * liste des propositions de r�ponse � la question
	 */
	private List<Proposition> list;



	/*Constructeurs*/

	/**
	 * Constructeur sans param�tres
	 */

	public Question() {
	}


	/**
	 * Constructeur avec param�tres
	 * @param id
	 * @param libelle
	 * @param bonneReponse
	 * @param list
	 */

	public Question(String libelle, Proposition bonneReponse, List<Proposition> list) {
		this.libelle = libelle;
		this.bonneReponse = bonneReponse;
		this.list = list;
	}


	/**
	 * Constructeur par recopie
	 * @param Question q
	 */
	public Question(Question q) {
		this.id = q.id;
		this.libelle = q.libelle;
		this.bonneReponse = q.bonneReponse;
		this.list = q.list;
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
	 * @param libelle : the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	/**
	 * m�thode:getBonneReponse()
	 * @return the bonneReponse
	 */
	public Proposition getBonneReponse(){
		return bonneReponse;
	}

	/**
	 * m�thode: setBonneReponse()
	 * @param reponse the bonneReponse to set
	 */
	public void setBonneReponse(Proposition p) {
		this.bonneReponse =p;
	}

	/**
	 * m�thode:getList()
	 * @return the list
	 */
	public List<Proposition> getList() {
		return list;
	}

	/** m�thode: setList()
	 * @param l: the list of proposition to set
	 */
	public void setList(List<Proposition> l) {
		this.list=l;
	}


}

