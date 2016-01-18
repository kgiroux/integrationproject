/**
 * @author TIONO KEVIN & ENRIFATH TIDJANI
 */
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
import fr.esigelec.quiz.dto.Proposition;
import fr.esigelec.quiz.dto.Question;
import fr.esigelec.quiz.dto.Quiz;

/**
 * 
 * @author minconghuang
 * @deprecated
 */
public class StatsAction extends Action{
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
		QuizDAOImpl quizdaoimpl = new QuizDAOImpl();
		
		Quiz quiz = (Quiz)session.getAttribute("quiz");
		ActionService actionService = new ActionService();
		
		//OUT 
		quiz.setEtape(2);
		boolean statut = quizdaoimpl.updateQuiz(quiz);
		Quiz q = quizdaoimpl.getQuiz(quiz.getId());
		Question questioncur=(Question) session.getAttribute("questioncur");
		List<Proposition> listpro=actionService.getPourcentagePropositions(q, questioncur);
		session.setAttribute("listpro", listpro);
		session.setAttribute("quiz", q);
		
		return mapping.findForward("succes");
		
		
	}
	

}
