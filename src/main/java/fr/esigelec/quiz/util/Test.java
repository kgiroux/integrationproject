package fr.esigelec.quiz.util;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import fr.esigelec.quiz.dao.hibernate.PropositionDAOImpl;
import fr.esigelec.quiz.dao.hibernate.QuestionDAOImpl;
import fr.esigelec.quiz.dao.hibernate.QuizDAOImpl;
import fr.esigelec.quiz.dto.Proposition;
import fr.esigelec.quiz.dto.Question;
import fr.esigelec.quiz.dto.Quiz;


public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Word w = new Word();
		w.setId(15);
		
		Word w1 = new Word();
		w1.setId(12);
		
		Word w2 = new Word();
		w2.setId(17);
		
		Word w3 = new Word();
		w3.setId(13);
		
		Word w4 = new Word();
		w4.setId(85);
		
		Word w5 = new Word();
		w5.setId(42);
		
		Word w6 = new Word();
		w6.setId(29);
		
		Word w7 = new Word();
		w7.setId(16);
		
		
		ArrayList<Word> array = new ArrayList<Word>();
		HashSet<Word> set = new HashSet<Word>();
		
		set.add(w);
		set.add(w1);
		set.add(w2);
		set.add(w3);
		set.add(w4);
		set.add(w5);
		set.add(w6);
		set.add(w7);
		
		
		System.out.println(set.toString());
		SetToListConverter.SetToList(array, set);
		System.out.println(array);
		
		
		
		/*
		 * Programme de test de la bdd
		 */
		long date = System.currentTimeMillis();
		Quiz q = new Quiz();
		q.setLibelle("TEST DE LA BASE DE DONNEE");
		q.setDateDebutQuiz(new Timestamp(date));
		q.setDateFinQuiz(new Timestamp(date));
		q.setDateDebutQuestion(new Timestamp(date));
		Quiz q1 = new Quiz();
		q1.setLibelle("TEST DE LA BASE DE DONNEE 1");
		q1.setDateDebutQuiz(new Timestamp(date));
		q1.setDateFinQuiz(new Timestamp(date));
		q1.setDateDebutQuestion(new Timestamp(date));
		Quiz q2 = new Quiz();
		q2.setLibelle("TEST DE LA BASE DE DONNEE 2");
		q2.setDateDebutQuiz(new Timestamp(date));
		q2.setDateFinQuiz(new Timestamp(date));
		q2.setDateDebutQuestion(new Timestamp(date));
		Quiz q3 = new Quiz();
		q3.setLibelle("TEST DE LA BASE DE DONNEE 3");
		q3.setDateDebutQuiz(new Timestamp(date));
		q3.setDateFinQuiz(new Timestamp(date));
		q3.setDateDebutQuestion(new Timestamp(date));
		
		Question qu1 = new Question();
		qu1.setLibelle("QUESTION SUR LE FONCTIONEMENT DE LA BASE DE DONNE");

		Question qu2 = new Question();
		qu2.setLibelle("QUESTION SUR LE FONCTIONEMENT DE LA BASE DE DONNE 1");
		
		Question qu3 = new Question();
		qu3.setLibelle("QUESTION SUR LE FONCTIONEMENT DE LA BASE DE DONNE 2");
		
		Question qu4 = new Question();
		qu4.setLibelle("QUESTION SUR LE FONCTIONEMENT DE LA BASE DE DONNE 3");
		
		Question qu5 = new Question();
		qu5.setLibelle("QUESTION SUR LE FONCTIONEMENT DE LA BASE DE DONNE 4");
		
		Proposition p = new Proposition();
		p.setLibelle("PROPOSITION POUR LE TEST DE LA BDD");
		
		Proposition p1 = new Proposition();
		p1.setLibelle("PROPOSITION POUR LE TEST DE LA BDD");
		
		Proposition p2 = new Proposition();
		p2.setLibelle("PROPOSITION POUR LE TEST DE LA BDD");
		
		Proposition p3 = new Proposition();
		p3.setLibelle("PROPOSITION POUR LE TEST DE LA BDD");
		
		Proposition p4 = new Proposition();
		p4.setLibelle("PROPOSITION POUR LE TEST DE LA BDD");
		
		Proposition p5 = new Proposition();
		p5.setLibelle("PROPOSITION POUR LE TEST DE LA BDD");
		
		Proposition p6 = new Proposition();
		p6.setLibelle("PROPOSITION POUR LE TEST DE LA BDD");
		
		Proposition p7 = new Proposition();
		p7.setLibelle("PROPOSITION POUR LE TEST DE LA BDD");
		
		Proposition p8 = new Proposition();
		p8.setLibelle("PROPOSITION POUR LE TEST DE LA BDD");
		
		
		QuizDAOImpl daoQuiz = new QuizDAOImpl();
		
		daoQuiz.createQuiz(q);
		daoQuiz.createQuiz(q1);
		daoQuiz.createQuiz(q2);
		daoQuiz.createQuiz(q3);
		
		daoQuiz.deleteQuiz(q);
		daoQuiz.deleteQuiz(q1);
		daoQuiz.deleteQuiz(q2);
		daoQuiz.deleteQuiz(q3);
		
		daoQuiz.createQuiz(q);
		daoQuiz.createQuiz(q1);
		daoQuiz.createQuiz(q2);
		daoQuiz.createQuiz(q3);
		
		HashSet<Question> questions = new HashSet<>();
		daoQuiz.createQuiz(q);
		
		

		QuestionDAOImpl daoQuestion = new QuestionDAOImpl();
		daoQuestion.createQuestion(qu1);
		daoQuestion.createQuestion(qu2);
		daoQuestion.createQuestion(qu3);
		daoQuestion.createQuestion(qu4);
		daoQuestion.createQuestion(qu5);
		
		
		
		PropositionDAOImpl daoProposition = new PropositionDAOImpl();
		daoProposition.createProposition(p1);
		daoProposition.createProposition(p2);
		daoProposition.createProposition(p3);
		daoProposition.createProposition(p4);
		daoProposition.createProposition(p5);
		daoProposition.createProposition(p6);
		daoProposition.createProposition(p7);
		daoProposition.createProposition(p8);
		
		
		qu1.setId(q.getId());
		qu1.setBonneReponse(p1);
		qu2.setId(q.getId());
		qu2.setBonneReponse(p1);
		qu3.setId(q.getId());
		qu3.setBonneReponse(p1);
		qu4.setId(q.getId());
		qu4.setBonneReponse(p1);
		qu5.setId(q.getId());
		qu5.setBonneReponse(p1);
		
		
		/*QuestionDAOImpl daoQuestion = new QuestionDAOImpl();
		daoQuestion.createQuestion(qu1);
		daoQuestion.createQuestion(qu2);
		daoQuestion.createQuestion(qu3);
		daoQuestion.createQuestion(qu4);
		daoQuestion.createQuestion(qu5);*/
		
		
		q.setQuestions(questions);
		daoQuiz.updateQuiz(q);
		
	}

}
