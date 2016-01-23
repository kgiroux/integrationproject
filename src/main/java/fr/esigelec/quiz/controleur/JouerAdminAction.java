package fr.esigelec.quiz.controleur;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import fr.esigelec.quiz.business.ActionService;
import fr.esigelec.quiz.dao.IQuizDAO;
import fr.esigelec.quiz.dao.hibernate.QuizDAOImpl;
import fr.esigelec.quiz.dto.Question;
import fr.esigelec.quiz.dto.Quiz;

/**
 * 
 * (last modified by mincong)
 * @author minconghuang
 *
 */
public class JouerAdminAction extends Action  {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		// init
		IQuizDAO quizDAO = new QuizDAOImpl();
		HttpSession session = request.getSession();

		// IN: get params
		int idParam = Integer.parseInt(request.getParameter("idQuiz"));
		System.out.println("idQuiz " + idParam);
		Quiz quiz = quizDAO.getQuizAvecQuestions(idParam);
		Question question;
		
		// compute
		quiz.setDateDebutQuiz(new Timestamp(System.currentTimeMillis()));
		quiz.setDateDebutQuestion(new Timestamp(System.currentTimeMillis()));
		quiz.setEtape(1);
		quiz.setNoQuestionCourante(0);
		quizDAO.updateQuiz(quiz);
		question = ActionService.getQuestionByQuizId(idParam);
				
		// OUT
		session.setAttribute("quiz", quiz);
		session.setAttribute("question", question);
		int compteur=0;
		session.setAttribute("compteur", compteur);
		return mapping.findForward("succes");
	}
}
