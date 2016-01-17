package fr.esigelec.quiz.dto;

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
	 * intitulé de la question
	 */
	private String libelle; 

	/*Constructeurs*/

	/**
	 * Constructeur sans paramétres
	 */

	public Question() {
		this.id = 0;
		this.libelle = "";
	}


	/**
	 * Constructeur avec paramétres
	 * @param libelle
	 */

	public Question(String libelle) {
		this.id = 0;
		this.libelle = libelle;
	}
	
	/**
	 * Constructeur par recopie
	 * @param Question q
	 */
	public Question(Question q) {
		this.id = q.id;
		this.libelle = q.libelle;
	}

	/*Getters et setters*/
	
	public int getId() {
		return id;
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


	@Override
	public String toString() {
		return "Question [id=" + id + ", libelle=" + libelle + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((libelle == null) ? 0 : libelle.hashCode());
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
		Question other = (Question) obj;
		if (id != other.id)
			return false;
		if (libelle == null) {
			if (other.libelle != null)
				return false;
		} else if (!libelle.equals(other.libelle))
			return false;
		return true;
	}
	

}