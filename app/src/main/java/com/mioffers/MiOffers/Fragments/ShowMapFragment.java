package com.mioffers.MiOffers.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mioffers.MiOffers.MainActivity;
import com.mioffers.MiOffers.R;

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

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        MainActivity.mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        MainActivity.mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

}
