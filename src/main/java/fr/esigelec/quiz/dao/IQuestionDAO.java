package fr.esigelec.quiz.dao;

import java.util.List;

import fr.esigelec.quiz.dto.Question; 

/**Projet d'integration
  * Le jeu de TF8
  * @author GSI-IR
  * BOSSO BOSSO Ghyslaine and NGANE Pascale
  * Interface QuestionDAO
  * */

public interface IQuestionDAO {
	
	/**
	 * m�thode : createQuestion
	 * @param  q the question to create
	 */
	
	public void createQuestion(Question q);
	
	/**
	 * m�thode : getQuestion
	 * @param  id the id of the question we want
	 * @return the question
	 */
	
	public Question getQuestion(int id_question); 
	
	/**
	 * m�thode : listQuestion
	 * @return all the questions
	 */
	
	public List<Question> listQuestion(); 
	
	/**
	 * m�thode : updateQuestion
	 * @param  q the question which should be updated
	 */
	
	public void updateQuestion(Question q); 
	
	/**
	 * m�thode : deleteQuestion
	 * @param  q the question to delete
	 */
	
	public void deleteQuestion(Question q); 


}
