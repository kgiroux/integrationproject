package fr.esigelec.quiz.dao.hibernate;

/**
* @author BOSSO BOSSO Ghyslaine
* @author  CHOUAKRIA Farid
* @author DELAUNAY Brice
* @author NGANE Pascale
*/
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import fr.esigelec.quiz.controleur.TestLogger;
import fr.esigelec.quiz.dao.IChoisirDAO;
import fr.esigelec.quiz.dto.Choisir;
import fr.esigelec.quiz.dto.Personne;
import fr.esigelec.quiz.dto.Proposition;
import fr.esigelec.quiz.dto.Quiz;


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
	public List<Choisir> getChoixPersonneParQuiz(Personne p, Quiz q) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = "from Proposition where quiz.id = " + q.getId() + " and personne.id = " + p.getId();
		@SuppressWarnings("unchecked")
		List<Choisir> retour = session.createQuery(hql).list();
		session.getTransaction().commit();
		session.close();
		logger.info("getChoixPersonneParQuiz: " + " for personne : " + p.toString() + " and quiz : " + q.toString());
		return retour;
	}
	
	@Override
	public int getNombrePersonneParQuiz(Quiz q) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = "from Choisir where quiz.id = " + q.getId();
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
		System.out.println(retour.toString());
		session.getTransaction().commit();
		session.close();
		logger.info("get Choix: " + retour.toString() + " From id : " + id);
		return retour;
	}
}
