package fr.esigelec.quiz.dao.hibernate;

/**Projet d'integration
 * Le jeu de TF8
 * @author GSI-IR
 * Farid CHOUAKRIA et DELAUNAY BRICE
 * Classe QuestionDAOImpl
 * Implï¿½mentation des mï¿½thodes de l'interface QuestionDAO
 * pour les liens avec la base de donnï¿½es
 * */

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import fr.esigelec.quiz.controleur.TestLogger;
import fr.esigelec.quiz.dao.IQuestionDAO;
import fr.esigelec.quiz.dto.Question;
import fr.esigelec.quiz.dto.Quiz;

public class QuestionDAOImpl implements IQuestionDAO {

	private static final Logger logger = Logger.getLogger(TestLogger.class);

	/**
	 * mï¿½thode : createQuestion
	 * 
	 * @param q
	 *            the question to create
	 */
	@Override
	public boolean createQuestion(Question q) {

		logger.info("createQuestion question");

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(q);
		session.getTransaction().commit();
		session.close();
		logger.info("createQuestion : " + q.toString());
		Cache.viderCacheListeQuiz();
		return (q.getId() != 0);
	}

	/**
	 * mï¿½thode : getQuestion
	 * 
	 * @param id
	 *            the id of the question we want
	 * @return the question
	 */
	@Override
	public Question getQuestion(int id) {

		logger.info("getQuestion id");

		// si cache à jour on cherche dans le cache
		if (Cache.getCacheListeQuiz() != null) {

			for (Quiz q : Cache.getCacheListeQuiz())
				for (Question question : q.getListeQuestions())
					if (question.getId() == id)
						return question;
			// quiz pas trouvé
			return null;

		}

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Question question = session.get(Question.class, id);
		session.getTransaction().commit();
		session.close();
		logger.info("get Question: " + question.toString() + " From id : " + id);
		return question;
	}

	/**
	 * mï¿½thode : listQuestion
	 * 
	 * @return all the questions
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Question> listQuestion() {
		logger.info("listQuestion");

		List<Question> listeQuestions = null;

		// si cache à jour on cherche dans le cache
		if (Cache.getCacheListeQuiz() != null) {
			listeQuestions = new ArrayList<Question>();
			for (Quiz q : Cache.getCacheListeQuiz())
				for (Question question : q.getListeQuestions())
					listeQuestions.add(question);

			// quiz pas trouvé
			return listeQuestions;

		}

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Question");

		listeQuestions = (List<Question>) query.list();
		session.getTransaction().commit();
		session.close();
		logger.info("get liste Question: " + listeQuestions.toString());
		return listeQuestions;
	}

	/**
	 * mï¿½thode : updateQuestion
	 * 
	 * @param q
	 *            the question which should be updated
	 */
	@Override
	public boolean updateQuestion(Question q) {

		logger.info("updateQuestion question");

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(q);
		Question newQuestion = getQuestion(q.getId());
		session.getTransaction().commit();
		session.close();
		logger.info("updateQuestion: " + newQuestion.toString());
		Cache.viderCacheListeQuiz();
		return (newQuestion.equals(q));
	}

	/**
	 * mï¿½thode : deleteQuestion
	 * 
	 * @param q
	 *            the question to delete
	 */
	@Override
	public boolean deleteQuestion(Question q) {

		logger.info("deleteQuestion question");

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(q);
		session.getTransaction().commit();
		session.close();
		logger.info("deleteQuestion: " + q);
		Cache.viderCacheListeQuiz();
		return (q == null);
	}

}
