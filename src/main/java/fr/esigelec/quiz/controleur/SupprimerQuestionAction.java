package fr.esigelec.quiz.controleur;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import fr.esigelec.quiz.dao.IQuestionDAO;
import fr.esigelec.quiz.dao.IQuizDAO;
import fr.esigelec.quiz.dao.hibernate.QuestionDAOImpl;
import fr.esigelec.quiz.dao.hibernate.QuizDAOImpl;
import fr.esigelec.quiz.dto.Personne;

/**
 * 
 * @author Vincent Marion & Damien Bellenger
 *
 */

public class SupprimerQuestionAction extends Action {
	private static final Logger supprimerQuestionActionLogger = Logger.getLogger(SupprimerQuestionAction.class);
	

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			// Get parameters and sessions
			Personne p = (Personne) request.getSession().getAttribute("personne");
			Question question = (Question) request.getParameter("idQuestion");
			
			if (p.getDroits() == Personne.ADMIN) {
				IQuestionDAO questionDAO = new QuestionDAOImpl();
				questionDAO.deleteQuestion(question);
				request.setAttribute("listeQuestions", questionDAO.listQuestion());
				return mapping.findForward("succes");	/* Need to map to quizAdmin.jsp */
			} else {
				supprimerQuestionActionLogger.info("erreur: droit d'admin requis");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			// Add attributes of error message
			supprimerQuestionActionLogger.info("erreur: "+e);
		}
		return mapping.findForward("succes");
	}
}
