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


public class AndroidConnexionPersonneAction extends Action {
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
			
			if("GET".equals(request.getMethod())){
				JSONObject json = AndroidHelper.DoGetForbiddenException();
				request.setAttribute("json", json.toString());
				return mapping.findForward("succes");
		
			}else if("POST".equals(request.getMethod())){
			
				String mail = request.getParameter("mail");
				String mdp = request.getParameter("mdp");
				JSONObject json = new JSONObject();
				IPersonneDAO personneDAO = new PersonneDAOImpl();
				if (mail != null && mdp != null) {
					Personne p = personneDAO.getPersonne(mail);
					// check login
					// user not found
					if (p == null) {
						json = AndroidHelper.UserNotFoundException();
					// password incorrect
					} else if (!mdp.equals(p.getMdp())) {
						json = AndroidHelper.PassIncorrectException();
					} else {
						p.setMail("");
						p.setMdp("");
						json = new JSONObject(p);
					}
				} else {
					json = AndroidHelper.MissingArgException();
				}
				request.setAttribute("json",json.toString());
				return mapping.findForward("succes");
			}else{
				return mapping.findForward("error");
			}
		}
}
