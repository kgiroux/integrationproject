package fr.esigelec.quiz.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fr.esigelec.quiz.util.SetToListConverter;

/**Projet d'integration
 * Le jeu de TF8
 * @author GSI-IR
 * BOSSO BOSSO Ghyslaine and NGANE Pascale
 * Classe Question
 * */

public class Question implements  Comparable<Question>, Serializable  {

	/*Attributs de la classe Question*/

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *identfiant de la question 
	 */
	private int id; 

	/**
	 * intitulé de la question
	 */
	private String libelle; 
	
	private Set<Proposition> propositions;

	/*Constructeurs*/

	/**
	 * Constructeur sans paramétres
	 */

	public Question() {
		this.id = 0;
		this.libelle = "";
		propositions = new HashSet<Proposition>();
	}


	/**
	 * Constructeur avec paramétres
	 * @param libelle
	 */

	public Question(String libelle) {
		this.id = 0;
		this.libelle = libelle;
		propositions = new HashSet<Proposition>();
	}
	
	/**
	 * Constructeur par recopie
	 * @param Question q
	 */
	public Question(Question q) {
		this.id = q.id;
		this.libelle = q.libelle;
		this.propositions = q.propositions;
		
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


	public Set<Proposition> getPropositions() {
		return propositions;
	}


	public void setPropositions(Set<Proposition> propositions) {
		this.propositions = propositions;
	}


	@Override
	public String toString() {
		return "Question [id=" + id + ", libelle=" + libelle + ", propositions=" + propositions + "]";
	}

	public List<Proposition> getListePropositions() {
		List<Proposition> array = new ArrayList<Proposition>();
		SetToListConverter.SetToList(array, propositions);
		return array;
	}

	@Override
	public int hashCode() {
		return id;
		/*final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((libelle == null) ? 0 : libelle.hashCode());
		result = prime * result + ((propositions == null) ? 0 : propositions.hashCode());
		return result;*/
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
		if (propositions == null) {
			if (other.propositions != null)
				return false;
		} else if (!propositions.equals(other.propositions))
			return false;
		return true;
	}


	@Override
	public int compareTo(Question o) {
		if(this.getId() < o.getId())
			return -1;
		else if (this.getId() == o.getId())
			return 0;
		else 
			return 1;
	}
	
	
	
	
	
}