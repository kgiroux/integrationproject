package fr.esigelec.quiz.controleur;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import fr.esigelec.quiz.dao.IQuestionDAO;
import fr.esigelec.quiz.dao.IQuizDAO;
import fr.esigelec.quiz.dao.hibernate.QuestionDAOImpl;
import fr.esigelec.quiz.dao.hibernate.QuizDAOImpl;
import fr.esigelec.quiz.dto.Personne;
import fr.esigelec.quiz.dto.Question;
import fr.esigelec.quiz.dto.Quiz;

/**
 * EditerQuizAction
 * @author Wenfeng
 *
 */
public class EditerQuizAction extends Action {
	private final Logger editerQuizActionLogger = Logger.getLogger(EditerQuizAction.class);

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		editerQuizActionLogger.debug("Execute");
		ActionErrors errors = new ActionErrors();
		HttpSession session = request.getSession();
		
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
				return mapping.findForward("erreur");							// TODO: mapping `erreur'
			}
			
			/* Everything goes well; set questions to attribute */
			IQuizDAO 		quizDAO 	= new QuizDAOImpl();
			IQuestionDAO 	questionDAO = new QuestionDAOImpl();
			Quiz quiz = quizDAO.getQuizAvecQuestions(Integer.parseInt(idQuiz));
			List<Question> listeQuestionsQuiz = quiz.getListeQuestions();
			for (Question q : listeQuestionsQuiz) {
				editerQuizActionLogger.info("Question :" + q);
			}
			List <Question> listeQuestions = questionDAO.listQuestion();
			
			session.setAttribute("listeQuestions", listeQuestions);
			session.setAttribute("listeQuestionsQuiz", listeQuestionsQuiz);
			session.setAttribute("libelleQuiz", quiz.getLibelle());
			session.setAttribute("idQuiz", quiz.getId());
			
			editerQuizActionLogger.debug("Question �dit�e");
			return mapping.findForward("succes");	/* Need to map to quizAdmin.jsp */
			
		} catch (Exception e) {
			e.printStackTrace();
			// Add attributes of error message
			editerQuizActionLogger.debug("Action terminee avec erreur : "+e.getMessage());
			return mapping.findForward("erreur");
		}
	}

}
