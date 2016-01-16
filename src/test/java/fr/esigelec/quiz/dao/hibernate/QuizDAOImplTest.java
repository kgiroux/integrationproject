package fr.esigelec.quiz.dao.hibernate;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import fr.esigelec.quiz.dto.Question;
import fr.esigelec.quiz.dto.Quiz;
import fr.esigelec.quiz.util.SetToListConverter;

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
		Question q1 = new Question("Q1");
		QuestionDAOImpl daoQuest = new QuestionDAOImpl();
		daoQuest.createQuestion(q1);
		quiz.addQuestion(q1);
		boolean statut = daoQuiz.createQuiz(quiz);
		System.out.println("createQuiz, Requete réussi: " + statut);
		System.out.println("Quiz id = " + quiz.toString());
		assertEquals(true, statut);
	}
	
	@Test
	public void BtestGetQuiz() {
		System.out.println("quiz base : " + quiz.toString());
		Quiz q = daoQuiz.getQuiz(quiz.getId());
		System.out.println("quiz recup dans la base : " + q.toString());
		assertTrue(q.equals(quiz));
	}
	
	@Test
	public void CtestUpdateQuiz() {
		Question q1 = new Question("Q1");
		QuestionDAOImpl daoQuest = new QuestionDAOImpl();
		daoQuest.createQuestion(q1);
		quiz.addQuestion(q1);
		System.out.println("quiz base : " + quiz.toString());
		daoQuiz.updateQuiz(quiz);
		Quiz q = daoQuiz.getQuiz(quiz.getId());
		System.out.println("quiz recup dans la base : " + q.toString());
		assertEquals(q, quiz);
	}
	
	@Test
	public void GtestListQuestionQuiz() {
		Question q1 = new Question("Q1");
		QuestionDAOImpl daoQuest = new QuestionDAOImpl();
		daoQuest.createQuestion(q1);
		
		Quiz quiz = new Quiz("Quiz test", 0, 0);
		quiz.addQuestion(q1);
		daoQuiz.createQuiz(quiz);
		List<Question> qs = new ArrayList<Question>();
		SetToListConverter.SetToList(qs, quiz.getQuestions());
		List<Question> bddListeQuestions = daoQuiz.listQuestionQuiz(quiz);
		System.out.println(bddListeQuestions.toString());
		assertEquals(qs, bddListeQuestions);
		//Delete 
		daoQuest.deleteQuestion(q1);
		daoQuiz.deleteQuiz(quiz);
	}
	
	@Test
	public void HtestGetNbQuestionParQuiz() {
		Question q1 = new Question("Q1");
		QuestionDAOImpl daoQuest = new QuestionDAOImpl();
		daoQuest.createQuestion(q1);
		
		Quiz quiz = new Quiz("Quiz test", 0, 0);
		quiz.addQuestion(q1);
		daoQuiz.createQuiz(quiz);
		
		int tailleDeBase = quiz.getQuestions().size();
		int tailleBDD = daoQuiz.getNbQuestionParQuiz(quiz);
		System.out.println(tailleBDD);
		assertEquals(tailleDeBase, tailleBDD);
		//Delete 
		daoQuest.deleteQuestion(q1);
		daoQuiz.deleteQuiz(quiz);
	}
	
	@Test(expected=NullPointerException.class)
	public void DtestDeleteQuiz() {
		int id = quiz.getId();
		boolean statut = daoQuiz.deleteQuiz(quiz);
		System.out.println("deleteQuiz, Requete réussi: " + statut);
		System.out.println("Quiz id = " + quiz.getId());
		Quiz q = daoQuiz.getQuiz(id);
		System.out.println(q.toString());
	}
	
	
	@Test
	public void EtestListQuiz() throws SQLException {
		Quiz a = new Quiz("Quiz test a", 1, 1);
		daoQuiz.createQuiz(a);
		Quiz b = new Quiz("Quiz test b", 1, 1);
		daoQuiz.createQuiz(b);
		List<Quiz> listeOne = daoQuiz.listQuiz();
		Quiz c = new Quiz("Quiz test c", 1, 1);
		listeOne.add(c);
		daoQuiz.createQuiz(c);
		List<Quiz> listeTwo = daoQuiz.listQuiz();
		assertEquals(listeOne, listeTwo);
	}
	
	@Test
	public void EtestGetListQuizPublie() {
		Date d = new Date();
		Timestamp t = new Timestamp(d.getTime());
		Quiz qPublie = new Quiz("Quiz test", 1, 1);
		daoQuiz.createQuiz(qPublie);
		qPublie.setDateDebutQuiz(t);
		daoQuiz.updateQuiz(qPublie);
		Quiz qNotPublie = new Quiz("Quiz test", 1, 1);
		daoQuiz.createQuiz(qNotPublie);
		List<Quiz> l = daoQuiz.getListQuizFinish();
		System.out.println(l.size());
		boolean publie = true;
		for (int i = 0; i < l.size(); i++) {
			publie = (l.get(i).getDateFinQuiz() != null);
		}
		daoQuiz.deleteQuiz(qPublie);
		daoQuiz.deleteQuiz(qNotPublie);
		assertTrue(publie);
	}

	@Test
	public void FtestGetListQuizFinish() {
		Date d = new Date();
		Timestamp t = new Timestamp(d.getTime());
		
		Quiz qFinish = new Quiz("Quiz test", 1, 1);
		daoQuiz.createQuiz(qFinish);
		qFinish.setDateFinQuiz(t);
		daoQuiz.updateQuiz(qFinish);
		Quiz qNotFinish = new Quiz("Quiz test", 1, 1);
		daoQuiz.createQuiz(qNotFinish);
		List<Quiz> l = daoQuiz.getListQuizFinish();
		System.out.println(l.size());
		boolean finish = true;
		for (int i = 0; i < l.size(); i++) {
			finish = (l.get(i).getDateFinQuiz() != null);
		}
		daoQuiz.deleteQuiz(qFinish);
		daoQuiz.deleteQuiz(qNotFinish);
		assertTrue(finish);
	}

}

