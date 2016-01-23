package fr.esigelec.gsi.quizintegration.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
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
    private int idQuiz = -1;
    private Button buttons[] = new Button[4];
	private TextView timer;
    private final Handler gameHandler = new Handler();

    //Thread pour la récupération d'infos
    Thread requestThread;

	@Override
	protected void onCreate (Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		idQuiz = getIntent ().getIntExtra ("idQuiz",0);

        //Récupération des boutons
        buttons[0] = (Button) findViewById(R.id.choice_one);
        buttons[1] = (Button) findViewById(R.id.choice_two);
        buttons[2] = (Button) findViewById(R.id.choice_three);
        buttons[3] = (Button) findViewById(R.id.choice_four);

        //Create question
        question = new Question();
    }

    @Override
    protected void onResume()
    {
        //Chargement des informations de la question
        onQuizListener(0);
        //initTest();
        super.onResume();
    }

    @Override
    protected void onPause() {

        //Désactivation des threads de la partie jouer
        requestThread.interrupt();
        requestThread = null;

        super.onPause();
    }

    //Méthode d'initialisation des IHMs
    private void initQuestionIHM(){
        TextView titleQuestion = (TextView) findViewById(R.id.quest_number);
        titleQuestion.setText("Question " + (question.getNumQuestion() + 1) + ":");

        TextView questionText = (TextView) findViewById(R.id.question);

        questionText.setText(question.getLibelle());
        for(int i=0; i < 4;i++)
        {
            //Reset butons
            buttons[i].setClickable(true);
            buttons[i].setEnabled(true);
            buttons[i].setSelected(false);
            buttons[i].setActivated(false);

            //Définition du contenu des boutons
            buttons[i].setText(question.getListePropositions().get(i).getLibelle());
            buttons[i].setOnClickListener(this);
        }

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
        question = new Question(1,"De quelle année date le premier Iphone ?");
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

    //Méthode de création de la question
    protected void createQuestion(JSONObject qtJson)
    {
        //Remplissage de l'objet question de l'interface courante
        if(qtJson != null)
            question.JSONObjectToQuestion(qtJson);

        //Réactivation des bouttons
        for(int i=0;i<4;i++)
            buttons[i].setClickable(true);

        //Initialisation des IHMs
        initQuestionIHM();

        //Récupération du timer et lancement du thread
        timer = (TextView) findViewById(R.id.timer);
        try {
            createTimer(qtJson.getInt("timer"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //Méthode d'affichage de la réponse
    protected void displayResponse(int idProp)
    {
        //Affichage de la réponse en surbrillant vert
        List<Proposition> props = question.getListePropositions();
        for(int i=0; i<props.size();i++) {
            if (props.get(i).getId() == idProp)
            {
                buttons[i].setActivated(true);
                buttons[i].setEnabled(true);
                break;
            }
        }

        //Attente de la question suivante
        onQuizListener(0);
    }

    @Override
    public void onClick (View v)
    {
        //Variable récupérant l'id de la proposition sélectionnée
        int idProposition = -1;

        if(!"0".equals(timer.getText())) {
            //Changement de la couleur du bouton sélectionné et désactivation des autres boutons
            switch (v.getId()) {
                case R.id.choice_one:
                    buttons[0].setClickable(false);
                    buttons[0].setSelected(true);
                    buttons[1].setEnabled(false);
                    buttons[2].setEnabled(false);
                    buttons[3].setEnabled(false);
                    idProposition = question.getListePropositions().get(0).getId();
                    break;
                case R.id.choice_two:
                    buttons[0].setEnabled(false);
                    buttons[1].setSelected(true);
                    buttons[1].setClickable(false);
                    buttons[2].setEnabled(false);
                    buttons[3].setEnabled(false);
                    idProposition = question.getListePropositions().get(1).getId();
                    break;
                case R.id.choice_three:
                    buttons[0].setEnabled(false);
                    buttons[1].setEnabled(false);
                    buttons[2].setSelected(true);
                    buttons[2].setClickable(false);
                    buttons[3].setEnabled(false);
                    idProposition = question.getListePropositions().get(2).getId();
                    break;
                case R.id.choice_four:
                    buttons[0].setEnabled(false);
                    buttons[1].setEnabled(false);
                    buttons[2].setEnabled(false);
                    buttons[3].setClickable(false);
                    buttons[3].setSelected(true);
                    idProposition = question.getListePropositions().get(3).getId();
                    break;
            }

            //Création de l'objet du choix de l'utilisateur
            Choisir chx = new Choisir();
            chx.setPersonne(SingletonPersonne.getInstance().getPersonne().getId());
            chx.setQuiz(idQuiz);
            chx.setProposition(idProposition);

            //Booléen vérifiant si l'envoie a été effectué
            boolean send = false;

            //Envoie du choix de l'utilisateur au serveur
            try {
                JSONObject choiceJSON = new AndroidHTTPRequest().execute(new String[]{MainActivity.IPSERVER + "AndroidChoisir.do", "POST", AndroidHTTPRequest.createParamString(chx.ChoiceToHashMap())}).get();
                if (choiceJSON.has("err_code")) {
                    int err_code = choiceJSON.getInt("err_code");
                    ErrorManager error = SingletonErrorManager.getInstance().getError();

                    //Choix sauvegardé, passage de la variable de succés à true
                    if ("CHOICE_SAVE".equals(error.errorManager(err_code))) {
                        Toast.makeText(getApplicationContext(), error.errorManager(err_code), Toast.LENGTH_LONG).show();
                        send = true;
                    }
                    //Si erreur affichage de celle-ci
                    else {
                        Toast.makeText(getApplicationContext(), error.errorManager(err_code), Toast.LENGTH_LONG).show();
                    }
                }
            } catch (Exception ex) {
                Log.e("ERROR", ex.getMessage());
            }
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
                    gameHandler.post(new Runnable(){
                        @Override
                        public void run() {
                            timer.setText(Integer.toString(counter));
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    counter--;
                } while (counter >= 0);

                //Récupération de la réponse à la fin du timer
                gameHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        onQuizListener(1);

                        //Désactivation des boutons
                        for (int i=0; i<4 ;i++)
                        {
                            buttons[i].setClickable(false);
                            buttons[i].setEnabled(false);
                        }
                    }
                });
            }
        }.start();
    }

    /** Méthode de lancement de l'écoute d'un changement d'état du quiz
     * @param requestType Indique si ce que l'on souhaite récupérer
     *                    0-> Récupération de la question
     *                    1-> Récupération de la réponse
     */
    private void onQuizListener(final int requestType)
    {
        requestThread = new Thread() {
            JSONObject objJson = null;
            boolean resend = true;
            int requestCode = requestType;

            @Override
            public void run() {
                do {
                    try {
                        objJson = new AndroidHTTPRequest().execute(new String[]{MainActivity.IPSERVER + "AndroidJouer.do", "POST", "queryType=" + requestCode + "&idQuestion=" + question.getId()}).get();

                        //Vérification si le JSON contient les informations désirées
                        if (objJson != null)
                            if (objJson.has("Question") || objJson.has("Reponse"))
                                resend = false;

                        //Attendre 500ms avant le renvoie d'une autre requête
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    } catch (Exception ex) {
                        Log.e("ERROR", ex.getMessage());
                    }
                } while (resend && !this.isInterrupted());

                //Exécution de la méthode en lien avec la réponse
                if(!this.isInterrupted())
                {
                    try {
                        switch (requestCode) {
                            case 0: //On veut la question
                                final JSONObject qt = objJson.getJSONObject("Question");
                                gameHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        createQuestion(qt);
                                    }
                                });
                                break;
                            case 1: //On veut la réponse
                                final int rp = objJson.getInt("Reponse");
                                gameHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        displayResponse(rp);
                                    }
                                });
                                break;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        requestThread.start();
    }
}
