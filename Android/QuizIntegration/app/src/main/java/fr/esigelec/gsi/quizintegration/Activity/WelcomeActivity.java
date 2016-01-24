package fr.esigelec.gsi.quizintegration.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import fr.esigelec.gsi.quizintegration.R;

/**
 * Created by Yuhuang, Tang on 13/01/2016. Package : fr.esigelec.gsi.quizintegration.activity Project Name : QuizIntegration
 */
public class WelcomeActivity extends AppCompatActivity {

    public static String IPSERVER = "http://10.0.2.2:8080/quiz/";
    public static Typeface quizFont;
    public static boolean DEBUG = false;
    public static boolean DEV = false;
    private long SPLASH_LENGTH = 3000;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        /* Récupération du numéro de version de l'application */
        try{
            PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            String version = pInfo.versionName;

			// Récupération du TextView permettant l'affichage de la version
            TextView versionNumber = (TextView) findViewById (R.id.versionNumber);
            versionNumber.setText (version);
        }catch (PackageManager.NameNotFoundException e ){
            e.printStackTrace ();
        }

        //Chargement de la police d'écriture
        quizFont = Typeface.createFromAsset(getAssets(), "fonts/show.ttf");

        if(DEV){
            SPLASH_LENGTH = 500;
        }


		/* Affichage du SplashScreen pendant la durée SPLASH_LENGTH */
        handler.postDelayed(new Runnable() {

            public void run() {
				// On Lance l'activité MainActivity lorsqu'on a atteint le temps SPLASH_LENGTH
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_LENGTH);
    }
}
