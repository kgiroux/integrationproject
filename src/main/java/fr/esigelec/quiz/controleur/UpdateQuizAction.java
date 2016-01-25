package fr.esigelec.quiz.controleur;

import java.util.ArrayList;
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

import fr.esigelec.quiz.dao.IQuestionDAO;
import fr.esigelec.quiz.dao.IQuizDAO;
import fr.esigelec.quiz.dao.hibernate.QuestionDAOImpl;
import fr.esigelec.quiz.dao.hibernate.QuizDAOImpl;
import fr.esigelec.quiz.dto.Personne;
import fr.esigelec.quiz.dto.Question;
import fr.esigelec.quiz.dto.Quiz;

public class UpdateQuizAction extends Action {
private final Logger updateQuizActionLogger = Logger.getLogger(UpdateQuizAction.class);
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		updateQuizActionLogger.debug("Execute");
		ActionErrors errors = new ActionErrors();
		
		try {
			/* Get parameters and sessions */
			Personne p = (Personne) request.getSession().getAttribute("personne");
			String libelleQuiz = request.getParameter("libelleQuiz");
			String quizId = request.getParameter("quizId");
			String[] questionIds = request.getParameterValues("questionId");
			
			/* Verify authentication session */
			if (p == null || p.getDroits() != Personne.ADMIN) {
				errors.add("err.session.auth", new ActionMessage("err.session.auth.notfound"));
				if (!errors.isEmpty())
					addErrors(request, errors);
				return mapping.findForward("login");
			}
			
			/* Verify inputs (parameter `libelleQuez' && attribute `questions') */
			if (libelleQuiz == null || "".equals(libelleQuiz) || questionIds.length == 0) {
				errors.add("err.inputs", new ActionMessage("err.inputs.null"));	
				if (!errors.isEmpty())
					addErrors(request, errors);
				return mapping.findForward("erreur");
			}
			
			/* Verify inputs (parameter `libelleQuez' && attribute `questions') */
			if (quizId == null || "".equals(quizId)) {
				errors.add("err.inputs", new ActionMessage("err.inputs.null"));	
				if (!errors.isEmpty())
					addErrors(request, errors);
				return mapping.findForward("erreur");
			}
			
			int id = Integer.parseInt(quizId);
			
			
			/* Everything goes well; create quiz */
			IQuestionDAO questionDAO = new QuestionDAOImpl();
			List<Question> questions = new ArrayList<Question>();
			for (String s : questionIds) {
				int questionId = Integer.parseInt(s);
				questions.add(questionDAO.getQuestion(questionId));
			}
//			
//			Quiz quiz = new Quiz();
//			quiz.setLibelle(libelleQuiz);
//			quiz.setListeQuestions(questions);
//			// Add to bdd
			IQuizDAO quizDAO = new QuizDAOImpl();
			Quiz quiz = quizDAO.getQuiz(id);
			quiz.setListeQuestions(questions);
			
			updateQuizActionLogger.debug(quiz.toString());
			quizDAO.updateQuiz(quiz);
			updateQuizActionLogger.debug("Quiz update");
			return mapping.findForward("succes");
			
		} catch (Exception e) {
			e.printStackTrace();
			// Add attributes of error message
			updateQuizActionLogger.debug("Action terminee avec erreur : "+e.getMessage());
			return mapping.findForward("erreur");
		}
	}

}
