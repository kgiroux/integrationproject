package fr.esigelec.quiz.controleur;

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

public class SupprimerQuizAction extends Action {
	private static final Logger supprimerQuizActionLogger = Logger.getLogger(SupprimerQuizAction.class);
	

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			// Get parameters and sessions
			Personne p = (Personne) request.getSession().getAttribute("personne");
			int idQuiz = Integer.parseInt(request.getParameter("idQuiz"));
			
			if (p.getDroits() == Personne.ADMIN) {
				IQuizDAO quizDAO = new QuizDAOImpl();
				quizDAO.deleteQuiz(quizDAO.getQuiz(idQuiz));
				return mapping.findForward("succes");	/* Need to map to quizAdmin.jsp */
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
