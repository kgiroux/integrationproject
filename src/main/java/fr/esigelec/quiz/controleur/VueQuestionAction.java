/**
 * @author Rodolphe AGUIDISSOU - ESIGELEC 2016
 */
package fr.esigelec.quiz.controleur;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import fr.esigelec.quiz.dto.Quiz;

public class VueQuestionAction extends Action {
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		//TODO : Action Question View  implementation 
		HttpSession session = request.getSession();
		
		Quiz quiz = (Quiz) session.getAttribute("quiz");
		Personne Personne = (Personne) session.getAttribute("personne");
		int idProposition =  Integer.parseInt((String) session.getAttribute("idPoposition"));
		hashtablePourcentage	Hashtable<Integer,Float>
		idBonneReponse	int
		classement	List<Score>

		
		
			
		return null;
		
	}

}
