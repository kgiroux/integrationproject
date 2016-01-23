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
 * Implemented by Kévin PACE
 */
public class ListenerQuestion
{
	/***
	 * Fonction permettant le retour de la question ou de la réponse pour le quiz en cours
	 * @param requestType = valeur permettant d'identifier le type de requête envoyé au serveur
	 *                 0 -> Récupération de la question
	 *                 1 -> Récupération de la réponse
	 * @return un objet JSON contenant les informations de retour
	 */
	public JSONObject OnListener(int requestType) {
		JSONObject objJson = null;
		boolean resend = true;
		do {
			try
			{
				objJson = new AndroidHTTPRequest ().execute (new String[]{MainActivity.IPSERVER + "AndroidJouer.do", "POST", "queryType="+requestType}).get ();

			}catch(Exception ex)
			{
				Log.e("ERROR",ex.getMessage());
			}
		}while(objJson == null && objJson.isNull("Question") && objJson.isNull("Reponse"));

		return objJson;
	}
}
