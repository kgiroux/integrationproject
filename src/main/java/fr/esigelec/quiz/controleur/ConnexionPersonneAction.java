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

/***
 * Action permettant la connexion d'un joueur ou de l'animateur
 *
 *
 */
public class ConnexionPersonneAction extends Action {

	private final Logger connexionPersonneActionLogger = Logger
			.getLogger(ConnexionPersonneAction.class);

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws SQLException {

		connexionPersonneActionLogger.debug("Execute");
		// recuperation session
		HttpSession session = request.getSession();
		// on recupere les données du formulaire de connexion
		ConnexionForm f = (ConnexionForm) form;
		String mail = f.getMail();
		// recuperation du mot de passe puis encodage en MD5
		String mdp = SecurityHelper.MD5(f.getPassword());

		IPersonneDAO personneDAO = new PersonneDAOImpl();
		IQuizDAO quizDAO = new QuizDAOImpl();
		// recuperation de la liste des quiz avec les questions
		List<Quiz> listeQuiz = quizDAO.listQuizAvecQuestions();

		// on recupere la personne correspondant au mail dans la BDD
		Personne personne = personneDAO.getPersonne(mail);
		// si la personne n'existe pas on revient sur le formulaire
		if (personne == null) {
			connexionPersonneActionLogger
					.debug("Action terminee avec erreur : Utilisateur inexistant");
			return mapping.findForward("erreur");
		}
		// si la personne existe mais mauvais mot de passe
		else if (!mdp.equals(personne.getMdp())) {
			connexionPersonneActionLogger
					.debug("Action terminee avec erreur : mot de passe incorrect");
			return mapping.findForward("erreur");
		}
		// si bon mot de passe et droit admin
		else if (personne.getDroits() == Personne.ADMIN) {
			// envoi de la liste des quiz à la page suivante
			request.setAttribute("listeQuiz", listeQuiz);
			// ajout de la personne en session
			session.setAttribute("personne", personne);
			connexionPersonneActionLogger.debug("Connexion rï¿½ussie");
			return mapping.findForward("succes-admin");
		}
		// sinon on est connecté joueur
		else {
			// envoi de la liste des quiz à la page suivante
			request.setAttribute("listeQuiz", listeQuiz);
			// ajout de la personne en session
			session.setAttribute("personne", personne);
			connexionPersonneActionLogger.debug("Connexion reussie");
			return mapping.findForward("succes");
		}
	}
}
