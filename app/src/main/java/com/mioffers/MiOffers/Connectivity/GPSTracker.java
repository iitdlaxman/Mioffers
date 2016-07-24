package com.mioffers.MiOffers.Connectivity;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;

/**
 * Created by laxman.muttineni on 06/02/16.
 */
public class GPSTracker extends Service implements LocationListener {

    private final Context context;
    boolean isGPSEnabled = false;
    boolean isNetworkEnabled = false;
    public boolean canGetLocation = false;
    Location location;
    double latitude;
    double langtitude;
    private static final Long MIN_DISTANCE_CHANGE_FOR_UPDATES=10L;
    private static final Long MIN_TIME_BTW_UPDATES=1000 * 60 *1L;
    private LocationManager locationManager;

    public GPSTracker(Context context){
        this.context = context;
        getLocation();
    }

    public Location getLocation() {
        try{
            locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
            isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            if(!isGPSEnabled && ! isNetworkEnabled){

            }
            else{
                canGetLocation = true;
                if(isNetworkEnabled){
                        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME_BTW_UPDATES , MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                    if (locationManager != null){
                        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (location!=null){
                            langtitude = location.getLongitude();
                            latitude = location.getLatitude();
                        }
                    }
                }

                if(isGPSEnabled){
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BTW_UPDATES , MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                    if (locationManager != null){
                        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        if (location!=null){
                            langtitude = location.getLongitude();
                            latitude = location.getLatitude();
                        }
                    }
                }


            }


        }
        catch (SecurityException e){
            e.printStackTrace();

        }

    return location;
    }

    public  void stopUsingGPS(){
        if(locationManager != null){
            try{
                locationManager.removeUpdates(GPSTracker.this);
            }
            catch(SecurityException e){
                e.printStackTrace();
            }
        }
    }

    public double getLatitude(){
        if(location != null ){
            latitude =  location.getLatitude();
        }
        return latitude;
    }

    public double getLongtitude(){
        if(location != null ){
            langtitude =  location.getLatitude();
        }
        return langtitude;
    }

    public boolean canGetLocation(){
        return canGetLocation;
    }

    public void showSettingsAlert(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("GPS is Settings");
        alertDialog.setMessage("GPS is not enabled, do you want to go to settings menu?");
        alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                context.startActivity(intent);
            }
        });
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.show();
    }


    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
