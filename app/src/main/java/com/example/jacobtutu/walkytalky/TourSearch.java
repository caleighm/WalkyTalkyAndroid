package com.example.jacobtutu.walkytalky;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class TourSearch extends AppCompatActivity {

    private TextView mTextMessage;
    final static  String bundleTourName = "TOUR NAME";
    final static  String bundleDescrip = "DESCRIP";
    final static String bundleAuthor = "AUTHOR";
    final static String bundleDateCreated = "DATE CREATED";
    final static  String bundleCity = "CITY";
    final static String bundleRating = "RATING";
    final static String bundleImageURL = "IMAGE URL";
    Tour selectedTour;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_search);

        ArrayList<Tour> arrayOfTours = Tour.getTours();
        ToursAdapter adapter = new ToursAdapter(this, arrayOfTours);

        final ListView lv = (ListView) findViewById (R.id.lvTours);

        lv.setAdapter(adapter);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                // out.println("print something");
                Tour tour = (Tour) lv.getItemAtPosition(position);

                Bundle bundle = new Bundle();

                bundle.putString(bundleTourName, tour.tourName);
                bundle.putString(bundleDescrip, tour.descrip);
                bundle.putString(bundleAuthor, tour.author);

                // bundle.putString(bundleDateCreated, tour.dateCreated.toString());
                bundle.putString(bundleDateCreated, "");

                bundle.putString(bundleCity, tour.city);

                // bundle.putString(bundleRating, tour.rating.toString());
                bundle.putString(bundleRating, "");

                bundle.putString(bundleImageURL, "https://www.ubc.ca/_assets/img/martha-piper-plaza-1920x700.jpg");
                Intent appInfo = new Intent(getBaseContext(), TourDetailActivity.class);

                appInfo.putExtra("tour", tour);

                startActivity(appInfo);
            }
        });


    }


}
