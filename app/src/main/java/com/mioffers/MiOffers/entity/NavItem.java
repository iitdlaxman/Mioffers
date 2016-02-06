package com.mioffers.MiOffers.entity;

/**
 * Created by laxman.muttineni on 02/02/16.
 */
public class NavItem {
    public String mTitle;
    public String mSubtitle;
    public int mIcon;

    public NavItem(String title, String subtitle, int icon) {
        mTitle = title;
        mSubtitle = subtitle;
        mIcon = icon;
    }
}
