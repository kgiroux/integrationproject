/**
 * @author Rodolphe AGUIDISSOU - ESIGELEC 2016
 *
 *Classe utiliser par les Classe Actions
 * pour gerer des tratitements specifiques
 */
package fr.esigelec.quiz.business;

import fr.esigelec.quiz.dao.hibernate.QuestionDAOImpl;
import fr.esigelec.quiz.dao.hibernate.QuizDAOImpl;
import fr.esigelec.quiz.dto.Question;
import fr.esigelec.quiz.dto.Quiz;

public class ActionService {
	
	public static  Question  getQuestionByQuizId(int idQuiz){
		
		//UTILS 
		QuizDAOImpl quizdaoimpl = new QuizDAOImpl();
		QuestionDAOImpl questiondaoimpl = new QuestionDAOImpl();
		
		Quiz quiz= quizdaoimpl.getQuiz(idQuiz);	
		Question question = questiondaoimpl.getQuestion(quiz.getNoQuestionCourante());
		
		return question;
	}

}
