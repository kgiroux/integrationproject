package fr.esigelec.quiz.controleur.android;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Kévin Giroux;
 * 
 */


public class AndroidJouerAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
			if("GET".equals(request.getMethod()))
			{
				//TODO
				// Return the current quizz
				
				return mapping.findForward("succes");
			}
			return mapping.findForward("error");
	}
	
}
