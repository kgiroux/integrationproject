package fr.esigelec.quiz.dao.hibernate;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
/**
* @author BOSSO BOSSO Ghyslaine
* @author  CHOUAKRIA Farid
* @author DELAUNAY Brice
* @author NGANE Pascale
*/
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.map.LinkedMap;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import fr.esigelec.quiz.controleur.TestLogger;
import fr.esigelec.quiz.dao.IChoisirDAO;
import fr.esigelec.quiz.dto.Choisir;
import fr.esigelec.quiz.dto.Personne;
import fr.esigelec.quiz.dto.Proposition;
import fr.esigelec.quiz.dto.Question;
import fr.esigelec.quiz.dto.Quiz;
import fr.esigelec.quiz.util.SetToListConverter;


public class ChoisirDAOImpl implements IChoisirDAO {

	private static final Logger logger = Logger.getLogger(TestLogger.class);
	
	@Override
	public boolean createChoix(Choisir c) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(c);
		session.getTransaction().commit();
		session.close();
		logger.info("createChoix : " + c.toString());
		return (c.getId() != 0);
	}

	@Override
	public boolean updateChoix(Choisir c) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(c);
		session.getTransaction().commit();
		session.close();
		return true;
	}

	@Override
	public boolean deleteChoix(Choisir c) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(c);
		session.getTransaction().commit();
		session.close();
		logger.info("deleteChoix: " + c);
		return (c == null);
	}
	
	
	@Override
	public int deleteChoix(Quiz q) {
		int retour=0;
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = "delete from Choisir where quiz.id= :quizId";
		retour=session.createQuery(hql).setInteger("quizId", q.getId()).executeUpdate();
				
		session.getTransaction().commit();
		session.close();
		logger.info("deleteChoix du quiz: " + q.getId());
		return retour;
	}
	
	

	@Override
	public List<Choisir> getChoixPersonneParQuiz(Personne p, Quiz q) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		//
		// ERROR !!
		// The table shoud be Choisir instead of Proposition.
		// Marked by @mincong-h
		//
		// String hql = "from Proposition where quiz.id = " + q.getId() + " and personne.id = " + p.getId();
		String hql = "from Choisir where quiz.id = " + q.getId() + " and personne.id = " + p.getId();
		@SuppressWarnings("unchecked")
		List<Choisir> retour = session.createQuery(hql).list();
		session.getTransaction().commit();
		session.close();
		logger.info("getChoixPersonneParQuiz: " + " for personne : " + p.toString() + " and quiz : " + q.toString());
		return retour;
	}
	
	@Override
	public List<Choisir> getChoixPersonneParQuizPersonneEtQuestion(Personne p, Quiz q,Question question) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = "from Choisir where quiz.id = " + q.getId() + " and personne.id = " + p.getId()+ " and proposition.question.id = "+question.getId();
		@SuppressWarnings("unchecked")
		List<Choisir> retour = session.createQuery(hql).list();
		session.getTransaction().commit();
		session.close();
		logger.info("getChoixPersonneParQuizPersonneEtQuestion: " + " for personne : " + p.toString() + " and quiz : " + q.toString());
		return retour;
	}
	
	/**
	 * 
	 * @author HUANG Mincong
	 * @see https://docs.jboss.org/hibernate/orm/3.3/reference/en/html/queryhql.html#queryhql-grouping
	 */
	public int getNombrePersonneParQuiz(Quiz q) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = "select count(personne) from Choisir "
				+ "where quiz.id = " + q.getId() + " "
				+ "group by personne";
		@SuppressWarnings("unchecked")
		List<Choisir> retour = session.createQuery(hql).list();
		session.getTransaction().commit();
		session.close();
		//logger.info("getNombrePersonneParQuiz" + retour.toString() + " for quiz : " + q.toString());
		return retour.size();
	}
	
	@Override
	public int getNombrePersonneParProposition(Quiz q, Proposition p) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = "from Choisir where quiz.id = " + q.getId() + " and proposition.id= " + p.getId();
		@SuppressWarnings("unchecked")
		List<Choisir> retour = session.createQuery(hql).list();
		session.getTransaction().commit();
		session.close();
		logger.info("getNombrePersonneParProposition" + retour.size() + " for quiz : " + q.toString() + " and Proposition : " + p.toString());
		return retour.size();
	}
	
	@Override
	public Choisir getChoix(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Choisir retour = (Choisir) session.get(Choisir.class, id);
		//System.out.println(retour.toString());
		session.getTransaction().commit();
		session.close();
		logger.info("get Choix: " + retour.toString() + " From id : " + id);
		return retour;
	}

	/**
	 * @author minconghuang
	 * 
	 */
	public Choisir getChoix(Personne p, Quiz quiz, Question q) {
		// get liste de proposition-id à partir de question
		List<Integer> propoIds = new ArrayList<Integer>();
		for (Proposition propo: q.getListePropositions()) {
			propoIds.add(propo.getId());
		}
		// hibernate
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Choisir.class);
		criteria.add(Restrictions.in("proposition.id", propoIds));
		criteria.add(Restrictions.eq("quiz.id", quiz.getId()));
		criteria.add(Restrictions.eq("personne.id", p.getId()));
		Choisir retour = (Choisir) criteria.uniqueResult();
		if (retour != null) {
			logger.info("get Choix: " + retour.toString());
		}
		session.getTransaction().commit();
		session.close();
		return retour;
	}
	
	public List<Choisir> getChoixByQuiz(Quiz quiz){
		
		ArrayList<Personne> toReturn = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		String hql = "from Choisir where quiz.id = " + quiz.getId();
		List<Choisir> listChoix = session.createQuery(hql).list();
		return listChoix;
	}
}
