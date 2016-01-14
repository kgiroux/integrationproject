package fr.esigelec.quiz.controleur;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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


public class EnregistrerQuestionAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String libelleQuestion = request.getParameter("libelleQuestion");
		String p1, p2, p3, p4, p5, p6, p7, p8;
		Personne p = (Personne) request.getSession().getAttribute("personne");
		
		if (p.getDroits() == Personne.ADMIN) {
			
			String idQuiz = request.getParameter("idQuiz");
		
			IQuestionDAO questionDAO = new QuestionDAOImpl();
			IPropositionDAO propositionDAO = new PropositionDAOImpl();
			IQuizDAO quizDAO = new QuizDAOImpl(); 
			
			Question question = new Question();
			Quiz quiz = quizDAO.getQuiz(Integer.valueOf(idQuiz));
			
			Proposition propositionBonneReponse = new Proposition();
			propositionBonneReponse.setLibelle(libelleQuestion);
			propositionBonneReponse.setIdQuestion(question);
			question.setBonneReponse(propositionBonneReponse);
			
			List<Proposition> listPropositions = new ArrayList<Proposition>();
			if((p1 = request.getParameter("p1")) != null)
			{
				Proposition prop = new Proposition();
				prop.setLibelle(p1);
				prop.setIdQuestion(question);
				propositionDAO.createProposition(prop);
				listPropositions.add(prop);
			}
			if((p2 = request.getParameter("p2")) != null)
			{
				Proposition prop = new Proposition();
				prop.setLibelle(p2);
				prop.setIdQuestion(question);
				propositionDAO.createProposition(prop);
				listPropositions.add(prop);
			}
			if((p3 = request.getParameter("p3")) != null)
			{
				Proposition prop = new Proposition();
				prop.setLibelle(p3);
				prop.setIdQuestion(question);
				propositionDAO.createProposition(prop);
				listPropositions.add(prop);
			}
			if((p4 = request.getParameter("p4")) != null)
			{
				Proposition prop = new Proposition();
				prop.setLibelle(p4);
				prop.setIdQuestion(question);
				propositionDAO.createProposition(prop);
				listPropositions.add(prop);
			}
			if((p5 = request.getParameter("p5")) != null)
			{
				Proposition prop = new Proposition();
				prop.setLibelle(p5);
				prop.setIdQuestion(question);
				propositionDAO.createProposition(prop);
				listPropositions.add(prop);
			}
			if((p6 = request.getParameter("p6")) != null)
			{
				Proposition prop = new Proposition();
				prop.setLibelle(p6);
				prop.setIdQuestion(question);
				propositionDAO.createProposition(prop);
				listPropositions.add(prop);
			}
			if((p7 = request.getParameter("p7")) != null)
			{
				Proposition prop = new Proposition();
				prop.setLibelle(p7);
				prop.setIdQuestion(question);
				propositionDAO.createProposition(prop);
				listPropositions.add(prop);
			}
			if((p8 = request.getParameter("p8")) != null)
			{
				Proposition prop = new Proposition();
				prop.setLibelle(p8);
				prop.setIdQuestion(question);
				propositionDAO.createProposition(prop);
				listPropositions.add(prop);
			}
			question.setListePropositions(listPropositions);
			questionDAO.createQuestion(question);
			quiz.getListeQuestions().add(question);
			quizDAO.updateQuiz(quiz);
			request.setAttribute("listeQuiz", quiz.getListeQuestions());
		}
		
		return mapping.findForward("succes");
	}
}
