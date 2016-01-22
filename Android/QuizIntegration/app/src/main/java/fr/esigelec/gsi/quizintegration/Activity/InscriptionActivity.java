package fr.esigelec.gsi.quizintegration.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.style.SuperscriptSpan;
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
import fr.esigelec.gsi.quizintegration.utils.ErrorManager;
import fr.esigelec.gsi.quizintegration.utils.SingletonErrorManager;
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
		// Définition de layout
		setContentView (R.layout.activity_inscription);
		// Récupération des boutons disponibles
		Button valid = (Button) findViewById(R.id.valid);
		Button cancel = (Button) findViewById(R.id.cancel);

		// Définition des listeners
		valid.setOnClickListener(this);
		cancel.setOnClickListener(this);

		// Définition de la HashMap()
		initMap();
	}

	private void initMap(){

		// Association de l'ID de EditText avec un code d'erreur
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
		//Récupération des différents Input de l'inscription afin de remplir un objet personne afin de l'envoyer au serveur
		for(Map.Entry<Integer,String> entry : idError.entrySet()){
			// Récupération des edit texts
			EditText editText = (EditText) findViewById(entry.getKey());
			int result = pers.fillPersonneByIndex(comptor,editText.getText().toString());
			// Si result est égale à -1, il y a une erreur dans un des champs EditText
			if(result == -1)
			{
				editText = (EditText) findViewById (entry.getKey ());
				editText.setError (entry.getValue ());
				resultToReturn = -1;
			}
			//On passe à l'édit Text suivant en changeant l'index pour la methode FillPersonneByIndex
			comptor++;
		}
		// On affiche la personne dans un message
		Toast.makeText(getApplicationContext(),pers.toString(),Toast.LENGTH_LONG).show();
		return resultToReturn;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			// Quand l'utilisateur clique sur Valider
			case R.id.valid :
				// Création de l'instance de Personne
				pers = new Personne();
				// On récupère les informations via l'appel de la méthode getData();
				int result = getData();
				// Si résult égale 0, pas d'erreur trouvé
				if(result == 0){
					// Méthode envoyant un requete post pour l'inscription vers le serveur
					try{

						JSONObject perJson = new AndroidHTTPRequest().execute(new String[]{MainActivity.IPSERVER + "AndroidInscriptionPersonne.do", "POST", AndroidHTTPRequest.createParamString(pers.PersonneToHashMap())}).get();
						if(null != perJson)
						{
							if (perJson.has ("err_code"))
							{
								// S'il y un code d'erreur
								int err_code = perJson.getInt("err_code");
								ErrorManager error = SingletonErrorManager.getInstance().getError();
								Toast.makeText(getApplicationContext(),error.errorManager(err_code), Toast.LENGTH_LONG).show();
							}else{
								pers.JSONObjectToPersonne(perJson);
								Toast.makeText(getApplicationContext(),pers.toString(),Toast.LENGTH_LONG).show();
								setResult(RESULT_OK);
								finish();
							}
						}
					}catch (Exception ex){
						// Erreur en cas de problème d'inscription
						Log.e("ERROR", ex.getMessage());
						Toast.makeText(getApplicationContext(),getString(R.string.error_occur_sub),Toast.LENGTH_LONG).show();
					}
				}
				break;
			case R.id.cancel:
				// On annule l'inscription
				setResult(RESULT_CANCELED);
				finish();
				break;
		}
	}
}
