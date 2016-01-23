package fr.esigelec.quiz.dao;

import java.util.List;

import fr.esigelec.quiz.dto.Choisir;
import fr.esigelec.quiz.dto.Personne;
import fr.esigelec.quiz.dto.Proposition;
import fr.esigelec.quiz.dto.Question;
import fr.esigelec.quiz.dto.Quiz;

/**Projet d'integration
 * Le jeu de TF8
 * GSI-IR
 * @author BOSSO BOSSO Ghyslaine
 * @author CHOUAKRIA Farid
 * @author DELAUNAY Brice
 * @author NGANE Pascale
 * 
 * Interface IChoisirDAO
 */

public interface IChoisirDAO {
	
	
	public boolean createChoix(Choisir c);
	
	public boolean updateChoix(Choisir c);
	
	public boolean deleteChoix(Choisir c);
	
	public List<Choisir> getChoixPersonneParQuiz(Personne p, Quiz q);

	public List<Choisir> getChoixPersonneParQuizPersonneEtQuestion(Personne p, Quiz q,Question question);

	public int getNombrePersonneParQuiz(Quiz q);

	public int getNombrePersonneParProposition(Quiz q, Proposition p);
	
	public Choisir getChoix(int id);

	public Choisir getChoix(Personne p, Quiz quiz, Question q);
	
	public List<Choisir> getChoixByQuiz(Quiz q);
	
}
