package fr.esigelec.quiz.controleur;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import fr.esigelec.quiz.dao.IPersonneDAO;
import fr.esigelec.quiz.dao.IQuestionDAO;
import fr.esigelec.quiz.dao.IQuizDAO;
import fr.esigelec.quiz.dao.hibernate.PersonneDAOImpl;
import fr.esigelec.quiz.dao.hibernate.QuestionDAOImpl;
import fr.esigelec.quiz.dao.hibernate.QuizDAOImpl;
import fr.esigelec.quiz.dto.Personne;
import fr.esigelec.quiz.dto.Question;
import fr.esigelec.quiz.dto.Quiz;
import fr.esigelec.quiz.forms.InscrireForm;
/*
 * Projet Intégration GSI-IR
 * @uthor Enrifath TIDJANI
 */

public class JouerAction extends Action {
	int id_quiz;
	Quiz quiz;
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			id_quiz = Integer.parseInt(request.getParameter("id_quiz"));
			IQuizDAO quizDAO = new QuizDAOImpl();
			quiz = quizDAO.getQuiz(id_quiz);
			request.setAttribute("listQuestion", quiz);
			return mapping.findForward("succes");
		} catch (Exception e) {
			// Set error attributes
			return mapping.findForward("erreur");
		}
	}
	

}
