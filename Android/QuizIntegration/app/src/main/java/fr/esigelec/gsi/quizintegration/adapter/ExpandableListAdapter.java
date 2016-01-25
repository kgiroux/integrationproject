package fr.esigelec.gsi.quizintegration.adapter;
/**
 * Created by Kevin Giroux
 */

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import fr.esigelec.gsi.quizintegration.R;


public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Activity context;
    private Map<String, List<String>> ClassCollection;
    private List<String> ClassList;

    public ExpandableListAdapter(Activity context, List<String> character, Map<String, List<String>> Collections) {
        this.context = context;
        this.ClassCollection = Collections;
        this.ClassList = character;
    }

    public Object getChild(int groupPosition, int childPosition) {
        return ClassCollection.get(ClassList.get(groupPosition)).get (childPosition);
    }

    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, final ViewGroup parent) {
        return null;
    }

    public int getChildrenCount(int groupPosition) {
        if (ClassList.get(groupPosition) != null) {
            if (ClassCollection.get(ClassList.get(groupPosition)) != null) {
                return ClassCollection.get(ClassList.get(groupPosition)).size();
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    public Object getGroup(int groupPosition) {
        return ClassList.get(groupPosition);
    }

    public int getGroupCount() {
        return ClassList.size();
    }

    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, final ViewGroup parent) {
        String classPosition = (String) getGroup(groupPosition);
        ExpandableListView mExpandableListView = (ExpandableListView) parent;
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate (R.layout.group_item,parent,false);
        }
        TextView item = (TextView) convertView.findViewById(R.id.name_character_xml);
        item.setTypeface(null, Typeface.BOLD);
        item.setText(classPosition);
        return convertView;
    }

    public boolean hasStableIds() {
        return true;
    }

    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
