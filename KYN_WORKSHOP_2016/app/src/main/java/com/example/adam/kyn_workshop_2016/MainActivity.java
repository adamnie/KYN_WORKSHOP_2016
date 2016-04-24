package com.example.adam.kyn_workshop_2016;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.estimote.sdk.Nearable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MainActivity extends Activity {

    private ApplicationKYN2016 mApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mApplication = (ApplicationKYN2016) getApplication();

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.main_fragment, new BeaconListFragment(), "BeaconListFragment").commit();
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        mApplication.startRanging();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mApplication.stopRanging();
    }

}
