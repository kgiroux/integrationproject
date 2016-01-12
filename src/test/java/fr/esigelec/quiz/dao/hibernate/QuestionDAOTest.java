package fr.esigelec.quiz.dao.hibernate;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import fr.esigelec.quiz.dao.hibernate.QuestionDAOImpl;
import fr.esigelec.quiz.dto.Proposition;
import fr.esigelec.quiz.dto.Question;

public class QuestionDAOTest {

	/**
	 * Instance de QuestionDAOImpl utilis� dans les tests
	 */
	private QuestionDAOImpl qdao = new QuestionDAOImpl();

	@Test
	public void testCreateQuestion() {

		/*Proposition rep = new Proposition("oui");
		List<Proposition> listP = new ArrayList<Proposition>();
		listP.add(new Proposition("oui"));
		listP.add(new Proposition("non"));
		Question q = new Question("Question de test", rep, listP);*/
		
		List<Question> listQ = qdao.listQuestion();
		
		//ajout de la voiture
		//qdao.createQuestion(q);
		
		List<Question> listQ_after = qdao.listQuestion();
		
		//tests
		//si la question a �t� ajout�e, listQ_after = listQ + q
		assertEquals(listQ.size()+1, listQ_after.size());
		assertNotNull(listQ_after);
	}

	@Test
	public void testGetQuestion() {
		
		Question get = qdao.getQuestion(0);
		List<Proposition> listP = new ArrayList<Proposition>();
		listP.add(new Proposition("Paris", get.getId()));
		listP.add(new Proposition("Bruxelles", get.getId()));
		listP.add(new Proposition("Marseille", get.getId()));
		assertEquals(get,new Question("Quelle est la capitale de la France?",new Proposition("Paris",get.getId()),listP));
	}

	@Test
	public void testListQuestion() {
		List<Question> listQ = qdao.listQuestion();
		assertNotNull(listQ);
		
		for(Question q : listQ){
			if(q.getId() == 0){
				assertEquals(q,new Question());
			}
		}
	}

	@Test
	public void testUpdateQuestion() {
		Question q = qdao.getQuestion(1);
		q.setLibelle(":)");
		qdao.updateQuestion(q);
		Question newQ = qdao.getQuestion(1);
		
		assertEquals(q,newQ);
	}

	@Test
	public void testDeleteQuestion() {
		Question q = qdao.getQuestion(2);
		qdao.deleteQuestion(q);
		Question newQ = qdao.getQuestion(2);
		
		assertNull(newQ);
	}

}
