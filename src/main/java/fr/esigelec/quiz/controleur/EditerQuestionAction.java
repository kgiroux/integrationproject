package fr.esigelec.quiz.controleur;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import fr.esigelec.quiz.dao.IQuestionDAO;
import fr.esigelec.quiz.dao.IQuizDAO;
import fr.esigelec.quiz.dao.hibernate.QuestionDAOImpl;
import fr.esigelec.quiz.dao.hibernate.QuizDAOImpl;
import fr.esigelec.quiz.dto.Personne;
import fr.esigelec.quiz.dto.Question;
/**
 * 
 * @author Vincent Marion & Damien Bellenger
 *
 */

public class EditerQuestionAction extends Action {
	private static final Logger editerQuestionActionLogger = Logger.getLogger(EditerQuestionAction.class);
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		editerQuestionActionLogger.debug("Execute");
		
		try {
			// Get parameters and sessions
			Personne p = (Personne) request.getSession().getAttribute("personne");
			Question question = (Question) request.getParameter("Question");
			String libelle = request.getParameter("libelle");
			
			if (p.getDroits() == Personne.ADMIN) {
				if(!libelle.equals("")){
					IQuestionDAO questionDAO = new QuestionDAOImpl();
					question.setLibelle(libelle);
					questionDAO.updateQuestion(question);
					request.setAttribute("listeQuestions", questionDAO.listQuestion());
					editerQuestionActionLogger.debug("Action terminee avec succes : question editee");
					return mapping.findForward("succes"); // questionsQuizzAdmin.jsp 
				}
				else editerQuestionActionLogger.debug("erreur: libelle vide");
			} else editerQuestionActionLogger.debug("erreur: droit d'admin requis");
			
			
		} catch (Exception e) {
			e.printStackTrace();
			// Add attributes of error message
			editerQuestionActionLogger.debug("erreur: "+e.getMessage());
		}
		editerQuestionActionLogger.debug("Action terminee avec succes : question editee");
		return mapping.findForward("succes");
	}
}
