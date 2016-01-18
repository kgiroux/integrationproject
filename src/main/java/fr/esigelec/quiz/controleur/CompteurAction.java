package fr.esigelec.quiz.controleur;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import fr.esigelec.quiz.dao.hibernate.QuizDAOImpl;
import fr.esigelec.quiz.dto.Quiz;

public class CompteurAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		//UTILS
				HttpSession session = request.getSession();
				int compteur = Integer.parseInt(request.getParameter("compteur"));
				compteur++;
				session.setAttribute("compteur", compteur);
				
				QuizDAOImpl quizdaoimpl = new QuizDAOImpl();
				Quiz quiz = (Quiz)session.getAttribute("quiz");
							
				
				quiz.setNoQuestionCourante(compteur);
				quiz.setEtape(0);
				quizdaoimpl.updateQuiz(quiz);
				Quiz q = quizdaoimpl.getQuiz(quiz.getId());
				session.setAttribute("quiz", q);
		return mapping.findForward("succes");
		
	}

}
