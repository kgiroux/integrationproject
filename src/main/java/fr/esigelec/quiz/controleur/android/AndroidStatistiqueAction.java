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
 * Action permettant l'affichage des statistiques sur ANdroid
 */
public class AndroidStatistiqueAction extends Action{
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
			if("POST".equals(request.getMethod()))
			{
				// Récupération de l'id du QUIZ
				int idQuiz = Integer.parseInt(request.getParameter("idQuiz"));
				
				QuizDAOImpl daoQuiz = new QuizDAOImpl();
				// Récupération du QUIZ
				Quiz quiz = daoQuiz.getQuizAvecQuestions(idQuiz);
				
				
				ChoisirDAOImpl choixDao = new ChoisirDAOImpl();
				List<Choisir> listChoix = choixDao.getChoixByQuiz(quiz);
				// Calcul du score
				for (Choisir c : listChoix){
					if(c.getProposition().isEstBonneReponse()){
						c.getPersonne().setScore(c.getPersonne().getScore()+1);
					}
				}
				
				// Récupération uniquement des personnes et donc du classement
				Set<Personne> setPersonne = new HashSet<Personne>();
				for (Choisir c : listChoix){
					c.getPersonne().setMail("");
					c.getPersonne().setMdp("");
					setPersonne.add(c.getPersonne());
				}
				
				// Ajout dans un JSONOBject
				JSONObject json = new JSONObject();
				JSONArray array = new JSONArray();
				for(Personne p : setPersonne){
					array.put(p.toJson());
				}
				
				json.put("Scores", array);
				
				System.out.println(json);
				request.setAttribute("json",json.toString());
				return mapping.findForward("succes");
			}
			return mapping.findForward("error");
	}
}
