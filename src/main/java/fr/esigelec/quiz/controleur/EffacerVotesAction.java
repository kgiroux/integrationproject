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

import fr.esigelec.quiz.dao.IChoisirDAO;
import fr.esigelec.quiz.dao.IQuizDAO;
import fr.esigelec.quiz.dao.hibernate.ChoisirDAOImpl;
import fr.esigelec.quiz.dao.hibernate.QuizDAOImpl;
import fr.esigelec.quiz.dto.Personne;
import fr.esigelec.quiz.dto.Quiz;

/**
 * SupprimerQuizAction
 * @author Wenfeng
 *
 */
public class EffacerVotesAction extends Action {
	
	private final Logger effacerVotesActionLogger = Logger.getLogger(EffacerVotesAction.class);

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		effacerVotesActionLogger.info("effacer votes");
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
			
			/* Everything goes well; efface vote du  quiz by id */
			int id = Integer.parseInt(idQuiz);
			IQuizDAO quizDAO = new QuizDAOImpl();
			
			Quiz quiz=quizDAO.getQuiz(id);
			
			
			// on efface tous les anciens votes associés à ce quiz
			IChoisirDAO choixDAO = new ChoisirDAOImpl();
			choixDAO.deleteChoix(quiz);
			
			
			return mapping.findForward("succes");
			
		} catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("erreur");
		}
	}
}
