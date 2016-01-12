package fr.esigelec.quiz.controleur;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import fr.esigelec.quiz.dao.IQuizDAO;
import fr.esigelec.quiz.dao.hibernate.QuizDAOImpl;
import fr.esigelec.quiz.dto.Personne;
import fr.esigelec.quiz.dto.Question;
import fr.esigelec.quiz.dto.Quiz;

public class AjouterQuizAction extends Action {
	private final Logger actionQuizActionLogger = Logger.getLogger(AjouterQuizAction.class);
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			// Get parameters and sessions
			Personne p = (Personne) request.getSession().getAttribute("personne");
			String libelleQuiz = request.getParameter("libelleQuiz");
			List<Question> questions = (List<Question>) request.getAttribute("listeQuestions");
			
			if (p.getDroits() == Personne.ADMIN) {
				// Create quiz
				Quiz quiz = new Quiz();
				quiz.setLibelle(libelleQuiz);
				quiz.setListeQuestions(questions);
				// Add to bdd
				IQuizDAO quizDAO = new QuizDAOImpl();
				quizDAO.createQuiz(quiz);
				// Set attributes and return map
				request.setAttribute("listeQuestions", questions);
				return mapping.findForward("succes");	/* Need to map to questionsQuizAdmin.jsp */
			} else {
				return mapping.findForward("login");	/* If user is not admin, map to somewhere */
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			// Add attributes of error message
			return mapping.findForward("erreur");
		}
	}

}
