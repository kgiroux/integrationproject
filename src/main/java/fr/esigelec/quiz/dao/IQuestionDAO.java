package fr.esigelec.quiz.dao;

import java.util.List;
import java.util.Set;

import fr.esigelec.quiz.dto.Question;


/**Projet d'integration
 * Le jeu de TF8
 * @author GSI-IR
 * BOSSO BOSSO Ghyslaine and NGANE Pascale
 * Interface IQuestionDAO
 */
public interface IQuestionDAO {

	/**
	 * m�thode : createQuestion
	 * @param  q the question to create
	 */
	public boolean createQuestion(Question q);

	/**
	 * m�thode : getQuestion
	 * @param  id the id of the question we want
	 * @return the question
	 */
	public Question getQuestion(int id); 

	/**
	 * m�thode : listQuestion
	 * @return all the questions
	 */
	public Set<Question> listQuestion(); 
	
	/**
	 * m�thode : updateQuestion
	 * @param  q the question which should be updated
	 */
	public boolean updateQuestion(Question q); 

	/**
	 * m�thode : deleteQuestion
	 * @param  q the question to delete
	 */
	public boolean deleteQuestion(Question q); 

}