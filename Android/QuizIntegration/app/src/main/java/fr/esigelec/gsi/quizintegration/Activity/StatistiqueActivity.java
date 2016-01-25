package fr.esigelec.gsi.quizintegration.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import fr.esigelec.gsi.quizintegration.objects.Personne;
import fr.esigelec.gsi.quizintegration.objects.Quiz;
import fr.esigelec.gsi.quizintegration.R;
import fr.esigelec.gsi.quizintegration.adapter.PersonneTableDataAdapter;
import fr.esigelec.gsi.quizintegration.utils.AndroidHTTPRequest;
import fr.esigelec.gsi.quizintegration.utils.ErrorManager;
import fr.esigelec.gsi.quizintegration.utils.SingletonErrorManager;
import fr.esigelec.gsi.quizintegration.utils.SortablePersonneTableView;

/**
 * Created by Kevin-Giroux on 22/01/2016. Package : fr.esigelec.gsi.quizintegration.activity Project Name : QuizIntegration
 *
 */


public class StatistiqueActivity extends AppCompatActivity {


	private List<Personne> personneArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistique);
		int idQuiz = getIntent ().getIntExtra ("idQuiz",0);

		Quiz q = new Quiz ();
		q.setId (idQuiz);

        // Si l'ID du QUIZ est 0 => ERROR
		if(idQuiz == 0){
            ErrorManager errorManager = SingletonErrorManager.getInstance().getError();
            Toast.makeText(getApplicationContext(),errorManager.errorManager(8), Toast.LENGTH_LONG).show();
            // On revient à l'activité précédente, et on affiche un message d'erreur
            finish();
		}else{
			try {
                // On essaye de récuperer le resultat du test
				JSONObject quizJson = new AndroidHTTPRequest ().execute(new String[]{WelcomeActivity.IPSERVER + "AndroidStatistique.do", "POST",AndroidHTTPRequest.createParamString (q.QuizToHashMap ()) }).get();
				// On va parser le resultat
                JSONArray array = quizJson.getJSONArray ("Scores");
				personneArrayList = new ArrayList<> ();
				for(int i =0; i<array.length (); i++)
				{
                    //  On ajoute tout les participants aux quiz dans une liste qui va nous permettre de generer le tableau des scores
					JSONObject j = array.getJSONObject (i);
					Personne p = new Personne ();
                    // On renseigne les différents champs
					p.setId (j.getInt ("id"));
					p.setNom (j.getString ("nom"));
					p.setPrenom (j.getString ("prenom"));
					p.setScore (j.getInt ("score"));
					personneArrayList.add (p);
				}
			} catch (InterruptedException | ExecutionException | JSONException e) {
                // Si une exeception se produit
                ErrorManager errorManager = SingletonErrorManager.getInstance().getError();
                Toast.makeText(getApplicationContext(),errorManager.errorManager(9), Toast.LENGTH_LONG).show();
				e.printStackTrace();
                finish();
			}
            Collections.sort(personneArrayList);
            // On définit un classement
            int classement = 1;
            for (Personne p : personneArrayList){
                p.setClassement (classement);
                classement++;
            }
            initIHM();
		}


    }

	public void initIHM(){
        SortablePersonneTableView tableView = (SortablePersonneTableView) findViewById (R.id.tableView);
        tableView.setDataAdapter (new PersonneTableDataAdapter (this,personneArrayList));
	}
}
