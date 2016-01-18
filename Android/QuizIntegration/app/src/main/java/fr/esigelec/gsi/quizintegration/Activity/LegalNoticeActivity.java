package fr.esigelec.gsi.quizintegration.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import fr.esigelec.gsi.quizintegration.R;
import fr.esigelec.gsi.quizintegration.utils.AndroidHTTPRequest;
/**
 * Created by Kevin-Giroux on 11/01/2016. Package : fr.esigelec.gsi.quizintegration.Activity Project Name : QuizIntegration
 */
public class LegalNoticeActivity extends AppCompatActivity
{



	@Override
	protected void onCreate (Bundle savedInstanceState)
	{
		super.onCreate (savedInstanceState);
		setContentView (R.layout.activity_legal_notice);
		String IPSERVER = MainActivity.IPSERVER;
		TextView legalNoticeText = (TextView) findViewById(R.id.legalNoticeText);

		try {
			JSONObject legalNotice = new AndroidHTTPRequest().execute(IPSERVER + "LegalNotice.do", "GET",null).get();
            if(legalNotice != null)
			    legalNoticeText.setText(legalNotice.toString());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
}
