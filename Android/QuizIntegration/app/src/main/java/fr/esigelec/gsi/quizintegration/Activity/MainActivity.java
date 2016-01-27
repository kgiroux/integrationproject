package fr.esigelec.gsi.quizintegration.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import fr.esigelec.gsi.quizintegration.objects.Personne;
import fr.esigelec.gsi.quizintegration.R;
import fr.esigelec.gsi.quizintegration.adapter.CustomActionBarDrawerToggle;
import fr.esigelec.gsi.quizintegration.adapter.ExpandableListAdapter;
import fr.esigelec.gsi.quizintegration.utils.AndroidHTTPRequest;
import fr.esigelec.gsi.quizintegration.utils.ErrorManager;
import fr.esigelec.gsi.quizintegration.utils.SingletonErrorManager;
import fr.esigelec.gsi.quizintegration.utils.SingletonPersonne;

/**
 * Created by Kevin-Giroux on 11/01/2016. Package : fr.esigelec.gsi.quizintegration.activity Project Name : QuizIntegration
 * Edited by Cyril Lefebvre on 16/01/2016
 */
public class MainActivity extends Activity implements View.OnClickListener
{
	private DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mDrawerToggle;
	List<String> groupList;
	private final int REQUEST_CODE_INSCRIPTION = 42;
	Map<String, List<String>> ItemCollection;
	private Dialog dialog;
	private Dialog configuration;
	private Toolbar toolbar;
	private Personne pers;

	@Override
	protected void onCreate (Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//Création de l'instance unique de la personne connecté
		pers = SingletonPersonne.getInstance ().getPersonne ();

        //Mise en forme de la Toolbar
		toolbar = (Toolbar) findViewById (R.id.tool_bar);
		toolbar.setTitle(R.string.app_name);
        toolbar.setTitleTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));

        //Affichage des items dans le menu
		create_expandable_list();

		//Création de l'object popUp de connexion
		dialog = createAndManageDialog();

		// Création de l'objet PopUp de configuration de l'IP;
		configuration = createAndManageConfigurationDialog();

        //Mise en forme des boutons et ajout du listener
		Button button = (Button) findViewById (R.id.start);
        button.setTypeface(WelcomeActivity.quizFont);
        button.setOnClickListener (this);
        Button quit = (Button) findViewById (R.id.quit);
        quit.setTypeface(WelcomeActivity.quizFont);
		quit.setOnClickListener (this);
	}

    //Méthode d'ouverture du menu
	public void create_drawer ()
	{
		mDrawerLayout = (DrawerLayout) findViewById (R.id.drawer_layout);
		mDrawerToggle = new CustomActionBarDrawerToggle (this, mDrawerLayout,toolbar);
	}

    //Méthode d'affichage des items dans le menu
	public void create_expandable_list ()
	{
		final ExpandableListView mDrawerExpandableList;
		ExpandableListAdapter expListAdapter;
		createGroupList ();
		createCollection ();
		// récupération des différents élements pour le menu
		mDrawerLayout = (DrawerLayout) findViewById (R.id.drawer_layout);
		mDrawerExpandableList = (ExpandableListView) findViewById (R.id.drawer_list);
		// Création de l'adapter
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
						mDrawerLayout.closeDrawer (mDrawerExpandableList);
						configuration.show ();
						break;
					case 1:
						// On clique sur connexion
						mDrawerLayout.closeDrawer (mDrawerExpandableList);
						// On ouvre une boite de dialogue demande la connexion
						dialog.show ();
						if(WelcomeActivity.DEBUG)
							Toast.makeText (getApplicationContext (), R.string.connexion, Toast.LENGTH_LONG).show ();
						break;
					case 2:
						// On clique sur inscription
						mDrawerLayout.closeDrawer (mDrawerExpandableList);
						t = new Intent(getApplicationContext (),InscriptionActivity.class);

						// On lance l'application mais on attend un résultat
						startActivityForResult (t,REQUEST_CODE_INSCRIPTION);
						if(WelcomeActivity.DEBUG)
							Toast.makeText (getApplicationContext (), R.string.inscription, Toast.LENGTH_LONG).show ();
						break;

					case 3:
						// On clique sur About
						mDrawerLayout.closeDrawer (mDrawerExpandableList);
						t = new Intent(getApplicationContext (),AboutActivity.class);
						if(WelcomeActivity.DEBUG)
							Toast.makeText (getApplicationContext (), R.string.about, Toast.LENGTH_LONG).show ();
						// On lance l'activité
						startActivity (t);
						break;
					case 4:
						// On clique sur Légales Notices
						mDrawerLayout.closeDrawer (mDrawerExpandableList);
						t = new Intent (getApplicationContext (),LegalNoticeActivity.class);
						if(WelcomeActivity.DEBUG)
							Toast.makeText (getApplicationContext (), R.string.mentionlegales, Toast.LENGTH_LONG).show ();
						// On lance l'activité pour les légales Notices
						startActivity (t);
						break;
					case 5:
						// On quite l'application;
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
		// On charge le menu disponible dans string.xml
		String listItem[] = getResources ().getStringArray (R.array.listMenu);
		groupList = new ArrayList<> ();

		for (int i = 0; i != listItem.length; i++)
		{
			if(WelcomeActivity.DEBUG)
				Log.e ("DEBUG",listItem[i]);
			groupList.add (listItem[i]);
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data){
		// Après le retour de l'activité Inscription

		// Si l'utilisateur n'a pas annuler
		if(resultCode != RESULT_CANCELED){
			switch (requestCode){
				case REQUEST_CODE_INSCRIPTION :
					// On ouvre la fenetre de connexion
					dialog.show();
					break;
			}
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
		super.onConfigurationChanged (newConfig);
		mDrawerToggle.onConfigurationChanged (newConfig);
	}

	@Override
	public void onClick (View v)
	{
        //Gestion des boutons de la page
		switch(v.getId ()){
			case R.id.start :
                //Affichage de la boite de dialogue de connexion
				dialog.show ();
				break;
			case R.id.quit :
				finish();
				break;
		}
	}

    //Méthode d'initialisation et de gestion de la boite de dialogue de connexion
	private Dialog createAndManageDialog(){
		final Dialog dialog = new Dialog (MainActivity.this);
		SharedPreferences preferences =  PreferenceManager.getDefaultSharedPreferences(getApplicationContext ());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.login_dialog);

        //Application de la police sur les TextView
        TextView logText = (TextView) dialog.findViewById(R.id.titleText);
        logText.setTypeface(WelcomeActivity.quizFont);

		TextView email = (TextView) dialog.findViewById(R.id.login);
        email.setText (preferences.getString ("mail",""));

		TextView mdp = (TextView) dialog.findViewById(R.id.password);
        mdp.setText (preferences.getString ("mdp",""));

        //Mise en forme et action des boutons

        //Subscribe
		Button subscribeButton = (Button) dialog.findViewById (R.id.Register);
        subscribeButton.setTypeface(WelcomeActivity.quizFont);
		subscribeButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
                //Lancement de l'activité d'inscription
				Intent t = new Intent(getApplicationContext(), InscriptionActivity.class);
				startActivityForResult(t, REQUEST_CODE_INSCRIPTION);
			}
		});

        //Log In
		Button signIn = (Button) dialog.findViewById (R.id.SignIn);
        signIn.setTypeface(WelcomeActivity.quizFont);
		signIn.setOnClickListener (new View.OnClickListener ()
		{
			@Override
			public void onClick (View v)
			{
                //Variable définissant si les informations de connexion sont bonnes
				boolean isEmailValid = true;
				boolean isMdpValid = true;

                /*
                Définition et récupération de l'EditText Email
                en vérifiant que celui-ci n'est pas vide
                 */
				EditText emailOrPseudo = (EditText)dialog.findViewById (R.id.login);
				String loginValue = emailOrPseudo.getText ().toString ();
				if("".equals (loginValue)){
                    //Affichage d'un logo d'erreur et passage de la variable de validité de l'email à faux
					emailOrPseudo.setError (getString (R.string.error_invalid_email));
					isEmailValid = false;
				}

                /*
                Définition et récupération de l'EditText Password
                en vérifiant que celui-ci n'est pas vide
                 */
				EditText password = (EditText) dialog.findViewById (R.id.password);
				String passwordValue = password.getText ().toString ();
				if("".equals (passwordValue)){
                    //Affichage d'un logo d'erreur et passage de la variable de validité du password à faux
					password.setError (getString (R.string.error_invalid_password));
					isMdpValid = false;
				}

                //On set les variable du singleton personne avec les variable entrées
                pers.setMail(loginValue);
				pers.setMdp(passwordValue);
				pers.setNoEncryMdp (passwordValue);

                //Envois du singleton personne au serveur via JSON pour vérifier les identifiants
				try
				{
					JSONObject perJson = new AndroidHTTPRequest().execute(new String[]{WelcomeActivity.IPSERVER + "AndroidConnexionPersonne.do", "POST", AndroidHTTPRequest.createParamString(pers.PersonneToHashMap())}).get();
					if(null != perJson){
						if(perJson.has("err_code")){
                            /*
                            Affichage d'un message d'erreur en cas d'échec de connexion et
                            passage des variable de connexion à faux
                            */
							int err_code = perJson.getInt("err_code");
							ErrorManager error = SingletonErrorManager.getInstance().getError();
							Toast.makeText(getApplicationContext(),error.errorManager(err_code), Toast.LENGTH_LONG).show();
							isMdpValid = false;
							isEmailValid = false;
						}else{
                            //Affichage d'un message d'erreur un cas de conneion réussie
							pers.JSONObjectToPersonne(perJson);
							Toast.makeText(getApplicationContext(),pers.toString(),Toast.LENGTH_LONG).show();
						}
					}else{
                        //On affiche un message d'erreue en cas de non réponse du serveur
						TextView tv = (TextView) dialog.findViewById(R.id.errorText);
						tv.setText(getString(R.string.error_connection));
					}
				}
				catch(Exception ex)
				{
					Log.e("ERROR",ex.getMessage());
					TextView tv = (TextView) dialog.findViewById(R.id.errorText);
					tv.setText(getString(R.string.error_connection));
                    isEmailValid = false;
                    isMdpValid = false;
				}
				if(WelcomeActivity.DEV){
					isEmailValid = true;
					isMdpValid = true;
				}

                /*
                Si les variables sont restées à true et que l'ide de la personne a été récupéré,
                on renseigne les variable renvoyé par le serveur dans la personne et on lance le menu
                */
				if(isEmailValid && isMdpValid && (pers.getId () != 0)){
					SharedPreferences preferences =  PreferenceManager.getDefaultSharedPreferences(getApplicationContext ());
					SharedPreferences.Editor editor = preferences.edit();
					editor.putString ("mail",pers.getMail());
					editor.putString("mdp", pers.getNoEncryMdp());
					editor.apply();

					Intent t = new Intent (getApplicationContext (), MenuActivity.class);
					startActivity (t);
					finish();
				}
			}
		});
		return dialog;
	}

	private Dialog createAndManageConfigurationDialog(){
		final Dialog dialog = new Dialog (MainActivity.this);
		SharedPreferences preferences =  PreferenceManager.getDefaultSharedPreferences(getApplicationContext ());
		final SharedPreferences.Editor editor = preferences.edit ();
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView (R.layout.configuration_dialog);

		final EditText text = (EditText) dialog.findViewById (R.id.IPserverValue);
		Button valide = (Button) dialog.findViewById (R.id.Validate);
		Button cancel = (Button) dialog.findViewById (R.id.cancel);

		valide.setOnClickListener (new View.OnClickListener ()
		{
			@Override
			public void onClick (View v)
			{
				String IP = text.getText ().toString ();
				try
				{
					JSONObject perJson = new AndroidHTTPRequest().execute(new String[]{IP + "AndroidConnexionPersonne.do", "POST", AndroidHTTPRequest.createParamString(pers.PersonneToHashMap())}).get();
					// On teste si on à une erreur afin de verifier que l'IP du serveur est correcte
					if(perJson.has("err_code")){
						editor.putString ("IPSERVER",IP);
						editor.apply ();
						dialog.cancel ();
						Toast.makeText (getApplicationContext (),getResources ().getString (R.string.confirmIP), Toast.LENGTH_LONG).show ();
						Toast.makeText (getApplicationContext (),getResources ().getString (R.string.nextDO), Toast.LENGTH_LONG).show ();
					}
				}
				catch (InterruptedException |ExecutionException e)
				{
					e.printStackTrace ();
				}
			}
		});

		cancel.setOnClickListener (new View.OnClickListener ()
		{
			@Override
			public void onClick (View v)
			{
				dialog.cancel ();
			}
		});
		return dialog;
	}
}
