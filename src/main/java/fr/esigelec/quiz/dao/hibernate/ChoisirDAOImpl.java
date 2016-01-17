package fr.esigelec.quiz.dao.hibernate;

import java.util.List;

import fr.esigelec.quiz.dao.IChoisirDAO;
import fr.esigelec.quiz.dto.Choisir;
import fr.esigelec.quiz.dto.Personne;


public class ChoisirDAOImpl implements IChoisirDAO {

	@Override
	public boolean faireChoix(Choisir c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modifierChoix(Choisir c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteChoix(Choisir c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Choisir> getChoixPersonne(Personne P) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int scorePersonne(Personne P) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Choisir> getChoixQuiz(Personne P) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updatePersonne(Personne p) {
		// TODO Auto-generated method stub
		return false;
	}

}
