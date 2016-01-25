package fr.esigelec.gsi.quizintegration.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import de.codecrafters.tableview.TableDataAdapter;
import fr.esigelec.gsi.quizintegration.activity.WelcomeActivity;
import fr.esigelec.gsi.quizintegration.objects.Personne;
import fr.esigelec.gsi.quizintegration.R;

/**
 * Created by kevin on 23/01/2016. Package : fr.esigelec.gsi.quizintegration.adapter Project Name : QuizIntegration
 */
public class PersonneTableDataAdapter extends TableDataAdapter<Personne>
{

	public PersonneTableDataAdapter(Context context,List<Personne> data){
		super(context,data);
	}

	@Override
	public View getCellView (int rowIndex, int columnIndex, ViewGroup parentView)
	{
		Personne p = getRowData (rowIndex);
		View renderedView = null;
		switch (columnIndex){
			case 0 :
				renderedView = renderClassement(p.getClassement ());
				break;
			case 1 :
				renderedView = renderNom (p.getNom ());
				break;
			case 2 :
				renderedView = renderPrenom (p.getPrenom ());
				break;
			case 3 :
				renderedView = renderScore (p.getScore ());
				break;
		}
		return renderedView;
	}

	private View renderClassement(int classement){
		TextView tv = new TextView (getContext ());
		tv.setText (String.valueOf (classement));
		tv.setTextColor (ContextCompat.getColor(getContext (), R.color.colorPrimary));
		tv.setTextSize (14);
		tv.setTypeface(WelcomeActivity.quizFont);
		return tv;
	}

	private View renderNom(String nom){
		TextView tv = new TextView (getContext ());
		tv.setText (nom);
		tv.setTextColor (ContextCompat.getColor(getContext (), R.color.colorPrimary));
		tv.setTextSize (14);
		tv.setTypeface(WelcomeActivity.quizFont);
		return tv;
	}

	private View renderPrenom(String prenom){
		TextView tv = new TextView (getContext ());
		tv.setText (prenom);
		tv.setTextColor (ContextCompat.getColor(getContext (), R.color.colorPrimary));
		tv.setTextSize (14);
		tv.setTypeface(WelcomeActivity.quizFont);
		return tv;
	}

	private View renderScore(int score){
		TextView tv = new TextView (getContext ());
		tv.setText (String.valueOf (score));
		tv.setTextColor (ContextCompat.getColor(getContext (), R.color.colorPrimary));
		tv.setTextSize (14);
		tv.setTypeface(WelcomeActivity.quizFont);
		return tv;
	}
}
