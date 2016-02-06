package com.mioffers.expandablelistview;

import com.mioffers.expandablelistview.entity.NavItem;

import java.util.ArrayList;

/**
 * Created by laxman.muttineni on 03/02/16.
 */
public class GetNavItems {
    public GetNavItems() {
    }

    public ArrayList<NavItem> getNavItems(){
        ArrayList<NavItem> mNavItems= new ArrayList<>();
        mNavItems.add(new NavItem("Profile", "change settings", R.drawable.ic_launcher));
        mNavItems.add(new NavItem("Invite", "invite your friends", R.drawable.ic_launcher));
        mNavItems.add(new NavItem("Reminders", "offers want to use", R.drawable.ic_launcher));
        mNavItems.add(new NavItem("Shared", "by me/ for me / notifications", R.drawable.ic_launcher));
        mNavItems.add(new NavItem("Promotions", "special offers for you", R.drawable.ic_launcher));
        //mNavItems.add(new NavItem("Home", "Meetup destination", R.drawable.ic_launcher));
        //mNavItems.add(new NavItem("Preferences", "Change your preferences", R.drawable.ic_launcher));
        mNavItems.add(new NavItem("About", "Get to know about us", R.drawable.ic_launcher));
        mNavItems.add(new NavItem("Post", "post offers", R.drawable.ic_launcher)); return mNavItems;
    }
}
