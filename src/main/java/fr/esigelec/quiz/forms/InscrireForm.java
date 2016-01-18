package fr.esigelec.quiz.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 * ActionForm for inscription
 * @author Wenfeng
 *
 */
public class InscrireForm extends ActionForm {

	private static final long serialVersionUID = 6581245846878699178L;
	
	private String nom;
	private String prenom;
	private String mail;
	private String mdp;
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getPrenom() {
		return prenom;
	}
	
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public String getMail() {
		return mail;
	}
	
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public String getMdp() {
		return mdp;
	}
	
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		
		if ("".equals(nom)) 	errors.add("erreur.nom", new ActionMessage("erreur.nom.vide"));
		if ("".equals(prenom))	errors.add("erreur.prenom", new ActionMessage("erreur.prenom.vide"));
		if ("".equals(mail))	errors.add("erreur.mail", new ActionMessage("erreur.mail.vide"));
		if ("".equals(mdp))		errors.add("erreur.mdp", new ActionMessage("erreur.mdp.vide"));
		
		return errors;
	}
	
	
}
