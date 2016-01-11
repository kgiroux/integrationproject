package fr.esigelec.quiz.dao.hibernate;

/**Projet d'integration
 * Le jeu de TF8
 * @author GSI-IR
 * Farid CHOUAKRIA et DELAUNAY BRICE
 * Classe QuizDAOImpl
 * Impl�mentation des m�thodes de l'interface QuizDAO
 * pour les liens avec la base de donn�es
 * */

import java.util.List;

import fr.esigelec.quiz.dao.IQuizDAO;
import fr.esigelec.quiz.dto.Proposition;
import fr.esigelec.quiz.dto.Question;
import fr.esigelec.quiz.dto.Quiz;

 
public class QuizDAOImpl implements IQuizDAO{

	
	/**
	 * m�thode : createQuiz
	 * @param  q the quiz to create
	 */
	@Override
	public void createQuiz(Proposition q) {
		// TODO Auto-generated method stub
		
	}

	
	/**
	 * m�thode : getQuiz
	 * @param  id the id of the quiz we want
	 * @return the quiz
	 */
	@Override
	public Question getQuiz(int id_quiz) {
		// TODO Auto-generated method stub
		return null;
	}

	
	/**
	 * m�thode : listQuiz
	 * @return all the quizs
	 */
	@Override
	public List<Quiz> listQuiz() {
		// TODO Auto-generated method stub
		return null;
	}

	
	/**
	 * m�thode : updateQuiz
	 * @param  q the quiz which should be updated
	 */
	@Override
	public void updateQuiz(Quiz q) {
		// TODO Auto-generated method stub
		
	}

	
	/**
	 * m�thode : deleteQuiz
	 * @param  q the quiz to delete
	 */
	@Override
	public void deleteQuiz(Quiz q) {
		// TODO Auto-generated method stub
		
	}

}
