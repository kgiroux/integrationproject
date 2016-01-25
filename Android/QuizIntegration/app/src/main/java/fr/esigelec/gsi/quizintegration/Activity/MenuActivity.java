package fr.esigelec.gsi.quizintegration.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import fr.esigelec.gsi.quizintegration.objects.Personne;
import fr.esigelec.gsi.quizintegration.objects.Quiz;
import fr.esigelec.gsi.quizintegration.R;
import fr.esigelec.gsi.quizintegration.adapter.CustomActionBarDrawerToggle;
import fr.esigelec.gsi.quizintegration.adapter.ExpandableListAdapter;
import fr.esigelec.gsi.quizintegration.utils.AndroidHTTPRequest;
import fr.esigelec.gsi.quizintegration.utils.ErrorManager;
import fr.esigelec.gsi.quizintegration.utils.SingletonErrorManager;
import fr.esigelec.gsi.quizintegration.utils.SingletonPersonne;

/**
 * Created by Kevin-Giroux on 11/01/2016. Package : fr.esigelec.gsi.quizintegration.activity Project Name : QuizIntegration
 * Edited by Kevin Pace and Cyril Lefebvre on 22/01/2016
 */
public class MenuActivity extends Activity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener
{
	private DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mDrawerToggle;
	List<String> groupList;
	Map<String, List<String>> ItemCollection;
	private Toolbar toolbar;
	private Personne pers;
    private List<Quiz> quizList = null;
    private Quiz currentQuiz = null;
	private SwipeRefreshLayout layout;
	
	@Override
	protected void onCreate (Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		// Récupération de l'unique instance de Personne
		pers = SingletonPersonne.getInstance ().getPersonne();


		// Chargement de la toolbar via récupération dans le layoute
		toolbar = (Toolbar) findViewById (R.id.tool_bar);
		// Définition du titre
		toolbar.setTitle(R.string.app_name);
		// Définition de la couleur
        toolbar.setTitleTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));

		 layout = (SwipeRefreshLayout) findViewById (R.id.swipe_container);
		layout.setOnRefreshListener(this);

		layout.setColorSchemeResources(android.R.color.holo_blue_bright,
				android.R.color.holo_green_light,
				android.R.color.holo_orange_light,
				android.R.color.holo_red_light);


		// Définition de la liste
		create_expandable_list();

	}

	public void initIHM()
	{
		try {

			JSONObject quizJson = new AndroidHTTPRequest().execute(new String[]{WelcomeActivity.IPSERVER + "AndroidQuizList.do", "GET", null}).get();

			if(quizJson != null) {
				//Get the list of all finished quiz from request
				if(quizJson.has("QuizList")) {
					quizList = new ArrayList<>();
					JSONArray quizListJson = quizJson.getJSONArray("QuizList");
					for (int i = 0; i < quizListJson.length(); i++) {
						JSONObject quiz = quizListJson.getJSONObject(i);
						Quiz q = new Quiz();
						q.JSONObjectToQuiz(quiz);
						quizList.add(q);
					}
				}

				//Save current quizz if exist
				if(quizJson.has("CurrentQuiz")) {
					JSONObject curQuiz = quizJson.getJSONObject("CurrentQuiz");
					currentQuiz = new Quiz();
					currentQuiz.JSONObjectToQuiz(curQuiz);
				}else
					currentQuiz = null;
			}

		} catch (InterruptedException | ExecutionException | JSONException e) {
			ErrorManager errorManager = SingletonErrorManager.getInstance().getError();
			Toast.makeText(getApplicationContext(),errorManager.errorManager(9), Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}

		TextView curText = (TextView) findViewById(R.id.current);
		TextView oldText = (TextView) findViewById(R.id.old);
		curText.setTypeface(WelcomeActivity.quizFont);
		oldText.setTypeface(WelcomeActivity.quizFont);

		final LinearLayout currentQuizLayout = (LinearLayout) findViewById(R.id.current_quiz);
		if (currentQuiz != null){

			//Montrer la vue affichant le quiz en cours
			currentQuizLayout.setVisibility(View.VISIBLE);
			curText.setVisibility(View.VISIBLE);

			//Remplissage de l'objet affichant le quiz courant
			TextView titre = (TextView) findViewById(R.id.title);
			titre.setText(currentQuiz.getLibelle());
			TextView date = (TextView) findViewById(R.id.date);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			date.setText(simpleDateFormat.format(currentQuiz.getDateDebutQuiz()));
			TextView nbQuest = (TextView) findViewById(R.id.question);
			String text = getString(R.string.question) + currentQuiz.getNoQuestionCourante() + getString(R.string.separator) + currentQuiz.getNbQuestion();
			nbQuest.setText(text);
		} else {
			//Masquage du champs pour le quiz courant
			currentQuizLayout.setVisibility(View.GONE);
			curText.setVisibility(View.GONE);
		}

		currentQuizLayout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), GameActivity.class);
				intent.putExtra("idQuiz", currentQuiz.getId());
				startActivity(intent);
			}
		});

		LinearLayout oldQuiz = (LinearLayout) findViewById(R.id.old_quiz);
		oldQuiz.removeAllViews();

		if(quizList != null)
		{
			if(quizList.size() >= 1) {
				oldText.setVisibility(View.VISIBLE);
				for (Quiz quiz : quizList) {
					LayoutInflater curQuizLayout = this.getLayoutInflater();
					View view = curQuizLayout.inflate(R.layout.old_quiz, null);
					view.setId(quiz.getId());
					TextView title = (TextView) view.findViewById(R.id.title);
					TextView date = (TextView) view.findViewById(R.id.date);

					title.setText(quiz.getLibelle());
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
					date.setText(simpleDateFormat.format(quiz.getDateDebutQuiz()));

					view.setOnClickListener(this);
					oldQuiz.addView(view);
				}
			}
			else
				oldText.setVisibility(View.GONE);
		}
	}


	public void create_drawer ()
	{
		mDrawerLayout = (DrawerLayout) findViewById (R.id.drawer_layout);
		mDrawerToggle = new CustomActionBarDrawerToggle (this, mDrawerLayout,toolbar);

		//Initialisation des informations sur les quiz
		initIHM();
	}

	public void create_expandable_list ()
	{
		final ExpandableListView mDrawerExpandableList;
		ExpandableListAdapter expListAdapter;
		createGroupList ();
		createCollection ();
		mDrawerLayout = (DrawerLayout) findViewById (R.id.drawer_layout);
		mDrawerExpandableList = (ExpandableListView) findViewById (R.id.drawer_list);
		expListAdapter = new ExpandableListAdapter (this, groupList, ItemCollection);
		mDrawerExpandableList.setGroupIndicator (null);
		mDrawerExpandableList.setAdapter (expListAdapter);
		create_drawer ();
		mDrawerLayout.setDrawerListener (mDrawerToggle);
		mDrawerExpandableList.setOnGroupClickListener (new ExpandableListView.OnGroupClickListener ()
		{
			@Override
			public boolean onGroupClick (ExpandableListView parent, View v, int groupPosition, long id)
			{
            Intent t;
            switch (groupPosition)
            {
                case 0:
					// On clique sur le nom de la personne
                    break;
                case 1:
					// On se déconnecte
                    t = new Intent(getApplicationContext (),MainActivity.class);
					// On revient à l'activité précédente
                    startActivity (t);
					if(WelcomeActivity.DEBUG)
						Toast.makeText (getApplicationContext (), R.string.bye, Toast.LENGTH_LONG).show ();
					finish();
                    break;
                case 2:
					// On affiche le About de l'application
                    t = new Intent(getApplicationContext (),AboutActivity.class);
					if(WelcomeActivity.DEBUG)
						Toast.makeText (getApplicationContext (), R.string.about, Toast.LENGTH_LONG).show ();
                    // On lance l'activité About
					startActivity (t);
                    break;
                case 3:
					//On affiche les mentions légales de l'application
                    t = new Intent (getApplicationContext (),LegalNoticeActivity.class);
					if(WelcomeActivity.DEBUG)
						Toast.makeText (getApplicationContext (), R.string.mentionlegales, Toast.LENGTH_LONG).show ();
                    // On lance l'activité concernant les mentions légales
					startActivity (t);
                    break;
                case 4:
					// On quitte l'application
                    Toast.makeText (getApplicationContext (), R.string.bye, Toast.LENGTH_LONG).show ();
                    finish ();
                    break;
            }
            return true;
			}
		});
	}
	//Méthode de remplissage des sous-niveaux du menu
	private void createCollection ()
	{
		ItemCollection = new LinkedHashMap<> ();
	}
	//Méthode de création des groupe d'item du menu
	private void createGroupList ()
	{
		String listItem[] = getResources ().getStringArray (R.array.listMenuActivity);
		groupList = new ArrayList<> ();
		// On ajoute le nom de la personne qui vient de se connecter
		groupList.add(pers.getNom() + " " + pers.getPrenom());
		for (int i = 1; i != listItem.length; i++)
		{
			groupList.add (listItem[i]);
		}
	}

	//Méthode qui change l'état du menu (open, close) après la création (OnCreate)
	protected void onPostCreate (Bundle savedInstanceState)
	{
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}
	//Méthode qui associe la toolbar au menu
	public void onConfigurationChanged (Configuration newConfig)
	{
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public void onClick (View v)
	{
			// Gestion de la liste des quiz terminés
			// On affiche les statistiques du quiz
			Intent t = new Intent (this,StatistiqueActivity.class);
			t.putExtra ("idQuiz",v.getId ());
			startActivity (t);
	}

	@Override
	public void onRefresh ()
	{
		// On rafraichit la liste
		new Handler ().postDelayed(new Runnable() {
			@Override
			public void run() {
				layout.setRefreshing(false);
				initIHM();
			}
		}, 1500);
	}
}
