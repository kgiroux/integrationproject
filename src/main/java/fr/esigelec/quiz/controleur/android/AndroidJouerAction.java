package fr.esigelec.quiz.controleur.android;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.JSONObject;

import fr.esigelec.quiz.dao.IQuizDAO;
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
				
				//DAO & Parameters
				IQuizDAO quizDAO = new QuizDAOImpl();
				int idQuiz = Integer.parseInt(request.getParameter("quizId"));
				Quiz quiz = quizDAO.getQuizAvecQuestions(idQuiz);
				int qtNum = quiz.getNoQuestionCourante();
				Question qt = quiz.getListeQuestions().get(qtNum);
				
				switch(quiz.getEtape())
				{
					case 1: //Question is open an waiting answers
						json.put("Question", qt);
						
						//Calculate timer
						Timestamp qtStart = quiz.getDateDebutQuestion();
						Timestamp now = new Timestamp(System.currentTimeMillis());
						long timer = now.getTime() - qtStart.getTime();
						json.put("Timer", timer);
						
						//Send question number
						json.put("NumQuestion", quiz.getNoQuestionCourante());
						
						break;
					case 2:
						break;
					case 3: //True answer is available
						for(Proposition prop : qt.getListePropositions())
						{
							if(prop.isEstBonneReponse())
							{
								json.put("GoodAnswer", prop);
								json.put("AnswerPos", qt.getListePropositions().indexOf(prop));
								break;
							}	
						}
						break;
				}

				request.setAttribute("json",json.toString());
				return mapping.findForward("succes");
			}
			else{
				
				JSONObject json = new JSONObject();
				json = AndroidHelper.TimeOutExeception();
				request.setAttribute("json", json);
				return mapping.findForward("error");	
			}
	}
	
}
