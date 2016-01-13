package fr.esigelec.quiz.dto;


/**Projet d'integration
 * Le jeu de TF8
 * @author GSI-IR
 * BOSSO BOSSO Ghyslaine and NGANE Pascale
 * Classe Personne
 * */

public class Personne {

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Personne other = (Personne) obj;
		if (droits != other.droits)
			return false;
		if (id != other.id)
			return false;
		if (mail == null) {
			if (other.mail != null)
				return false;
		} else if (!mail.equals(other.mail))
			return false;
		if (mdp == null) {
			if (other.mdp != null)
				return false;
		} else if (!mdp.equals(other.mdp))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (prenom == null) {
			if (other.prenom != null)
				return false;
		} else if (!prenom.equals(other.prenom))
			return false;
		return true;
	}

	//Attributs de la classe Personne
	/**
	 * id de la personne
	 */
	private int id;
	/**
	 * nom de la personne
	 */
	private String nom;
	/**
	 * prenom de la personne
	 */
	private String prenom;
	/**
	 * adresse e-mail de la personne
	 */
	private String mail;
	/**
	 * mot de passe de la personne
	 */
	private String mdp;
	/**
	 * droits d'acc�s de la personne
	 * 0 --> Joueur
	 * 1000 --> Admin
	 */
	private int droits;
	
	public static final int ADMIN = 1000;
	public static final int JOUEUR = 0;


	//M�thodes de la classe Personne

	/**
	 * Constructeur sans param�tres
	 */
	public Personne() {}
	
	/**
	 * Constructeur avec param�tres
	 * @param id 
	 * @param nom
	 * @param prenom
	 * @param mail
	 * @param mdp
	 * @param droits
	 */
	public Personne(int id, String nom, String prenom, String mail, String mdp, int droits) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.mdp = mdp;
		this.droits = droits;
	}

	/**
	 * Constructeur par recopie
	 * @param p
	 */
	public Personne(Personne p) {
		this.id = p.id;
		this.nom = p.nom;
		this.prenom = p.prenom;
		this.mail = p.mail;
		this.mdp = p.mdp;
		this.droits = p.droits;
	}

	/**
	 * getter d'id
	 * @return l'id de la personne
	 */
	public int getId() {
		return id;
	}

	/**
	 * setter d'id
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * getter de nom
	 * @return le nom de la personne
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * setter de nom
	 * @param nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * getter de prenom
	 * @return le prenom de la personne
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * setter de prenom
	 * @param prenom
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * getter de mail
	 * @return l'adresse e-mail de la personne
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * setter de mail
	 * @param mail
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * getter de mdp
	 * @return le mot de passe de l'utilisateur
	 */
	public String getMdp() {
		return mdp;
	}

	/**
	 * setter de mdp
	 * @param mdp
	 */
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	/**
	 * getter de droits
	 * @return les droits d'acc�s pour cet utilisateur
	 */
	public int getDroits() {
		return droits;
	}

	/**
	 * setter de droits
	 * @param droits
	 */
	public void setDroits(int droits) {
		this.droits = droits;
	}
	
	@Override
	public String toString() {
		return "Personne [id=" + id + ", nom=" + nom + ", prenom=" + prenom
				+ ", mail=" + mail + ", mdp=" + mdp + ", droits=" + droits
				+ "]";
	}
}
