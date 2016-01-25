package fr.esigelec.gsi.quizintegration.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import fr.esigelec.gsi.quizintegration.R;
import fr.esigelec.gsi.quizintegration.utils.AndroidHTTPRequest;

/**
 * Created by Kevin-Giroux on 11/01/2016. Package : fr.esigelec.gsi.quizintegration.activity Project Name : QuizIntegration
 */
public class AboutActivity extends AppCompatActivity
{
	@Override
	protected void onCreate (Bundle savedInstanceState)
	{
		super.onCreate (savedInstanceState);
		setContentView (R.layout.activity_about);
		TextView tvAbout = (TextView) findViewById(R.id.textView4);
		try {
			JSONObject legalNotice = new AndroidHTTPRequest().execute(new String[]{WelcomeActivity.IPSERVER + "About.do", "GET",null}).get();
			if(legalNotice != null)
				tvAbout.setText(legalNotice.toString());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
}
