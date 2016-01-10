package fr.esigelec.gsi.quizintegration;

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

import fr.esigelec.gsi.quizintegration.adapter.ExpandableListAdapter;

public class MainActivity extends Activity implements View.OnClickListener, Toolbar.OnMenuItemClickListener
{
	public static boolean DEBUG = true;
	private DrawerLayout mDrawerLayout;

	private ActionBarDrawerToggle mDrawerToggle;
	List<String> groupList;
	private final int REQUEST_CODE_INSCRIPTION = 42;
	Map<String, List<String>> ItemCollection;
	private Toolbar toolbar;
	@Override
	protected void onCreate (Bundle savedInstanceState)
	{
		super.onCreate (savedInstanceState);
		setContentView (R.layout.activity_main);

		toolbar = (Toolbar) findViewById (R.id.tool_bar);
		toolbar.setTitle (R.string.app_name);
		toolbar.setOnMenuItemClickListener (this);
		create_expandable_list ();
	}

	public void create_drawer ()
	{
		mDrawerLayout = (DrawerLayout) findViewById (R.id.drawer_layout);
		mDrawerToggle = new CustomActionBarDrawerToggle (this, mDrawerLayout);
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
						Toast.makeText (getApplicationContext (), R.string.connexion, Toast.LENGTH_LONG).show ();
						break;
					case 1:
						t = new Intent(getApplicationContext (),Inscription.class);
						startActivityForResult (t,REQUEST_CODE_INSCRIPTION);
						Toast.makeText (getApplicationContext (), R.string.inscription, Toast.LENGTH_LONG).show ();
						break;

					case 2:
						t = new Intent(getApplicationContext (),About.class);
						Toast.makeText (getApplicationContext (), R.string.about, Toast.LENGTH_LONG).show ();
						startActivity (t);
						break;
					case 3:
						t = new Intent (getApplicationContext (),LegalNotice.class);
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
		String listItem[] = getResources ().getStringArray (R.array.listMenu);
		groupList = new ArrayList<> ();

		for (int i = 0; i != listItem.length; i++)
		{
			Log.e ("DEBUG",listItem[i]);
			groupList.add (listItem[i]);
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data){
		if(resultCode != RESULT_CANCELED){
			switch (requestCode){
				case REQUEST_CODE_INSCRIPTION :

					break;
			}
		}
	}


	/**
	 * When using the ActionBarDrawerToggle, you must call it during
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

	@Override
	public void onClick (View v)
	{

	}

	@Override
	public boolean onMenuItemClick (MenuItem item)
	{
		return false;
	}


	private class CustomActionBarDrawerToggle extends ActionBarDrawerToggle
	{

		public CustomActionBarDrawerToggle (Activity mActivity, DrawerLayout mDrawerLayout)
		{
			super (mActivity, mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
		}

		@Override
		public void onDrawerClosed (View view)
		{
			toolbar.setTitle (R.string.app_name);
			invalidateOptionsMenu (); // creates call to onPrepareOptionsMenu()
		}

		@Override
		public void onDrawerOpened (View drawerView)
		{
			toolbar.setTitle (R.string.app_name);
			invalidateOptionsMenu (); // creates call to onPrepareOptionsMenu()
		}
	}
}
