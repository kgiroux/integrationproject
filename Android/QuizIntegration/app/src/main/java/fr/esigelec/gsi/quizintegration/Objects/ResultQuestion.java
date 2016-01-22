package fr.esigelec.gsi.quizintegration.Objects;

import java.util.HashMap;

/**
 * Created by kevin on 22/01/2016. Package : fr.esigelec.gsi.quizintegration.Objects Project Name : QuizIntegration
 */
public class ResultQuestion
{
	private int idQuestion;
	private int idQuiz;

	public ResultQuestion (int idQuestion, int idQuiz)
	{
		this.idQuestion = idQuestion;
		this.idQuiz = idQuiz;
	}

	public int getIdQuestion ()
	{

		return idQuestion;
	}

	public void setIdQuestion (int idQuestion)
	{
		this.idQuestion = idQuestion;
	}

	public int getIdQuiz ()
	{
		return idQuiz;
	}

	public void setIdQuiz (int idQuiz)
	{
		this.idQuiz = idQuiz;
	}

	public HashMap<String,String> ResultQuestionToHashMap(){
		HashMap<String, String> returnHashMap = new HashMap<> ();
		if(!"".equals (String.valueOf (idQuestion))){
			returnHashMap.put ("idQuestion",String.valueOf (idQuestion));
		}
		if(!"".equals (String.valueOf (idQuiz))){
			returnHashMap.put ("idQuiz",String.valueOf (idQuiz));
		}
		return returnHashMap;
	}
}
