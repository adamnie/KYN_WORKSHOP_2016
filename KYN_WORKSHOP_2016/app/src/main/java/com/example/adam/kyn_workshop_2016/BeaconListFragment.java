package com.example.adam.kyn_workshop_2016;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.estimote.sdk.Nearable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class BeaconListFragment extends Fragment {
    private ListView mBeaconList;
    private ApplicationKYN2016 mApplication;
    private Handler mHandler;
    private List<String> mDataset;
    private ProgressDialog progress;

    private static final Integer UPDATE_LIST_INTERVAL = 4 * 1000;

    public BeaconListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_beacon_list, container, false);

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