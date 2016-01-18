package fr.esigelec.gsi.quizintegration.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

import fr.esigelec.gsi.quizintegration.Objects.Personne;
import fr.esigelec.gsi.quizintegration.R;
import fr.esigelec.gsi.quizintegration.utils.AndroidHTTPRequest;
import fr.esigelec.gsi.quizintegration.utils.SingletonPersonne;

/**
 * Created by Kevin-Giroux on 11/01/2016. Package : fr.esigelec.gsi.quizintegration.Activity Project Name : QuizIntegration
 */
public class InscriptionActivity extends AppCompatActivity implements View.OnClickListener
{
	private Personne pers;
	private Map<Integer,String> idError;
	@Override
	protected void onCreate (Bundle savedInstanceState)
	{
		super.onCreate (savedInstanceState);
		setContentView (R.layout.activity_inscription);
		Button valid = (Button) findViewById(R.id.valid);
		Button cancel = (Button) findViewById(R.id.cancel);

		valid.setOnClickListener(this);
		cancel.setOnClickListener(this);

		pers = SingletonPersonne.getInstance().getPersonne();
		initMap();
	}

	private void initMap(){
		idError = new LinkedHashMap<>();
		idError.put(R.id.name,getString(R.string.error_field_required));
		idError.put(R.id.surname,getString(R.string.error_field_required));
		idError.put(R.id.mail,getString(R.string.error_invalid_email));
		idError.put(R.id.pwd,getString(R.string.error_invalid_password));
		idError.put(R.id.confirm,getString(R.string.error_invalid_password));
	}

	private int getData(){
		int resultToReturn = 0;
		int comptor = 1;
		for(Map.Entry<Integer,String> entry : idError.entrySet()){
			EditText editText = (EditText) findViewById(entry.getKey());
			int result = pers.fillPersonneByIndex(comptor,editText.getText().toString());
			if(result == -1){
				editText = (EditText) findViewById(entry.getKey());
				editText.setError(entry.getValue());
				resultToReturn = -1;
			}
			comptor++;
		}
		Toast.makeText(getApplicationContext(),pers.toString(),Toast.LENGTH_LONG).show();
		return resultToReturn;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.valid :
				int result = getData();
				if(result == 0){
					try{
						JSONObject perJson = new AndroidHTTPRequest().execute(MainActivity.IPSERVER + "AndroidInscriptionPersonne.do", "POST", AndroidHTTPRequest.createParamString(pers.PersonneToHashMap())).get();
						Toast.makeText(getApplicationContext(),perJson.toString(),Toast.LENGTH_LONG).show();
						pers.JSONObjectToPersonne(perJson);
						Toast.makeText(getApplicationContext(),pers.toString(),Toast.LENGTH_LONG).show();
						setResult(RESULT_OK);
						finish();
					}catch (Exception ex){
						Log.e("ERROR", ex.getMessage());
						Toast.makeText(getApplicationContext(),getString(R.string.error_occur_sub),Toast.LENGTH_LONG).show();
					}
				}
				break;
			case R.id.cancel:
				setResult(RESULT_CANCELED);
				finish();
				break;
		}
	}
}
