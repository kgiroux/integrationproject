/**
 * @author Rodolphe AGUIDISSOU - Mincong HUANG ESIGELEC 2016
 */
package fr.esigelec.quiz.controleur;

import java.util.ArrayList;
import java.util.Hashtable;
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
 * Action qui permet l'affichage de la question courante au joueur la question
 * s'affiche automatiquement en fonction de son etape
 * 
 * @author serais sebastien
 *
 */
public class VueQuestionAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// recuperation session
		HttpSession session = request.getSession();
		// creation dao
		IQuizDAO quizDAO = new QuizDAOImpl();

		// ENTREE
		// recuperation du quiz courant
		Quiz quiz = (Quiz) session.getAttribute("quiz");
		// recuperation question courante
		Question question = ActionService.getQuestionByQuizId(quiz.getId());

		int idBonneReponse = 0;
		// liste des propositions d la question courante
		Set<Proposition> listProposition = question.getPropositions();

		// on rafraichit le quiz a partir de la BDD (pour recuperer l'etape
		// principalement)
		quiz = quizDAO.getQuizAvecQuestions(quiz.getId());

		// recherche de la proposition bonne reponse
		for (Proposition proposition : listProposition) {
			if (proposition.isEstBonneReponse()) {
				idBonneReponse = proposition.getId();
			}
		}

		// on ne calcule les % et le classement que quand c'est necessaire
		if (quiz.getEtape() == 3) {
			// recuperation du classement en etape 3 uniquement
			List<Personne> classement = ActionService.getClassement(quiz);
			session.setAttribute("classement", classement);
		}
		if (quiz.getEtape() >= 2) {
			// recuperation des % en etape 2 et 3 uniquement
			List<Proposition> pourcentage = ActionService
					.getPourcentagePropositions(quiz, question);
			session.setAttribute("pourcentage", pourcentage);
		}

		// SORTIE
		// on envoie le quiz, la question, id de la BonneReponse et l'heure
		// courante pour l'affichage du compteur
		session.setAttribute("quiz", quiz);
		session.setAttribute("question", question);
		session.setAttribute("idBonneReponse", idBonneReponse);
		request.setAttribute("currentTimestamp", System.currentTimeMillis());

		return mapping.findForward("succes");

	}

}
