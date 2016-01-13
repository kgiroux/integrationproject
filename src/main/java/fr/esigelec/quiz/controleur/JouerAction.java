/**
 * @author Rodolphe AGUIDISSOU - ESIGELEC 2016
 */
package fr.esigelec.quiz.controleur;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import fr.esigelec.quiz.business.ActionService;
import fr.esigelec.quiz.dao.hibernate.QuizDAOImpl;
import fr.esigelec.quiz.dto.Personne;
import fr.esigelec.quiz.dto.Question;
import fr.esigelec.quiz.dto.Quiz;



public class JouerAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		//TODO : Action Play implementation 	
		
		//UTILS
		QuizDAOImpl quizdaoimpl = new QuizDAOImpl();
		HttpSession session = request.getSession();
		
		//IN 
		Personne personne = (Personne) session.getAttribute("personne");
		int idQuiz = Integer.parseInt(request.getParameter("idQuiz"));
		
		
		//OUT 
		Quiz quiz = quizdaoimpl.getQuiz(idQuiz);
		Question question = ActionService.getQuestionByQuizId(idQuiz);
		
		
		session.setAttribute("quiz", quiz);
		session.setAttribute("question", question);
		
		
		return mapping.findForward("succes");
		
	}
	

}
