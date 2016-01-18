package fr.esigelec.quiz.controleur.android;

import java.sql.Timestamp;

/**
 * @author Kévin Giroux;
 * @edited by Kevin PACE;
 * 
 */


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.JSONObject;

import fr.esigelec.quiz.dao.IChoisirDAO;
import fr.esigelec.quiz.dao.IPersonneDAO;
import fr.esigelec.quiz.dao.IPropositionDAO;
import fr.esigelec.quiz.dao.IQuizDAO;
import fr.esigelec.quiz.dao.hibernate.ChoisirDAOImpl;
import fr.esigelec.quiz.dao.hibernate.PersonneDAOImpl;
import fr.esigelec.quiz.dao.hibernate.PropositionDAOImpl;
import fr.esigelec.quiz.dao.hibernate.QuizDAOImpl;
import fr.esigelec.quiz.dto.Choisir;
import fr.esigelec.quiz.dto.Personne;
import fr.esigelec.quiz.dto.Proposition;
import fr.esigelec.quiz.dto.Quiz;
import fr.esigelec.quiz.util.AndroidHelper;

public class AndroidChoisirAction extends Action{
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
			if("POST".equals(request.getMethod()))
			{
				//DAO
				IPropositionDAO propositionDAO = new PropositionDAOImpl();
				IQuizDAO quizDAO = new QuizDAOImpl();
				IPersonneDAO personneDAO = new PersonneDAOImpl();
				IChoisirDAO choisirDAO = new ChoisirDAOImpl();
				
				//Retrive proposition of the player
				/*int idProposition = Integer.parseInt(request.getParameter("idProposition"));
				int idPersonne = Integer.parseInt(request.getParameter("idPersonne"));
				int idQuiz = Integer.parseInt(request.getParameter("idQuiz"));
				Proposition prop = propositionDAO.getProposition(idProposition);
				Quiz quiz = quizDAO.getQuiz(idQuiz);
				Personne pers = personneDAO.getPersonne(idPersonne);
				*/
				Proposition prop = new Proposition();
				prop.setId(3);
				Quiz quiz = new Quiz();
				quiz.setId(1);
				Personne pers = new Personne();
				pers.setId(5);
				//Save proposition to the database
				Choisir choix = new Choisir(new Timestamp(System.currentTimeMillis()),prop,quiz,pers);
				JSONObject json = new JSONObject();
				if(choisirDAO.createChoix(choix))
				{
					json = AndroidHelper.ChoiceSaveSuccess();	
				}else
				{
					json = AndroidHelper.DatabaseInsertFail();					
				}
				
				//Return error code to client
				request.setAttribute("json", json.toString());
				return mapping.findForward("succes");
			} else {
				JSONObject json = AndroidHelper.DoGetForbiddenException();
				request.setAttribute("json", json.toString());
				return mapping.findForward("error");
			}
	}

}
