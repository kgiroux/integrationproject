package fr.esigelec.quiz.dao.hibernate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

/**Projet d'integration
 * Le jeu de TF8
 * GSI-IR
 * @author Ghyslaine BOSSO BOSSO
 * @author NGANE Pascale
 * @author DELAUNAY Brice
 * @author Farid CHOUAKRIA 
 * Classe QuizDAOImpl
 * Impl�mentation des m�thodes de l'interface QuizDAO
 * pour les liens avec la base de donn�es
 * */

import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;

import fr.esigelec.quiz.dao.IQuizDAO;
import fr.esigelec.quiz.dto.Question;
import fr.esigelec.quiz.dto.Quiz;
 
public class QuizDAOImpl implements IQuizDAO{

	public boolean createQuiz(Quiz q) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(q);
		session.getTransaction().commit();
		session.close();
		return (q.getId() != 0);
	}

	public Quiz getQuiz(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Quiz quiz = (Quiz)session.get(Quiz.class, id);
		session.getTransaction().commit();
		session.close();
		return quiz;
	}

	public List<Quiz> listQuiz() throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		// get pays en utilisant HQL
		String hql = "from Quiz";
		@SuppressWarnings("unchecked")
		List<Quiz> retour = session.createQuery(hql).list();
		session.getTransaction().commit();
		session.close();
		return retour;
	}

	public List<Quiz> getListQuizPublie(int status){
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("FROM Quiz WHERE dateDebutQuiz is not null");
		Set<Quiz> listeQuiz = (Set<Quiz>) query.list();
		List<Quiz> retour = new LinkedList(listeQuiz);
		session.getTransaction().commit();
		session.close();
		return retour;
	}
	
	public List<Quiz> getListQuizFinish(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("FROM Quiz WHERE dateFinQuiz is not null");
		List<Quiz> listeQuiz = query.list();
		session.getTransaction().commit();
		session.close();
		return listeQuiz;
	}
	
	public List<Question> listQuestionQuiz(Quiz quiz){
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("FROM Quiz");
		List<Question> listQuiz =  query.list();
		session.getTransaction().commit();
		session.close();
		return listQuiz;
	}

	public int getNbQuestionParQuiz(Quiz quiz){
		List<Question> listeQuestions = listQuestionQuiz(quiz);
		return listeQuestions.size();
	}
	
	public boolean updateQuiz(Quiz q) {  
		Session session= HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(q);
		session.getTransaction().commit();
		session.close();
		Quiz newQuiz = getQuiz(q.getId());
		return (newQuiz.equals(q));
	}

	@Override
	public boolean deleteQuiz(Quiz q) {
		Session session= HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(q);
		session.getTransaction().commit();
		session.close();
		return (q == null);
	}
}
