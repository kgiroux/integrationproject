package fr.esigelec.quiz.controleur.android;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import fr.esigelec.quiz.dao.hibernate.QuizDAOImpl;

/**
 * @author Kévin Giroux;
 * 
 */


public class AndroidQuizListAction extends Action{
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
			if("GET".equals(request.getMethod()))
			{
				QuizDAOImpl dao = new QuizDAOImpl();
				ArrayList<Quiz> listQuiz = 
				return mapping.findForward("succes");
			}
			return mapping.findForward("error");
	}
}
