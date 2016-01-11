package fr.esigelec.quiz.dao.hibernate;

/**Projet d'integration
 * Le jeu de TF8
 * @author GSI-IR
 * Farid CHOUAKRIA et DELAUNAY BRICE
 * Classe QuizDAOImpl
 * Implémentation des méthodes de l'interface IQuizDAO
 * pour les liens avec la base de données
 */

import java.util.List;

import fr.esigelec.quiz.dao.IQuizDAO;
import fr.esigelec.quiz.dto.Proposition;
import fr.esigelec.quiz.dto.Quiz;

public class QuizDAOImpl implements IQuizDAO{

	/**
	 * méthode : createQuiz
	 * @param  q the quiz to create
	 */
	@Override
	public void createQuiz(Proposition q) {
		// TODO Auto-generated method stub
		
	}

	
	/**
	 * méthode : getQuiz
	 * @param  id the id of the quiz we want
	 * @return the quiz
	 */
	@Override
	public Quiz getQuiz(int id_quiz) {
		// TODO Auto-generated method stub
		return null;
	}

	
	/**
	 * méthode : listQuiz
	 * @return all the quizs
	 */
	@Override
	public List<Quiz> listQuiz() {
		// TODO Auto-generated method stub
		return null;
	}

	
	/**
	 * méthode : updateQuiz
	 * @param  q the quiz which should be updated
	 */
	@Override
	public void updateQuiz(Quiz q) {
		// TODO Auto-generated method stub
		
	}

	
	/**
	 * méthode : deleteQuiz
	 * @param  q the quiz to delete
	 */
	@Override
	public void deleteQuiz(Quiz q) {
		// TODO Auto-generated method stub
		
	}

}
