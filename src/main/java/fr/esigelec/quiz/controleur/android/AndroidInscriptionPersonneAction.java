package fr.esigelec.quiz.controleur.android;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.JSONObject;

import fr.esigelec.quiz.dao.IPersonneDAO;
import fr.esigelec.quiz.dao.hibernate.PersonneDAOImpl;
import fr.esigelec.quiz.dto.Personne;
import fr.esigelec.quiz.exception.AndroidHelper;

public class AndroidInscriptionPersonneAction extends Action{
	
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
			if("GET".equals(request.getMethod())){
				//Personne p = new Personne(0, "Serais", "Sebastien", "serais@esigelec.com", "1234567890", 1);
				//p.setId(42);
				//JSONObject json = new JSONObject(p);
				JSONObject json = AndroidHelper.DoGetForbiddenException();
				request.setAttribute("json", json.toString());
				return mapping.findForward("error");
		
			}else if("POST".equals(request.getMethod())){
			
				Personne p = new Personne();
				p.setMail(request.getParameter("mail"));
				p.setMdp(request.getParameter("mdp"));
				// Only a simple user
				p.setDroits(Personne.JOUEUR);
				p.setNom(request.getParameter("nom"));
				p.setPrenom(request.getParameter("prenom"));
				
				
				// Need DAO ACtion for subscription
				IPersonneDAO personneDAO = new PersonneDAOImpl();
				personneDAO.createPersonne(p);
				
				// Validation
				Personne p2 = personneDAO.getPersonne(p.getMail());
				JSONObject json = new JSONObject(p2);
				
				
				request.setAttribute("json",json.toString());
				return mapping.findForward("succes");
			}else{
				return mapping.findForward("error");
			}
		}
}
