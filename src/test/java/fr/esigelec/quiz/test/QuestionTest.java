package fr.esigelec.quiz.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import fr.esigelec.quiz.dao.hibernate.QuestionDAOImpl;
import fr.esigelec.quiz.dto.Question;

public class QuestionTest {

	/**
	 * Instance de QuestionDAOImpl utilisé dans les tests
	 */
	private QuestionDAOImpl qdao = new QuestionDAOImpl();
	/**
	 * Instance de Question utilisée dans les tests
	 */
	private Question q;

	@Test
	public void testCreateQuestion() {


		//ajout de la voiture
		qdao.createQuestion(q);



		//tests
		//si la voiture a été ajoutée, cars_after = cars+1
		//assertEquals(list.size()+1, list_after.size());
		//
		//assertEquals(q.toString(),newQ.toString());
	}

	@Test
	public void testGetQuestion() {
		fail("Not yet implemented");
	}

	@Test
	public void testListQuestion() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateQuestion() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteQuestion() {
		fail("Not yet implemented");
	}

}
