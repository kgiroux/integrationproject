package fr.esigelec.quiz.dao;
import java.util.List;

import fr.esigelec.quiz.dto.Personne;


/**Projet d'integration
 * Le jeu de TF8
 * @author GSI-IR
 * BOSSO BOSSO Ghyslaine and NGANE Pascale
 * Interface IPersonneDAO
 */

public interface IPersonneDAO {

	/**
	 * Méthode : createPersonne
	 * Crée une personne dans la base de données
	 * @param p
	 */
	public void createPersonne(Personne p);

	/**
	 * Méthode : getPersonne
	 * @param id
	 * @return une personne à partir d'un id
	 */
	public Personne getPersonne(int id);

	/**
	 * Méthode : listPersonne
	 * @return la liste de toutes les personnes enregistrées dans la base de données
	 */
	public List<Personne> listPersonne();

	/**
	 * Méthode : updatePersonne
	 * Met à jour une personne placée en entrée dans la bdd
	 * si elle a été enregistrée avant
	 * @param p
	 */
	public void updatePersonne(Personne p);

	/**
	 * Méthode : deletePersonne
	 * Supprime une personne placée en entrée dans la bdd
	 * si elle est enregistrée
	 * @param p
	 */
	public void deletePersonne(Personne p);

}
