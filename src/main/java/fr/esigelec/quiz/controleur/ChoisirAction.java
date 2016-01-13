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

import fr.esigelec.quiz.dao.hibernate.QuizDAOImpl;
import fr.esigelec.quiz.dto.Personne;
import fr.esigelec.quiz.dto.Question;
import fr.esigelec.quiz.dto.Quiz;


public class ChoisirAction extends Action {
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		//TODO : Action Choose  implementation 
		//UTILS
		HttpSession session = request.getSession();
		
		
		//IN 
		int idProposition = Integer.parseInt(request.getParameter("idProposition"));
		Quiz quiz = (Quiz) session.getAttribute("quiz");
		Personne pesonne = (Personne) session.getAttribute("personne");
		Question question = (Question) session.getAttribute("question");
		
		
		//OUT 
		session.setAttribute("idProposition", idProposition);
		session.setAttribute("quiz", quiz);
		
		return mapping.findForward("succes");
		
	}

}
