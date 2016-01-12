package fr.esigelec.quiz.controleur;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import fr.esigelec.quiz.dao.IPersonneDAO;
import fr.esigelec.quiz.dao.hibernate.PersonneDAOImpl;
import fr.esigelec.quiz.dto.Personne;
import fr.esigelec.quiz.forms.InscrireForm;

/*
 * There'e several errors now, because no TDO and DAO.
 */
public class InscrireAction extends Action {
	private static final Logger inscrireActionLogger = Logger.getLogger(InscrireAction.class);

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		try {
			
			inscrireActionLogger.info("start InscrireAction");
			
			InscrireForm inscrireForm = (InscrireForm) form;
			Personne p = new Personne();
			p.setNom(inscrireForm.getNom());
			p.setPrenom(inscrireForm.getPrenom());
			p.setMdp(inscrireForm.getMdp());
			IPersonneDAO personneDAO = new PersonneDAOImpl();
			// Maybe we should add a check to verify if the person is already in db.
			personneDAO.createPersonne(p);
			// Maybe set some attributes on the request.
			return mapping.findForward("succes");
		} catch (Exception e) {
			// Set error attributes
			return mapping.findForward("erreur");
		}
	}
	
}
