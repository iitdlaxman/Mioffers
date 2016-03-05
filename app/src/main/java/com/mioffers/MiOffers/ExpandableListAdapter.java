package com.mioffers.MiOffers;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.mioffers.MiOffers.Fragments.ShowMapFragment;

import java.util.HashMap;
import java.util.List;

/**
 * Created by laxman.muttineni on 31/12/15.
 */
public class ExpandableListAdapter extends BaseExpandableListAdapter{


    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<String>> _listDataChild;
    private String expandableType = "OFFER";

    public ExpandableListAdapter(Context context, List<String> listDataHeader,
                                 HashMap<String, List<String>> listChildData, String expandableType) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
        this.expandableType = expandableType;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);


        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item, null);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.description);
        txtListChild.setText(childText);
        txtListChild.setHighlightColor(12);
        Button reminderButton = (Button) convertView.findViewById(R.id.reminder);
        Button shareButton = (Button) convertView.findViewById(R.id.share);
        Button mapButton = (Button) convertView.findViewById(R.id.mapLocation);
        reminderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View parent= (View) v.getParent();
                View parentofp= (View) parent.getParent();

                //Reminder.putReminder(MainActivity.UserId, "2", MainActivity.firebaseRef);  //todo : uniqueId for each user, offer
            }
        });

        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ShowMapFragment();
                android.app.FragmentTransaction fragmentTransaction = MainActivity.fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.your_placeholder, fragment);
                fragmentTransaction.addToBackStack("last");
                fragmentTransaction.commit();
            }
        });
        if(this.expandableType.equalsIgnoreCase("reminder")){
            reminderButton.setVisibility(View.GONE);
            shareButton.setVisibility(View.GONE);
            mapButton.setVisibility(View.GONE);
        }
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }


    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.offerTitle);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);
        TextView lblListHeader2 = (TextView) convertView
                .findViewById(R.id.companyName);
        lblListHeader2.setTypeface(null, Typeface.BOLD);
        lblListHeader2.setText("MiOffers");
        TextView lblListHeader3 = (TextView) convertView
                .findViewById(R.id.dateTime);
        lblListHeader3.setTypeface(null, Typeface.BOLD);
        lblListHeader3.setText("12/12/12 12:00:00");

        return convertView;
    }


    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
