package fr.esigelec.quiz.dao;

import java.util.List;

import fr.esigelec.quiz.dto.Choisir;
import fr.esigelec.quiz.dto.Personne;

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
	
	
	public boolean faireChoix(Choisir c);
	
	public boolean modifierChoix(Choisir c);
	
	public boolean deleteChoix(Choisir c);
	
	public List<Choisir> getChoixPersonne(Personne P);
	
	public int scorePersonne(Personne P);
	
	public List<Choisir> getChoixQuiz(Personne P);
	
	public boolean updatePersonne(Personne p);
	

}
