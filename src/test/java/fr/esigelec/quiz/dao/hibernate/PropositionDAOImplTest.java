package fr.esigelec.quiz.dao.hibernate;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import fr.esigelec.quiz.dto.Proposition;

//On regle la classe pour que les classes s'executent par odre alphabetique
@FixMethodOrder (MethodSorters.NAME_ASCENDING)

public class PropositionDAOImplTest {
	
	static Proposition proposition;
	PropositionDAOImpl daoProposition;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		proposition = new Proposition("Proposition", 0);
	}

	@Before
	public void setUp() throws Exception {
		daoProposition = new PropositionDAOImpl();
	}

	@Test
	public void testCreateProposition() {
		fail("Not yet implemented");
	}

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

}
