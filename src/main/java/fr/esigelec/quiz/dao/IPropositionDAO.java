package fr.esigelec.quiz.dao;

import java.util.List;

import fr.esigelec.quiz.dto.Proposition;
import fr.esigelec.quiz.dto.Question;


/**Projet d'integration
 * Le jeu de TF8
 * @author GSI-IR
 * BOSSO BOSSO Ghyslaine and NGANE Pascale
 * Interface IPropositionDAO
 */

public interface IPropositionDAO {

	/**
	 * méthode : createProposition
	 * @param  q the proposition to create
	 */

	public void createProposition(Proposition q);

	/**
	 * méthode : getProposition
	 * @param  id the id of the proposition we want
	 * @return the proposition
	 */

	public Question getProposition(int id_proposition); 

	/**
	 * méthode : listProposition
	 * @return all the propositions
	 */

	public List<Proposition> listProposition(); 

	/**
	 * méthode : getPropositionParQuestion
	 * @param  q the Question 
	 * @return the list of proposition
	 */

	public List<Proposition> getPropositionParQuestion(Question q);

	/**
	 * méthode : updateProposition
	 * @param  q the proposition which should be updated
	 */

	public void updateProposition(Proposition p); 

	/**
	 * méthode : deleteProposition
	 * @param  q the proposition which should be deleted
	 */

	public void deleteProposition(Proposition p); 

}

