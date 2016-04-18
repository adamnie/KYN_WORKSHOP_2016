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
        // Get the data item for this position
        BeaconItem item = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }

        // Lookup view for data population
        ImageView icon = (ImageView) convertView.findViewById(R.id.icon);
        TextView name = (TextView) convertView.findViewById(R.id.name);

        // Populate the data into the template view using the data object
        Resources resources = mContext.getResources();
        final int resourceId = resources.getIdentifier(item.getName(), "drawable",
                mContext.getPackageName());

        icon.setImageResource(resourceId);
        name.setText(item.getName());

        // Return the completed view to render on screen
        return convertView;
    }
}
