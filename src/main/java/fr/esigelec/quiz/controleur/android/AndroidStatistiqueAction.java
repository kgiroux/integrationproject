package fr.esigelec.quiz.controleur.android;

import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.JSONObject;

import fr.esigelec.quiz.dao.hibernate.ChoisirDAOImpl;
import fr.esigelec.quiz.dao.hibernate.QuestionDAOImpl;
import fr.esigelec.quiz.dao.hibernate.QuizDAOImpl;
import fr.esigelec.quiz.dto.Choisir;
import fr.esigelec.quiz.dto.Personne;
import fr.esigelec.quiz.dto.Proposition;
import fr.esigelec.quiz.dto.Question;
import fr.esigelec.quiz.dto.Quiz;
import fr.esigelec.quiz.util.AndroidHelper;
/**
 * @author K�vin Giroux;
 * 
 */
public class AndroidStatistiqueAction extends Action{
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
			if("POST".equals(request.getMethod()))
			{
				int idQuiz = Integer.parseInt(request.getParameter("idQuiz"));
				int idQuestion = Integer.parseInt(request.getParameter("idQuestion"));
				
				
				//List<Quiz> quizList
				QuizDAOImpl daoQuiz = new QuizDAOImpl();
				Quiz quiz = daoQuiz.getQuizAvecQuestions(idQuiz);
				Question currentQuestion = null;
				for(Question q : quiz.getListeQuestions()){
					if(q.getId() == idQuestion){
						currentQuestion = q;
						break;
					}
				}
				JSONObject object = new JSONObject();
				ChoisirDAOImpl choixDao = new ChoisirDAOImpl();
				ArrayList<Integer> listChoisir = new ArrayList<>();
				if(currentQuestion != null){
					for(Proposition p : currentQuestion.getListePropositions()){
						listChoisir.add(choixDao.getNombrePersonneParProposition(quiz, p));
					}
				}else{
					// TODO error no question Found
				}
				
				JSONObject json = new JSONObject(listChoisir);
				System.out.println(json.toString());
				request.setAttribute("json",json.toString());
				return mapping.findForward("succes");
			}
			return mapping.findForward("error");
	}
}
