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

	private Question idQuestion;
	
	/*Constructeurs*/

	/**
	 * Constructeur sans param�tres
	 */
	public Proposition() {
		super();
	}

	/**
	 * Constructeur avec param�tres
	 * @param id
	 * @param libelle
	 */
	public Proposition(String libelle, int idQuestion) {
		super();
		this.id = 0;
		
		this.libelle = libelle;
	}
	
	public Proposition(String libelle) {
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
	
	public Question getIdQuestion() {
		return idQuestion;
	}
	
	/**
	 * methode setIdQuestion
	 * @param the id to set
	 */
	public void setIdQuestion(Question idQuestion) {
		this.idQuestion = idQuestion;
	}

	/**
	 * methode equals
	 * to compare two Proposition's objects
	 * @return true if the two objetcs are equals
	 * @return false if not.
	 */
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Proposition other = (Proposition) obj;
		if (id != other.id)
			return false;
		if (idQuestion == null) {
			if (other.idQuestion != null)
				return false;
		} else if (!idQuestion.equals(other.idQuestion))
			return false;
		if (libelle == null) {
			if (other.libelle != null)
				return false;
		} else if (!libelle.equals(other.libelle))
			return false;
		return true;
	}
}
