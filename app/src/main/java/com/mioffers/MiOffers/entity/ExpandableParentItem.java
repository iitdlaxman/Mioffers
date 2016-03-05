package com.mioffers.MiOffers.entity;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by laxman.muttineni on 15/02/16.
 */
public class ExpandableParentItem {
    public String id;
    public String company;
    public String title;
    public String description;
    public String validity;
    public LatLng latLng;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }
}
