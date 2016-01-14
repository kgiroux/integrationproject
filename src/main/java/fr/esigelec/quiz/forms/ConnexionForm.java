package fr.esigelec.quiz.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class ConnexionForm extends ActionForm {
	
	String mail;
	String password;
	
	public String getMail() {
		return mail;
	}
	
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String toString(){
		return mail+" "+password;
	}
	
	@Override
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		
		ActionErrors errors=new ActionErrors();
	
		
		if("".equals(mail))
				errors.add("erreur.mail", new ActionMessage("erreur.connexion.mail.vide"));
		if("".equals(password))
				errors.add("erreur.password", new ActionMessage("erreur.connexion.mdp.vide"));
		
		return errors;
	}

}
