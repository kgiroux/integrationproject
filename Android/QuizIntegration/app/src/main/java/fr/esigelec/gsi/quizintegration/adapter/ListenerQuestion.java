package fr.esigelec.gsi.quizintegration.adapter;

import android.app.usage.UsageEvents;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONObject;

import fr.esigelec.gsi.quizintegration.Activity.MainActivity;
import fr.esigelec.gsi.quizintegration.R;
import fr.esigelec.gsi.quizintegration.utils.AndroidHTTPRequest;

/**
 * Created by kevin on 22/01/2016. Package : fr.esigelec.gsi.quizintegration.adapter Project Name : QuizIntegration
 */
public class ListenerQuestion
{
	public JSONObject OnListener(){
		JSONObject quizJson = null;
		do{
			try
			{
				quizJson = new AndroidHTTPRequest ().execute (new String[]{MainActivity.IPSERVER + "AndroidQuizList.do", "GET", null}).get ();

			}catch(Exception ex)
			{
				Log.e("ERROR",ex.getMessage());
			}



		}while(quizJson.has ("noQuestion") && null != quizJson);

		return quizJson;
	}
}
