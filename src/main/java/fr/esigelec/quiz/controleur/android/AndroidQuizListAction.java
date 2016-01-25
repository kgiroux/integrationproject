package fr.esigelec.quiz.controleur.android;

import java.util.ArrayList;
import java.util.HashMap;
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
import fr.esigelec.quiz.util.AndroidHelper;

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
				try
				{
					//Find finished quizz list
					QuizDAOImpl dao = new QuizDAOImpl();
					List<Quiz> listQuiz = dao.getListQuizFinish();
					JSONObject json = new JSONObject();
					if(listQuiz != null)
						json.put("QuizList", listQuiz);
					
					//Find current quizz
					Quiz currentQuiz = dao.getCurrentQuiz();
					
					//Find questions count for the current quizz
					if(currentQuiz != null)
					{
						//Calculate question number
						Quiz questionQuiz = dao.getQuizAvecQuestions(currentQuiz.getId());
						int nbQuestions = questionQuiz.getQuestions().size();
						
						//Generate quizMap to share informations
						HashMap<String,Object> quizMap = new HashMap<String,Object>();
						quizMap.put("id", currentQuiz.getId());
						quizMap.put("libelle", currentQuiz.getLibelle());
						quizMap.put("dateDebutQuiz", currentQuiz.getDateDebutQuiz());
						quizMap.put("dateDebutQuestion", currentQuiz.getDateDebutQuestion());
						quizMap.put("noQuestionCourante", currentQuiz.getNoQuestionCourante());
						quizMap.put("etape", currentQuiz.getEtape());
						quizMap.put("nbQuestions", nbQuestions);
						
						//Post result
						json.put("CurrentQuiz", quizMap);
					}
					
					//Return informations to client
					request.setAttribute("json",json.toString());
					return mapping.findForward("succes");
					
				}catch(Exception ex)
				{
					ex.printStackTrace();
					request.setAttribute("json",AndroidHelper.TimeOutExeception());
					return mapping.findForward("succes");
				}
			}
			return mapping.findForward("error");
	}
}
