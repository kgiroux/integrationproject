package fr.esigelec.gsi.quizintegration.utils;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;

import de.codecrafters.tableview.SortableTableView;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;
import de.codecrafters.tableview.toolkit.SortStateViewProviders;
import de.codecrafters.tableview.toolkit.TableDataRowColorizers;
import fr.esigelec.gsi.quizintegration.Objects.Personne;
import fr.esigelec.gsi.quizintegration.R;

/**
 * Created by kevin on 24/01/2016. Package : fr.esigelec.gsi.quizintegration.utils Project Name : QuizIntegration
 */
public class SortablePersonneTableView extends SortableTableView<Personne>
{
	public SortablePersonneTableView (Context context)
	{
		super (context);
		SimpleTableHeaderAdapter simpleTableHeaderAdapter = new SimpleTableHeaderAdapter (context,"Classement","Nom","Prenom","Score");
		simpleTableHeaderAdapter.setTextColor(ContextCompat.getColor(getContext (), R.color.table_header_text));
		setHeaderAdapter(simpleTableHeaderAdapter);


		int rowColorEven = ContextCompat.getColor(getContext (), R.color.table_data_row_even);
		int rowColorOdd = ContextCompat.getColor(getContext (), R.color.table_data_row_odd);
		setDataRowColoriser(TableDataRowColorizers.alternatingRows(rowColorEven, rowColorOdd));
		setHeaderSortStateViewProvider(SortStateViewProviders.brightArrows());


		setColumnWeight(0, 2);
		setColumnWeight(1, 3);
		setColumnWeight(2, 3);
		setColumnWeight(3, 2);


		setColumnComparator(3, ScoreComparator.getPersonneScoreComparator ());
	}

	public SortablePersonneTableView (Context context, AttributeSet attributes, int styleAttributes)
	{
		super (context, attributes, styleAttributes);

		SimpleTableHeaderAdapter simpleTableHeaderAdapter = new SimpleTableHeaderAdapter (context,"Classement","Nom","Prenom","Score");
		simpleTableHeaderAdapter.setTextColor(ContextCompat.getColor(getContext (), R.color.table_header_text));
		setHeaderAdapter(simpleTableHeaderAdapter);


		int rowColorEven = ContextCompat.getColor(getContext (), R.color.table_data_row_even);
		int rowColorOdd = ContextCompat.getColor(getContext (), R.color.table_data_row_odd);
		setDataRowColoriser(TableDataRowColorizers.alternatingRows(rowColorEven, rowColorOdd));
		setHeaderSortStateViewProvider(SortStateViewProviders.brightArrows());


		setColumnWeight(0, 2);
		setColumnWeight(1, 3);
		setColumnWeight(2, 3);
		setColumnWeight(3, 2);


		setColumnComparator(3, ScoreComparator.getPersonneScoreComparator ());
	}

	public SortablePersonneTableView (Context context, AttributeSet attributes)
	{
		super (context, attributes);

		SimpleTableHeaderAdapter simpleTableHeaderAdapter = new SimpleTableHeaderAdapter (context,"Classement","Nom","Prenom","Score");
		simpleTableHeaderAdapter.setTextColor(ContextCompat.getColor(getContext (), R.color.table_header_text));
		setHeaderAdapter(simpleTableHeaderAdapter);


		int rowColorEven = ContextCompat.getColor(getContext (), R.color.table_data_row_even);
		int rowColorOdd = ContextCompat.getColor(getContext (), R.color.table_data_row_odd);
		setDataRowColoriser(TableDataRowColorizers.alternatingRows(rowColorEven, rowColorOdd));
		setHeaderSortStateViewProvider(SortStateViewProviders.brightArrows());


		setColumnWeight(0, 2);
		setColumnWeight(1, 3);
		setColumnWeight(2, 3);
		setColumnWeight(3, 2);


		setColumnComparator(3, ScoreComparator.getPersonneScoreComparator ());
	}
}
