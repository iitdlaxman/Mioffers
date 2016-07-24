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
import com.mioffers.MiOffers.Constants;
import com.mioffers.MiOffers.ExpandableList.ExpandableList;
import com.mioffers.MiOffers.MainActivity;
import com.mioffers.MiOffers.Mappers;
import com.mioffers.MiOffers.R;

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



        MainActivity.firebaseRef.child(Constants.FIRE_BASE_CHILD_ACC).child(MainActivity.UserId).child(Constants.FIRE_BASE_CHILD_REMINDERS).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                try{
                    final String msgId =  dataSnapshot.getValue().toString();
                    MainActivity.firebaseRef.child(Constants.FIRE_BASE_CHILD_MSG).child(msgId).addValueEventListener(new ValueEventListener() {

                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            MainActivity.reminderMsgIds.add(msgId);
                            MainActivity.remindersExpandableData.add(Mappers.mapMsgDataToExpandableParent(dataSnapshot));
                            ExpandableListView expandableListView = (ExpandableListView)MainActivity.remindersFragmentView.findViewById(R.id.lvExp);
                            new ExpandableList(expandableListView, MainActivity.context ,Constants.REMINDER, MainActivity.remindersExpandableData);
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

        MainActivity.remindersFragmentView = inflater.inflate(R.layout.main_screen, container, false);
        ExpandableListView expandableListView = (ExpandableListView)MainActivity.remindersFragmentView.findViewById(R.id.lvExp);
        new ExpandableList(expandableListView, container.getContext(), Constants.REMINDER, MainActivity.remindersExpandableData);
        return MainActivity.remindersFragmentView;
    }




}
