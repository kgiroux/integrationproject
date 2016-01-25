package fr.esigelec.quiz.dao;

import java.sql.SQLException;
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
	 * méthode : createQuiz
	 * @param  q the quiz to create
	 */
	
	public boolean createQuiz(Quiz q);
	
	/**
	 * méthode : getQuiz
	 * @param  id the id of the quiz we want
	 * @return the quiz
	 */
	
	public Quiz getQuiz(int id); 
	
	public Quiz getQuizAvecQuestions(int id);
	
	
	public Quiz getQuizSansQuestions(int id);
	
    
    /**
     * m�thode : getListQuizFinish
     * @return all the quizs finished
     */
    public Quiz getCurrentQuiz();
	
	/**
	 * méthode : listQuiz
	 * @return all the quizs
	 * @throws SQLException 
	 */
	
	public List<Quiz> listQuiz() throws SQLException; 
	
	/**
	 * méthode : listQuiz
	 * @return all the quiz with questions loaded
	 * @throws SQLException 
	 */
	
	public List<Quiz> listQuizAvecQuestions() throws SQLException; 
	
	/**
	 * méthode : getListQuizPublie
	 * @return all the quizs publicated
	 */
	public List<Quiz> getListQuizPublie();
	
	/**
	 * méthode : getListQuizFinish
	 * @return all the quizs finished
	 */
	public List<Quiz> getListQuizFinish();
	
	/**
	 * méthode : listQuestion
	 * @return all the questions
	 */
	public List<Question> listQuestionQuiz(Quiz quiz);
	
	/**
	 * méthode : getNbQuestionParQuiz
	 * @param idQuiz
	 * @return the size of the list
	 */
	public int getNbQuestionParQuiz(Quiz quiz);
	
	/**
	 * méthode : updateQuiz
	 * @param  q the quiz which should be updated
	 */
	
	public boolean updateQuiz(Quiz q); 
	
	/**
	 * méthode : deleteQuiz
	 * @param  q the quiz to delete
	 */
	
	public boolean deleteQuiz(Quiz q); 
	
}
