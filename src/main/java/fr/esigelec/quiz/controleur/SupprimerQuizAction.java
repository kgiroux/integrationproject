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

import fr.esigelec.quiz.dao.IQuizDAO;
import fr.esigelec.quiz.dao.hibernate.QuizDAOImpl;
import fr.esigelec.quiz.dto.Personne;

/**
 * SupprimerQuizAction
 * @author Wenfeng
 *
 */
public class SupprimerQuizAction extends Action {
	
	private final Logger supprimerQuizActionLogger = Logger.getLogger(SupprimerQuizAction.class);

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		ActionErrors errors = new ActionErrors();

		try {
			/* Get parameters and sessions */
			Personne p = (Personne) request.getSession().getAttribute("personne");
			String idQuiz = request.getParameter("idQuiz");
			
			/* Verify authentication session */
			if (p == null || p.getDroits() != Personne.ADMIN) {
				errors.add("err.session.auth", new ActionMessage("err.session.auth.notfound"));
				if (!errors.isEmpty())
					addErrors(request, errors);
				return mapping.findForward("login");
			}
			
			/* Verify input parameter */
			if (idQuiz == null || "".equals(idQuiz)) {
				errors.add("err.inputs", new ActionMessage("err.inputs.null"));	
				if (!errors.isEmpty())
					addErrors(request, errors);
				return mapping.findForward("erreur");
			}
			
			/* Everything goes well; delete quiz by id */
			int id = Integer.parseInt(idQuiz);
			IQuizDAO quizDAO = new QuizDAOImpl();
			quizDAO.deleteQuiz(quizDAO.getQuizAvecQuestions(id));
			
			return mapping.findForward("succes");
			
		} catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("erreur");
		}
	}
}
