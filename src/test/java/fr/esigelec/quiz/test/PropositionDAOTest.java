package fr.esigelec.quiz.test;

/**Projet d'integration
 * Le jeu de TF8
 * @author GSI-IR
 * BOSSO BOSSO Ghyslaine and NGANE Pascale
 * Classe PropositionDAOTest
 * Pour tester les méthodes de la classe PropositionDAOImpl
 * */

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import fr.esigelec.quiz.dao.hibernate.PropositionDAOImpl;
import fr.esigelec.quiz.dto.Proposition;


public class PropositionDAOTest {
	
	PropositionDAOImpl propDao =new PropositionDAOImpl();
	
	/**
	 * méthode : testCreateProposition
	 */
	
	public void testCreateProposition(){
	
		Proposition p=new Proposition( "jee");
		propDao.createProposition(p);
		Proposition q=propDao.getProposition(1);
		assertEquals(p.getLibelle(), q.getLibelle());
	}
	
	/**
	 * méthode : testGetProposition
	 */
	
	public void testGetProposition(){
		Proposition q=propDao.getProposition(1);
		assertEquals("jee", q.getLibelle());
		
	}
	
	/**
	 * méthode : testListProposition
	 */
	
	public void testListProposition(){
		Proposition p= new Proposition("java");
		propDao.createProposition(p);
		assertEquals(propDao.listProposition().size(),2);
	}
	
	/**
	 * méthode : testGetPropositionParQuestion
	 */
	
	public void testGetPropositionParQuestion(){
		assertEquals(propDao.getPropositionParQuestion(null).size(), 0);
		}
	
	/**
	 * méthode : testUpdateProposition
	 */
	
	public void testUpdateProposition(){
		Proposition q=propDao.getProposition(1);
		q.setLibelle("jee1");
		propDao.updateProposition(q);
		q=propDao.getProposition(1);
		assertEquals(q.getLibelle(), "jee1");
		
	}
	
	/**
	 * méthode : testDeleteProposition
	 */
	
	public void testDeleteProposition(){
		Proposition q=propDao.getProposition(1);
		propDao.deleteProposition(q);
		assertEquals(propDao.listProposition().size(),1);
	}

}