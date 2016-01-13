package fr.esigelec.quiz.dao;

import java.util.List;

import fr.esigelec.quiz.dto.Question;
import fr.esigelec.quiz.dto.Quiz;


/**Projet d'integration
 * Le jeu de TF8
 * @author GSI-IR
 * BOSSO BOSSO Ghyslaine and NGANE Pascale
 * Interface IQuizDAO
 */

public interface IQuizDAO {
	
	/**
	 * m�thode : createQuiz
	 * @param  q the quiz to create
	 */
	
	public boolean createQuiz(Quiz q);
	
	/**
	 * m�thode : getQuiz
	 * @param  id the id of the quiz we want
	 * @return the quiz
	 */
	
	public Quiz getQuiz(int id); 
	
	/**
	 * m�thode : listQuiz
	 * @return all the quizs
	 */
	
	public List<Quiz> listQuiz(); 
	
	/**
	 * m�thode : getListQuizPublie
	 * @return all the quizs publicated
	 */
	public List<Quiz> getListQuizPublie(int status);
	
	/**
	 * m�thode : getListQuizFinish
	 * @return all the quizs finished
	 */
	public List<Quiz> getListQuizFinish();
	
	/**
	 * m�thode : listQuestion
	 * @return all the questions
	 */
	public List<Question> listQuestionQuiz(int idQuiz);
	
	/**
	 * m�thode : getNbQuestionParQuiz
	 * @param idQuiz
	 * @return the size of the list
	 */
	public int getNbQuestionParQuiz(int idQuiz);
	
	/**
	 * m�thode : updateQuiz
	 * @param  q the quiz which should be updated
	 */
	
	public boolean updateQuiz(Quiz q); 
	
	/**
	 * m�thode : deleteQuiz
	 * @param  q the quiz to delete
	 */
	
	public boolean deleteQuiz(Quiz q); 
}
