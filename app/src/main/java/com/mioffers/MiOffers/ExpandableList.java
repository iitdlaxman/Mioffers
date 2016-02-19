package com.mioffers.MiOffers;

import android.content.Context;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.mioffers.MiOffers.entity.ExpandableParentItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by laxman.muttineni on 12/02/16.
 */
public class ExpandableList {
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    public ExpandableList(){}

    public ExpandableList(ExpandableListView expandableListView, final Context context, String dataType, List<ExpandableParentItem> expandableParentItemList){
        //todo dataType in getting remainder,ofeer data
        expListView = expandableListView;
        prepareListData(expandableParentItemList);
        listAdapter = new ExpandableListAdapter(context, listDataHeader, listDataChild, dataType);
        expListView.setAdapter(listAdapter);

        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                Toast.makeText(context,
                        "Group Clicked " + listDataHeader.get(groupPosition),
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(context,
                        listDataHeader.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();
                //TextView expList = (TextView) findViewById(R.id.lblListHeader2);
                //expList.setVisibility(View.VISIBLE);
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(context,
                        listDataHeader.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();
                //TextView expList = (TextView) findViewById(R.id.lblListHeader2);
                //expList.setVisibility(View.GONE);


            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(
                        context,
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });
    }

    private void prepareListData(List<ExpandableParentItem> expandableParentItemList) {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        for(ExpandableParentItem expandableParentItem : expandableParentItemList) {
            listDataHeader.add(expandableParentItem.getTitle());
            List<String> childData = new ArrayList<String>();
            childData.add(expandableParentItem.getDescription());
            listDataChild.put(expandableParentItem.getTitle(), childData);
        }
    }


}
