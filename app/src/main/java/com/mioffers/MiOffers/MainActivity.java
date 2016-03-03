package com.mioffers.MiOffers;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.maps.*;
import com.mioffers.MiOffers.Fragments.ExpandableFragment;
import com.mioffers.MiOffers.Fragments.PostFragment;
import com.mioffers.MiOffers.Fragments.RemindersFragment;
import com.mioffers.MiOffers.Fragments.ShowMapFragment;
import com.mioffers.MiOffers.entity.ExpandableParentItem;
import com.mioffers.MiOffers.entity.NavItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button reminderButton;
    private Reminder reminder=new Reminder();

    public static ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private RelativeLayout mDrawerPane;
    private String mActivityTitle;
    ArrayList<NavItem> mNavItems;
    private int content;
    public static  Firebase firebaseRef;
    public static String UserId = "1";
    public static ExpandableListView expandableListView;
    public static View expandableFragmentView;
    public static List<ExpandableParentItem> offersExpandableData = new ArrayList<>();
    public static List<ExpandableParentItem> remindersExpandableData = new ArrayList<>();
    public static View remindersFragmentView;
    public static Context context;
    public static FragmentManager fragmentManager;



    public static GoogleMap mMap;
    public static View mapView;
    public static android.support.v4.app.FragmentManager supportMapFragment;


   public static GPSTracker gps;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        supportMapFragment = getSupportFragmentManager();
        fragmentManager = getFragmentManager();
        Firebase.setAndroidContext(this);
        firebaseRef = new Firebase("https://mioffers.firebaseIO.com");

        prepareListData();
        context= getApplicationContext();

        setContentView(R.layout.activity_main);
       // selectItemFromDrawer(0);
        mNavItems = new GetNavItems().getNavItems();
        mActivityTitle = getTitle().toString();
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mDrawerList = (ListView)findViewById(R.id.navList);




        addDrawerItems();
        setupDrawer();


        // Populate the Navigtion Drawer with options
        mDrawerPane = (RelativeLayout) findViewById(R.id.drawerPane);




        gps= new GPSTracker(MainActivity.this);
        if(gps.canGetLocation){
            double latitude = gps.getLatitude();
            double longitude = gps.getLongtitude();
            Toast.makeText(getApplicationContext() , "your location lat :"  + latitude + " long : " + longitude, Toast.LENGTH_LONG).show();
        }
        else {
            gps.showSettingsAlert();
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        selectItemFromDrawer(0);
        content = R.layout.main_screen;

        View inflater = getLayoutInflater().inflate(R.layout.list_item, null);
        reminderButton = (Button) inflater.findViewById(R.id.reminder);
        reminderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Button Clicked", Toast.LENGTH_SHORT).show();
                Reminder.putReminder("1", "2", firebaseRef);  //todo : uniqueId for each user, offer
            }
        });



    }



    private void addDrawerItems() {
        DrawerListAdapter adapter = new DrawerListAdapter(this, mNavItems);
        mDrawerList.setAdapter(adapter);

        // Drawer Item click listeners
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Time for an upgrade!", Toast.LENGTH_SHORT).show();
                selectItemFromDrawer(position);
            }
        });
    }




    public void setDrawerState(boolean isEnabled) {
        if ( isEnabled ) {
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            //mDrawerToggle.onDrawerStateChanged(DrawerLayout.LOCK_MODE_UNLOCKED);
            mDrawerToggle.setDrawerIndicatorEnabled(true);
            mDrawerToggle.syncState();

        }
        else {
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
           // mDrawerToggle.onDrawerStateChanged(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            mDrawerToggle.setDrawerIndicatorEnabled(false);
            mDrawerToggle.syncState();
        }
    }





    /*
* Called when a particular item from the navigation drawer
* is selected.
* */
    public void selectItemFromDrawer(int position) {
        Fragment fragment;
        android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if(position == 0) {
            fragment = new ExpandableFragment();
            setDrawerState(true);
        }

        else if(position == 1){

            fragment = new RemindersFragment();
           // fragmentTransaction.replace(R.id.your_placeholder, fragment);
            setDrawerState(false);
        }
        else if(position == 7){

            fragment = new PostFragment();
           // fragmentTransaction.replace(R.id.your_placeholder, fragment); //todo : shows background
            setDrawerState(false);
        }
        else{
            fragment = new ShowMapFragment();
            //fragmentTransaction.replace(R.id.your_placeholder, fragment);
            setDrawerState(false);
        }



        fragmentTransaction.replace(R.id.your_placeholder, fragment).addToBackStack(String.valueOf(position));
        fragmentTransaction.commit();
        mDrawerList.setItemChecked(position, true);
        setTitle(mNavItems.get(position).mTitle);


        // Close the drawer
        mDrawerLayout.closeDrawer(mDrawerPane);


        /*mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        mDrawerToggle.setDrawerIndicatorEnabled(false);
        mDrawerToggle.syncState();
        getSupportActionBar().setHomeButtonEnabled(false);
*/

    }


    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Navigation!");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);



    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        // Activate the navigation drawer toggle
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        setDrawerState(true);
        selectItemFromDrawer(0);
        return super.onOptionsItemSelected(item);
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        try{
            mDrawerLayout.isDrawerOpen(mDrawerList);
        }
        catch (Exception e){
            System.out.print(e);
        }

       // menu.findItem(R.id.action_search).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }



    private void prepareListData() {
        ExpandableParentItem expandableParentItem1 = new ExpandableParentItem();
        ExpandableParentItem expandableParentItem2 = new ExpandableParentItem();
        ExpandableParentItem expandableParentItem3 = new ExpandableParentItem();
        expandableParentItem1.setTitle("OFFER1");
        expandableParentItem2.setTitle("OFFER2");
        expandableParentItem3.setTitle("OFFER3");
        expandableParentItem1.setDescription("offer1 descabkcb kbdvk jkbvka b avbdvk kdva k kjdavs k bkdsvb k vaksbj");
        expandableParentItem2.setDescription("offer2  sdhkhaksd hadks kjds k khsdh k sdkj kjhds k dksajh ksd kjsd kj sdkhj");
        expandableParentItem3.setDescription("offer3 bvks ksbv ak sjvdbk skjv jkdsbvkv bkv kjsdvb ksvbks vkdsvb kdsv dskvbskdv");
        MainActivity.offersExpandableData.add(expandableParentItem1);
        MainActivity.offersExpandableData.add(expandableParentItem2);
        MainActivity.offersExpandableData.add(expandableParentItem3);
    }

    @Override
    public void onBackPressed() {
        if (fragmentManager.getBackStackEntryCount() <= 1) {
            finish();

            return;
        }
        super.onBackPressed();
    }



}
