package com.mioffers.MiOffers;

import com.firebase.client.Firebase;

/**
 * Created by laxman.muttineni on 05/01/16.
 */
public class Reminder {
    public static void putReminder(String userId, String offerId,Firebase firebaseRef){
        firebaseRef.child("ACC").child(userId).child("reminders").push().setValue(offerId);
    }

    public static void removeReminder(String userId, String offerId,Firebase firebaseRef){
        firebaseRef.child("ACC").child(userId).child("reminders").child(offerId).removeValue();
    }
}
