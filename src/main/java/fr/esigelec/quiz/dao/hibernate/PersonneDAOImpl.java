package fr.esigelec.quiz.dao.hibernate;

/**Projet d'integration
 * Le jeu de TF8
 * @author GSI-IR
 * Farid CHOUAKRIA et DELAUNAY BRICE
 * Classe PersonneDAOImpl
 * Impl�mentation des m�thodes de l'interface PersonneDAO
 * pour les liens avec la base de donn�es
 * */

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import fr.esigelec.quiz.dao.IPersonneDAO;
import fr.esigelec.quiz.dto.Personne; 

public class PersonneDAOImpl implements IPersonneDAO{

	/**
	 * M�thode : createPersonne
	 * Cr�e une personne dans la base de donn�es
	 * @param p
	 */
	@Override
	public void createPersonne(Personne p) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(p);
		session.getTransaction().commit();
		session.close();	
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
		Personne retour = (Personne) session.createCriteria(Personne.class)
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
		List<Personne> retour = session.createQuery(hql).list();
		session.getTransaction().commit();
		session.close();
		return retour;
	}

	
	/**
	 * M�thode : updatePersonne
	 * Met � jour une personne plac�e en entr�e dans la bdd
	 * si elle a �t� enregistr�e avant
	 * @param p
	 */
	@Override
	public void updatePersonne(Personne p) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(p);
		session.getTransaction().commit();
		session.close();
	}

	
	/**
	 * M�thode : deletePersonne
	 * Supprime une personne plac�e en entr�e dans la bdd
	 * si elle est enregistr�e
	 * @param p
	 */
	@Override
	public void deletePersonne(Personne p) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(p);
		session.getTransaction().commit();
		session.close();
	}

}
