package fr.esigelec.quiz.dao.hibernate;

import java.util.ArrayList;
import java.util.LinkedList;

/**Projet d'integration
 * Le jeu de TF8
 * @author GSI-IR
 * Farid CHOUAKRIA et DELAUNAY BRICE
 * Classe PersonneDAOImpl
 * Impl�mentation des m�thodes de l'interface PersonneDAO
 * pour les liens avec la base de donn�es
 * */

import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import fr.esigelec.quiz.dao.IPersonneDAO;
import fr.esigelec.quiz.dto.Personne;
import fr.esigelec.quiz.util.SetToListConverter; 

public class PersonneDAOImpl implements IPersonneDAO{

	/**
	 * M�thode : createPersonne
	 * Cr�e une personne dans la base de donn�es
	 * @param p
	 */
	@Override
	public boolean createPersonne(Personne p) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(p);
		session.getTransaction().commit();
		session.close();
		return (p.getId() != 0);
	}

	/**
	 * M�thode : getPersonne
	 * @param id
	 * @return une personne � partir d'un id
	 */
	@Override
	public Personne getPersonne(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Personne retour = (Personne) session.get(Personne.class, id);
		session.getTransaction().commit();
		session.close();
		return retour;
	}
	
	/**
	 * M�thode : getPersonne
	 * @param id
	 * @return une personne � partir d'un id
	 */
	@Override
	public Personne getPersonne(String mail) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Personne retour = (Personne) session.createCriteria(Personne.class, mail)
				.add(Restrictions.eq("mail", mail))
				.uniqueResult();
		session.getTransaction().commit();
		session.close();
		return retour;
	}

	
	/**
	 * M�thode : listPersonne
	 * @return la liste de toutes les personnes enregistr�es dans la base de donn�es
	 */
	@Override
	public List<Personne> listPersonnes() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = "from Personne";
		List<Personne> myList = session.createQuery(hql).list();
		session.getTransaction().commit();
		session.close();
		return myList;
	}

	
	/**
	 * M�thode : updatePersonne
	 * Met � jour une personne plac�e en entr�e dans la bdd
	 * si elle a �t� enregistr�e avant
	 * @param p
	 */
	@Override
	public boolean updatePersonne(Personne p) {
		boolean modifie = false;
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(p);
		Personne newPers = getPersonne(p.getId());
		session.getTransaction().commit();
		session.close();
		return (p.equals(newPers));
	}

	
	/**
	 * M�thode : deletePersonne
	 * Supprime une personne plac�e en entr�e dans la bdd
	 * si elle est enregistr�e
	 * @param p
	 */
	@Override
	public boolean deletePersonne(Personne p) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(p);
		session.getTransaction().commit();
		session.close();
		return (p == null);
	}

	@Override
	public Personne connexion(String email, String pwd) {
		Personne p = getPersonne(email);
		if (p.getMdp().equals(pwd)){
			return p;
		}
		return null;
	}
}
