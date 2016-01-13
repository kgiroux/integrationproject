package fr.esigelec.quiz.controleur.android;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.JSONObject;

import fr.esigelec.quiz.dto.Personne;
import fr.esigelec.quiz.util.AndroidHelper;

public class AndroidChoisirAction extends Action{
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
			if("POST".equals(request.getMethod()))
			{
				//TODO
				int idProposition = Integer.parseInt(request.getParameter("idProposition"));
				int idPersonne = Integer.parseInt(request.getParameter("idPersonne"));
				int idQuiz = Integer.parseInt(request.getParameter("idQuiz"));
				
				
				//TODO
				//FIND ALL the object with the previous id;
				
				return mapping.findForward("succes");
			} else {
				JSONObject json = AndroidHelper.DoGetForbiddenException();
				request.setAttribute("json", json.toString());
				return mapping.findForward("error");
			}
	}

}
