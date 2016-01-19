package fr.esigelec.quiz.controleur;

import java.sql.SQLException;

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
import fr.esigelec.quiz.forms.ConnexionForm;
import fr.esigelec.quiz.util.SecurityHelper;

/**
 * Verify authentication then forward to `VueQuizAdmin' directly.
 * @author 
 * @modified Wenfeng : forward to `VueQuizAdmin' directly instead of getting `listeQuiz' everywhere.
 */
public class ConnexionAdministrateurAction extends Action {

	private final Logger connexionAdministrateurActionLogger = Logger.getLogger(ConnexionAdministrateurAction.class);
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws SQLException {

		connexionAdministrateurActionLogger.debug("Execute");
		
		ConnexionForm f= (ConnexionForm) form;
		String mail = f.getMail();
		String mdp = SecurityHelper.MD5(f.getPassword());
		
		IPersonneDAO personneDAO = new PersonneDAOImpl();
		Personne personne = personneDAO.getPersonne(mail);
//		IQuizDAO quizDAO = new QuizDAOImpl();
//		List<Quiz> listeQuiz = quizDAO.listQuiz();
		
		if( personne == null ) {
			connexionAdministrateurActionLogger.debug("Action terminee avec erreur : les coordonnees ne correspondent pas � un administrateur");
			return mapping.findForward("erreur");
		}
		else if(!mdp.equals(personne.getMdp())) {
			connexionAdministrateurActionLogger.debug("Action terminee avec erreur : mot de passe incorrect");
			return mapping.findForward("erreur");
		}
		else {
//			request.setAttribute("listeQuiz", listeQuiz);
			request.getSession().setAttribute("personne", personne);
			connexionAdministrateurActionLogger.debug("Connexion r�ussie");
			return mapping.findForward("succes");
		}
	}
}
