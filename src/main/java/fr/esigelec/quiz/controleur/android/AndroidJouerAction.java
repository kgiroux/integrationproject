package fr.esigelec.quiz.controleur.android;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.JSONArray;
import org.json.JSONObject;

import fr.esigelec.quiz.dao.IPropositionDAO;
import fr.esigelec.quiz.dao.IQuizDAO;
import fr.esigelec.quiz.dao.hibernate.PropositionDAOImpl;
import fr.esigelec.quiz.dao.hibernate.QuizDAOImpl;
import fr.esigelec.quiz.dto.Proposition;
import fr.esigelec.quiz.dto.Question;
import fr.esigelec.quiz.dto.Quiz;
import fr.esigelec.quiz.util.AndroidHelper;

/**
 * @author Kévin Giroux;
 * @edited by Kevin PACE
 */


public class AndroidJouerAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
			if("POST".equals(request.getMethod()))
			{
				JSONObject json = new JSONObject();
				
				//DAO elements
				IQuizDAO quizDAO = new QuizDAOImpl();
				IPropositionDAO propDAO = new PropositionDAOImpl();
				
				/* Get query type from the request
				 * 	0 -> return question
				 *  1 -> return answer
				 * */
				int queryType = Integer.parseInt(request.getParameter("queryType"));
				int questionId = Integer.parseInt(request.getParameter("idQuestion"));
				
				//Get current quiz informations (currentQuestion & quizState)
				Quiz currentQuiz = quizDAO.getCurrentQuiz();
				Quiz currentQuizWithQuestions = quizDAO.getQuizAvecQuestions(currentQuiz.getId());
				int qtNum = currentQuiz.getNoQuestionCourante();
				Question qt = currentQuizWithQuestions.getListeQuestions().get(qtNum);
				System.out.println("CurrentQuiz values =>"+currentQuiz.toString());
				
				//Perform action to return the question because question is open an waiting answers
				if(queryType == 0 && currentQuiz.getEtape() >= 1 && qt.getId() != questionId)
				{					
					//Calculate timer start
					Timestamp qtStart = currentQuiz.getDateDebutQuestion();
					Timestamp now = new Timestamp(System.currentTimeMillis());
					int timer = (int)(30-(now.getTime() - qtStart.getTime())*0.001);
					timer = timer>0 ? timer : 0;					

					//Return question
					HashMap<String,Object> qtMap = new HashMap<String,Object>();
					qtMap.put("libelle", qt.getLibelle());
					qtMap.put("id", qt.getId());
					qtMap.put("timer", timer);
					qtMap.put("numQuestion", qtNum);
					JSONArray propArray = new JSONArray();
					
					//Return only proposition label and Id
					for(Proposition p : qt.getListePropositions())
					{
						JSONObject jsonProp = new JSONObject();
						jsonProp.put("id", Integer.toString(p.getId()));
						jsonProp.put("libelle", p.getLibelle());
						propArray.put(jsonProp);
					}
					
					//Return info to client
					qtMap.put("propositions",propArray);
					json.put("Question", qtMap);
				}
				//Return the answer because it was published on the server
				else if(queryType == 1 && currentQuiz.getEtape() == 3)
				{
					for(Proposition prop : qt.getListePropositions())
					{
						if(prop.isEstBonneReponse())
						{
							json.put("Reponse", prop.getId());
							break;
						}	
					}
				}

				request.setAttribute("json",json.toString());
				return mapping.findForward("succes");
			}
			else
			{				
				JSONObject json = new JSONObject();
				json = AndroidHelper.DoGetForbiddenException();
				request.setAttribute("json", json);
				return mapping.findForward("error");	
			}
	}
	
}
