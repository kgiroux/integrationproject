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
 * 
 * (last modified by mincong)
 * 
 * @author minconghuang
 *
 */
public class JouerAdminAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Question question=null; // question courante

		// recuperation DAO
		IQuizDAO quizDAO = new QuizDAOImpl();
		// recuperatoin session
		HttpSession session = request.getSession();

		// ENTREE
		// recuperation personne connectee dans session
		Personne personne = (Personne) session.getAttribute("personne");

		// si la personne est bien admin on lance le quiz
		if (personne.getDroits() == Personne.ADMIN) {

			// ENTREEs
			// recuperation du quiz qui va etre lancé
			int idQuiz = Integer.parseInt(request.getParameter("idQuiz"));
			System.out.println("lancement du quiz " + idQuiz);
			Quiz quiz = quizDAO.getQuizAvecQuestions(idQuiz);

			// TRAITEMENTS
			// la date de debut du quiz devient la date courante
			quiz.setDateDebutQuiz(new Timestamp(System.currentTimeMillis()));
			// la date de debut de la question courante (premiere question du
			// quiz) devient la date courante
			quiz.setDateDebutQuestion(new Timestamp(System.currentTimeMillis()));
			// l'etape de la question est fixée à 1 : les joueurs peuvent voter
			quiz.setEtape(3);
			// la question courante est la question 0 (les no commencent à 0)
			quiz.setNoQuestionCourante(-1);
			// mise à jour du quiz en BDD
			quizDAO.updateQuiz(quiz);

			
			//on previent les client qu'un quiz vient d'etre lancé
			WebSocket.prevenirQueQuizLance();
			
			
			// recuperation question courante sauf si on n'a pas encore decelncher la partie
			if(quiz.getNoQuestionCourante()>-1)
				question = ActionService.getQuestionByQuizId(idQuiz);

			
			
			
			

			// SORTIE
			session.setAttribute("quiz", quiz);
			
			//rappel question vaut null si on a pas encore commencé
			if(question==null)session.removeAttribute("question");
			else session.setAttribute("question",question);
			
			
			
			
			// on envoie le no de la question courante (-1 car c'est avant la premiere)
			session.setAttribute("compteur", -1);
			// envoi a la vue de la date courante pour affichage du compteur
			request.setAttribute("currentTimestamp", System.currentTimeMillis());
		}
		return mapping.findForward("succes");
	}
}
