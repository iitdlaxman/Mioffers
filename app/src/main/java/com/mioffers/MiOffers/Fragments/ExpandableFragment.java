package com.mioffers.MiOffers.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mioffers.MiOffers.Constants;
import com.mioffers.MiOffers.ExpandableList.ExpandableList;
import com.mioffers.MiOffers.MainActivity;
import com.mioffers.MiOffers.Mappers;
import com.mioffers.MiOffers.R;
import com.mioffers.MiOffers.entity.ExpandableParentItem;

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

        Toast.makeText(MainActivity.context, SystemClock.elapsedRealtimeNanos() +"your location lat :" + SystemClock.elapsedRealtime() , Toast.LENGTH_LONG).show();
        MainActivity.firebaseRef.child(Constants.FIRE_BASE_CHILD_MSG).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                try {
                    //MainActivity.reminderMsgIds.add(dataSnapshot.getKey());
                    Toast.makeText(MainActivity.context, "your location lat child:" +SystemClock.elapsedRealtimeNanos() + SystemClock.elapsedRealtime(), Toast.LENGTH_LONG).show();
                    ExpandableParentItem expandableParentItem = Mappers.mapMsgDataToExpandableParent(dataSnapshot);
                    MainActivity.offersExpandableData.add(expandableParentItem);
                    MainActivity.mMap.addMarker(new MarkerOptions().position(expandableParentItem.getLatLng()).title("Your Location"));
                    ExpandableListView expandableListView = (ExpandableListView) MainActivity.expandableFragmentView.findViewById(R.id.lvExp);
                    new ExpandableList(expandableListView, MainActivity.context, Constants.OFFER, MainActivity.offersExpandableData);
                } catch (Exception e) {
                    Log.i("", "", e);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });






        MainActivity.expandableFragmentView = inflater.inflate(R.layout.main_screen, container, false);
        ExpandableListView expandableListView = (ExpandableListView) MainActivity.expandableFragmentView.findViewById(R.id.lvExp);
        new ExpandableList(expandableListView, container.getContext(), Constants.OFFER, MainActivity.offersExpandableData);
        return MainActivity.expandableFragmentView;
    }

}
