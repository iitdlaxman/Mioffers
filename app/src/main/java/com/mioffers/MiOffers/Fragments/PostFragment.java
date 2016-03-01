package com.mioffers.MiOffers.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mioffers.MiOffers.R;

/**
 * Created by laxman.muttineni on 02/03/16.
 */
public class PostFragment extends Fragment {
    public PostFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.post_layout, container, false);


    }

}
