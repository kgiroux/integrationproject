package fr.esigelec.quiz.dao.hibernate;

import java.util.ArrayList;

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
import org.hibernate.Query;
import org.hibernate.Session;

import fr.esigelec.quiz.dao.IQuizDAO;
import fr.esigelec.quiz.dto.Question;
import fr.esigelec.quiz.dto.Quiz;
import fr.esigelec.quiz.util.SetToListConverter;
import fr.esigelec.quiz.util.Word;

import java.sql.SQLException;
 
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
		@SuppressWarnings("unchecked")
		List<Quiz> listeQuiz =  query.list();
		session.getTransaction().commit();
		session.close();
		return listeQuiz;
	}
	
	public List<Quiz> getListQuizFinish(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("FROM Quiz WHERE dateFinQuiz is not null");
		@SuppressWarnings("unchecked")
		List<Quiz> listeQuiz = query.list();
		session.getTransaction().commit();
		session.close();
		return listeQuiz;
	}
	
	public List<Question> listQuestionQuiz(Quiz quiz){
		Quiz q = getQuiz(quiz.getId());
		// TODO: use SetToListConverter
		// List<Question> array = new ArrayList<Question>();
		// SetToListConverter.SetToList(array, q.getQuestions());
		List<Question> questions = new ArrayList<>(q.getQuestions());
		return questions;
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
