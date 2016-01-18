package fr.esigelec.quiz.controleur;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class CompteurAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		//UTILS
				HttpSession session = request.getSession();
				int Compteur = Integer.parseInt(request.getParameter("compteur"));
				session.setAttribute("Compteur", Compteur);
				
		return mapping.findForward("succes");
		
	}

}
