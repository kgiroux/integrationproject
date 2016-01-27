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
 * Implï¿½mentation des mï¿½thodes de l'interface QuizDAO
 * pour les liens avec la base de donnï¿½es
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
		logger.info("Create Quiz : ");
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(q);
		session.getTransaction().commit();
		session.close();
		logger.info("Create Quiz : " + q.toString());

		// on vide le cache pour forcer une reconstruction au prochain appel
		Cache.setCacheListeQuiz(null);

		return (q.getId() != 0);
	}

	@Deprecated
	public Quiz getQuiz(int id) {
		logger.info("GetQuiz id");

		// si cache existe on cherche dans le cache
		if (Cache.getCacheListeQuiz() != null) {

			for (Quiz q : Cache.getCacheListeQuiz())
				if (q.getId() == id)
					return q;
			return null;

		}
		// sinon on va dans la BDD
		else {

			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Quiz quiz = (Quiz) session.get(Quiz.class, id);
			session.getTransaction().commit();
			logger.info("get Quiz: " + quiz.toString() + " From id : " + id);
			session.close();
			return quiz;
		}
	}

	public Quiz getQuizSansQuestions(int id) {
		logger.info("GetQuizSansQuestions id");

		// si cache à jour on cherche dans le cache
		if (Cache.getCacheListeQuiz() != null) {

			for (Quiz q : Cache.getCacheListeQuiz())
				if (q.getId() == id)
					return q;

			// quiz pas trouvé
			return null;

		}
		// sinon on cherche dans la BDD
		else {

			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Quiz quiz = (Quiz) session.get(Quiz.class, id);
			session.getTransaction().commit();
			logger.info("get Quiz: " + quiz.toString() + " From id : " + id);
			session.close();
			return quiz;
		}

	}

	public Quiz getCurrentQuiz() {
		logger.info("GeCurrenttQuiz id");

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session
				.createQuery("FROM Quiz WHERE dateDebutQuiz is not null and dateFinQuiz is null");
		@SuppressWarnings("unchecked")
		Quiz quiz = (Quiz) query.uniqueResult();
		session.getTransaction().commit();
		session.close();
		return quiz;
	}

	public Quiz getQuizAvecQuestions(int id) {
		logger.info("GetQuizAvecQuestions id");

		// si pas de cache on le charge
		if (Cache.getCacheListeQuiz() == null) {
			try {
				listQuizAvecQuestions();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		for (Quiz q : Cache.getCacheListeQuiz())
			if (q.getId() == id)
				return q;

		// quiz pas trouvé
		return null;

		// sinon on cherche en BDD
		/*
		 * else{
		 * 
		 * Session session = HibernateUtil.getSessionFactory().openSession();
		 * session.beginTransaction(); Quiz quiz = (Quiz)session.get(Quiz.class,
		 * id); //ATTENTION NE PAS SUPPRIMER CE COMMENTAIRE
		 * System.out.println(quiz.getQuestions());
		 * session.getTransaction().commit(); //logger.info("get Quiz: " +
		 * quiz.toString() + " From id : " + id); session.close(); return quiz;
		 * }
		 */
	}

	public List<Quiz> listQuiz() throws SQLException {
		logger.info("ListQuiz");

		// si cache existe on le retourne
		if (Cache.getCacheListeQuiz() != null)
			return Cache.getCacheListeQuiz();

		// sinon on va en BDD
		else {

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
	}

	/**
	 * methode qui retourne les quiz avec les questions
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Quiz> listQuizAvecQuestions() throws SQLException {
		logger.info("listQuizAvecQuestions");

		// si cache à jour on cherche dans le cache
		if (Cache.getCacheListeQuiz() != null) {

			return Cache.getCacheListeQuiz();

		}

		else {

			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			String hql = "from Quiz";
			@SuppressWarnings("unchecked")
			List<Quiz> retour = session.createQuery(hql).list();
			// on charge les qestions NE pa SUPPRIMER!
			for (Quiz q : retour)
				System.out.println(q.getQuestions());
			session.getTransaction().commit();
			session.close();
			logger.info("get liste Quiz: " + retour.toString());

			// on met dans le cache pour les prochaines fois
			Cache.setCacheListeQuiz(retour);

			return retour;
		}
	}

	public List<Quiz> getListQuizPublie() {

		logger.info("getListQuizPublie");

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session
				.createQuery("FROM Quiz WHERE dateDebutQuiz is not null");
		@SuppressWarnings("unchecked")
		List<Quiz> listeQuiz = query.list();
		session.getTransaction().commit();
		session.close();
		logger.info("getListQuizPublie: " + listeQuiz.toString());
		return listeQuiz;
	}

	public List<Quiz> getListQuizFinish() {

		logger.info("getListQuizFinish");

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session
				.createQuery("FROM Quiz WHERE dateFinQuiz is not null");
		@SuppressWarnings("unchecked")
		List<Quiz> listeQuiz = query.list();
		session.getTransaction().commit();
		session.close();
		logger.info("getListQuizFinish: " + listeQuiz.toString());
		return listeQuiz;
	}

	/**
	 * This doesn't work! And in order to get questions of a quiz, we can use
	 * `listQuizAvevQuiestions()' and `quiz.getListeQuestions()' instead. So
	 * this method is not useful, maybe we should remove it. --thought by
	 * Wenfeng.
	 */
	@Deprecated
	public List<Question> listQuestionQuiz(Quiz quiz) {

		logger.info("listQuestionQuiz quiz");

		Quiz q = getQuiz(quiz.getId());

		List<Question> array = new ArrayList<Question>();
		SetToListConverter.SetToList(array, q.getQuestions());
		logger.info("listQuestionQuiz: " + array.toString());
		return array;
	}

	/**
	 * retourne le nb de questions d'un quiz
	 * 
	 * @return le nb de question d'un quiz; 0 si quiz non trouvé
	 */
	public int getNbQuestionParQuiz(Quiz quiz) {

		logger.info("getNbQuestionParQuiz quiz");

		// si cache à jour on cherche dans le cache
		if (Cache.getCacheListeQuiz() != null) {

			for (Quiz q : Cache.getCacheListeQuiz())
				if (q.getId() == quiz.getId())
					return q.getQuestions().size();

			// quiz pas trouvé
			return 0;

		}

		List<Question> listeQuestions = listQuestionQuiz(quiz);
		logger.info("getNbQuestionParQuiz: " + listeQuestions.size()
				+ " From quiz" + quiz.toString());
		return listeQuestions.size();
	}

	public boolean updateQuiz(Quiz q) {

		logger.info("updateQuiz quiz");

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(q);
		session.getTransaction().commit();
		session.close();
		Quiz newQuiz = getQuiz(q.getId());
		logger.info("updateQuiz: " + newQuiz.toString());

		// on vide le cache pour forcer son rechargement
		Cache.viderCacheListeQuiz();

		return (newQuiz.equals(q));
	}

	@Override
	public boolean deleteQuiz(Quiz q) {

		logger.info("deleteQuiz quiz");

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(q);
		session.getTransaction().commit();
		session.close();
		logger.info("deleteQuiz: " + q);
		// on vide le cache pour forcer son rechargement
		Cache.viderCacheListeQuiz();

		return (q == null);
	}
}
