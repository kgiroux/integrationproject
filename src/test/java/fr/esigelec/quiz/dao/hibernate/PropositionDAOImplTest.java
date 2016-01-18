package fr.esigelec.quiz.dao.hibernate;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

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
	static Question question;
	
	PropositionDAOImpl daoProposition;
	QuestionDAOImpl daoQuestion;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		proposition = new Proposition("Proposition Generale", true);
	}

	@Before
	public void setUp() throws Exception {
		daoProposition = new PropositionDAOImpl();
		daoQuestion = new QuestionDAOImpl();
	}
	

	@Test
	public void AtestCreateProposition() {
		Question question = new Question("Proposition Test Create Proposition");
		proposition = new Proposition("Proposition",false);
		question.getPropositions().add(proposition);
		boolean statut = daoQuestion.createQuestion(question);
		System.out.println("Proposition :" + proposition.toString());
		assertEquals(true, statut);
	}

	/*@Test
	public void BtestGetProposition() {
		System.out.println("La classe de base: " + proposition.toString());
		Proposition p = daoProposition.getProposition(proposition.getId());
		System.out.println("La classe recup dans la base : " + p.toString());
		assertTrue(p.equals(proposition));
	}
	
	@Test
	public void CtestUpdateProposition() {
		proposition.setEstBonneReponse(true);
		daoProposition.updateProposition(proposition);
		Proposition p = daoProposition.getProposition(proposition.getId());
		assertEquals(p, proposition);
	}
	
	
	@Test(expected=NullPointerException.class)
	public void DtestDeletePropositionEnCascade() {
		int id = proposition.getId();
		daoQuestion.deleteQuestion(question);
		Proposition p = daoProposition.getProposition(id);
		System.out.println(p.toString());
	}
	
	
	@Test(expected=NullPointerException.class)
	public void EtestDeleteProposition() {
		//Initialisation
		question = new Question("Proposition Test Delete Proposition");
		proposition = new Proposition("Proposition DeleteProposition", true);
		daoProposition.createProposition(proposition);
		//Corps
		int id = proposition.getId();
		daoProposition.deleteProposition(proposition);
		Proposition p = daoProposition.getProposition(id);
		System.out.println(p.toString());
		//Delete Question 
		//daoQuestion.deleteQuestion(question);
	}
	
	@Test
	public void FtestListProposition() {
		Question question = new Question("Proposition Test");
		daoQuestion.createQuestion(question);
		
		Proposition a = new Proposition("Proposition 1", false);
		daoProposition.createProposition(a);
		
		Proposition b = new Proposition("Proposition 2", true);
		daoProposition.createProposition(b);
		
		List<Proposition> listeOne = daoProposition.listProposition();
		
		Proposition c = new Proposition("Proposition 3", false);
		listeOne.add(c);
		
		daoProposition.createProposition(c);
		
		List<Proposition> listeTwo = daoProposition.listProposition();
		assertEquals(listeOne, listeTwo);
		
		//Delete
		daoQuestion.deleteQuestion(question);
	}


	@Test
	public void HtestGetBonneReponse() {
		Question question = new Question("Proposition Test Get Bonne Reponse");
		daoQuestion.createQuestion(question);
		
		Proposition a = new Proposition("Proposition 1 gbr", false);
		daoProposition.createProposition(a);
		
		System.out.println("a" + question.getId());
		Proposition b = new Proposition("Proposition 2 gbr", true);
		daoProposition.createProposition(b);
		
		System.out.println("b" + question.getId());
		Proposition c = new Proposition("Proposition 3 gbr", false);
		daoProposition.createProposition(c);
		System.out.println("c" + question.getId());
		
		Proposition p = daoProposition.getBonneReponse(question);
		assertTrue(p.isEstBonneReponse());
		//Delete
		daoQuestion.deleteQuestion(question);
	}*/
}