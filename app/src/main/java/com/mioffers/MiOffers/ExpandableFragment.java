package com.mioffers.MiOffers;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

/**
 * Created by laxman.muttineni on 03/02/16.
 */
public class ExpandableFragment extends Fragment {
    public ExpandableFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment




        View rootView = inflater.inflate(R.layout.main_screen, container, false);
        ExpandableListView expandableListView = (ExpandableListView)rootView.findViewById(R.id.lvExp);
        new ExpandableList(expandableListView, container.getContext(),"OFFER",MainActivity.offersExpandableData);


        return rootView;
    }

}
