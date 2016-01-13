package fr.esigelec.quiz.dao.hibernate;

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
	
	/**
	 * methode : createQuiz
	 * @param  q the quiz to create
	 */
	@Override
	public boolean createQuiz(Quiz q) {
		Session session= HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(q);
		session.getTransaction().commit();
		session.close();
	
		return (q.getId() != 0);
	}

	
	/**
	 * methode : getQuiz
	 * @param  id the id of the quiz we want
	 * @return the quiz
	 */
	@Override
	public Quiz getQuiz(int id) {
		Session session= HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Quiz quiz=(Quiz)session.get(Quiz.class,id);
		session.getTransaction().commit();
		session.close();
		return quiz;
	}

	
	/**
	 * methode : listQuiz
	 * @return all the quizs
	 */
	@Override
	public List<Quiz> listQuiz() {
		Session session= HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query=session.createQuery("from Quiz");
		List<Quiz> listeQuizs=query.list();
		session.getTransaction().commit();
		session.close();
		return listeQuizs;
	}

	/**
	 * methode : getListQuizPublie
	 * @param status
	 * @return all the quizs publicated
	 */
	public List<Quiz> getListQuizPublie(int status){
		Session session= HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query=session.createQuery("from Quiz WHERE dateDebutQuiz is not null");
		List<Quiz> listeQuizs=query.list();
		session.getTransaction().commit();
		session.close();
		return listeQuizs;
	}
	
	/**
	 * methode : getListQuizFinish
	 * @return all the quizs finished
	 */
	public List<Quiz> getListQuizFinish(){
		Session session= HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query=session.createQuery("from Quiz WHERE dateFinQuiz is not null");
		List<Quiz> listeQuizs=query.list();
		session.getTransaction().commit();
		session.close();
		return listeQuizs;
	}
	
	/**
	 * m�thode : listQuestionQuiz
	 * @param  id the id of the quiz
	 * @return the list of the questions
	 */
	public Set<Question> listQuestionQuiz(int idQuiz){
		Session session= HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Set<Question> listeQuestionsQuiz=getQuiz(idQuiz).getListeQuestions();
		session.getTransaction().commit();
		session.close();
		return listeQuestionsQuiz;
	}

	/**
	 * methode : getNbQuestionParQuiz
	 * @param  id the id of the quiz
	 * @return the number of questions per quiz
	 */
	public int getNbQuestionParQuiz(int idQuiz){
		
		Session session= HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Set<Question> listeQuestions=listQuestionQuiz(idQuiz);
		session.getTransaction().commit();
		session.close();
		return listeQuestions.size();
	}
	
	/**
	 * methode : updateQuiz
	 * @param  q the quiz which should be updated
	 */
	@Override
	public boolean updateQuiz(Quiz q) {  
		Session session= HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(q);
		Quiz newQuiz = getQuiz(q.getId());
		session.getTransaction().commit();
		session.close();
		
		return (newQuiz.equals(q));
	}

	
	/**
	 * methode : deleteQuiz
	 * @param  q the quiz to delete
	 */
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
