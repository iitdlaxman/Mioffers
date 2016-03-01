package com.mioffers.MiOffers.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.mioffers.MiOffers.ExpandableList;
import com.mioffers.MiOffers.MainActivity;
import com.mioffers.MiOffers.R;

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

        if(MainActivity.expandableFragmentView != null ) {
            return MainActivity.expandableFragmentView;
        }
        MainActivity.expandableFragmentView = inflater.inflate(R.layout.main_screen, container, false);
        ExpandableListView expandableListView = (ExpandableListView) MainActivity.expandableFragmentView.findViewById(R.id.lvExp);
        new ExpandableList(expandableListView, container.getContext(),"OFFER",MainActivity.offersExpandableData);


        return MainActivity.expandableFragmentView;
    }

}
