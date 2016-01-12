package fr.esigelec.quiz.dao.hibernate;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.List;

import org.junit.Test;

import fr.esigelec.quiz.dao.hibernate.QuizDAOImpl;
import fr.esigelec.quiz.dto.Quiz;

public class QuizDAOTest {

	/**
	 * Instance de QuizDAOImpl utilis� dans les tests
	 */
	private QuizDAOImpl qdao = new QuizDAOImpl();
	
	@Test
	public void testCreateQuiz() {
		@SuppressWarnings("deprecation")
		Quiz q = new Quiz("Quiz Test", new Timestamp(2016, 01, 11, 22, 55, 0, 0), new Timestamp(2016, 01, 11, 23, 05, 0, 0), new Timestamp(2016, 01, 11, 22, 05, 0, 0),
				3, 3);
		
		List<Quiz> listQ = qdao.listQuiz();
		
		//ajout de la voiture
		qdao.createQuiz(q);
		
		List<Quiz> listQ_after = qdao.listQuiz();
		
		//tests
		//si la Quiz a �t� ajout�e, listQ_after = listQ + q
		assertEquals(listQ.size()+1, listQ_after.size());
		assertNotNull(listQ_after);
	}

	@Test
	public void testGetQuiz() {
		Quiz get = qdao.getQuiz(0);
		assertEquals(get,new Quiz());
	}

	@Test
	public void testListQuiz() {
		List<Quiz> listQ = qdao.listQuiz();
		assertNotNull(listQ);
		
		for(Quiz q : listQ){
			if(q.getId() == 0){
				assertEquals(q,new Quiz());
			}
		}
	}

	@Test
	public void testUpdateQuiz() {
		Quiz q = qdao.getQuiz(1);
		q.setLibelle(":)");
		qdao.updateQuiz(q);
		Quiz newQ = qdao.getQuiz(1);
		
		assertEquals(q,newQ);
	}

	@Test
	public void testDeleteQuiz() {
		Quiz q = qdao.getQuiz(2);
		qdao.deleteQuiz(q);
		Quiz newQ = qdao.getQuiz(2);
		
		assertNull(newQ);
	}

}
