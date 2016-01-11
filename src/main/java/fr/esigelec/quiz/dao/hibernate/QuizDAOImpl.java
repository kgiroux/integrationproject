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

import org.hibernate.Query;
import org.hibernate.Session;
import fr.esigelec.quiz.dao.IQuizDAO;
import fr.esigelec.quiz.dto.Quiz;
 
public class QuizDAOImpl implements IQuizDAO{

	
	/**
	 * m�thode : createQuiz
	 * @param  q the quiz to create
	 */
	@Override
	public void createQuiz(Quiz q) {
		Session session= HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(q);
		session.getTransaction().commit();
		session.close();
	}

	
	/**
	 * m�thode : getQuiz
	 * @param  id the id of the quiz we want
	 * @return the quiz
	 */
	@Override
	public Quiz getQuiz(int id_quiz) {
		Session session= HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Quiz quiz=(Quiz)session.get(Quiz.class,id_quiz);
		session.getTransaction().commit();
		session.close();
		return quiz;
	}

	
	/**
	 * m�thode : listQuiz
	 * @return all the quizs
	 */
	@Override
	public List<Quiz> listQuiz() {
		Session session= HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query=session.createQuery("from Quiz");
		List<Quiz> liste_quiz=query.list();
		session.getTransaction().commit();
		session.close();
		return liste_quiz;
	}

	
	/**
	 * m�thode : updateQuiz
	 * @param  q the quiz which should be updated
	 */
	@Override
	public void updateQuiz(Quiz q) {
		Session session= HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(q);
		session.getTransaction().commit();
		session.close();
	}

	
	/**
	 * m�thode : deleteQuiz
	 * @param  q the quiz to delete
	 */
	@Override
	public void deleteQuiz(Quiz q) {
		Session session= HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(q);
		session.getTransaction().commit();
		session.close();
	}

}
