package com.example.adam.kyn_workshop_2016;

import android.app.Application;
import android.widget.Spinner;

import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Nearable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by adam on 03.04.16.
 */
public class ApplicationKYN2016 extends Application {

    private BeaconManager mBeaconManager;
    private Map<Nearable, Double> mNearables;

    @Override
    public void onCreate() {
        super.onCreate();
        mBeaconManager = new BeaconManager(getApplicationContext());
        mNearables = new HashMap<Nearable, Double>();

        mBeaconManager.setNearableListener(new BeaconManager.NearableListener() {

            @Override
            public void onNearablesDiscovered(List<Nearable> nearables) {
                for(Nearable nearable: nearables) {
                    mNearables.put(nearable, nearable.temperature);
                }
            }
        });
    }

    public void startRanging(){
        mBeaconManager.connect(new BeaconManager.ServiceReadyCallback() {

            @Override
            public void onServiceReady() {
                mBeaconManager.startNearableDiscovery();
            }
        });
    }

    public void stopRanging(){
        mBeaconManager.disconnect();
    }

    public Map<Nearable, Double> getTemperatureMap(){
        return mNearables;
    }
}
