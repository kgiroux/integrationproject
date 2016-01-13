package fr.esigelec.gsi.quizintegration.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import fr.esigelec.gsi.quizintegration.Objects.Personne;
import fr.esigelec.gsi.quizintegration.Objects.Proposition;
import fr.esigelec.gsi.quizintegration.Objects.Question;
import fr.esigelec.gsi.quizintegration.Objects.Quiz;
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
    private List<Quiz> quizList;

	@Override
	protected void onCreate (Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		pers = SingletonPersonne.getInstance ().getPersonne ();
		toolbar = (Toolbar) findViewById (R.id.tool_bar);
		toolbar.setTitle(R.string.app_name);
		toolbar.setOnMenuItemClickListener(this);
		create_expandable_list();

        //Initialisation pour test
        initTest();

		LinearLayout currentQuiz = (LinearLayout) findViewById(R.id.current_quiz);
		currentQuiz.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(v.getContext(),GameActivity.class));
			}
		});
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
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

    protected void initTest ()
    {
        quizList= new ArrayList<>();
        Quiz quizCur = new Quiz(3,"Animaux", new Timestamp(System.currentTimeMillis()),null,4,new Timestamp(System.currentTimeMillis()),1);
        Quiz quiz1 = new Quiz(1,"Geek", new Timestamp(2015,5,5,5,5,5,0), new Timestamp(2015,5,5,6,5,5,0),0,null,0);
        Quiz quiz2 = new Quiz(2,"Espace", new Timestamp(2015,4,5,5,5,5,0), new Timestamp(2015,4,5,6,5,5,0),0,null,0);
        List<Question> questionListQuiz1 = new ArrayList<>();
        Question question1 = new Question(1,"En quelle année date le premier Iphone ?", 2);
        Question question2 = new Question(2,"rlihgseldhgistghsh ?", 3);
        Question question3 = new Question(3,"mkllkl ?", 1);
        List<Question> questionListQuiz2 = new ArrayList<>();
        Question question4 = new Question(4,"mshnklslkkkbkl ?", 1);
        Question question5 = new Question(5,"pppppppppppppp ?", 4);
        Question question6 = new Question(6,"ddd ?", 4);
        List<Question> questionListQuizCur = new ArrayList<>();
        Question question7 = new Question(7,"oooooooooooooooooooooooooooooooo ?", 4);
        Question question8 = new Question(8,"PPPPPPPPPPFFFFFFFFFFFFFFFf ?", 2);
        Question question9 = new Question(9,"rgoisroigjsdrogj ?", 3);
        List<Proposition> propositionListquestion1 = new ArrayList<>();
        Proposition proposition1 = new Proposition(1, "dsgfseg");
        Proposition proposition2 = new Proposition(2, "dsfqgq");
        Proposition proposition3 = new Proposition(3, "jytyy,");
        Proposition proposition4 = new Proposition(4, "tyrty");
        propositionListquestion1.add(proposition1);
        propositionListquestion1.add(proposition2);
        propositionListquestion1.add(proposition3);
        propositionListquestion1.add(proposition4);
        List<Proposition> propositionListquestion2 = new ArrayList<>();
        propositionListquestion2.add(proposition1);
        propositionListquestion2.add(proposition2);
        propositionListquestion2.add(proposition3);
        propositionListquestion2.add(proposition4);
        List<Proposition> propositionListquestion3 = new ArrayList<>();
        propositionListquestion3.add(proposition1);
        propositionListquestion3.add(proposition2);
        propositionListquestion3.add(proposition3);
        propositionListquestion3.add(proposition4);
        List<Proposition> propositionListquestion4 = new ArrayList<>();
        propositionListquestion4.add(proposition1);
        propositionListquestion4.add(proposition2);
        propositionListquestion4.add(proposition3);
        propositionListquestion4.add(proposition4);
        List<Proposition> propositionListquestion5 = new ArrayList<>();
        propositionListquestion5.add(proposition1);
        propositionListquestion5.add(proposition2);
        propositionListquestion5.add(proposition3);
        propositionListquestion5.add(proposition4);
        List<Proposition> propositionListquestion6 = new ArrayList<>();
        propositionListquestion6.add(proposition1);
        propositionListquestion6.add(proposition2);
        propositionListquestion6.add(proposition3);
        propositionListquestion6.add(proposition4);
        List<Proposition> propositionListquestion7 = new ArrayList<>();
        propositionListquestion7.add(proposition1);
        propositionListquestion7.add(proposition2);
        propositionListquestion7.add(proposition3);
        propositionListquestion7.add(proposition4);
        List<Proposition> propositionListquestion8 = new ArrayList<>();
        propositionListquestion8.add(proposition1);
        propositionListquestion8.add(proposition2);
        propositionListquestion8.add(proposition3);
        propositionListquestion8.add(proposition4);
        List<Proposition> propositionListquestion9 = new ArrayList<>();
        propositionListquestion9.add(proposition1);
        propositionListquestion9.add(proposition2);
        propositionListquestion9.add(proposition3);
        propositionListquestion9.add(proposition4);


        questionListQuiz1.add(question1);
        questionListQuiz1.add(question2);
        questionListQuiz1.add(question3);
        questionListQuiz2.add(question4);
        questionListQuiz2.add(question5);
        questionListQuiz2.add(question6);
        questionListQuizCur.add(question7);
        questionListQuizCur.add(question8);
        questionListQuizCur.add(question9);
    }
}
