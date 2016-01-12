package fr.esigelec.quiz.controleur.android;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.JSONObject;

import fr.esigelec.quiz.dto.Personne;

public class AndroidConnexionPersonneAction extends Action {
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
			if("GET".equals(request.getMethod())){
				// REMOVE WHEN DAO OK
				Personne p = new Personne("Serais", "Sebastien", "serais@esigelec.com", "1234567890", 1);
				p.setId(42);
				JSONObject json = new JSONObject(p);
				request.setAttribute("json", json.toString());
				return mapping.findForward("succes");
		
			}else if("POST".equals(request.getMethod())){
				Personne p = new Personne();
				p.setMail(request.getParameter("mail"));
				p.setMdp(request.getParameter("mdp"));
				JSONObject json = new JSONObject(p);
				request.setAttribute("json",json.toString());
				return mapping.findForward("succes");
			}else{
				return mapping.findForward("error");
			}
		}
}
