package fr.esigelec.gsi.quizintegration.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import fr.esigelec.gsi.quizintegration.Objects.Quiz;
import fr.esigelec.gsi.quizintegration.R;
import fr.esigelec.gsi.quizintegration.utils.AndroidHTTPRequest;

public class StatistiqueActivity extends AppCompatActivity{




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistique);

		int idQuiz= getIntent ().getIntExtra ("idQuiz",0);

		Quiz q = new Quiz ();
		q.setId (1);

		if(idQuiz == 0){
			//TODO error
		}else{
			try {
				JSONObject quizJson = new AndroidHTTPRequest ().execute(new String[]{MainActivity.IPSERVER + "AndroidStatistique.do", "POST",AndroidHTTPRequest.createParamString (q.QuizToHashMap ()) }).get();

				JSONObject curQuiz = quizJson.getJSONObject("CurrentQuiz");
				//if(!"noQuiz".equals(curQuiz.toString()))
					//	currentQuiz.JSONObjectToQuiz(curQuiz);

				JSONArray quizListJson = quizJson.getJSONArray("QuizList");
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

    }


}
