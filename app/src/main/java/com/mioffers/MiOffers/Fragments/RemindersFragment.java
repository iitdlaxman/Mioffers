package com.mioffers.MiOffers.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.maps.model.LatLng;
import com.mioffers.MiOffers.ExpandableList;
import com.mioffers.MiOffers.MainActivity;
import com.mioffers.MiOffers.R;
import com.mioffers.MiOffers.entity.ExpandableParentItem;

import java.util.Map;

/**
 * Created by laxman.muttineni on 14/02/16.
 */
public class RemindersFragment extends Fragment {

    public RemindersFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(MainActivity.remindersFragmentView != null){
            return MainActivity.remindersFragmentView;
        }



        MainActivity.firebaseRef.child("ACC").child(MainActivity.UserId).child("reminders").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                try{
                    final String msgId =  dataSnapshot.getValue().toString();
                    MainActivity.firebaseRef.child("MSG").child(msgId).addValueEventListener(new ValueEventListener() {

                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            dataSnapshot.getValue();
                            System.out.println(dataSnapshot.getValue());
                            Map<String, String> msgData = (Map<String, String>) dataSnapshot.getValue();
                            ExpandableParentItem ex= new ExpandableParentItem();
                            ex.setCompany(msgData.get("company"));
                            ex.setDescription(msgData.get("description"));
                            ex.setTitle(msgData.get("title"));
                            ex.setValidity(msgData.get("validity"));
                            ex.setId(msgId);
                            //LatLng latLng = new LatLng(Double.valueOf(msgData.get("lat")),Double.valueOf(msgData.get("long")));
                            //ex.setLatLng(latLng);
                            MainActivity.reminderMsgIds.add(msgId);
                            MainActivity.remindersExpandableData.add(ex);

                            ExpandableListView expandableListView = (ExpandableListView)MainActivity.remindersFragmentView.findViewById(R.id.lvExp);
                            new ExpandableList(expandableListView, MainActivity.context ,"REMINDER", MainActivity.remindersExpandableData);

                        }
                        @Override
                        public void onCancelled(FirebaseError firebaseError) {

                        }
                    });
                }
                catch(Exception e){
                    Log.i("","",e);
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

        View rootView = inflater.inflate(R.layout.main_screen, container, false);
        ExpandableListView expandableListView = (ExpandableListView)rootView.findViewById(R.id.lvExp);
        new ExpandableList(expandableListView, container.getContext(),"REMINDER", MainActivity.remindersExpandableData);
        MainActivity.remindersFragmentView = rootView;
        return rootView;
    }




}
