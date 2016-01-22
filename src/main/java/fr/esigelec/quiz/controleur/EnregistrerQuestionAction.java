package fr.esigelec.quiz.controleur;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import fr.esigelec.quiz.dao.IPropositionDAO;
import fr.esigelec.quiz.dao.IQuestionDAO;
import fr.esigelec.quiz.dao.IQuizDAO;
import fr.esigelec.quiz.dao.hibernate.PropositionDAOImpl;
import fr.esigelec.quiz.dao.hibernate.QuestionDAOImpl;
import fr.esigelec.quiz.dao.hibernate.QuizDAOImpl;
import fr.esigelec.quiz.dto.Personne;
import fr.esigelec.quiz.dto.Proposition;
import fr.esigelec.quiz.dto.Question;
import fr.esigelec.quiz.dto.Quiz;

/**
 * 
 * @author Vincent Marion & Damien Bellenger
 *
 */

public class EnregistrerQuestionAction extends Action {
	
	private static final Logger enregistrerQuestionActionLogger = Logger.getLogger(EnregistrerQuestionAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		enregistrerQuestionActionLogger.debug("Execute");
		
		String libelleQuestion = request.getParameter("libelleQuestion");
		String bonneReponse = request.getParameter("bonneReponse");
		String p1, p2, p3, p4, p5, p6, p7, p8;
		Personne p = (Personne) request.getSession().getAttribute("personne");
		
		if (p.getDroits() == Personne.ADMIN) {
					
			IQuestionDAO questionDAO = new QuestionDAOImpl();
			IPropositionDAO propositionDAO = new PropositionDAOImpl();
			IQuizDAO quizDAO = new QuizDAOImpl(); 
			
			Question question = new Question(libelleQuestion);
			
			Set<Proposition> listeProposition = new HashSet<Proposition>();
			
			Proposition propositionBonneReponse = new Proposition();
			propositionBonneReponse.setLibelle(bonneReponse);
			propositionBonneReponse.setEstBonneReponse(true);
			
			listeProposition.add(propositionBonneReponse);
			
			if((p1 = request.getParameter("p1")) != null)
			{
				Proposition prop = new Proposition();
				prop.setLibelle(p1);
				listeProposition.add(prop);
			}
			if((p2 = request.getParameter("p2")) != null)
			{
				Proposition prop = new Proposition();
				prop.setLibelle(p2);
				listeProposition.add(prop);
			}
			if((p3 = request.getParameter("p3")) != null)
			{
				Proposition prop = new Proposition();
				prop.setLibelle(p3);
				listeProposition.add(prop);
			}
			if((p4 = request.getParameter("p4")) != null)
			{
				Proposition prop = new Proposition();
				prop.setLibelle(p4);
				listeProposition.add(prop);
			}
			if((p5 = request.getParameter("p5")) != null)
			{
				Proposition prop = new Proposition();
				prop.setLibelle(p5);
				listeProposition.add(prop);
			}
			if((p6 = request.getParameter("p6")) != null)
			{
				Proposition prop = new Proposition();
				prop.setLibelle(p6);
				listeProposition.add(prop);
			}
			if((p7 = request.getParameter("p7")) != null)
			{
				Proposition prop = new Proposition();
				prop.setLibelle(p7);
				listeProposition.add(prop);
			}
			if((p8 = request.getParameter("p8")) != null)
			{
				Proposition prop = new Proposition();
				prop.setLibelle(p8);
				listeProposition.add(prop);
			}
			
			question.setPropositions(listeProposition);
			questionDAO.createQuestion(question);
			
			request.setAttribute("listeQuestion", questionDAO.listQuestion());
		}

		enregistrerQuestionActionLogger.debug("Question enregistree");
		return mapping.findForward("succes");
	}
}
