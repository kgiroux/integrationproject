package fr.esigelec.quiz.dao.hibernate;

/**Projet d'integration
 * Le jeu de TF8
 * @author GSI-IR
 * Farid CHOUAKRIA et DELAUNAY BRICE
 * Classe PersonneDAOImpl
 * Impl�mentation des m�thodes de l'interface IPersonneDAO
 * pour les liens avec la base de donn�es
 */

import java.util.List;

import fr.esigelec.quiz.dao.IPersonneDAO;
import fr.esigelec.quiz.dto.Personne;

public class PersonneDAOImpl implements IPersonneDAO{

	/**
	 * M�thode : createPersonne
	 * Cr�e une personne dans la base de donn�es
	 * @param p
	 */
	@Override
	public void createPersonne(Personne p) {
		// TODO Auto-generated method stub
		
	}

	
	/**
	 * M�thode : getPersonne
	 * @param id
	 * @return une personne � partir d'un id
	 */
	@Override
	public Personne getPersonne(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	/**
	 * M�thode : listPersonne
	 * @return la liste de toutes les personnes enregistr�es dans la base de donn�es
	 */
	@Override
	public List<Personne> listPersonne() {
		// TODO Auto-generated method stub
		return null;
	}

	
	/**
	 * M�thode : updatePersonne
	 * Met � jour une personne plac�e en entr�e dans la bdd
	 * si elle a �t� enregistr�e avant
	 * @param p
	 */
	@Override
	public void updatePersonne(Personne p) {
		// TODO Auto-generated method stub
		
	}

	
	/**
	 * M�thode : deletePersonne
	 * Supprime une personne plac�e en entr�e dans la bdd
	 * si elle est enregistr�e
	 * @param p
	 */
	@Override
	public void deletePersonne(Personne p) {
		// TODO Auto-generated method stub
		
	}

}
