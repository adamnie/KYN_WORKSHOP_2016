package com.example.adam.kyn_workshop_2016;


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

    private static final Integer UPDATE_LIST_INTERVAL = 4 * 1000;

    public BeaconListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_beacon_list, container, false);

        mApplication = (ApplicationKYN2016) getActivity().getApplication();
        mBeaconList = (ListView) view.findViewById(R.id.beacon_list);
        mDataset = new ArrayList<>();
        mHandler = new Handler();

        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BeaconItem beacon = (BeaconItem) parent.getItemAtPosition(position);

                Bundle bundle = new Bundle();
                bundle.putString("beaconId", beacon.getId());
                Fragment fragment = new BeerListFragment();
                fragment.setArguments(bundle);

                getActivity().getFragmentManager().beginTransaction()
                        .replace(R.id.main_fragment, fragment, "BeerListFragment")
                        .addToBackStack("BeerListFragment").commit();

            }
        };

        mBeaconList.setOnItemClickListener(itemClickListener);


        return view;
    }

    private void updateAvailableBeacons(){
        Set<Nearable> nearbales = mApplication.getTemperatureMap().keySet();
        for(Nearable nearable: nearbales) {
            if(!mDataset.contains(nearable.identifier)) {
                mDataset.add(nearable.identifier);
            }
        }

        BeaconArrayAdapter beaconAdapter = new BeaconArrayAdapter(getActivity(), BeaconItem.fromList(mDataset));
        mBeaconList.setAdapter(beaconAdapter);

        beaconAdapter.notifyDataSetChanged();
    }

    final Runnable updateBeaconList = new Runnable() {
        @Override
        public void run() {
            try {
                updateAvailableBeacons();
            } finally {
                mHandler.postDelayed(updateBeaconList, UPDATE_LIST_INTERVAL);
            }
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        updateAvailableBeacons();
        updateBeaconList.run();
    }

    @Override
    public void onPause() {
        super.onPause();
        mHandler.removeCallbacks(updateBeaconList);
    }

}