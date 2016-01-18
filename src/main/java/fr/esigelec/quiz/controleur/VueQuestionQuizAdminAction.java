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

public class VueQuestionQuizAdminAction extends Action {
	
	private static final Logger vueQuestionQuizAdminAction = Logger.getLogger(VueQuestionQuizAdminAction.class);
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		try {
			// Get parameters and sessions
			Personne p = (Personne) request.getSession().getAttribute("personne");
			int idQuestion = Integer.parseInt(request.getParameter("idQuestion"));
			
			if (p.getDroits() == Personne.ADMIN) {
				IQuestionDAO questionDAO = new QuestionDAOImpl();
				request.setAttribute("listeQuestions", questionDAO.listQuestion());
				return mapping.findForward("succes");	/* Need to map to quizAdmin.jsp */
			} else {
				vueQuestionQuizAdminAction.info("erreur: droit d'admin requis");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			// Add attributes of error message
			vueQuestionQuizAdminAction.info("erreur: "+e);
		}
	}
}