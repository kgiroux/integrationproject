package fr.esigelec.gsi.quizintegration.Activity;

import android.app.Activity;
import android.os.Bundle;

import fr.esigelec.gsi.quizintegration.R;

public class GameActivity extends Activity
{

	@Override
	protected void onCreate (Bundle savedInstanceState)
	{
		super.onCreate (savedInstanceState);
		setContentView (R.layout.activity_game);
	}
}