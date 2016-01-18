package fr.esigelec.quiz.dao.hibernate;

import static org.junit.Assert.*;
import java.sql.Timestamp;
import java.util.Date;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.esigelec.quiz.dto.Choisir;
import fr.esigelec.quiz.dto.Personne;
import fr.esigelec.quiz.dto.Proposition;
import fr.esigelec.quiz.dto.Question;
import fr.esigelec.quiz.dto.Quiz;

public class ChoisirDAOImplTest {

	static Choisir choix;
	ChoisirDAOImpl daoChoix;

	@Before
	public void setUp() throws Exception {
		daoChoix = new ChoisirDAOImpl();
	}
	
	@Test
	public void AtestCreatePersonne() {
		Personne personne = new Personne("Esigelec", "IR", "IR@esigelec.fr", "Esigelec", Personne.ADMIN);
		
		Quiz quiz = new Quiz("Quiz test choisir", 0, 0);
		
		Question question = new Question("Proposition Test Create Proposition");
		
		Proposition proposition1 = new Proposition("Proposition 1",false);
		question.getPropositions().add(proposition1);
		
		new QuestionDAOImpl().createQuestion(question);
		quiz.addQuestion(question);
		new QuizDAOImpl().createQuiz(quiz);
		
		Date date = new Date();
		Timestamp dateCourante = new Timestamp(date.getTime());
				
		choix = new Choisir(dateCourante, proposition1, quiz, personne);
		boolean statut = new ChoisirDAOImpl().createChoix(choix);
		
		assertEquals(true, statut);
	}
}
