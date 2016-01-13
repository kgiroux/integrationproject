import java.util.LinkedList;
import java.util.List;

import fr.esigelec.quiz.dao.hibernate.QuestionDAOImpl;
import fr.esigelec.quiz.dao.hibernate.QuizDAOImpl;
import fr.esigelec.quiz.dto.Proposition;
import fr.esigelec.quiz.dto.Question;
import fr.esigelec.quiz.dto.Quiz;

public class Test2 {

	public static void main(String[] args) {
		QuizDAOImpl daoQuiz = new QuizDAOImpl();
		/**QuestionDAOImpl daoQuestion = new QuestionDAOImpl();
		Proposition p = new Proposition("Blalala");
		List<Proposition> list = new LinkedList<Proposition>();
		list.add(p);
		list.add(p);
		//list.add(p);
		//list.add(p);
		Question question = new Question("Blalalala", p, list);
		
		//List<Question> listQuestion = new LinkedList<Question>();
		//listQuestion.add(question);*/
		Quiz q = new Quiz("", 0, 0);
		//q.setListeQuestions(listQuestion);
		daoQuiz.createQuiz(q);
		
		System.out.println(daoQuiz.getQuiz(q.getId()));

	}

}
