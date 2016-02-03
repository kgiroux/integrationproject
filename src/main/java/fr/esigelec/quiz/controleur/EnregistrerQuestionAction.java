package fr.esigelec.quiz.controleur;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

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
		ActionErrors errors = new ActionErrors();
		
		// Verify input parameter
		if (bonneReponse == null || "".equals(bonneReponse)) {
			errors.add("err.inputs", new ActionMessage("err.inputs.null"));	
			if (!errors.isEmpty())
				addErrors(request, errors);
			return mapping.findForward("erreur");
		}
		
		if (p.getDroits() == Personne.ADMIN) {
					
			IQuestionDAO questionDAO = new QuestionDAOImpl();
			IPropositionDAO propositionDAO = new PropositionDAOImpl();
			IQuizDAO quizDAO = new QuizDAOImpl(); 

			// enregister la question
			Question question = new Question(libelleQuestion);
			questionDAO.createQuestion(question);
			enregistrerQuestionActionLogger.debug(question.toString());
			Set<Proposition> listeProposition = new HashSet<Proposition>();
			
			
//			Proposition propositionBonneReponse = new Proposition();
//			propositionBonneReponse.setLibelle(bonneReponse);
//			propositionBonneReponse.setEstBonneReponse(true);
//			
//			listeProposition.add(propositionBonneReponse);
			
			//enregistrer bonne reponse
			Proposition prop = new Proposition();
			prop.setLibelle(bonneReponse);
			prop.setEstBonneReponse(true);
			prop.setQuestion(question);
			propositionDAO.createProposition(prop);
			enregistrerQuestionActionLogger.info(prop.toString());
			enregistrerQuestionActionLogger.info(listeProposition.size());
			listeProposition.add(prop);
			
			
			
			// enregistrer les propositions
			p1 = request.getParameter("p1");
			if(p1 != null && !"".equals(p1))
			{
				prop = new Proposition();
				prop.setLibelle(p1);
				boolean estBonne = p1.equals(bonneReponse) ? true : false;
				prop.setEstBonneReponse(estBonne);
				prop.setQuestion(question);
				propositionDAO.createProposition(prop);
				enregistrerQuestionActionLogger.info(prop.toString());
				enregistrerQuestionActionLogger.info(listeProposition.size());
				listeProposition.add(prop);
			}
			p2 = request.getParameter("p2");
			if(p2 != null && !"".equals(p2))
			{
				prop = new Proposition();
				prop.setLibelle(p2);
				boolean estBonne = p2.equals(bonneReponse) ? true : false;
				prop.setEstBonneReponse(estBonne);
				prop.setQuestion(question);
				propositionDAO.createProposition(prop);
				enregistrerQuestionActionLogger.info(prop.toString());
				enregistrerQuestionActionLogger.info(listeProposition.size());
				listeProposition.add(prop);
			}
			p3 = request.getParameter("p3");
			if(p3 != null && !"".equals(p3))
			{
				prop = new Proposition();
				prop.setLibelle(p3);
				boolean estBonne = p3.equals(bonneReponse) ? true : false;
				prop.setEstBonneReponse(estBonne);
				prop.setQuestion(question);
				propositionDAO.createProposition(prop);
				enregistrerQuestionActionLogger.info(prop.toString());
				enregistrerQuestionActionLogger.info(listeProposition.size());
				listeProposition.add(prop);
			}
			p4 = request.getParameter("p4");
			if(p4 != null && !"".equals(p4))
			{
				prop = new Proposition();
				prop.setLibelle(p4);
				boolean estBonne = p4.equals(bonneReponse) ? true : false;
				prop.setEstBonneReponse(estBonne);
				prop.setQuestion(question);
				propositionDAO.createProposition(prop);
				enregistrerQuestionActionLogger.info(prop.toString());
				enregistrerQuestionActionLogger.info(listeProposition.size());
				listeProposition.add(prop);
			}
			p5 = request.getParameter("p5");
			if(p5 != null && !"".equals(p5))
			{
				prop = new Proposition();
				prop.setLibelle(p5);
				boolean estBonne = p5.equals(bonneReponse) ? true : false;
				prop.setEstBonneReponse(estBonne);
				prop.setQuestion(question);
				propositionDAO.createProposition(prop);
				enregistrerQuestionActionLogger.info(prop.toString());
				enregistrerQuestionActionLogger.info(listeProposition.size());
				listeProposition.add(prop);
			}
			p6 = request.getParameter("p6");
			if(p6 != null && !"".equals(p6))
			{
				prop = new Proposition();
				prop.setLibelle(p6);
				boolean estBonne = p6.equals(bonneReponse) ? true : false;
				prop.setEstBonneReponse(estBonne);
				prop.setQuestion(question);
				propositionDAO.createProposition(prop);
				enregistrerQuestionActionLogger.info(prop.toString());
				enregistrerQuestionActionLogger.info(listeProposition.size());
				listeProposition.add(prop);
			}
			p7 = request.getParameter("p7");
			if(p7 != null && !"".equals(p7))
			{
				prop = new Proposition();
				prop.setLibelle(p7);
				boolean estBonne = p7.equals(bonneReponse) ? true : false;
				prop.setEstBonneReponse(estBonne);
				prop.setQuestion(question);
				propositionDAO.createProposition(prop);
				enregistrerQuestionActionLogger.info(prop.toString());
				enregistrerQuestionActionLogger.info(listeProposition.size());
				listeProposition.add(prop);
			}
			
			
			for(Proposition _p: listeProposition) {
				enregistrerQuestionActionLogger.debug(_p.toString());
			}
			question.setPropositions(listeProposition);
			
			request.setAttribute("listeQuestion", questionDAO.listQuestion());
		}

		enregistrerQuestionActionLogger.debug("Question enregistree");
		return mapping.findForward("succes");
	}
}
