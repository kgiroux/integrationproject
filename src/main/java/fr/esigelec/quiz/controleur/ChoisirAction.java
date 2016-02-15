/**
 * @author Rodolphe AGUIDISSOU - ESIGELEC 2016
 * @author Mincong HUANG
 */

package fr.esigelec.quiz.controleur;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import fr.esigelec.quiz.business.ActionService;
import fr.esigelec.quiz.dao.IChoisirDAO;
import fr.esigelec.quiz.dao.hibernate.ChoisirDAOImpl;
import fr.esigelec.quiz.dto.Choisir;
import fr.esigelec.quiz.dto.Personne;
import fr.esigelec.quiz.dto.Proposition;
import fr.esigelec.quiz.dto.Question;
import fr.esigelec.quiz.dto.Quiz;

/**
 * l'Action Choisir correspon au clic du joueur sur une proposition
 * 
 */
public class ChoisirAction extends Action {

	private final Logger choisirActionLogger = Logger
			.getLogger(ChoisirAction.class);

	@Override
	public synchronized ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		choisirActionLogger.debug("Execute");
		// ENTREE
		// recuperation session
		HttpSession session = request.getSession();
		// recuperation personne en session
		Personne personne = (Personne) session.getAttribute("personne");
		// recuperation de l'id de la proposition qui a eteselectionnee
		int idProposition = Integer.parseInt(request
				.getParameter("idProposition"));
		// recuperation quiz
		Quiz quiz = (Quiz) session.getAttribute("quiz");
		// DAO choisir
		IChoisirDAO choisirDAO = new ChoisirDAOImpl();

		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		Timestamp questionStartTime = quiz.getDateDebutQuestion();

		Calendar cal = Calendar.getInstance();
		cal.setTime(questionStartTime);
		cal.add(Calendar.SECOND, 30);

		// si on vote dans le temps imparti
		if (currentTime.before(cal.getTime())) {

			choisirActionLogger.debug("vote dans les 30s");

			// creation d'un objet proposition correspondant au choix du joueur
			Proposition proposition = new Proposition();
			proposition.setId(idProposition);

			// on recupere la question courante
			Question question = ActionService.getQuestionByQuizId(quiz.getId());

			// liste des choix dejï¿½ faits pour cette question et ce joueur
			List<Choisir> listeChoix = choisirDAO
					.getChoixPersonneParQuizPersonneEtQuestion(personne, quiz,
							question);

			// si la proposition cliquée fait bien partie de la liste des
			// propositions de la question courante alors on peut valider
			if (!question.getPropositions().contains(proposition)) {
				choisirActionLogger
						.debug("Choix d'une proposition ne faisant pas partie des propositions de la question courante");
				System.out.println(question.getPropositions());
				System.out.println(proposition);
			} else {

				// si la personne avait deja choisi , on remplace son choix par
				// le
				// nouveau
				if (listeChoix.size() > 0) {
					choisirActionLogger
					.debug("mise à jour du choix");
					Choisir choisir = new Choisir(listeChoix.get(0).getId(),
							new Timestamp(System.currentTimeMillis()),
							proposition, quiz, personne);

					choisirDAO.updateChoix(choisir);
				}
				// sinon on cree le nouveau choix et on l'ajoute
				else {
					choisirActionLogger
					.debug("ajout choix");
					Choisir choisir = new Choisir(new Timestamp(
							System.currentTimeMillis()), proposition, quiz,
							personne);
					// on l'ajoute
					choisirDAO.createChoix(choisir);
				}
				choisirActionLogger.debug("Action terminee avec succes");
			}
			// envoi de l'id pour la vue qui mettra en avant le choix
			// selectionné
			session.setAttribute("idProposition", idProposition);
			// on envoie la date actuelle
			request.setAttribute("currentTimestamp", System.currentTimeMillis());
			return mapping.findForward("succes");
		} else {
			// on envoie la date actuelle
			request.setAttribute("currentTimestamp", System.currentTimeMillis());
			// le temps est depassï¿½
			choisirActionLogger.debug("vote apres les 30s");
			// session.setAttribute("idProposition", -1);
			return mapping.findForward("time-out");
		}
	}
}
