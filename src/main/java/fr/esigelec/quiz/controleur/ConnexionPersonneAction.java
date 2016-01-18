package fr.esigelec.quiz.controleur;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import fr.esigelec.quiz.dao.IPersonneDAO;
import fr.esigelec.quiz.dao.IQuizDAO;
import fr.esigelec.quiz.dao.hibernate.PersonneDAOImpl;
import fr.esigelec.quiz.dao.hibernate.QuizDAOImpl;
import fr.esigelec.quiz.dto.Personne;
import fr.esigelec.quiz.dto.Quiz;
import fr.esigelec.quiz.forms.ConnexionForm;
import fr.esigelec.quiz.util.SecurityHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ConnexionPersonneAction extends Action {
	
	private final Logger connexionPersonneActionLogger = Logger.getLogger(ConnexionPersonneAction.class);
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws SQLException {

		connexionPersonneActionLogger.debug("Execute");
		HttpSession session =request.getSession();
		ConnexionForm f= (ConnexionForm) form;
		String mail = f.getMail();
		String mdp = SecurityHelper.MD5(f.getPassword());
		
		IPersonneDAO personneDAO = new PersonneDAOImpl();
		IQuizDAO quizDAO = new QuizDAOImpl();
		List<Quiz> listeQuiz = quizDAO.listQuizAvecQuestions();
		
		
		
		Personne personne = personneDAO.getPersonne(mail);
		
		if( personne == null ) {
			connexionPersonneActionLogger.debug("Action terminee avec erreur : Utilisateur inexistant");
			return mapping.findForward("erreur");
		}
		else if(!mdp.equals(personne.getMdp())) {
			connexionPersonneActionLogger.debug("Action terminee avec erreur : mot de passe incorrect");
			return mapping.findForward("erreur");
		}
		else {
			request.setAttribute("listeQuiz", listeQuiz);
			session.setAttribute("personne", personne);
			connexionPersonneActionLogger.debug("Connexion réussie");
			return mapping.findForward("succes");
		}
	}
}
