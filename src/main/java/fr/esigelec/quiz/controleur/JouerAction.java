/**
 * @author Rodolphe AGUIDISSOU - ESIGELEC 2016
 */
package fr.esigelec.quiz.controleur;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import fr.esigelec.quiz.business.ActionService;
import fr.esigelec.quiz.dao.hibernate.QuizDAOImpl;

import fr.esigelec.quiz.dto.Question;
import fr.esigelec.quiz.dto.Quiz;

/**
 * Action appel�e quend le joueur lance un quiz et uniquement � ce moment l�
 * 
 *
 */
public class JouerAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// recuperation session
		HttpSession session = request.getSession();
		QuizDAOImpl quizdaoimpl = new QuizDAOImpl();
		int idQuiz = 0; // id du quiz
		Quiz quiz = null; // quiz courant

		// ENTREE
		// si on a bien recu l'idQuiz
		if (request.getParameter("idQuiz") != null) {

			idQuiz = Integer.parseInt(request.getParameter("idQuiz"));
			// on recupere le quiz
			quiz = quizdaoimpl.getQuizAvecQuestions(idQuiz);
		}
		// cette partie l� n'est plus utilis�e normalement..
		else {
			quiz = (Quiz) session.getAttribute("quiz");
			idQuiz = quiz.getId();
		}

		// on recupere la question courante
		Question question = ActionService.getQuestionByQuizId(idQuiz);

		// SORTIE
		// on efface lancien vote qui pourrait etre encore en session
		session.removeAttribute("idProposition");

		// on envoi le quiz et la question
		session.setAttribute("quiz", quiz);
		session.setAttribute("question", question);
		// Utile ???? � priori non
		request.setAttribute("currentTimestamp", System.currentTimeMillis());

		return mapping.findForward("succes");
	}

}
