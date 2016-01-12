package fr.esigelec.quiz.controleur;

import java.util.List;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import fr.esigelec.quiz.dao.IPersonneDAO;
import fr.esigelec.quiz.dao.IQuizDAO;
import fr.esigelec.quiz.dao.hibernate.PersonneDAOImpl;
import fr.esigelec.quiz.dao.hibernate.QuizDAOImpl;
import fr.esigelec.quiz.dto.Personne;
import fr.esigelec.quiz.dto.Quiz;
import fr.esigelec.quiz.util.SecurityHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConnexionPersonneAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String mail = request.getParameter("mail");
		String mdp = SecurityHelper.MD5(request.getParameter("mdp"));
		
		//FAIRE TRANSFORMATION EN MD5
		
		//CHECK MAIL/MDP
		
		IPersonneDAO personneDAO = new PersonneDAOImpl();
		IQuizDAO quizDAO = new QuizDAOImpl();
		List<Quiz> listeQuiz = quizDAO.listQuiz();
		Personne personne = personneDAO.getPersonne(mail);
		
		if( personne != null 
			&& mail.equals(personne.getMail()) 
			&& mdp.equals(personne.getMdp())) {
			
			
			request.setAttribute("listeQuiz", listeQuiz);
			request.setAttribute("personne", personne);
			return mapping.findForward("succes");
		}
		else {
			return mapping.findForward("erreur");
		}
	}
}
