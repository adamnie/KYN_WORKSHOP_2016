package com.example.adam.kyn_workshop_2016;


import android.content.res.Resources;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.estimote.sdk.Nearable;

import java.util.Set;


public class TemperatureFragment extends Fragment {

    private TextView mTemperatureView, mOptimalTemperatureView;
    private ImageView mBeerView, mThermometerView;
    private Handler mHandler;
    private Nearable mCurrentNearable;
    private ApplicationKYN2016 mApplication;
    private Integer[] temperatureRange;
    private static final Integer UPDATE_TEMPERATURE_INTERVAL = 1 * 1000;

    private String beer;

    public TemperatureFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_temperature, container, false);

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }


}
