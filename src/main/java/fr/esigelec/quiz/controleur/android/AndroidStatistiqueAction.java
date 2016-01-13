package fr.esigelec.quiz.controleur.android;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.JSONObject;

import fr.esigelec.quiz.dao.hibernate.QuizDAOImpl;
import fr.esigelec.quiz.dto.Personne;
import fr.esigelec.quiz.dto.Quiz;
import fr.esigelec.quiz.util.AndroidHelper;
/**
 * @author Kévin Giroux;
 * 
 */

public class AndroidStatistiqueAction extends Action{
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
			if("POST".equals(request.getMethod()))
			{
				//TODO
				int idQuiz = Integer.parseInt(request.getParameter("idQuiz"));
				
				
				QuizDAOImpl dao = new QuizDAOImpl();
				Quiz quiz = dao.getQuiz(idQuiz);
				if(quiz == null){
					JSONObject json = AndroidHelper.DoGetForbiddenException();
					request.setAttribute("json", json.toString());
					return mapping.findForward("succes");
				}
				JSONObject json = new JSONObject(quiz);
				System.out.println(json.toString());
				request.setAttribute("json",json.toString());
				
				return mapping.findForward("succes");
			}
			return mapping.findForward("error");
	}
}
