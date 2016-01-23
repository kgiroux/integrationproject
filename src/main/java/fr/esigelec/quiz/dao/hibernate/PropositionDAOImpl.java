package fr.esigelec.quiz.dao.hibernate;

/**Projet d'integration
 * Le jeu de TF8
 * @author GSI-IR
 * Farid CHOUAKRIA et DELAUNAY BRICE
 * Classe PropositionDAOImpl
 * Impl�mentation des m�thodes de l'interface PropositionDAO
 * pour les liens avec la base de donn�es
 * */


import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import fr.esigelec.quiz.controleur.TestLogger;
import fr.esigelec.quiz.dao.IPropositionDAO;
import fr.esigelec.quiz.dto.Proposition;
import fr.esigelec.quiz.dto.Question;

public class PropositionDAOImpl implements IPropositionDAO {
	
	private static final Logger logger = Logger.getLogger(TestLogger.class);

	@Override
	public boolean createProposition(Proposition p) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(p);
		session.getTransaction().commit();
		session.close();
		logger.info("createProposition : " + p.toString());
		return (p.getId() != 0);
	}

	@Override
	public Proposition getProposition(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Proposition retour = (Proposition) session.get(Proposition.class, id);
		session.getTransaction().commit();
		session.close();
		logger.info("get Proposition: " + retour.toString() + " From id : " + id);
		return retour;
	}

	public List<Proposition> listProposition() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = "from Proposition";
		@SuppressWarnings("unchecked")
		List<Proposition> retour = (List<Proposition>) session.createQuery(hql).list();
		session.getTransaction().commit();
		session.close();
		logger.info("get listProposition: " + retour.toString());
		return retour;
	}

	@Override
	public boolean updateProposition(Proposition p) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(p);
		Proposition newPro = getProposition(p.getId());
		session.getTransaction().commit();
		session.close();
		logger.info("updateProposition: " + newPro.toString());
		return (p.equals(newPro));
	}

	@Override
	public boolean deleteProposition(Proposition p) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(p);
		session.getTransaction().commit();
		session.close();
		logger.info("deleteProposition: " + p);
		return (p == null);
	}

	@Override
	public Proposition getBonneReponse(Question q) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = "From Proposition where estBonneReponse = 1 AND id_question = " + q.getId();
		Proposition retour = (Proposition) session.createQuery(hql).uniqueResult();
		/*Proposition retour = (Proposition) session.createCriteria(Proposition.class)
				.add(Restrictions.eq("id_question", q.getId()))
				.add(Restrictions.eq("estBonneReponse", 1))
				.uniqueResult();*/
		session.getTransaction().commit();
		session.close();
		logger.info("getBonneReponse : " + retour.toString() + " From question" + q.toString());
		return retour;
	}
}
