package fr.esigelec.quiz.dao.hibernate;

/**Projet d'integration
 * Le jeu de TF8
 * @author GSI-IR
 * Farid CHOUAKRIA et DELAUNAY BRICE
 * Classe QuestionDAOImpl
 * Implémentation des méthodes de l'interface IQuestionDAO
 * pour les liens avec la base de données
 */

import java.util.List;

import fr.esigelec.quiz.dao.IQuestionDAO;
import fr.esigelec.quiz.dto.Question;

public class QuestionDAOImpl implements IQuestionDAO{

	/**
	 * méthode : createQuestion
	 * @param  q the question to create
	 */
	@Override
	public void createQuestion(Question q) {
		// TODO Auto-generated method stub
		
	}

	
	/**
	 * méthode : getQuestion
	 * @param  id the id of the question we want
	 * @return the question
	 */
	@Override
	public Question getQuestion(int id_question) {
		// TODO Auto-generated method stub
		return null;
	}

	
	/**
	 * méthode : listQuestion
	 * @return all the questions
	 */
	@Override
	public List<Question> listQuestion() {
		// TODO Auto-generated method stub
		return null;
	}

	
	/**
	 * méthode : updateQuestion
	 * @param  q the question which should be updated
	 */
	@Override
	public void updateQuestion(Question q) {
		// TODO Auto-generated method stub
		
	}

	
	/**
	 * méthode : deleteQuestion
	 * @param  q the question to delete
	 */
	@Override
	public void deleteQuestion(Question q) {
		// TODO Auto-generated method stub
		
	}

}
