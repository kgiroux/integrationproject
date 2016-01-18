package fr.esigelec.quiz.controleur;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import fr.esigelec.quiz.dao.IQuestionDAO;
import fr.esigelec.quiz.dao.hibernate.QuestionDAOImpl;
import fr.esigelec.quiz.dto.Personne;

/**
 * Get all questions from bdd, then store in attribure for jsp use.
 * @author C'est pas moi
 * @modified Wenfeng
 *
 */
public class VueQuestionQuizAdminAction extends Action {
	
	private static final Logger vueQuestionQuizAdminAction = Logger.getLogger(VueQuestionQuizAdminAction.class);
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		vueQuestionQuizAdminAction.info("starting...");
		ActionErrors errors = new ActionErrors();

		try {
			// Get parameters and sessions
			Personne p = (Personne) request.getSession().getAttribute("personne");
			
			if (p == null || p.getDroits() != Personne.ADMIN) {
				errors.add("err.session.auth", new ActionMessage("err.session.auth.notfound"));
				if (!errors.isEmpty())
					addErrors(request, errors);
				return mapping.findForward("login");
			}
			
			IQuestionDAO questionDAO = new QuestionDAOImpl();
			request.setAttribute("listeQuestions", questionDAO.listQuestion());
			
			/* What's `'listeQuiz' up to ? */
//			IQuizDAO quizDAO = new QuizDAOImpl();
//			request.setAttribute("listeQuiz", quizDAO.listQuiz());
			
		} catch (Exception e) {
			e.printStackTrace();
			// Add attributes of error message
			vueQuestionQuizAdminAction.info("erreur: "+e);
		}
		return mapping.findForward("succes");
	}
}