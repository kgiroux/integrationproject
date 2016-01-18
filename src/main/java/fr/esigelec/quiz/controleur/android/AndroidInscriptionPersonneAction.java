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
import fr.esigelec.quiz.util.AndroidHelper;

/**
 * @author Kévin Giroux;
 * 
 */


public class AndroidInscriptionPersonneAction extends Action{
	
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
			if("GET".equals(request.getMethod())){
				JSONObject json = AndroidHelper.DoGetForbiddenException();
				request.setAttribute("json", json.toString());
				return mapping.findForward("error");
			}
			else if("POST".equals(request.getMethod())){
			
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
				
				p.setMail("");
				p.setMdp("");
				
				if(p.getId() == 0){
					JSONObject json = AndroidHelper.DatabaseInsertFail();
					request.setAttribute("json", json.toString());
					return mapping.findForward("error");
				}
				JSONObject json = new JSONObject(p);
				
				request.setAttribute("json",json.toString());
				return mapping.findForward("succes");
			}else{
				return mapping.findForward("error");
			}
		}
}
