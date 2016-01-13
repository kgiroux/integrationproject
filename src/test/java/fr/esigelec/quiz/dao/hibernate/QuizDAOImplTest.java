package fr.esigelec.quiz.dao.hibernate;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import fr.esigelec.quiz.dto.Quiz;

//On regle la classe pour que les classes s'executent par odre alphabetique
@FixMethodOrder (MethodSorters.NAME_ASCENDING)


public class QuizDAOImplTest {

	QuizDAOImpl daoQuiz; 
	static Quiz quiz;	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//On initialise le quiz
		quiz = new Quiz("Quiz test", 0, 0);
	}
	
	@Before
	public void setUp() throws Exception {
		this.daoQuiz = new QuizDAOImpl();
	}
	
	@Test
	public void AtestCreateQuiz() {
		boolean statut = daoQuiz.createQuiz(quiz);
		System.out.println("createQuiz, Requete réussi: " + statut);
		System.out.println("Quiz id = " + quiz.getId());
		assertEquals(true, statut);
	}
	@Test
	public void BtestGetQuiz() {
		System.out.println("id de base : " + quiz.getId());
		Quiz q = daoQuiz.getQuiz(quiz.getId());
		System.out.println("id recup dans la base : " + q.getId());
		assertTrue(q.equals(quiz));
	}

	@Test
	public void CtestListQuiz() {
		List<Quiz> listeOne = daoQuiz.listQuiz();
		Quiz q = new Quiz("Quiz test 2", 1, 1);
		listeOne.add(q);
		daoQuiz.createQuiz(q);
		List<Quiz> listeTwo = daoQuiz.listQuiz();
		assertEquals(listeOne, listeTwo);
	}

	/**@Test
	public void DtestGetListQuizPublie() {
		fail("Not yet implemented");
	}

	@Test
	public void EtestGetListQuizFinish() {
		fail("Not yet implemented");
	}

	@Test
	public void FtestListQuestionQuiz() {
		fail("Not yet implemented");
	}

	@Test
	public void GtestGetNbQuestionParQuiz() {
		fail("Not yet implemented");
	}*/

	@Test
	public void HtestUpdateQuiz() {
		quiz.setEtape(1);
		System.out.println("Etape quiz: " + quiz.getEtape());
		boolean statut = daoQuiz.updateQuiz(quiz);
		System.out.println("updateQuiz, Requete réussi: " + statut);
		Quiz q = daoQuiz.getQuiz(quiz.getId());
		assertEquals(q, quiz);
	}

	@Test(expected=NullPointerException.class)
	public void ItestDeleteQuiz() {
		int id = quiz.getId();
		boolean statut = daoQuiz.deleteQuiz(quiz);
		System.out.println("deleteQuiz, Requete réussi: " + statut);
		System.out.println("Quiz id = " + quiz.getId());
		Quiz q = daoQuiz.getQuiz(id);
		System.out.println(q.toString());
	}

}
