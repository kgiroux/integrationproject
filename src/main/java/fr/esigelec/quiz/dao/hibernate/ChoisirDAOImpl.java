package fr.esigelec.quiz.dao.hibernate;

import java.util.List;

import org.hibernate.Session;

import fr.esigelec.quiz.dao.IChoisirDAO;
import fr.esigelec.quiz.dto.Choisir;
import fr.esigelec.quiz.dto.Personne;
import fr.esigelec.quiz.dto.Quiz;


public class ChoisirDAOImpl implements IChoisirDAO {

	@Override
	public boolean createChoix(Choisir c) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(c);
		session.getTransaction().commit();
		session.close();
		return (c.getId() != 0);
	}

	@Override
	public boolean updateChoix(Choisir c) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(c);
		session.getTransaction().commit();
		session.close();
		return false;
	}

	@Override
	public boolean deleteChoix(Choisir c) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(c);
		session.getTransaction().commit();
		session.close();
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
		return retour;
	}
}
