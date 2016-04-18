package com.example.adam.kyn_workshop_2016;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;


public class BeerListFragment extends Fragment {
    private String beaconId;

    public BeerListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_beer_list, container, false);

        ListView beerList = (ListView) view.findViewById(R.id.beer_list);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            beaconId = bundle.getString("beaconId", "");
        }

        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String beer = (String) parent.getItemAtPosition(position);

                Bundle bundle = new Bundle();
                bundle.putString("beaconId", beaconId);
                bundle.putString("beer", beer.toLowerCase());
                Fragment fragment = new TemperatureFragment();
                fragment.setArguments(bundle);

                getActivity().getFragmentManager().beginTransaction()
                        .replace(R.id.main_fragment, fragment, "TemperatureFragment")
                        .addToBackStack("TemperatureFragment").commit();

            }
        };

        beerList.setOnItemClickListener(itemClickListener);

        String[] list = getResources().getStringArray(R.array.beer_list);

        BeerArrayAdapter beerAdapter = new BeerArrayAdapter(getActivity(), new ArrayList<>(Arrays.asList(list)));
        beerList.setAdapter(beerAdapter);

        return view;
    }


}
