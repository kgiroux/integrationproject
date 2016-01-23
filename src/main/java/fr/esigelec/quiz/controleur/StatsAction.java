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
 * 
 * @author minconghuang
 * 
 */
public class StatsAction extends Action{
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		// init
		HttpSession session = request.getSession();
		IQuizDAO quizDAO = new QuizDAOImpl();
		ActionService service = new ActionService();
		
		// get params
		Quiz quiz = (Quiz) session.getAttribute("quiz");
		Question question = (Question) session.getAttribute("question");
		List<Personne> classement = ActionService.getClassement(quiz);
		@SuppressWarnings("static-access")
		List<Proposition> pourcentage = service.getPourcentagePropositions(quiz, question);
		int idBonneReponse = 0;
		
		// compute
		for(Proposition proposition : question.getPropositions()){
			if(proposition.isEstBonneReponse()){
				idBonneReponse = proposition.getId();
			}
		}
		quiz.setEtape(2);
		boolean statut = quizDAO.updateQuiz(quiz);
		Quiz q = quizDAO.getQuizAvecQuestions(quiz.getId());
		
		// send back
		session.setAttribute("quiz", q);
		session.setAttribute("idBonneReponse", idBonneReponse);
		session.setAttribute("classement", classement);
		session.setAttribute("pourcentage", pourcentage);
		return mapping.findForward("succes");
	}
}
