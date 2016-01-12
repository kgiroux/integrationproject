package fr.esigelec.quiz.controleur.android;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.JSONObject;

import fr.esigelec.quiz.dto.Personne;
import fr.esigelec.quiz.dto.Quiz;

public class AndroidStatistiqueAction extends Action{
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
			if("POST".equals(request.getMethod()))
			{
				//TODO
				int idQuiz = Integer.parseInt(request.getParameter("idQuiz"));
				
				
				
				//TODO
				//FIND ALL the object with the previous id;
				
				// TODO REMOVE WHEN OK
				Quiz quiz = new Quiz();
				quiz.setId(42);
				long time = System.currentTimeMillis() /1000;
				int delay = 30;
				quiz.setDateDebutQuestion(new Timestamp(time));
				quiz.setDateFinQuiz(new Timestamp(time+delay));
				quiz.setEtape(1);
				quiz.setLibelle("The android team is the best");
				
				JSONObject json = new JSONObject(quiz);
				System.out.println(json.toString());
				request.setAttribute("json",json.toString());
				
				return mapping.findForward("succes");
			}
			return mapping.findForward("error");
	}
}
