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

        mApplication = (ApplicationKYN2016) getActivity().getApplication();
        mTemperatureView = (TextView) view.findViewById(R.id.temperature_view);
        mOptimalTemperatureView = (TextView) view.findViewById(R.id.optimal_temperature_view);
        mBeerView = (ImageView) view.findViewById(R.id.beer_view);
        mThermometerView = (ImageView) view.findViewById(R.id.thermometer_view);
        mHandler = new Handler();

        String beaconId = "";

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            beaconId = bundle.getString("beaconId", "");
            beer = bundle.getString("beer", "");
        }

        Set<Nearable> nearables = mApplication.getTemperatureMap().keySet();
        for (Nearable nearable : nearables){
            if (nearable.identifier.equals(beaconId)){
                mCurrentNearable = nearable;
            }
        }

        Resources resources = getActivity().getResources();
        final int resourceId = resources.getIdentifier(beer.toLowerCase(), "drawable",
                getActivity().getPackageName());
        mBeerView.setImageResource(resourceId);

        temperatureRange = BeerItem.beerMap.get(beer);
        mOptimalTemperatureView.setText("Optymalna temperatura: " + temperatureRange[0] + " - " + temperatureRange[1] +  " \u2103");

        return view;
    }

    final Runnable updateTemperature = new Runnable() {
        @Override
        public void run() {
            try {
                String temperatureToDisplay;
                if (mCurrentNearable != null){
                    Double currentTemperature = mApplication.getTemperatureMap().get(mCurrentNearable);
                    temperatureToDisplay = "Aktualna temperatura: " + String.valueOf(currentTemperature) + " \u2103";
                    if (currentTemperature < temperatureRange[0])
                        mThermometerView.setImageResource(R.drawable.cold);
                    else if (currentTemperature > temperatureRange[1])
                        mThermometerView.setImageResource(R.drawable.hot);
                    else
                        mThermometerView.setImageResource(R.drawable.ideal);
                }

                else
                    temperatureToDisplay = "Unknown";

                mTemperatureView.setText(temperatureToDisplay);
            } finally {
                mHandler.postDelayed(updateTemperature, UPDATE_TEMPERATURE_INTERVAL);
            }
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        updateTemperature.run();
    }

    @Override
    public void onPause() {
        super.onPause();
        mHandler.removeCallbacks(updateTemperature);
    }


}
