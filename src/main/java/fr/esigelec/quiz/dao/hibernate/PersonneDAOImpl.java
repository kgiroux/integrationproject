package fr.esigelec.quiz.dao.hibernate;

/**Projet d'integration
 * Le jeu de TF8
 * @author GSI-IR
 * Farid CHOUAKRIA et DELAUNAY BRICE
 * Classe PersonneDAOImpl
 * Implémentation des méthodes de l'interface IPersonneDAO
 * pour les liens avec la base de données
 */

import java.util.List;

import fr.esigelec.quiz.dao.IPersonneDAO;
import fr.esigelec.quiz.dto.Personne;

public class PersonneDAOImpl implements IPersonneDAO{

	/**
	 * Méthode : createPersonne
	 * Crée une personne dans la base de données
	 * @param p
	 */
	@Override
	public void createPersonne(Personne p) {
		// TODO Auto-generated method stub
		
	}

	
	/**
	 * Méthode : getPersonne
	 * @param id
	 * @return une personne à partir d'un id
	 */
	@Override
	public Personne getPersonne(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	/**
	 * Méthode : listPersonne
	 * @return la liste de toutes les personnes enregistrées dans la base de données
	 */
	@Override
	public List<Personne> listPersonne() {
		// TODO Auto-generated method stub
		return null;
	}

	
	/**
	 * Méthode : updatePersonne
	 * Met à jour une personne placée en entrée dans la bdd
	 * si elle a été enregistrée avant
	 * @param p
	 */
	@Override
	public void updatePersonne(Personne p) {
		// TODO Auto-generated method stub
		
	}

	
	/**
	 * Méthode : deletePersonne
	 * Supprime une personne placée en entrée dans la bdd
	 * si elle est enregistrée
	 * @param p
	 */
	@Override
	public void deletePersonne(Personne p) {
		// TODO Auto-generated method stub
		
	}

}
