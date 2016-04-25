package com.example.adam.kyn_workshop_2016;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class BeaconArrayAdapter extends ArrayAdapter<BeaconItem>{
    private Context mContext;

    public BeaconArrayAdapter(Context context, ArrayList<BeaconItem> users) {
        super(context, 0, users);
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        return convertView;
    }
}
