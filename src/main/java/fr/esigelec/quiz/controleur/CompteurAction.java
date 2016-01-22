package fr.esigelec.quiz.controleur;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import fr.esigelec.quiz.dao.IQuizDAO;
import fr.esigelec.quiz.dao.hibernate.QuizDAOImpl;
import fr.esigelec.quiz.dto.Quiz;

public class CompteurAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		//UTILS
				HttpSession session = request.getSession();
				//compteur correspond au no de la question courante qui commence à zero.
				int compteur = Integer.parseInt(request.getParameter("compteur"));
				
				QuizDAOImpl quizdaoimpl = new QuizDAOImpl();
				Quiz quiz = (Quiz)session.getAttribute("quiz");
				
				if(compteur<quiz.getListeQuestions().size()-1)
						compteur++;
				
				
				
				session.setAttribute("compteur", compteur);
				
				Timestamp currentTime = new Timestamp(System.currentTimeMillis());
				quiz.setDateDebutQuestion(currentTime);
					
				
				
				
				
				quiz.setNoQuestionCourante(compteur);
				quiz.setEtape(1);
				quizdaoimpl.updateQuiz(quiz);
				Quiz q = quizdaoimpl.getQuizAvecQuestions(quiz.getId());
				session.setAttribute("quiz", q);
		return mapping.findForward("succes");
		
	}

}
