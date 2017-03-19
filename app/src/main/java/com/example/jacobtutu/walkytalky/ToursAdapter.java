package com.example.jacobtutu.walkytalky;

/**
 * Created by jacobtutu on 18/03/17.
 */

import android.widget.ArrayAdapter;
import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Caleigh on 3/18/2017.
 */

public class ToursAdapter extends ArrayAdapter<Tour> {
    public ToursAdapter(Context context, ArrayList<Tour> tours) {
        super(context, 0, tours);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Tour tour = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.search_list_item, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvHome = (TextView) convertView.findViewById(R.id.tvAuthor);
        // Populate the data into the template view using the data object
        tvName.setText(tour.tourName);
        tvHome.setText(tour.author);
        // Return the completed view to render on screen
        return convertView;
    }

}
