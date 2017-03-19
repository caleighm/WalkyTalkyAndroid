package com.example.jacobtutu.walkytalky;

import android.util.Log;
import android.widget.ArrayAdapter;
import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Caleigh on 3/19/2017.
 */

public class PointsAdapter extends ArrayAdapter<TourPoint> {
    public PointsAdapter(Context context, ArrayList<TourPoint> points) {
        super(context, 0, points);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        TourPoint point = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.tour_point_item, parent, false);
        }
        // Lookup view for data population
        TextView tvPointName = (TextView) convertView.findViewById(R.id.tvPointName);
        // Populate the data into the template view using the data object
        Log.d("Adapter", "Point name: " + point.name);
        tvPointName.setText(point.name);
        // Return the completed view to render on screen
        return convertView;
    }

}
