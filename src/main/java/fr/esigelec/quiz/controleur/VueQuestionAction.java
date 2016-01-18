/**
 * @author Rodolphe AGUIDISSOU - ESIGELEC 2016
 */
package fr.esigelec.quiz.controleur;


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
		Question question = (Question) session.getAttribute("question");
		Quiz quiz = (Quiz) session.getAttribute("quiz");
		
		int idBonneReponse = 0;
		Set<Proposition> listProposition = question.getPropositions();
		
		for(Proposition proposition : listProposition){
			if(proposition.isEstBonneReponse()){
				idBonneReponse = proposition.getId();
			}
		}
		
		
		List<Personne> classement = ActionService.getClassement(quiz);
		
		List<Proposition> pourcentage = ActionService.getPourcentagePropositions(quiz, question);
		
		
		
		
		//OUT
		session.setAttribute("idBonneReponse", idBonneReponse);
		session.setAttribute("classement", classement);
		session.setAttribute("pourcentage", pourcentage);
		
		
		
		return mapping.findForward("succes");
		
	}

}
