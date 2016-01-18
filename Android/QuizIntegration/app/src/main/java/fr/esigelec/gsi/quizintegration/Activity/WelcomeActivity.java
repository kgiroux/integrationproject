package fr.esigelec.gsi.quizintegration.Activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import fr.esigelec.gsi.quizintegration.R;



public class WelcomeActivity extends AppCompatActivity {

    private final long SPLASH_LENGTH = 3000;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        try{
            PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            String version = pInfo.versionName;
            TextView versionNumber = (TextView) findViewById (R.id.versionNumber);
            versionNumber.setText (version);
        }catch (PackageManager.NameNotFoundException e ){
            e.printStackTrace ();
        }



        ;

        handler.postDelayed(new Runnable() {

            public void run() {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_LENGTH);
    }
}
