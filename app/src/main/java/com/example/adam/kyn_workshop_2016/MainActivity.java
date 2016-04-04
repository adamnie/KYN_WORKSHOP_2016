package com.example.adam.kyn_workshop_2016;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.estimote.sdk.Nearable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MainActivity extends Activity {

    private Nearable mCurrentNearable;
    private ApplicationKYN2016 mApplication;
    private Spinner mBeaconSelection;
    private ArrayAdapter mBeaconAdapter;
    private Handler mHandler;
    private TextView mTemperatureView;
    private final Integer UPDATE_LIST_INTERVAL = 4 * 1000;
    private final Integer UPDATE_TEMPERATURE_INTERVAL = 1 * 1000;
    private List<String> mDataset;


    private class BeaconSelectedListener implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Set<Nearable> nearables = mApplication.getTemperatureMap().keySet();
            for (Nearable nearable : nearables){
                if (nearable.identifier.equals(parent.getItemAtPosition(position).toString())){
                    mCurrentNearable = nearable;
                }
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mApplication = (ApplicationKYN2016)getApplication();
        mBeaconSelection = (Spinner)findViewById(R.id.beacon_selection);
        mHandler = new Handler();
        mTemperatureView = (TextView)findViewById(R.id.temperature_view);
        mDataset = new ArrayList<String>();
        mBeaconAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, mDataset);

        mBeaconAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mBeaconSelection.setAdapter(mBeaconAdapter);

        mBeaconSelection.setOnItemSelectedListener(new BeaconSelectedListener());
    }

    final Runnable updateTemperature = new Runnable() {
        @Override
        public void run() {
            try {
                String temperature;
                if (mCurrentNearable != null)
                    temperature = String.valueOf(mApplication.getTemperatureMap().get(mCurrentNearable));
                else
                    temperature = "Unknown";

                mTemperatureView.setText(temperature);
            } finally {
                mHandler.postDelayed(updateTemperature, UPDATE_TEMPERATURE_INTERVAL);
            }
        }
    };

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
    protected void onStart() {
        super.onStart();
        mApplication.startRanging();

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("janusz", mApplication.getTemperatureMap().toString());
        updateAvailableBeacons();
        updateTemperature.run();
        updateBeaconList.run();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mHandler.removeCallbacks(updateTemperature);
        mHandler.removeCallbacks(updateBeaconList);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mApplication.stopRanging();
    }

    private void updateAvailableBeacons(){
        Set<Nearable> nearbales = mApplication.getTemperatureMap().keySet();
        for(Nearable nearable: nearbales) {
            if(!mDataset.contains(nearable.identifier))
                mDataset.add(nearable.identifier);
        }

        mBeaconAdapter.notifyDataSetChanged();
    }

}
