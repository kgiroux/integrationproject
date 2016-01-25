package fr.esigelec.quiz.dao.hibernate;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import fr.esigelec.quiz.dto.Question;

//On regle la classe pour que les classes s'executent par odre alphabetique
@FixMethodOrder (MethodSorters.NAME_ASCENDING)

public class QuestionDAOImplTest {
	
	static Question question;
	QuestionDAOImpl daoQuestion;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//On initialise la question
		question = new Question();
	}

	@Before
	public void setUp() throws Exception {
		daoQuestion = new QuestionDAOImpl();
	}

	@Test
	public void AtestCreateQuestion() {
		boolean statut = daoQuestion.createQuestion(question);
		System.out.println("createQuiz, Requete réussi: " + statut);
		System.out.println("Quiz id = " + question.getId());
		assertEquals(true, statut);
	}

	@Test
	public void BtestGetQuestion() {
		System.out.println("id de base : " + question.getId());
		Question q = daoQuestion.getQuestion(question.getId());
		System.out.println("id recup dans la base : " + q.getId());
		assertTrue(q.equals(question));
	}

	@Test
	public void CtestListQuestion() {
		List<Question> listeOne = daoQuestion.listQuestion();
		Question q = new Question();
		listeOne.add(q);
		daoQuestion.createQuestion(q);
		List<Question> listeTwo = daoQuestion.listQuestion();
		assertEquals(listeOne, listeTwo);
	}

	@Test
	public void DtestUpdateQuestion() {
		question.setLibelle("blabla");
		System.out.println("Libelle Question: " + question.getLibelle());
		boolean statut = daoQuestion.updateQuestion(question);
		System.out.println("updateQuestion, Requete réussi: " + statut);
		Question q = daoQuestion.getQuestion(question.getId());
		assertEquals(q, question);
	}

	@Test(expected=NullPointerException.class)
	public void EtestDeleteQuestion() {
		int id = question.getId();
		boolean statut = daoQuestion.deleteQuestion(question);
		System.out.println("deleteQuestion, Requete réussi: " + statut);
		System.out.println("Question id = " + question.getId());
		Question q = daoQuestion.getQuestion(id);
		System.out.println(q.toString());
	}

}