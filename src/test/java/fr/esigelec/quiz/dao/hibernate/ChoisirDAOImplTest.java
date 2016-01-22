package fr.esigelec.quiz.dao.hibernate;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.Date;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import fr.esigelec.quiz.dto.Choisir;
import fr.esigelec.quiz.dto.Personne;
import fr.esigelec.quiz.dto.Proposition;
import fr.esigelec.quiz.dto.Question;
import fr.esigelec.quiz.dto.Quiz;

//On regle la classe pour que les classes s'executent par odre alphabetique
@FixMethodOrder (MethodSorters.NAME_ASCENDING)

public class ChoisirDAOImplTest {

	static Choisir choix;
	ChoisirDAOImpl daoChoix;
	static Personne personne;
	static Quiz quiz;
	static Question question;
	static Proposition proposition1;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		personne = new PersonneDAOImpl().getPersonne(1);
		quiz = new Quiz("Quiz choisir encore encore encore un autre test ", 0, 0);
		question = new Question("Proposition Test Create Proposition");
		proposition1 = new Proposition("Proposition 1",false);
		
		question.getPropositions().add(proposition1);
		
		new QuestionDAOImpl().createQuestion(question);
		quiz.addQuestion(question);
		new QuizDAOImpl().createQuiz(quiz);
		
		Date date = new Date();
		Timestamp dateCourante = null;
				
		choix = new Choisir(dateCourante, proposition1, quiz, personne);
	}
	
	@Before
	public void setUp() throws Exception {
		daoChoix = new ChoisirDAOImpl();
	}

	@Test
	public void AtestCreateChoix() {
		boolean statut = new ChoisirDAOImpl().createChoix(choix);
		assertEquals(true, statut);
	}
	
	@Test
	public void BtestGetChoix() {
		System.out.println("id de base : " + choix.getId());
		System.out.println(choix.toString());
		Choisir c = daoChoix.getChoix(choix.getId());
		System.out.println(c.toString());
		System.out.println("id recup dans la base : " + c.getId());
		assertTrue(c.equals(choix));
	}
	
	@Test
	public void CtestGetNombrePersonneParProposition() {
		fail("Not yet implemented");
	}
	
	@Test
	public void DtestGetNombrePersonneParQuiz() {
		fail("Not yet implemented");
	}
	
	@Test
	public void EtestGetChoixPersonneParQuiz() {
		fail("Not yet implemented");
	}

	@Test
	public void FtestUpdateChoix() {
		Timestamp dateUpdate = Timestamp.valueOf("2016-22-01 15:15:15");
		choix.setDate(dateUpdate);
		boolean statut = daoChoix.updateChoix(choix);
		System.out.println("updatechoix, Requete réussi: " + statut);
		Choisir c = daoChoix.getChoix(choix.getId());
		assertEquals(c, choix);
	}

	@Test(expected=NullPointerException.class)
	public void GtestDeleteChoix() {
		int id = choix.getId();
		boolean statut = daoChoix.deleteChoix(choix);
		Choisir c = daoChoix.getChoix(id);
		System.out.println("deleteChoix, Requete réussi: " + statut);
		System.out.println("choix: " + c.toString());
	}
}
