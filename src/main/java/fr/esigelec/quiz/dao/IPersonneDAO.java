package fr.esigelec.quiz.dao;


import java.util.List;

import fr.esigelec.quiz.dto.Personne;
import fr.esigelec.quiz.dto.Quiz;


/**Projet d'integration
 * Le jeu de TF8
 * GSI-IR
 * @author BOSSO BOSSO Ghyslaine
 * @author  CHOUAKRIA Farid
 * @author DELAUNAY Brice
 * @author NGANE Pascale
 * Interface IPersonneDAO
 */

public interface IPersonneDAO {
	
	
	/**
	 * Methode : connexion
	 * Connexion au jeu
	 * @param email, pwd
	 * @return boolean
	 */
	public Personne connexion(String email, String pwd);
	
	
	/**
	 * Methode : createPersonne
	 * Cree une personne dans la base de donnees
	 * @param p
	 */
	public boolean createPersonne(Personne p);
	
	/**
	 * Methode : getPersonne
	 * @param id
	 * @return une personne a partir d'un id
	 */
	public Personne getPersonne(int id);
	
	/**
	 * Methode : getPersonne
	 * @param email
	 * @return une personne a partir de son mail
	 */
	public Personne getPersonne(String mail);
	
	/**
	 * Methode : listPersonne
	 * @return la liste de toutes les personnes enregistrees dans la base de donnees
	 */
	public List<Personne> listPersonnes();
	
	/**
	 * Methode : updatePersonne
	 * Met a jour une personne placee en entree dans la bdd
	 * si elle a ete enregistree avant
	 * @param p
	 */
	public boolean updatePersonne(Personne p);
	
	/**
	 * Methode : deletePersonne
	 * Supprime une personne placee en entree dans la bdd
	 * si elle est enregistree
	 * @param p
	 */
	public boolean deletePersonne(Personne p);

}

