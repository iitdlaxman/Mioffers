package com.mioffers.expandablelistview;

import com.firebase.client.Firebase;

/**
 * Created by laxman.muttineni on 05/01/16.
 */
public class Reminder {
    public void putReminder(String userId, String offerId,Firebase firebaseRef){
        firebaseRef.child(userId).child("reminders").push().setValue(offerId);
    }
}
