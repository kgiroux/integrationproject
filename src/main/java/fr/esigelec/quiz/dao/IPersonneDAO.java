package fr.esigelec.quiz.dao;


import java.util.List;

import fr.esigelec.quiz.dto.Personne;


/**Projet d'integration
 * Le jeu de TF8
 * @author GSI-IR
 * BOSSO BOSSO Ghyslaine and NGANE Pascale 
 * Interface PersonneDAO
 */

public interface IPersonneDAO {
	
	
	
	/**
	 * M�thode : connexion
	 * Connexion au jeu
	 * @param email, pwd
	 * @return boolean
	 */
	public boolean connexion(String email, String pwd);
	
	
	/**
	 * M�thode : createPersonne
	 * Cr�e une personne dans la base de donn�es
	 * @param p
	 */
	public boolean createPersonne(Personne p);
	
	/**
	 * M�thode : getPersonne
	 * @param id
	 * @return une personne � partir d'un id
	 */
	public Personne getPersonne(int id);
	
	/**
	 * M�thode : getPersonne
	 * @param email
	 * @return une personne � partir de son mail
	 */
	public Personne getPersonne(String mail);
	
	/**
	 * M�thode : listPersonne
	 * @return la liste de toutes les personnes enregistr�es dans la base de donn�es
	 */
	public List<Personne> listPersonnes();
	
	/**
	 * M�thode : updatePersonne
	 * Met � jour une personne plac�e en entr�e dans la bdd
	 * si elle a �t� enregistr�e avant
	 * @param p
	 */
	public boolean updatePersonne(Personne p);
	
	/**
	 * M�thode : deletePersonne
	 * Supprime une personne plac�e en entr�e dans la bdd
	 * si elle est enregistr�e
	 * @param p
	 */
	public boolean deletePersonne(Personne p);

}

