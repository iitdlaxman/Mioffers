package com.mioffers.MiOffers.entity;

import android.widget.Button;
import android.widget.TextView;

/**
 * Created by laxman.muttineni on 16/02/16.
 */
public class ExpandableChileItem {
    public TextView description;

    public Button reminder;

    public Button share;

    public Button mapLocation;

    public String id;

    public TextView getDescription() {
        return description;
    }

    public void setDescription(TextView description) {
        this.description = description;
    }

    public Button getReminder() {
        return reminder;
    }

    public void setReminder(Button reminder) {
        this.reminder = reminder;
    }

    public Button getShare() {
        return share;
    }

    public void setShare(Button share) {
        this.share = share;
    }

    public Button getMapLocation() {
        return mapLocation;
    }

    public void setMapLocation(Button mapLocation) {
        this.mapLocation = mapLocation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
