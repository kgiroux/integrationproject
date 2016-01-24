/**
 * @author TIONO KEVIN & ENRIFATH TIDJANI
 */
package fr.esigelec.quiz.controleur;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import fr.esigelec.quiz.business.ActionService;
import fr.esigelec.quiz.dao.IQuizDAO;
import fr.esigelec.quiz.dao.hibernate.QuizDAOImpl;
import fr.esigelec.quiz.dto.Personne;
import fr.esigelec.quiz.dto.Proposition;
import fr.esigelec.quiz.dto.Question;
import fr.esigelec.quiz.dto.Quiz;

/**
 * Action declenchee quand lanimateur clique sur le bouton pour afficher les %
 * fait passer l'etape de la question de 1 à 2
 * 
 * @author minconghuang - serais
 * 
 */
public class StatsAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// recuperation session
		HttpSession session = request.getSession();
		// creation DAO
		IQuizDAO quizDAO = new QuizDAOImpl();

		// recuperation personne connectee dans session
		Personne personne = (Personne) session.getAttribute("personne");

		// un peu de securité quand même ;)
		if (personne.getDroits() == Personne.ADMIN) {

			// recuperation quiz courant
			Quiz quiz = (Quiz) session.getAttribute("quiz");
			// recuperation question courante
			Question question = (Question) session.getAttribute("question");

			// recuperation des % associés au choix des juoeurs
			List<Proposition> pourcentage = ActionService
					.getPourcentagePropositions(quiz, question);
			int idBonneReponse = 0;

			// recuperatoin de la bonne reponse
			for (Proposition proposition : question.getPropositions()) {
				if (proposition.isEstBonneReponse()) {
					idBonneReponse = proposition.getId();
				}
			}

			// la question courante passe donc à l'etape 2
			quiz.setEtape(2);
			// mise à jour du quiz dans la BDD
			quizDAO.updateQuiz(quiz);

			// inutile ?
			// Quiz q = quizDAO.getQuizAvecQuestions(quiz.getId());

			// SORTIE
			// on envoie a la vue suivante le quiz, idBonneReponse, et les %
			session.setAttribute("quiz", quiz);
			session.setAttribute("idBonneReponse", idBonneReponse);
			// inutile ?
			// session.setAttribute("classement", classement);
			session.setAttribute("pourcentage", pourcentage);
		}
		return mapping.findForward("succes");
	}
}
