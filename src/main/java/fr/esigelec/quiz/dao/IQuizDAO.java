package fr.esigelec.quiz.dao;

import java.util.List;

import fr.esigelec.quiz.dto.Question;
import fr.esigelec.quiz.dto.Quiz;

/**Projet d'integration
 * Le jeu de TF8
 * @author GSI-IR
 * BOSSO BOSSO Ghyslaine and NGANE Pascale 
 * Interface QuizDAO
 * */

public interface IQuizDAO {
	
	/**
	 * m�thode : createQuiz
	 * @param  q the quiz to create
	 */
	
	public void createQuiz(Quiz q);
	
	/**
	 * m�thode : getQuiz
	 * @param  id the id of the quiz we want
	 * @return the quiz
	 */
	
	public Quiz getQuiz(int id_quiz); 
	
	/**
	 * m�thode : listQuiz
	 * @return all the quizs
	 */
	
	public List<Quiz> listQuiz(); 
	
	/**
	 * m�thode : updateQuiz
	 * @param  q the quiz which should be updated
	 */
	
	public void updateQuiz(Quiz q); 
	
	/**
	 * m�thode : deleteQuiz
	 * @param  q the quiz to delete
	 */
	
	public void deleteQuiz(Quiz q); 

}