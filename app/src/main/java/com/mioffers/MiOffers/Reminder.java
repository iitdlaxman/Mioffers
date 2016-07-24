package com.mioffers.MiOffers;

import com.firebase.client.Firebase;

/**
 * Created by laxman.muttineni on 05/01/16.
 */
public class Reminder {
    public static void putReminder(String userId, String offerId,Firebase firebaseRef){
        firebaseRef.child(Constants.FIRE_BASE_CHILD_ACC).child(userId).child(Constants.FIRE_BASE_CHILD_REMINDERS).child(offerId).push().setValue(offerId);
    }

    public static void removeReminder(String userId, String offerId,Firebase firebaseRef){
        firebaseRef.child(Constants.FIRE_BASE_CHILD_ACC).child(userId).child(Constants.FIRE_BASE_CHILD_REMINDERS).child(offerId).removeValue();
    }
}
