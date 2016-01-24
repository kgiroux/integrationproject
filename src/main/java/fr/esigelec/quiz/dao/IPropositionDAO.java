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
	 * m�thode : createProposition
	 * @param  q the proposition to create
	 */

	public boolean createProposition(Proposition q);

	/**
	 * m�thode : getProposition
	 * @param  id the id of the proposition we want
	 * @return the proposition
	 */

	public Proposition getProposition(int id_proposition); 

	/**
	 * m�thode : listProposition
	 * @return all the propositions
	 */

	public List<Proposition> listProposition(); 

	/**
	 * m�thode : updateProposition
	 * @param  q the proposition which should be updated
	 */

	public boolean updateProposition(Proposition p); 

	/**
	 * m�thode : deleteProposition
	 * @param  q the proposition which should be deleted
	 */

	public boolean deleteProposition(Proposition p); 
	
	/**
	 * m�thode : getBonneReponse
	 * @param  q the question 
	 */
	public Proposition getBonneReponse(Question q);

}