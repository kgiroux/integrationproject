package fr.esigelec.quiz.dao;

import java.util.List;

import fr.esigelec.quiz.dto.Quiz;


/**Projet d'integration
 * Le jeu de TF8
 * @author GSI-IR
 * BOSSO BOSSO Ghyslaine and NGANE Pascale
 * Interface IQuizDAO
 */

public interface IQuizDAO {
	
	/**
	 * méthode : createQuiz
	 * @param  q the quiz to create
	 */
	
	public void createQuiz(Quiz q);
	
	/**
	 * méthode : getQuiz
	 * @param  id the id of the quiz we want
	 * @return the quiz
	 */
	
	public Quiz getQuiz(int id_quiz); 
	
	/**
	 * méthode : listQuiz
	 * @return all the quizs
	 */
	
	public List<Quiz> listQuiz(); 
	
	/**
	 * méthode : updateQuiz
	 * @param  q the quiz which should be updated
	 */
	
	public void updateQuiz(Quiz q); 
	
	/**
	 * méthode : deleteQuiz
	 * @param  q the quiz to delete
	 */
	
	public void deleteQuiz(Quiz q); 

}
