package fr.esigelec.gsi.quizintegration.adapter;

import android.app.Activity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import fr.esigelec.gsi.quizintegration.R;

/**
 * Created by Kevin-Giroux on 11/01/2016. Package : fr.esigelec.gsi.quizintegration.adapter Project Name : QuizIntegration
 */
public class CustomActionBarDrawerToggle extends ActionBarDrawerToggle
{
	Toolbar toolbar;
	Activity activity;
	public CustomActionBarDrawerToggle (Activity mActivity, DrawerLayout mDrawerLayout,Toolbar toolbar)
	{
		super (mActivity, mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
		this.toolbar = toolbar;
		activity = mActivity;
	}

	@Override
	public void onDrawerClosed (View view)
	{
		//toolbar.setTitle (R.string.app_name);
		activity.invalidateOptionsMenu (); // creates call to onPrepareOptionsMenu()
	}

	@Override
	public void onDrawerOpened (View drawerView)
	{
		//toolbar.setTitle (R.string.app_name);
		activity.invalidateOptionsMenu (); // creates call to onPrepareOptionsMenu()
	}
}
