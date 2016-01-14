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
import java.util.Set;

import org.hibernate.Session;

import fr.esigelec.quiz.dao.IPropositionDAO;
import fr.esigelec.quiz.dto.Proposition;
import fr.esigelec.quiz.dto.Question;

public class PropositionDAOImpl implements IPropositionDAO {

	@Override
	public boolean createProposition(Proposition p) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(p);
		session.getTransaction().commit();
		session.close();
		return (p.getId() != 0);
	}

	@Override
	public Proposition getProposition(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Proposition retour = (Proposition) session.get(Proposition.class, id);
		session.getTransaction().commit();
		session.close();
		return retour;
	}

	public List<Proposition> listProposition() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = "from Proposition";
		List<Proposition> retour = session.createQuery(hql).list();
		session.getTransaction().commit();
		session.close();
		return retour;
	}

	public List<Proposition> getPropositionParQuestion(Question q) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = "from Proposition where question.id = " + q.getId();
		List<Proposition> retour = session.createQuery(hql).list();
		session.getTransaction().commit();
		session.close();
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
		return (p.equals(newPro));
	}

	@Override
	public boolean deleteProposition(Proposition p) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(p);
		session.getTransaction().commit();
		session.close();
		return (p == null);
	}
}
