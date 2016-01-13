package fr.esigelec.quiz.dao.hibernate;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import fr.esigelec.quiz.dto.Proposition;
import fr.esigelec.quiz.dto.Question;

//On regle la classe pour que les classes s'executent par odre alphabetique
@FixMethodOrder (MethodSorters.NAME_ASCENDING)

public class PropositionDAOImplTest {
	
	static Proposition proposition;
	
	PropositionDAOImpl daoProposition;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Question question = new Question();
		proposition = new Proposition("Proposition", question);
	}

	@Before
	public void setUp() throws Exception {
		daoProposition = new PropositionDAOImpl();
	}
	/**
	@Test
	public void testCreateProposition() {
		boolean statut = daoProposition.createProposition(proposition);
		System.out.println("createProposition, Requete réussi: " + statut);
		System.out.println("Proposition id = " + proposition.getId());
		System.out.println("Question id = " + proposition.getquestion().getId());
		assertEquals(true, statut);
	}*/
/**
	@Test
	public void testGetProposition() {
		fail("Not yet implemented");
	}

	@Test
	public void testListProposition() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPropositionParQuestion() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateProposition() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteProposition() {
		fail("Not yet implemented");
	}
*/
}
