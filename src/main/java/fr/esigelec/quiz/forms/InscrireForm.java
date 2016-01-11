package fr.esigelec.quiz.forms;

import org.apache.struts.action.ActionForm;

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
}
