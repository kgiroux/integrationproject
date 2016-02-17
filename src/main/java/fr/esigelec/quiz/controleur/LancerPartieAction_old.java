package fr.esigelec.quiz.controleur;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import fr.esigelec.quiz.business.ActionService;
import fr.esigelec.quiz.dao.IChoisirDAO;
import fr.esigelec.quiz.dao.IQuizDAO;
import fr.esigelec.quiz.dao.hibernate.ChoisirDAOImpl;
import fr.esigelec.quiz.dao.hibernate.QuizDAOImpl;
import fr.esigelec.quiz.dto.Personne;
import fr.esigelec.quiz.dto.Question;
import fr.esigelec.quiz.dto.Quiz;

/**
 * cette action n'est plus utilisée ???
 * 
 * @author Damien BELLENGER et Rodolphe AGUIDISSOU
 *
 */
@Deprecated
public class LancerPartieAction_old extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// ENTREE
		// recuperation de la session
		HttpSession session = request.getSession();
		// recuperation personne connectee dans session
		Personne personne = (Personne) session.getAttribute("personne");
		// recuperation id du quiz qui vient doit etre lancé
		int idQuiz = Integer.parseInt(request.getParameter("idQuiz"));

		// si la personne est bien admin on lance le quiz
		if (personne.getDroits() == Personne.ADMIN) {
			// recup date courante
			Timestamp currentTime = new Timestamp(System.currentTimeMillis());

			IQuizDAO quizDAO = new QuizDAOImpl();
			// recuperation du quiz dans la BDD
			Quiz quiz = quizDAO.getQuiz(idQuiz);
			// la date de debut du quiz devient la date courante
			quiz.setDateDebutQuiz(currentTime);
			// la date de debut de la question devient la date courante (c'est
			// la premiere question du quiz)
			quiz.setDateDebutQuestion(currentTime);
			// on fixe la question courante à 0 (premiere question du quiz)
			quiz.setNoQuestionCourante(-1);

			// letape est 1 (etape où les joueurs peuvent repondre)
			quiz.setEtape(1);
			
			//on efface tous les anciens votes associés à ce quiz
			IChoisirDAO choixDAO=new ChoisirDAOImpl();
			choixDAO.deleteChoix(quiz);
			
			// mise à jour du quiz dans la BDD
			quizDAO.updateQuiz(quiz);

			// recuperation de la question courante
			Question question = ActionService.getQuestionByQuizId(idQuiz);

			// SORTIE
			// on envoi a la vue le quiz et la question courante (dans la
			// session)
			session.setAttribute("quiz", quiz);
			session.setAttribute("question", question);
		}

		return mapping.findForward("succes");

	}

}
