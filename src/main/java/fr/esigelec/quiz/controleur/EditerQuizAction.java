package fr.esigelec.quiz.controleur;

import java.util.List;

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
import fr.esigelec.quiz.dto.Question;
import fr.esigelec.quiz.dto.Quiz;

/**
 * EditerQuizAction
 * @author Wenfeng
 *
 */
public class EditerQuizAction extends Action {
	private static final Logger editerQuizActionLogger = Logger.getLogger(EditerQuizAction.class);

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		editerQuizActionLogger.debug("Execute");
		
		try {
			// Get parameters and sessions
			Personne p = (Personne) request.getSession().getAttribute("personne");
			int idQuiz = Integer.parseInt(request.getParameter("idQuiz"));
			
			if (p.getDroits() == Personne.ADMIN) {
				IQuizDAO quizDAO = new QuizDAOImpl();
				IQuestionDAO questionDAO = new QuestionDAOImpl();
				Quiz quiz = quizDAO.getQuiz(idQuiz);
				List<Question> listeQuestionsQuiz = quizDAO.listQuestionQuiz(quiz);
				List <Question> listeQuestions = questionDAO.listQuestion();
				
				request.setAttribute("listeQuestions", listeQuestions);
				request.setAttribute("listeQuestionsQuiz", listeQuestionsQuiz);

				editerQuizActionLogger.debug("Question �dit�e");
				return mapping.findForward("succes");	/* Need to map to quizAdmin.jsp */
			} else {
				editerQuizActionLogger.debug("Action terminee avec erreur : l'utilisateur n'est pas un administrateur");
				return mapping.findForward("login");	/* If user is not admin, map to somewhere */
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			// Add attributes of error message
			editerQuizActionLogger.debug("Action terminee avec erreur : "+e.getMessage());
			return mapping.findForward("erreur");
		}
	}

}
