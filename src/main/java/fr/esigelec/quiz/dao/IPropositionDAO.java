package fr.esigelec.quiz.dao;
import java.util.List;

import fr.esigelec.quiz.dto.Proposition;
import fr.esigelec.quiz.dto.Question;


/**Projet d'integration
 * Le jeu de TF8
 * @author GSI-IR
 * BOSSO BOSSO Ghyslaine and NGANE Pascale
 * Interface PropositionDAO 
 */

public interface IPropositionDAO {
	
	/**
	 * m�thode : createProposition
	 * @param  q the proposition to create
	 */
	
	public void createProposition(Proposition q);
	
	/**
	 * m�thode : getProposition
	 * @param  id the id of the proposition we want
	 * @return the proposition
	 */
	
	public Question getProposition(int id_proposition); 
	
	/**
	 * m�thode : listProposition
	 * @return all the propositions
	 */
	
	public List<Proposition> listProposition(); 
	
	/**
	 * m�thode : getPropositionParQuestion
	 * @param  q the Question 
	 * @return the list of proposition
	 */
	
	public List<Proposition> getPropositionParQuestion( Question q);
	
	/**
	 * m�thode : updateProposition
	 * @param  q the proposition which should be updated
	 */
	
	public void updateProposition(Proposition q); 
	
	/**
	 * m�thode : deleteProposition
	 * @param  q the proposition which should be deleted
	 */
	
	public void deleteProposition(Proposition q); 

}