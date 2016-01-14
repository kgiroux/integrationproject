package fr.esigelec.quiz.dao;

import java.sql.SQLException;

import fr.esigelec.quiz.dao.IPropositionDAO;
import fr.esigelec.quiz.dao.IQuestionDAO;
import fr.esigelec.quiz.dao.IQuizDAO;
import fr.esigelec.quiz.dao.hibernate.PropositionDAOImpl;
import fr.esigelec.quiz.dao.hibernate.QuestionDAOImpl;
import fr.esigelec.quiz.dao.hibernate.QuizDAOImpl;
import fr.esigelec.quiz.dto.Proposition;
import fr.esigelec.quiz.dto.Question;
import fr.esigelec.quiz.dto.Quiz;

public class TestHbm {

public static void main(String[] args) throws SQLException {
		
		test_quiz();
		test_question();
		test_proposition();
	}
	
	private static void test_quiz() throws SQLException {

		System.out.println("---TEST QUIZ---");
		IQuizDAO quizDAO = new QuizDAOImpl();
		Quiz q = quizDAO.getQuiz(4);
		System.out.println("quiz=" + q.toString());
		System.out.println("------END------");
	}
	
	private static void test_question() throws SQLException {

		System.out.println("---TEST QUESTION---");
		IQuestionDAO questionDAO = new QuestionDAOImpl();
		Question q = questionDAO.getQuestion(2);
		System.out.println("question=" + q.toString());
		System.out.println("------END------");
	}
	
	/**
	 * IdQuestion supprimé
	 * @throws SQLException
	 */
	private static void test_proposition() throws SQLException {
		System.out.println("---TEST PROPOSITION---");
		IPropositionDAO propositionDAO = new PropositionDAOImpl();
		Proposition p = propositionDAO.getProposition(3);
		System.out.println("proposition=" + p.toString());
		System.out.println("------END------");
	}

}
