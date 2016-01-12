package fr.esigelec.gsi.quizintegration.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import fr.esigelec.gsi.quizintegration.Objects.Personne;
import fr.esigelec.gsi.quizintegration.R;
import fr.esigelec.gsi.quizintegration.adapter.CustomActionBarDrawerToggle;
import fr.esigelec.gsi.quizintegration.adapter.ExpandableListAdapter;
import fr.esigelec.gsi.quizintegration.utils.SingletonPersonne;

public class MenuActivity extends Activity implements Toolbar.OnMenuItemClickListener
{
	private DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mDrawerToggle;
	List<String> groupList;
	Map<String, List<String>> ItemCollection;
	private Toolbar toolbar;
	private Personne pers;
	@Override
	protected void onCreate (Bundle savedInstanceState)
	{
		super.onCreate (savedInstanceState);
		setContentView (R.layout.activity_menu);
		pers = SingletonPersonne.getInstance ().getPersonne ();
		toolbar = (Toolbar) findViewById (R.id.tool_bar);
		toolbar.setTitle (R.string.app_name);
		toolbar.setOnMenuItemClickListener (this);
		create_expandable_list ();
	}


	public void create_drawer ()
	{
		mDrawerLayout = (DrawerLayout) findViewById (R.id.drawer_layout);
		mDrawerToggle = new CustomActionBarDrawerToggle (this, mDrawerLayout,toolbar);
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

						break;
					case 1:
						t = new Intent(getApplicationContext (),InscriptionActivity.class);
						startActivity (t);
						break;
					case 2:
						t = new Intent(getApplicationContext (),AboutActivity.class);
						Toast.makeText (getApplicationContext (), R.string.about, Toast.LENGTH_LONG).show ();
						startActivity (t);
						break;
					case 3:
						t = new Intent (getApplicationContext (),LegalNoticeActivity.class);
						Toast.makeText (getApplicationContext (), R.string.mentionlegales, Toast.LENGTH_LONG).show ();
						startActivity (t);
						break;
					case 4:
						Toast.makeText (getApplicationContext (), R.string.bye, Toast.LENGTH_LONG).show ();
						finish ();
						break;
				}

				return true;
			}
		});
	}

	private void createCollection ()
	{
		ItemCollection = new LinkedHashMap<> ();
	}

	private void createGroupList ()
	{
		String listItem[] = getResources ().getStringArray (R.array.listMenuActivity);
		groupList = new ArrayList<> ();

		groupList.add (pers.getNom () + " " + pers.getPrenom () );
		for (int i = 1; i != listItem.length; i++)
		{
			groupList.add (listItem[i]);
		}

	}

	@Override
	public boolean onMenuItemClick (MenuItem item)
	{
		return false;
	}

	/*
	*  When using the ActionBarDrawerToggle, you must call it during
	* onPostCreate() and onConfigurationChanged()...
	*/

	protected void onPostCreate (Bundle savedInstanceState)
	{
		super.onPostCreate (savedInstanceState);
		mDrawerToggle.syncState ();
	}

	public void onConfigurationChanged (Configuration newConfig)
	{
		super.onConfigurationChanged (newConfig);
		mDrawerToggle.onConfigurationChanged (newConfig);
	}
}
