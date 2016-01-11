package fr.esigelec.quiz.test;

/**Projet d'integration
 * Le jeu de TF8
 * @author GSI-IR
 * BOSSO BOSSO Ghyslaine and NGANE Pascale
 * Classe PersonneDAOTest
 * Pour tester les méthodes de la classe PersonneDAOImpl
 * */

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import fr.esigelec.quiz.dao.hibernate.PersonneDAOImpl;
import fr.esigelec.quiz.dto.Personne;


public class PersonneDAOTest {

	PersonneDAOImpl persDao=new PersonneDAOImpl();
	/**
	 * Méthode : testCreatePersonne
	 */
	public void testCreatePersonne(){
		Personne p= new Personne("kevin", "charles", "test@mail.com", "12345", 1000);
		persDao.createPersonne(p);
		Personne pers=persDao.getPersonne(1);
		assertEquals(pers.getNom(), p.getNom());	
	}
	
	/**
	 * Méthode :testGetPersonne
	 */
	public void testGetPersonne(){
		Personne pers=persDao.getPersonne(1);
		assertEquals(pers.getNom(), "kevin");
	}
	
	/**
	 * Méthode : testListPersonne
	 */
	public void testListPersonne(){
		
		Personne p= new Personne("kevina", "charlia", "test2@mail.fr", "1yhj5", 0);
		persDao.createPersonne(p);
		assertEquals(persDao.listPersonne().size(), 2);
	}
	
	/**
	 * Méthode : testUpdatePersonne
	 */
	public void testUpdatePersonne(){
		Personne pers=persDao.getPersonne(2);
		pers.setPrenom("charlia2");
		persDao.updatePersonne(pers);
		pers=persDao.getPersonne(2);
		assertEquals(pers.getPrenom(), "charlia2");
	}
	
	/**
	 * Méthode : testDeletePersonne
	 */
	public void testDeletePersonne(){
		Personne pers=persDao.getPersonne(2);
		persDao.deletePersonne(pers);
		assertEquals(persDao.listPersonne().size(),1);
	}

}


