package fr.esigelec.quiz.controleur;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import fr.esigelec.quiz.dao.hibernate.PersonneDAO;
import fr.esigelec.quiz.forms.InscrireForm;

/*
 * There'e several errors now, because no TDO and DAO.
 */
public class InscrireAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		try {
			InscrireForm inscrireForm = (InscrireForm) form;
			Personne p = new Personne(inscrireForm.getNom(),
										inscrireForm.getPrenom(),
										inscrireForm.getMail(),
										inscrireForm.getMdp());
			PersonneDAO personneDAO = PersonneDAOFctory.getPersonneDAO();
			// We should add a check to verify if the person is already in db.
			personneDAO.ajouter(p);
			// Maybe set some attributes on the request.
			return mapping.findForward("succes");
		} catch (Exception e) {
			// Set error attributes
			return mapping.findForward("erreur");
		}
	}
	

}
