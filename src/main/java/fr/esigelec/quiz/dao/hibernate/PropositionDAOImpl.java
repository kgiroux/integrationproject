package fr.esigelec.quiz.dao.hibernate;

/**Projet d'integration
 * Le jeu de TF8
 * @author GSI-IR
 * Farid CHOUAKRIA et DELAUNAY BRICE
 * Classe PropositionDAOImpl
 * Implémentation des méthodes de l'interface IPropositionDAO
 * pour les liens avec la base de données
 */

import java.util.List;

import fr.esigelec.quiz.dao.IPropositionDAO;
import fr.esigelec.quiz.dto.Proposition;
import fr.esigelec.quiz.dto.Question;

public class PropositionDAOImpl implements IPropositionDAO{

	/**
	 * méthode : createProposition
	 * @param  q the proposition to create
	 */
	@Override
	public void createProposition(Proposition q) {
		// TODO Auto-generated method stub
		
	}

	
	/**
	 * méthode : getProposition
	 * @param  id the id of the proposition we want
	 * @return the proposition
	 */
	@Override
	public Proposition getProposition(int id_proposition) {
		// TODO Auto-generated method stub
		return null;
	}

	
	/**
	 * méthode : listProposition
	 * @return all the propositions
	 */
	@Override
	public List<Proposition> listProposition() {
		// TODO Auto-generated method stub
		return null;
	}

	
	/**
	 * méthode : getPropositionParQuestion
	 * @param  q the Question 
	 * @return the list of proposition
	 */
	@Override
	public List<Proposition> getPropositionParQuestion(Question q) {
		// TODO Auto-generated method stub
		return null;
	}

	
	/**
	 * méthode : updateProposition
	 * @param  q the proposition which should be updated
	 */
	@Override
	public void updateProposition(Proposition q) {
		// TODO Auto-generated method stub
		
	}

	
	/**
	 * méthode : deleteProposition
	 * @param  q the proposition which should be deleted
	 */
	@Override
	public void deleteProposition(Proposition q) {
		// TODO Auto-generated method stub
		
	}

}
