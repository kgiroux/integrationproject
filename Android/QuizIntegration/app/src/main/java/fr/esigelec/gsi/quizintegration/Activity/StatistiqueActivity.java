package fr.esigelec.gsi.quizintegration.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;
import fr.esigelec.gsi.quizintegration.Objects.Personne;
import fr.esigelec.gsi.quizintegration.Objects.Quiz;
import fr.esigelec.gsi.quizintegration.R;
import fr.esigelec.gsi.quizintegration.adapter.PersonneTableDataAdapter;
import fr.esigelec.gsi.quizintegration.utils.AndroidHTTPRequest;
import fr.esigelec.gsi.quizintegration.utils.SortablePersonneTableView;

public class StatistiqueActivity extends AppCompatActivity {


	private List<Personne> personneArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistique);
		int idQuiz = getIntent ().getIntExtra ("idQuiz",0);

		Quiz q = new Quiz ();
		q.setId (idQuiz);


		if(idQuiz == 0){
			//TODO error
		}else{
			try {
				JSONObject quizJson = new AndroidHTTPRequest ().execute(new String[]{WelcomeActivity.IPSERVER + "AndroidStatistique.do", "POST",AndroidHTTPRequest.createParamString (q.QuizToHashMap ()) }).get();
				JSONArray array = quizJson.getJSONArray ("Scores");
				personneArrayList = new ArrayList<> ();
				for(int i =0; i<array.length (); i++)
				{
					JSONObject j = array.getJSONObject (i);
					Personne p = new Personne ();
					p.setId (j.getInt ("id"));
					p.setNom (j.getString ("nom"));
					p.setPrenom (j.getString ("prenom"));
					p.setScore (j.getInt ("score"));

					personneArrayList.add (p);
				}
			} catch (InterruptedException | ExecutionException | JSONException e) {
				e.printStackTrace();
			}
		}
		Collections.sort(personneArrayList);
		int classement = 1;
		for (Personne p : personneArrayList){
			p.setClassement (classement);
			classement++;
		}
		SortablePersonneTableView tableView = (SortablePersonneTableView) findViewById (R.id.tableView);
		tableView.setDataAdapter (new PersonneTableDataAdapter (this,personneArrayList));
    }

	public void initIHM(){


	}
}
