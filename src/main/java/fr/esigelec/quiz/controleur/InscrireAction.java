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
import fr.esigelec.quiz.util.SecurityHelper;

/**
 * InscrireAction
 * @author Wenfeng
 *
 */
public class InscrireAction extends Action {
	private static final Logger inscrireActionLogger = Logger.getLogger(InscrireAction.class);

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		try {
			
			inscrireActionLogger.debug("Execute");
			
			InscrireForm inscrireForm = (InscrireForm) form;
			Personne p = new Personne();
			p.setNom(inscrireForm.getNom());
			p.setPrenom(inscrireForm.getPrenom());
			p.setMdp(SecurityHelper.MD5(inscrireForm.getMdp()));
			p.setMail(inscrireForm.getMail());
			IPersonneDAO personneDAO = new PersonneDAOImpl();
			inscrireActionLogger.info("p=" + p);
			// Maybe we should add a check to verify if the person is already in db.
			personneDAO.createPersonne(p);
			// Maybe set some attributes on the request.
			inscrireActionLogger.debug("Inscription terminee avec succes");
			return mapping.findForward("succes");
		} catch (Exception e) {
			// Set error attributes
			inscrireActionLogger.debug("Action terminee avec erreur : "+e.getMessage());
			return mapping.findForward("erreur");
		}
	}
	
}
