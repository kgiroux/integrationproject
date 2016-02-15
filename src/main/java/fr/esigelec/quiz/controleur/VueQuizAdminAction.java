package fr.esigelec.quiz.controleur;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import fr.esigelec.quiz.dao.IChoisirDAO;
import fr.esigelec.quiz.dao.IQuizDAO;
import fr.esigelec.quiz.dao.hibernate.ChoisirDAOImpl;
import fr.esigelec.quiz.dao.hibernate.QuizDAOImpl;
import fr.esigelec.quiz.dto.Personne;
import fr.esigelec.quiz.dto.Quiz;

/**
 * Verify authentication then get all quiz from db and store into `attribute' for jsp use.
 * @author Wenfeng
 *
 */
public class VueQuizAdminAction extends Action {
	
	private final Logger vueQuizAdminAction = Logger.getLogger(VueQuizAdminAction.class);
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		vueQuizAdminAction.info("start..");
		ActionErrors errors = new ActionErrors();

		try {
			/* Verify authentication */
			Personne p = (Personne) request.getSession().getAttribute("personne");
			if (p == null || p.getDroits() != Personne.ADMIN) {
				errors.add("err.session.auth", new ActionMessage("err.session.auth.notfound"));
				if (!errors.isEmpty())
					addErrors(request, errors);
				return mapping.findForward("login");
			}
			
			/* Store all quiz into attribute for jsp use */
			IQuizDAO quizDAO = new QuizDAOImpl();
			List<Quiz> listeQuiz= quizDAO.listQuiz();
			request.setAttribute("listeQuiz", quizDAO.listQuiz());
			
			//on recupere le nb de vote par quiz
			//et on charge dans une map
			Map<Integer,Integer> map=new HashMap<Integer,Integer>(); 
			IChoisirDAO choisirDAO=new ChoisirDAOImpl();
			for(Quiz q:listeQuiz){
				map.put(q.getId(),choisirDAO.getChoixByQuiz(q).size());
			}
			
			request.setAttribute("mapNbVotes",map );
			
		} catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("erreur");
		}
		return mapping.findForward("succes");
	}

}
