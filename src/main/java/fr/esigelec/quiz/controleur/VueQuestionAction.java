/**
 * @author Rodolphe AGUIDISSOU - Mincong HUANG ESIGELEC 2016
 */
package fr.esigelec.quiz.controleur;


import java.util.ArrayList;
import java.util.Hashtable;
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

public class VueQuestionAction extends Action {
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		
		//IN
		//Question question = (Question) session.getAttribute("question");
		Quiz quiz = (Quiz) session.getAttribute("quiz");
		
		Question question = ActionService.getQuestionByQuizId(quiz.getId());
		
		int idBonneReponse = 0;
		Set<Proposition> listProposition = question.getPropositions();
		
		
		IQuizDAO quizDAO=new QuizDAOImpl();
		//on rafraichit le quiz � partir de la BDD
		quiz=quizDAO.getQuizAvecQuestions(quiz.getId());
		
		
		
		for(Proposition proposition : listProposition){
			if(proposition.isEstBonneReponse()){
				idBonneReponse = proposition.getId();
			}
		}
		
		//on ne calcule les % et le classement que quand c'est necessaire
		if(quiz.getEtape()==3){
		List<Personne> classement = ActionService.getClassement(quiz);
		session.setAttribute("classement", classement);
		}
		if(quiz.getEtape()>=2){
		List<Proposition> pourcentage = ActionService.getPourcentagePropositions(quiz, question);
		session.setAttribute("pourcentage", pourcentage);
		}
		
		
		
		
		//OUT
		session.setAttribute("quiz", quiz);
		session.setAttribute("question", question);
		session.setAttribute("idBonneReponse", idBonneReponse);
		request.setAttribute("currentTimestamp", System.currentTimeMillis());
		
		
		
		return mapping.findForward("succes");
		
	}

}
