package fr.esigelec.quiz.controleur;

import java.sql.Timestamp;
import javax.servlet.http.*;
import org.apache.struts.action.*;
import fr.esigelec.quiz.business.ActionService;
import fr.esigelec.quiz.dao.hibernate.QuizDAOImpl;
import fr.esigelec.quiz.dto.*;

/**
 * action qui correspond au passage à la question suivante de la part de l'animateur
 * si on est dejà à la dernier question on revient sur cette derniere
 * @author sebastien serais
 *
 */
public class CompteurAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
				//ENTREE
				//recuperation de la session
				HttpSession session = request.getSession();
				//noQuestionCourante correspond au no de la question courante (qui commence à zero).
				int noQuestionCourante = Integer.parseInt(request.getParameter("compteur"));
				
				QuizDAOImpl quizdaoimpl = new QuizDAOImpl();
				//recuperation du quiz en session
				Quiz quiz = (Quiz)session.getAttribute("quiz");
				Question question;
				//si on est pas à la derniere question on peut passer a la question suivante.
				if(noQuestionCourante<quiz.getListeQuestions().size()-1)
					noQuestionCourante++;
				
				
				//TRAITEMENTS
				Timestamp currentTime = new Timestamp(System.currentTimeMillis());
				//on remet la date de debut question à la date courante (vu que c'est une nouvelle question affichée)
				quiz.setDateDebutQuestion(currentTime);
				//on met à jour le noquestioncourante dans le quiz								
				quiz.setNoQuestionCourante(noQuestionCourante);
				//on remet l'etape de la question à 1 (opur que les joueurs puissent repondre)
				quiz.setEtape(1);
				//mise à jour dans la BDD
				quizdaoimpl.updateQuiz(quiz);
				
				
				
				//SORTIE
				//on met le noquestioncourante en session
				session.setAttribute("compteur", noQuestionCourante);
				//on met le quiz en session
				Quiz q = quizdaoimpl.getQuizAvecQuestions(quiz.getId());
				session.setAttribute("quiz", q);
				//on met la question courante en session
				question = ActionService.getQuestionByQuizId(quiz.getId());
				session.setAttribute("question", question);
				//on envoie la date de debut de la question à la vue (date du serveur bien sur ;))
				request.setAttribute("currentTimestamp", System.currentTimeMillis());
				
				
		return mapping.findForward("succes");
		
	}

}
