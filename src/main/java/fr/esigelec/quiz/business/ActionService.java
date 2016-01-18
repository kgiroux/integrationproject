/**
 * @author Rodolphe AGUIDISSOU - ESIGELEC 2016
 * @author BOSSO BOSSO Ghyslaine
 * @author DELAUNAY Brice
 *
 *Classe utiliser par les Classe Actions
 * pour gerer des traitements specifiques
 */
package fr.esigelec.quiz.business;

import java.util.Collections;
import java.util.List;

import org.hibernate.Session;

import fr.esigelec.quiz.dao.hibernate.ChoisirDAOImpl;
import fr.esigelec.quiz.dao.hibernate.HibernateUtil;
import fr.esigelec.quiz.dao.hibernate.PersonneDAOImpl;
import fr.esigelec.quiz.dao.hibernate.PropositionDAOImpl;
import fr.esigelec.quiz.dao.hibernate.QuestionDAOImpl;
import fr.esigelec.quiz.dao.hibernate.QuizDAOImpl;
import fr.esigelec.quiz.dto.Choisir;
import fr.esigelec.quiz.dto.Personne;
import fr.esigelec.quiz.dto.Proposition;
import fr.esigelec.quiz.dto.Question;
import fr.esigelec.quiz.dto.Quiz;

public class ActionService {
	
	public static  Question  getQuestionByQuizId(int idQuiz){
		
		//UTILS 
		QuizDAOImpl quizdaoimpl = new QuizDAOImpl();
		QuestionDAOImpl questiondaoimpl = new QuestionDAOImpl();
		
		Quiz quiz= quizdaoimpl.getQuiz(idQuiz);	
		Question question = questiondaoimpl.getQuestion(quiz.getNoQuestionCourante());
		
		return question;
	}
	
	/**
	 * Méthode renvoyant le classement des personnes pour un quiz
	 * @param q : Le quiz demandé
	 * @return Le classement de toutes les personnes
	 */
	public static List<Personne> getClassement(Quiz q) {
		List<Personne> listePersonne = new PersonneDAOImpl().listPersonnes();
		for (Personne p : listePersonne) {
			p.setScore(scoreParPersonne(p, q));
		}
		Collections.sort(listePersonne);
		return listePersonne;
	}

	/**
	 * Méthode renvoyant le score d'une personne
	 * @param p : La personne demandé
	 * @param q : Le quiz demandé
	 * @return Le score de la personne en paramètre pour le quiz
	 */
	public static int scoreParPersonne(Personne p, Quiz q) {
		int score = 0;
		List<Choisir> listeChoixPersonne = new ChoisirDAOImpl().getChoixPersonneParQuiz(p, q);
		for (Choisir c : listeChoixPersonne){
			if (c.getProposition().isEstBonneReponse())
				score++;
		}
		return score;
	}
	
	/**
	 * Méthode renvoyant le pourcentage de bonnes réponses par question
	 * @param q : La question demandée
	 * @return La liste des propositions avec leurs pourcentages de bonnes réponses
	 */
	public static List<Proposition> getPourcentagePropositions(Quiz quiz, Question ques) {
		int nbPersonne = new ChoisirDAOImpl().getNombrePersonneParQuiz(quiz); //nb de joueurs
		double pourcentage = 0;
		List<Proposition> listeProposition = ques.getListePropositions();//propositions de la question
		for (Proposition p : listeProposition) {
			pourcentage =((new ChoisirDAOImpl().getNombrePersonneParProposition(quiz, p)) *100)/nbPersonne; //pourcentage par proposition
			p.setPourcentage(pourcentage);
		}
		
		return listeProposition;
	}
}
