package fr.esigelec.gsi.quizintegration.utils;

import java.util.Comparator;

import fr.esigelec.gsi.quizintegration.Objects.Personne;

/**
 * Created by kevin on 24/01/2016. Package : fr.esigelec.gsi.quizintegration.utils Project Name : QuizIntegration
 */
public class ScoreComparator
{
	public static Comparator<Personne> getPersonneScoreComparator(){
		return new PersonneScoreComparator();
	}

	private static class PersonneScoreComparator implements Comparator<Personne>{

		@Override
		public int compare (Personne lhs, Personne rhs)
		{
			return lhs.getScore () - rhs.getScore ();
		}
	}
}
