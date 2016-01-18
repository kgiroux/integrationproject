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
import fr.esigelec.quiz.dto.Personne;
import fr.esigelec.quiz.dto.Question;
import fr.esigelec.quiz.dto.Quiz;

/**
 * 
 * @author Damien BELLENGER et Rodolphe AGUIDISSOU
 *
 */
public class LancerPartieAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
		Personne personne = (Personne) session.getAttribute("personne");
		int idQuiz = Integer.valueOf(request.getParameter("idQuiz"));
		
		if(personne.getDroits() == Personne.ADMIN) {
			Timestamp currentTime = new Timestamp(System.currentTimeMillis());
			IQuizDAO quizDAO = new QuizDAOImpl();
			Quiz quiz = quizDAO.getQuiz(idQuiz);
			quiz.setDateDebutQuiz(currentTime);
			quiz.setDateDebutQuestion(currentTime);
			quiz.setNoQuestionCourante(0);
			quizDAO.updateQuiz(quiz);
			
			Question question = ActionService.getQuestionByQuizId(idQuiz);
			
			
			session.setAttribute("quiz", quiz);
			session.setAttribute("question", question);
		}
		
		
		return mapping.findForward("succes");
		
	}

}
