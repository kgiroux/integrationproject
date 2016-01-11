package fr.esigelec.quiz.dao.hibernate;

/**Projet d'integration
 * Le jeu de TF8
 * @author GSI-IR
 * Farid CHOUAKRIA et DELAUNAY BRICE
 * Classe QuestionDAOImpl
 * Impl�mentation des m�thodes de l'interface QuestionDAO
 * pour les liens avec la base de donn�es
 * */

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import fr.esigelec.quiz.dao.IQuestionDAO;
import fr.esigelec.quiz.dto.Question;
import fr.esigelec.quiz.dto.Quiz;


public class QuestionDAOImpl implements IQuestionDAO{

	/**
	 * m�thode : createQuestion
	 * @param  q the question to create
	 */ 
	@Override
	public void createQuestion(Question q) {
		Session session= HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(q);
		session.getTransaction().commit();
		session.close();
	}

	
	/**
	 * m�thode : getQuestion
	 * @param  id the id of the question we want
	 * @return the question
	 */
	@Override
	public Question getQuestion(int id) {
		Session session= HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Question question=(Question)session.get(Question.class,id);
		session.getTransaction().commit();
		session.close();
		return question;
	}

	
	/**
	 * m�thode : listQuestion
	 * @return all the questions
	 */ 
	@Override
	public List<Question> listQuestion() {
		Session session= HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query=session.createQuery("from Question");
		List<Question> listeQuestions=query.list();
		session.getTransaction().commit();
		session.close();
		return listeQuestions;
	}

	
	/**
	 * m�thode : updateQuestion
	 * @param  q the question which should be updated
	 */
	@Override
	public void updateQuestion(Question q) {
		Session session= HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(q);
		session.getTransaction().commit();
		session.close();
	}

	
	/**
	 * m�thode : deleteQuestion
	 * @param  q the question to delete
	 */
	@Override
	public void deleteQuestion(Question q) {
		Session session= HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(q);
		session.getTransaction().commit();
		session.close();
	}

}
