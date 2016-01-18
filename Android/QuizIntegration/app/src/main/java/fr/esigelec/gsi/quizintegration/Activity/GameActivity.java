package fr.esigelec.gsi.quizintegration.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MotionEvent;
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
 * Created by Kevin-Giroux on 11/01/2016. Package : fr.esigelec.gsi.quizintegration.Activity Project Name : QuizIntegration
 */


public class GameActivity extends Activity implements View.OnTouchListener
{
    private Question question;
    private int respGiven = 0;
    private String IPSERVER;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
	private TextView timer;
	@Override
	protected void onCreate (Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
        IPSERVER = MainActivity.IPSERVER;
        //Initialisation pour test
        initTest();
        initIHM();
		timer = (TextView) findViewById(R.id.timer);
        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
					TextView timer = (TextView) findViewById(R.id.timer);
                int second = Integer.parseInt(timer.getText().toString());
                timer.setText(String.valueOf(second - 1));
            }

            public void onFinish() {
                TextView timer = (TextView) findViewById(R.id.timer);
                timer.setText("00");
            }
        }.start();
    }

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

        button1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                button1.setPressed(true);
                button2.setEnabled(false);
                button3.setEnabled(false);
                button4.setEnabled(false);
                respGiven = 1;
                return true;
            }
        });
        button2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                button2.setPressed(true);
                button1.setEnabled(false);
                button3.setEnabled(false);
                button4.setEnabled(false);
                respGiven = 2;
                return true;
            }
        });
        button3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                button3.setPressed(true);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button4.setEnabled(false);
                respGiven = 3;
                return true;
            }
        });
        button4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                button4.setPressed(true);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
                respGiven = 4;
                return true;
            }
        });

        ImageButton quit = (ImageButton) findViewById(R.id.quit);
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

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
    public boolean onTouch (View v, MotionEvent event)
    {
        int idProposition = -1;
        switch (v.getId()){
            case R.id.choice_one :
                button1.setPressed(true);
                button2.setEnabled(false);
                button3.setEnabled(false);
                button4.setEnabled(false);
                idProposition =  question.getListePropositions ().get (0).getId ();
                break;
            case R.id.choice_two :
                button1.setPressed(false);
                button2.setEnabled(true);
                button3.setEnabled(false);
                button4.setEnabled(false);
                idProposition =  question.getListePropositions ().get (1).getId ();
                break;
            case R.id.choice_three :
                button1.setPressed(false);
                button2.setEnabled(false);
                button3.setEnabled(true);
                button4.setEnabled(false);
                idProposition =  question.getListePropositions ().get (2).getId ();
                break;
            case R.id.choice_four :
                button1.setPressed(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
                button4.setEnabled(true);
                idProposition =  question.getListePropositions ().get (3).getId ();
                break;
        }
        Choisir chx = new Choisir ();
        chx.setPersonne (SingletonPersonne.getInstance ().getPersonne ().getId ());
        chx.setQuiz (1);
        chx.setProposition (idProposition);
		boolean send = false;
        try
        {
            do{
				JSONObject choiceJSON = new AndroidHTTPRequest ().execute(IPSERVER + "AndroidChoisir.do", "POST", AndroidHTTPRequest.createParamString(chx.ChoiceToHashMap ())).get();
				if(choiceJSON.has("err_code")){
					int err_code = choiceJSON.getInt("err_code");
					ErrorManager error = SingletonErrorManager.getInstance().getError();
					if("CHOICE_SAVE".equals(error.errorManager (err_code))){

						Toast.makeText(getApplicationContext(),error.errorManager(err_code), Toast.LENGTH_LONG).show();
						send = true;
					}else{
						Toast.makeText(getApplicationContext(),error.errorManager(err_code),Toast.LENGTH_LONG).show();
					}
				}
			}while("00".equals(timer.getText ().toString ()) || send );


        }
        catch(Exception ex)
        {
            Log.e("ERROR",ex.getMessage());
        }
        return true;
    }
}
