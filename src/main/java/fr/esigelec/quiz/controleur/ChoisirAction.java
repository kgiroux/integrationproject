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


public class ChoisirAction extends Action {

	private final Logger choisirActionLogger = Logger.getLogger(ChoisirAction.class);
/*	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
	
		// UTILS
		choisirActionLogger.debug("Execute");
		HttpSession session = request.getSession();
	    IChoisirDAO choisirDAO = new ChoisirDAOImpl();
		
		// init des variables
		int idProposition = Integer.parseInt(request.getParameter("idProposition"));
		Personne personne = (Personne) session.getAttribute("personne");
	    Quiz quiz  = (Quiz) session.getAttribute("quiz");
	    Question question = ActionService.getQuestionByQuizId(quiz.getId());
	    Choisir ancienChoix = choisirDAO.getChoix(personne, quiz, question);
	    boolean dejaChoisi = false;
	    if (ancienChoix != null) {
	    	dejaChoisi = true;
	    }
	    
	    // calcule le temps pour la reponse
	    long now = System.currentTimeMillis();
		long debut = quiz.getDateDebutQuestion().getTime();
        long duree = now - debut;
	    
        // TIME_OUT
		if (duree > 30 * 1000L) {
			
			session.setAttribute("idProposition", -1);
			choisirActionLogger.debug("Time out, duree = " + duree + "ms.");
			return mapping.findForward("time-out");
				
		} else {

		    Proposition proposition = new Proposition();
		    proposition.setId(idProposition);
			Choisir nvChoix = new Choisir(new Timestamp(now), proposition, quiz, personne);
			
		    // DEJA CHOISI
			if (dejaChoisi) {
				// log
				String msg = "Choix existes deja pour (quiz, propo, personne) = ("
						+ quiz.getId() + ", " + proposition.getId() + ", "
						+ personne.getId() +  ").";
				choisirActionLogger.info(msg);
				// nouveau choix possede la meme id que l'ancien choix
				nvChoix.setId(ancienChoix.getId());
		    	choisirDAO.updateChoix(nvChoix);
		    
		    // NEW
			} else {
				// log
		    	String msg = "Insertion pour le choix (quiz, propo, personne) = ("
						+ quiz.getId() + ", " + proposition.getId() + ", "
						+ personne.getId() +  ").";
				choisirActionLogger.info(msg);
		    	// Le resultat de l'insertion est enregistre dans la vairable
		    	// dejaChoisi pour le traitement plus tard
		    	dejaChoisi = choisirDAO.createChoix(nvChoix);
				session.setAttribute("idProposition", idProposition);
		    }
		}
		choisirActionLogger.debug("Action terminee avec succes");
		return mapping.findForward("succes");
	}
*/
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		choisirActionLogger.debug("Execute");

		// recuperation session
		HttpSession session = request.getSession();
		// recuperation personne en session
		Personne personne = (Personne) session.getAttribute("personne");
		// recuperation de l'id de la proposition qui a �t� selectionn�e
		int idProposition = Integer.parseInt(request
				.getParameter("idProposition"));
		// recuperation quiz
		Quiz quiz = (Quiz) session.getAttribute("quiz");

		Timestamp currentTime = new Timestamp(System.currentTimeMillis());

		Timestamp questionStartTime = quiz.getDateDebutQuestion();

		Calendar cal = Calendar.getInstance();
		cal.setTime(questionStartTime);
		cal.add(Calendar.SECOND, 30);
		
		//si on vote dans le temps imparti
		//ATTENTION : controle du temps non activ� pour le moment pour faciliter le debugage
		if (currentTime.before(cal.getTime())) {

			choisirActionLogger.debug("vote dans les 30s");

			// creation d'un objet proposition
			Proposition proposition = new Proposition();
			proposition.setId(idProposition);

			// on recupere la question courante
			Question question = ActionService.getQuestionByQuizId(quiz.getId());

			IChoisirDAO choisirDAO = new ChoisirDAOImpl();
			// liste des choix dej� faits pour cette question et ce joueur
			List<Choisir> listeChoix = choisirDAO
					.getChoixPersonneParQuizPersonneEtQuestion(personne, quiz,
							question);

			// on cr�e le nouveau choix
			Choisir choisir = new Choisir(listeChoix.get(0).getId(),
					new Timestamp(System.currentTimeMillis()), proposition,
					quiz, personne);

			// si la personne avait d�j� choisi , on remplace son choix par le
			// nouveau
			if (listeChoix.size() > 0)
				choisirDAO.updateChoix(choisir);
			else
				// sinon on l'ajoute
				choisirDAO.createChoix(choisir);

			choisirActionLogger.debug("Action terminee avec succes");
			return mapping.findForward("succes");
		} else {
			//le temps est depass�
			choisirActionLogger.debug("vote apr�s les 30s");
			session.setAttribute("idProposition", -1);
			return mapping.findForward("time-out");
		}
	}
}
