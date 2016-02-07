package com.mioffers.MiOffers;

import com.mioffers.MiOffers.entity.NavItem;

import java.util.ArrayList;

/**
 * Created by laxman.muttineni on 03/02/16.
 */
public class GetNavItems {
    public GetNavItems() {
    }

    public ArrayList<NavItem> getNavItems(){
        ArrayList<NavItem> mNavItems= new ArrayList<>();
        mNavItems.add(new NavItem("Invite", "invite your friends", R.mipmap.contacts));
        mNavItems.add(new NavItem("Reminders", "offers want to use", R.mipmap.time));
        mNavItems.add(new NavItem("Shared", "by me/ for me", R.mipmap.share));
        mNavItems.add(new NavItem("Promotions", "special offers for you", R.mipmap.money));
        //mNavItems.add(new NavItem("Home", "Meetup destination", R.drawable.ic_launcher));
        //mNavItems.add(new NavItem("Preferences", "Change your preferences", R.drawable.ic_launcher));
        mNavItems.add(new NavItem("Settings", "change settings", R.mipmap.settings));
        mNavItems.add(new NavItem("About", "Get to know about us", R.mipmap.about));
        mNavItems.add(new NavItem("Post", "post offers", R.mipmap.post)); return mNavItems;
    }
}
