package fr.esigelec.quiz.controleur.android;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.iterators.EntrySetMapIterator;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.JSONArray;
import org.json.JSONObject;

import fr.esigelec.quiz.dao.hibernate.ChoisirDAOImpl;
import fr.esigelec.quiz.dao.hibernate.PersonneDAOImpl;
import fr.esigelec.quiz.dao.hibernate.QuizDAOImpl;
import fr.esigelec.quiz.dto.Choisir;
import fr.esigelec.quiz.dto.Personne;
import fr.esigelec.quiz.dto.Quiz;
import fr.esigelec.quiz.util.SetToListConverter;
/**
 * @author K�vin Giroux;
 * 
 */
public class AndroidStatistiqueAction extends Action{
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
			if("GET".equals(request.getMethod()))
			{
				int idQuiz = Integer.parseInt(request.getParameter("idQuiz"));
				
				//List<Quiz> quizList
				QuizDAOImpl daoQuiz = new QuizDAOImpl();
				Quiz quiz = daoQuiz.getQuizAvecQuestions(idQuiz);
				
				ChoisirDAOImpl choixDao = new ChoisirDAOImpl();
				PersonneDAOImpl personneDao = new PersonneDAOImpl();
				List<Choisir> listChoix = choixDao.getChoixByQuiz(quiz);
								
				for (Choisir c : listChoix){
					if(c.getProposition().isEstBonneReponse()){
						c.getPersonne().setScore(c.getPersonne().getScore()+1);
					}
					System.out.println(c.toString() + c.getPersonne().toString());
				}
				ArrayList<Personne> personnes = new ArrayList<>();
				Set<Personne> setPersonne = new HashSet<Personne>();
				for (Choisir c : listChoix){
					c.getPersonne().setMail("");
					c.getPersonne().setMdp("");
					setPersonne.add(c.getPersonne());
				}
				
				
				System.out.println("______________________________________________");
				

				JSONObject json = new JSONObject();
				json.put("Scores", setPersonne);
				
				System.out.println(json);
				request.setAttribute("json",json.toString());
				return mapping.findForward("succes");
			}
			return mapping.findForward("error");
	}
}
