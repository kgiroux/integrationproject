
package fr.esigelec.quiz.controleur.android;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

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
 * @author K�vin Giroux;
 * @edited by Kevin PACE
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
				
				String mail = null;
				String mdp = request.getParameter("mdp");
				JSONObject json = new JSONObject();
				
				try {
					mail = URLDecoder.decode(request.getParameter("mail"), "UTF-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//Check information into Database
				IPersonneDAO personneDAO = new PersonneDAOImpl();
				if (mail != null && mdp != null) {
					
					Personne p = personneDAO.getPersonne(mail); // check login
					
					// user not found
					if (p == null) {
						json = AndroidHelper.UserNotFoundException();
						
					// password incorrect
					} else if (!mdp.equals(p.getMdp())) {
						json = AndroidHelper.PassIncorrectException();
					} 
					else {
						p.setMail("");
						p.setMdp("");
						json = new JSONObject(p);
					}
				} 
				else {
					json = AndroidHelper.MissingArgException();
				}
				
				//Return information to client
				request.setAttribute("json",json.toString());
				return mapping.findForward("succes");
				
			}else{
				
				JSONObject json = new JSONObject();
				json = AndroidHelper.TimeOutExeception();
				request.setAttribute("json", json);
				return mapping.findForward("error");
				
			}
		}
}
