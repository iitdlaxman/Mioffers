package com.mioffers.MiOffers.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mioffers.MiOffers.MainActivity;
import com.mioffers.MiOffers.R;
import com.mioffers.MiOffers.entity.ExpandableParentItem;

/**

 * Created by laxman.muttineni on 18/02/16.
 */


public class ShowMapFragment extends Fragment implements OnMapReadyCallback {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        if(MainActivity.mapView != null ) {
            return MainActivity.mapView;
        }
        LayoutInflater infalInflater = (LayoutInflater) this.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        MainActivity.mapView = infalInflater.inflate(R.layout.fragment_location_info, null);

        SupportMapFragment mapFragment = (SupportMapFragment) MainActivity.supportMapFragment
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        getActivity().setTitle("MAP");

        return MainActivity.mapView;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MainActivity.mMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        //MainActivity.mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //MainActivity.mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        try{
            MainActivity.mMap.setMyLocationEnabled(true);

            //LatLng my = new LatLng(MainActivity.mMap.getMyLocation().getLatitude(), MainActivity.mMap.getMyLocation().getLongitude());
            //MainActivity.mMap.addMarker(new MarkerOptions().position(my).title("Your Location"));
            //MainActivity.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(my, 11));

            LocationManager locationManager = (LocationManager) getActivity().getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
            Criteria criteria = new Criteria();

            Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
            if (location != null)
            {
                MainActivity.mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                        new LatLng(location.getLatitude(), location.getLongitude()), 13));

                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(new LatLng(location.getLatitude(), location.getLongitude()))      // Sets the center of the map to location user
                        .zoom(17)                   // Sets the zoom
                        .bearing(90)                // Sets the orientation of the camera to east
                        .tilt(40)                   // Sets the tilt of the camera to 30 degrees
                        .build();                   // Creates a CameraPosition from the builder

                MainActivity.mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

            }

        }
        catch (Exception e){

        }

    }

}
