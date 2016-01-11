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
	
	public String getPrenom() {
		return password;
	}
	
	public void setPrenom(String password) {
		this.password = password;
	}
	
	public String toString(){
		return mail+" "+password;
	}
	
	@Override
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		
		ActionErrors errors=new ActionErrors();
	
		
		if("".equals(mail))
				errors.add("erreur.nom", new ActionMessage("erreur.nom.vide"));
		if("".equals(password))
				errors.add("erreur.prenom", new ActionMessage("erreur.prenom.vide"));
		
		return errors;
	}

}
