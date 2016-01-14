package fr.esigelec.quiz.dao.hibernate;

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

	public List<Quiz> listQuiz() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("FROM quiz");
		List<Quiz> retour = query.list();
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
		Set<Quiz> listeQuiz = (Set<Quiz>) query.list();
		List<Quiz> retour = new LinkedList(listeQuiz);
		session.getTransaction().commit();
		session.close();
		return retour;
	}
	
	public List<Question> listQuestionQuiz(int idQuiz){
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		ArrayList<Question> retour = new ArrayList<Question>();
		getQuiz(idQuiz).getListeQuestions(retour);
		session.getTransaction().commit();
		session.close();
		return retour;
	}

	public int getNbQuestionParQuiz(int idQuiz){
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		List<Question> listeQuestions = listQuestionQuiz(idQuiz);
		session.getTransaction().commit();
		session.close();
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
