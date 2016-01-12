package fr.esigelec.quiz.dao.hibernate;

/**Projet d'integration
 * Le jeu de TF8
 * @author GSI-IR
 * BOSSO BOSSO Ghyslaine and NGANE Pascale
 * Classe PersonneDAOTest
 * Pour tester les m�thodes de la classe PersonneDAOImpl
 * */

import static org.junit.Assert.*;

import org.junit.Test;

import fr.esigelec.quiz.dao.hibernate.PersonneDAOImpl;
import fr.esigelec.quiz.dto.Personne;


public class PersonneDAOTest {

	PersonneDAOImpl persDao=new PersonneDAOImpl();
	/**
	 * M�thode : testCreatePersonne
	 */
	@Test
	public void testCreatePersonne(){
		Personne p = new Personne(0, "kevin", "charles", "test@mail.com", "12345", 1000);
		persDao.createPersonne(p);
		Personne pers = persDao.getPersonne(p.getId());
		assertEquals(pers.getNom(), p.getNom());	
	}
	
	/**
	 * M�thode :testGetPersonne
	 */
	/*@Test
	public void testGetPersonne(){
		Personne pers=persDao.getPersonne(1);
		assertEquals(pers.getNom(), "kevin");
	}*/
	
	/**
	 * M�thode : testListPersonne
	 */
	/*public void testListPersonne(){
		
		Personne p= new Personne("kevina", "charlia", "test2@mail.fr", "1yhj5", 0);
		persDao.createPersonne(p);
		assertEquals(persDao.listPersonnes().size(), 2);
	}*/
	
	/**
	 * M�thode : testUpdatePersonne
	 */
	/*@Test
	public void testUpdatePersonne(){
		Personne pers=persDao.getPersonne(2);
		pers.setPrenom("charlia2");
		persDao.updatePersonne(pers);
		pers=persDao.getPersonne(2);
		assertEquals(pers.getPrenom(), "charlia2");
	}*/
	
	/**
	 * M�thode : testDeletePersonne
	 */
	/*@Test
	public void testDeletePersonne(){
		Personne pers=persDao.getPersonne(2);
		persDao.deletePersonne(pers);
		assertEquals(persDao.listPersonnes().size(),1);
	}*/

}


