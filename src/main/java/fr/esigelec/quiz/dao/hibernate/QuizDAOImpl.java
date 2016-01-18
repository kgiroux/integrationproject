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

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import fr.esigelec.quiz.controleur.TestLogger;
import fr.esigelec.quiz.dao.IQuizDAO;
import fr.esigelec.quiz.dto.Question;
import fr.esigelec.quiz.dto.Quiz;
import fr.esigelec.quiz.util.SetToListConverter;
import java.sql.SQLException;
 
public class QuizDAOImpl implements IQuizDAO {
	
	private static final Logger logger = Logger.getLogger(TestLogger.class);

	public boolean createQuiz(Quiz q) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(q);
		session.getTransaction().commit();
		session.close();
		logger.info("Create Quiz : " + q.toString());
		return (q.getId() != 0);
	}

	public Quiz getQuiz(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Quiz quiz = (Quiz)session.get(Quiz.class, id);
		session.getTransaction().commit();
		logger.info("get Quiz: " + quiz.toString() + " From id : " + id);
		session.close();
		return quiz;
	}

	public List<Quiz> listQuiz() throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = "from Quiz";
		@SuppressWarnings("unchecked")
		List<Quiz> retour = session.createQuery(hql).list();
		session.getTransaction().commit();
		session.close();
		logger.info("get liste Quiz: " + retour.toString());
		return retour;
	}
	
	/**
	 * methode qui retourne les quiz avec les questions
	 * @return
	 * @throws SQLException
	 */
	public List<Quiz> listQuizAvecQuestions() throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = "from Quiz";
		@SuppressWarnings("unchecked")
		List<Quiz> retour = session.createQuery(hql).list();
		//on charge les qestions
		for(Quiz q:retour)
			System.out.println(q.getQuestions());
		session.getTransaction().commit();
		session.close();
		logger.info("get liste Quiz: " + retour.toString());
		return retour;
	}
	
	
	

	public List<Quiz> getListQuizPublie(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("FROM Quiz WHERE dateDebutQuiz is not null");
		@SuppressWarnings("unchecked")
		List<Quiz> listeQuiz =  query.list();
		session.getTransaction().commit();
		session.close();
		logger.info("getListQuizPublie: " + listeQuiz.toString());
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
		logger.info("getListQuizFinish: " + listeQuiz.toString());
		return listeQuiz;
	}
	
	public List<Question> listQuestionQuiz(Quiz quiz){
		Quiz q = getQuiz(quiz.getId());
		// TODO: use SetToListConverter
		List<Question> array = new ArrayList<Question>();
		SetToListConverter.SetToList(array, q.getQuestions());
		logger.info("listQuestionQuiz: " + array.toString());
		return array;
	}

	public int getNbQuestionParQuiz(Quiz quiz){
		List<Question> listeQuestions = listQuestionQuiz(quiz);
		logger.info("getNbQuestionParQuiz: " + listeQuestions.size() +" From quiz" + quiz.toString());
		return listeQuestions.size();
	}
	
	public boolean updateQuiz(Quiz q) {  
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(q);
		session.getTransaction().commit();
		session.close();
		Quiz newQuiz = getQuiz(q.getId());
		logger.info("updateQuiz: " + newQuiz.toString());
		return (newQuiz.equals(q));
	}

	@Override
	public boolean deleteQuiz(Quiz q) {
		Session session= HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(q);
		session.getTransaction().commit();
		session.close();
		logger.info("deleteQuiz: " + q);
		return (q == null);
	}
}
