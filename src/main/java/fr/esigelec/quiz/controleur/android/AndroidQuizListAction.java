package fr.esigelec.quiz.controleur.android;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.JSONObject;

import fr.esigelec.quiz.dao.hibernate.QuizDAOImpl;
import fr.esigelec.quiz.dto.Quiz;

/**
 * @author K�vin Giroux;
 * 
 */


public class AndroidQuizListAction extends Action{
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
			if("GET".equals(request.getMethod()))
			{
				//Find finished quizz list
				QuizDAOImpl dao = new QuizDAOImpl();
				List<Quiz> listQuiz = dao.getListQuizFinish();
				JSONObject json = new JSONObject();
				json.put("QuizList", listQuiz);
				
				//Find current quizz
				List<Quiz> currentQuiz = new ArrayList<Quiz>();
				currentQuiz.add(dao.getCurrentQuiz());
				
				//Find questions count for the current quizz
				if(currentQuiz != null)
				{
					Quiz questionQuiz = dao.getQuizAvecQuestions((currentQuiz.get(0).getId()));
					int nbQuestion = questionQuiz.getQuestions().size();
					json.put("CurrentQuiz", currentQuiz);
					json.put("nbQuestions", nbQuestion);
				}
				
				//Return informations to client
				request.setAttribute("json",json.toString());
				return mapping.findForward("succes");
			}
			return mapping.findForward("error");
	}
}
