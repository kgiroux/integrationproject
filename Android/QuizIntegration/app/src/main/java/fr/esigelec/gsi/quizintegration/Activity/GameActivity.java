package fr.esigelec.gsi.quizintegration.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import fr.esigelec.gsi.quizintegration.Objects.Choisir;
import fr.esigelec.gsi.quizintegration.Objects.Proposition;
import fr.esigelec.gsi.quizintegration.Objects.Question;
import fr.esigelec.gsi.quizintegration.R;
import fr.esigelec.gsi.quizintegration.utils.AndroidHTTPRequest;
import fr.esigelec.gsi.quizintegration.utils.ErrorManager;
import fr.esigelec.gsi.quizintegration.utils.SingletonErrorManager;
import fr.esigelec.gsi.quizintegration.utils.SingletonPersonne;

/**
 * Created by Cyril Lefebvre on 11/01/2016. Package : fr.esigelec.gsi.quizintegration.Activity Project Name : QuizIntegration
 * Edited by Kévin PACE on 18/01/2016
 */
public class GameActivity extends Activity implements View.OnClickListener
{
    private Question question;
    private int respGiven = 0;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
	private TextView timer;
    private final Handler timerHandler = new Handler();

	@Override
	protected void onCreate (Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		int idQuiz = getIntent ().getIntExtra ("idQuiz",0);

        boolean TEST = true;

        //Initialisation pour test
        initTest();

        //Initialisation des IHMs
        initIHM();

        //Récupération du timer et lancement du thread
        timer = (TextView) findViewById(R.id.timer);
        //createTimer(28);


    }

    //Méthode d'initialisation des IHMs
    private void initIHM(){
        TextView questionText = (TextView) findViewById(R.id.question);
        button1 = (Button) findViewById(R.id.choice_one);
        button2 = (Button) findViewById(R.id.choice_two);
        button3 = (Button) findViewById(R.id.choice_three);
        button4 = (Button) findViewById(R.id.choice_four);

        questionText.setText(question.getLibelle());
        button1.setText(question.getListePropositions().get(0).getLibelle());
        button2.setText(question.getListePropositions().get(1).getLibelle());
        button3.setText(question.getListePropositions().get(2).getLibelle());
        button4.setText(question.getListePropositions().get(3).getLibelle());

		button1.setOnClickListener(this);
		button2.setOnClickListener(this);
		button3.setOnClickListener(this);
		button4.setOnClickListener(this);

        ImageButton quit = (ImageButton) findViewById(R.id.quit);
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //Méthode d'initialisation de la question pour test
    protected void initTest() {
        question = new Question(1,"De quelle année date le premier Iphone ?", 3);
        List<Proposition> propositionList = new ArrayList<>();
        Proposition proposition1 = new Proposition(1, "2001");
        Proposition proposition2 = new Proposition(2, "2005");
        Proposition proposition3 = new Proposition(3, "2007");
        Proposition proposition4 = new Proposition(4, "2010");
        propositionList.add(proposition1);
        propositionList.add(proposition2);
        propositionList.add(proposition3);
        propositionList.add(proposition4);
        question.setListePropositions(propositionList);
    }

    @Override
    public void onClick (View v)
    {
        //Variable récupérant l'id de la proposition sélectionnée
        int idProposition = -1;

        //Changement de la couleur du bouton sélectionné et désactivation des autres boutons
        switch (v.getId()){
            case R.id.choice_one :
                button1.setClickable(false);
                button1.setSelected(true);
                button2.setEnabled(false);
                button3.setEnabled(false);
                button4.setEnabled(false);
                idProposition =  question.getListePropositions ().get (0).getId ();
                break;
            case R.id.choice_two :
                button1.setEnabled(false);
                button2.setSelected(true);
                button2.setClickable(false);
                button3.setEnabled(false);
                button4.setEnabled(false);
                idProposition =  question.getListePropositions ().get (1).getId ();
                break;
            case R.id.choice_three :
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setSelected(true);
                button3.setClickable(false);
                button4.setEnabled(false);
                idProposition =  question.getListePropositions ().get (2).getId ();
                break;
            case R.id.choice_four :
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
                button4.setClickable(false);
                button4.setSelected(true);
                idProposition = question.getListePropositions().get(3).getId ();
                break;
        }

        //Création de l'objet du choix de l'utilisateur
        Choisir chx = new Choisir ();
        chx.setPersonne(SingletonPersonne.getInstance().getPersonne().getId());
        chx.setQuiz(1);
        chx.setProposition(idProposition);

        //Booléen vérifiant si l'envoie a été effectué
        boolean send = false;

        //Envoie du choix de l'utilisateur au serveur
        try
        {
            JSONObject choiceJSON = new AndroidHTTPRequest().execute(new String[]{MainActivity.IPSERVER + "AndroidChoisir.do", "POST", AndroidHTTPRequest.createParamString(chx.ChoiceToHashMap())}).get();
            if(choiceJSON.has("err_code"))
            {
                int err_code = choiceJSON.getInt("err_code");
                ErrorManager error = SingletonErrorManager.getInstance().getError();

                //Choix sauvegardé, passage de la variable de succés à true
                if("CHOICE_SAVE".equals(error.errorManager(err_code))){
                    Toast.makeText(getApplicationContext(),error.errorManager(err_code), Toast.LENGTH_LONG).show();
                    send = true;
                }
                //Si erreur affichage de celle-ci
                else
                {
                    Toast.makeText(getApplicationContext(),error.errorManager(err_code),Toast.LENGTH_LONG).show();
                }
            }
        }
        catch(Exception ex)
        {
            Log.e("ERROR",ex.getMessage());
        }
    }

    //Méthode de lancement du thread timer
    private void createTimer(final int initialValue)
    {
        new Thread() {
            int counter = initialValue;
            @Override
            public void run() {
                do {
                    timerHandler.post(new Runnable(){
                        @Override
                        public void run() {
                            timer.setText(Integer.toString(counter));
                        }
                    });
                    counter--;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }while(counter > 0);
            }
        }.start();
    }
}
