package fr.esigelec.gsi.quizintegration.utils;

import fr.esigelec.gsi.quizintegration.Objects.Personne;

/**
 * Created by Kevin-Giroux on 12/01/2016. Package : fr.esigelec.gsi.quizintegration.utils Project Name : QuizIntegration
 */
public class SingletonPersonne
{
	private static volatile SingletonPersonne instance;
	private Personne personne;

	private SingletonPersonne(){
		super();
		personne = new Personne ();
	}

	public Personne getPersonne ()
	{
		return personne;
	}

	public static SingletonPersonne getInstance(){
		if(SingletonPersonne.instance == null){
			synchronized (SingletonPersonne.class){
				if(SingletonPersonne.instance == null){
					SingletonPersonne.instance = new SingletonPersonne ();
				}
			}
		}
		return SingletonPersonne.instance;

	}
}
