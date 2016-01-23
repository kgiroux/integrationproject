package fr.esigelec.quiz.controleur.android;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.iterators.EntrySetMapIterator;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.JSONObject;

import fr.esigelec.quiz.dao.hibernate.ChoisirDAOImpl;
import fr.esigelec.quiz.dao.hibernate.PersonneDAOImpl;
import fr.esigelec.quiz.dao.hibernate.QuizDAOImpl;
import fr.esigelec.quiz.dto.Choisir;
import fr.esigelec.quiz.dto.Personne;
import fr.esigelec.quiz.dto.Quiz;
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
				List<Choisir> list = choixDao.getChoixByQuiz(quiz);
				LinkedHashMap<Choisir,Personne> mapPersonneChoisir = new LinkedHashMap<>();
				for(Choisir c : list){
					mapPersonneChoisir.put(c, personneDao.getPersonne(c.getPersonne().getId()));
				}
				
				for(Map.Entry<Choisir, Personne> entry : mapPersonneChoisir.entrySet()){
					System.out.println(entry.getKey());
					System.out.println(entry.getValue());
					System.out.println("_____________________________________________________________________________");
				}
				
					
				
				JSONObject json = new JSONObject(mapPersonneChoisir);
				System.out.println(json.toString());
				request.setAttribute("json",json.toString());
				return mapping.findForward("succes");
			}
			return mapping.findForward("error");
	}
}
