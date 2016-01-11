package fr.esigelec.quiz.dao.hibernate;

/**Projet d'integration
 * Le jeu de TF8
 * @author GSI-IR
 * Farid CHOUAKRIA et DELAUNAY BRICE
 * Classe PropositionDAOImpl
 * Impl�mentation des m�thodes de l'interface PropositionDAO
 * pour les liens avec la base de donn�es
 * */


import java.util.List;

import fr.esigelec.quiz.dao.IPropositionDAO;
import fr.esigelec.quiz.dto.Proposition;
import fr.esigelec.quiz.dto.Question;

public class PropositionDAOImpl implements IPropositionDAO{

	/**
	 * m�thode : createProposition
	 * @param  q the proposition to create
	 */
	@Override
	public void createProposition(Proposition q) {
		// TODO Auto-generated method stub
		
	}

	
	/**
	 * m�thode : getProposition
	 * @param  id the id of the proposition we want
	 * @return the proposition
	 */
	@Override
	public Question getProposition(int id_proposition) {
		// TODO Auto-generated method stub
		return null;
	}

	
	/**
	 * m�thode : listProposition
	 * @return all the propositions
	 */
	@Override
	public List<Proposition> listProposition() {
		// TODO Auto-generated method stub
		return null;
	}

	
	/**
	 * m�thode : getPropositionParQuestion
	 * @param  q the Question 
	 * @return the list of proposition
	 */
	@Override
	public List<Proposition> getPropositionParQuestion(Question q) {
		// TODO Auto-generated method stub
		return null; 
	}

	
	/**
	 * m�thode : updateProposition
	 * @param  q the proposition which should be updated
	 */
	@Override
	public void updateProposition(Proposition q) {
		// TODO Auto-generated method stub
		
	}

	
	/**
	 * m�thode : deleteProposition
	 * @param  q the proposition which should be deleted
	 */
	@Override
	public void deleteProposition(Proposition q) {
		// TODO Auto-generated method stub
		
	}

}
