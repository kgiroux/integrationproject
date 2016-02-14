package fr.esigelec.quiz.controleur;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import fr.esigelec.quiz.business.ActionService;
import fr.esigelec.quiz.dao.hibernate.QuizDAOImpl;
import fr.esigelec.quiz.dto.Personne;
import fr.esigelec.quiz.dto.Quiz;

public class ReponseAction extends Action{
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
		QuizDAOImpl quizdaoimpl = new QuizDAOImpl();
		
		Quiz quiz = (Quiz)session.getAttribute("quiz");
		
		// recuperation du classement en etape 3 uniquement
		List<Personne> classement = ActionService.getClassement(quiz);
				
		//OUT 
		quiz.setEtape(3);
		quizdaoimpl.updateQuiz(quiz);
		
		
		//on force le refresh de tous les clients connectes via les websockets
		WebSocket.rafraichirTousLesClients();
		
		Quiz q = quizdaoimpl.getQuizAvecQuestions(quiz.getId());
		session.setAttribute("quiz", q);
		session.setAttribute("classement", classement);
		session.setAttribute("listpro", ActionService.getPourcentagePropositions(quiz, ActionService.getQuestionByQuizId(q.getId())));
		return mapping.findForward("succes");
		
		
	}
	

}