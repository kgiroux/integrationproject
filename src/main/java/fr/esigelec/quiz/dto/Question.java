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
	private List<Proposition> listePropositions;

	/*Constructeurs*/

	/**
	 * Constructeur sans param�tres
	 */

	public Question() {
		this.id = 0;
		this.libelle = "";
		this.listePropositions = null;
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
		this.listePropositions = list;
	}


	/**
	 * Constructeur par recopie
	 * @param Question q
	 */
	public Question(Question q) {
		this.id = q.id;
		this.libelle = q.libelle;
		this.bonneReponse = q.bonneReponse;
		this.listePropositions = q.listePropositions;
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
		this.bonneReponse = p;
	}

	public List<Proposition> getListePropositions() {
		return listePropositions;
	}

	public void setListePropositions(List<Proposition> listePropositions) {
		this.listePropositions = listePropositions;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Question other = (Question) obj;
		if (bonneReponse == null) {
			if (other.bonneReponse != null)
				return false;
		} else if (!bonneReponse.equals(other.bonneReponse))
			return false;
		if (id != other.id)
			return false;
		if (libelle == null) {
			if (other.libelle != null)
				return false;
		} else if (!libelle.equals(other.libelle))
			return false;
		if (listePropositions == null) {
			if (other.listePropositions != null)
				return false;
		} else if (!listePropositions.equals(other.listePropositions))
			return false;
		return true;
	}
}

