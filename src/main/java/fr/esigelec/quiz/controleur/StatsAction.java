/**
 * @author TIONO KEVIN & ENRIFATH TIDJANI
 */
package fr.esigelec.quiz.controleur;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import fr.esigelec.quiz.dao.hibernate.QuizDAOImpl;
import fr.esigelec.quiz.dto.Quiz;

public class StatsAction extends Action{
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		

		QuizDAOImpl quizdaoimpl = new QuizDAOImpl();
		
		int idQuiz = Integer.parseInt(request.getParameter("idQuiz"));
		
		
		//OUT 
		Quiz quiz = quizdaoimpl.getQuiz(idQuiz);
		quiz.setEtape(2);
				
		return mapping.findForward("succes");
		
		
	}
	

}
