/**
 * @author Rodolphe AGUIDISSOU - ESIGELEC 2016
 */

package fr.esigelec.quiz.controleur;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.List;

import fr.esigelec.quiz.business.ActionService;
import fr.esigelec.quiz.dao.IChoisirDAO;
import fr.esigelec.quiz.dao.hibernate.ChoisirDAOImpl;
import fr.esigelec.quiz.dto.Choisir;
import fr.esigelec.quiz.dto.Personne;
import fr.esigelec.quiz.dto.Proposition;
import fr.esigelec.quiz.dto.Question;
import fr.esigelec.quiz.dto.Quiz;


public class ChoisirAction extends Action {

	private final Logger choisirActionLogger = Logger.getLogger(ChoisirAction.class);
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		choisirActionLogger.debug("Execute");
		
		
		//UTILS
		HttpSession session = request.getSession();
		
		Personne personne=(Personne)session.getAttribute("personne");
		//IN 
		int idProposition = Integer.parseInt(request.getParameter("idProposition"));
	    Quiz quiz  = (Quiz) session.getAttribute("quiz");
	    Proposition proposition = new Proposition();
	    proposition.setId(idProposition);
	    boolean dejaChoisi=false;
	    
	    Question question=ActionService.getQuestionByQuizId(quiz.getId());
	    List<Proposition> propositions=question.getListePropositions();
	    IChoisirDAO choisirDAO = new ChoisirDAOImpl() ;
	    List<Choisir> listeChoix=choisirDAO.getChoixPersonneParQuiz(personne,quiz);
	    
	    
	    //recherche si on a deja voté pour cette question
	    for(Proposition p:propositions)
	    	if(p.getId()==idProposition)
	    	{
	    		dejaChoisi=true;
	    		break;
	    	}
	    
	    
		Choisir choisir=new Choisir(new Timestamp(System.currentTimeMillis()),proposition,quiz,personne);
	    
	  
	  
	    
	    
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		
        Timestamp questionStartTime = quiz.getDateDebutQuestion() ;
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(questionStartTime);
        cal.add(Calendar.SECOND, 30);
       		
		if ( currentTime.before(cal.getTime())){
			
			//OUT 
			session.setAttribute("idProposition", idProposition);
			
			
			for(Choisir c:listeChoix){
				
				if(c.getProposition().)
				
				
			}
			
			dejaChoisi=choisirDAO.createChoix(choisir);
			
			if(dejaChoisi)
				choisirDAO.updateChoix(choisir);
					
			
		}
		else {
			session.setAttribute("idProposition", -1);
		}

		choisirActionLogger.debug("Action terminee avec succes");
		return mapping.findForward("succes");
		
		
		
	}

}
