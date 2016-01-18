package fr.esigelec.quiz.controleur;

import java.util.List;

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
import fr.esigelec.quiz.dto.Question;
import fr.esigelec.quiz.dto.Quiz;

/**
 * AjouterQuizAction
 * @author Wenfeng
 *
 */
public class AjouterQuizAction extends Action {
	
	private final Logger ajouterQuizActionLogger = Logger.getLogger(AjouterQuizAction.class);
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		ajouterQuizActionLogger.debug("Execute");
		ActionErrors errors = new ActionErrors();
		
		try {
			/* Get parameters and sessions */
			Personne p = (Personne) request.getSession().getAttribute("personne");
			String libelleQuiz = request.getParameter("libelleQuiz");
			List<Question> questions = (List<Question>) request.getAttribute("listeQuestionsQuiz");
			
			/* Verify authentication session */
			if (p == null || p.getDroits() != Personne.ADMIN) {
				errors.add("err.session.auth", new ActionMessage("err.session.auth.notfound"));	// TODO: add error message in `mesMessage.properties'
				return mapping.findForward("login");											// TODO: verify `login' goes well
			}
			
			/* Verify inputs (parameter `libelleQuez' && attribute `questions') */
			if (libelleQuiz == null || "".equals(libelleQuiz) || questions == null) {
				errors.add("err.inputs", new ActionMessage("err.inputs.null"));					// TODO: add error message in `mesMessage.properties'
				return mapping.findForward("erreur");											// TODO: verify `erreur' goes well
			}
			
			/* Everything goes well; create quiz */
			Quiz quiz = new Quiz();
			quiz.setLibelle(libelleQuiz);
			quiz.setListeQuestions(questions);
			// Add to bdd
			IQuizDAO quizDAO = new QuizDAOImpl();
			quizDAO.createQuiz(quiz);
			// Set attributes and return map
			request.setAttribute("listeQuestions", questions);
			ajouterQuizActionLogger.debug("Quiz ajout�");
			return mapping.findForward("succes");												// TODO: verify `succes' goes well
			
		} catch (Exception e) {
			e.printStackTrace();
			// Add attributes of error message
			ajouterQuizActionLogger.debug("Action terminee avec erreur : "+e.getMessage());
			return mapping.findForward("erreur");												// TODO: verify `erreur' goes well
		}
	}
}
