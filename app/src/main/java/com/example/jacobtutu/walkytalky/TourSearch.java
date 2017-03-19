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

import org.json.JSONException;

import java.io.IOException;
import java.io.Serializable;

import java.net.MalformedURLException;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Response;

public class TourSearch extends AppCompatActivity {

    private TextView mTextMessage;
    final static  String bundleTourName = "TOUR NAME";
    final static  String bundleDescrip = "DESCRIP";
    final static String bundleAuthor = "AUTHOR";
    final static String bundleDateCreated = "DATE CREATED";
    final static  String bundleCity = "CITY";
    final static String bundleRating = "RATING";
    final static String bundleImageURL = "IMAGE URL";
    final static String bundleTourId = "TOUR ID";

    Tour selectedTour;

    final static String TAG = "MyActivity";

    private HttpTask httpTask;


//    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
//            = new BottomNavigationView.OnNavigationItemSelectedListener() {
//
//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//            switch (item.getItemId()) {
//                case R.id.navigation_home:
//                    mTextMessage.setText(R.string.title_home);
//                    return true;
//                case R.id.navigation_dashboard:
//                    mTextMessage.setText(R.string.title_dashboard);
//                    return true;
//                case R.id.navigation_notifications:
//                    mTextMessage.setText(R.string.title_notifications);
//                    return true;
//            }
//            return false;
//        }
//
//    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_search);

        httpTask = new HttpTask(this);
        getTours();
    }

    private void getTours() {
        httpTask.getAllTours(new HttpTask.HttpCallback() {
            @Override
            public void onFailure(Exception e) {

            }

            @Override
            public void onError(Response response) {

            }

            @Override
            public void onSuccess(Response response) {
                try {
                    String jsonTours = response.body().string();
                    response.body().close();
                    TourParser parser = new TourParser();
                    final ArrayList<Tour> tours = parser.parseTours(jsonTours);

                    for (Tour tour : tours) {
                        Log.d(TAG, tour.tourName);
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            loadListView(tours);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void loadListView(ArrayList<Tour> tours) {
        ToursAdapter adapter = new ToursAdapter(this, tours);

        final ListView lv = (ListView) findViewById (R.id.lvTours);

        lv.setAdapter(adapter);


//        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                // out.println("print something");
                Tour tour = (Tour) lv.getItemAtPosition(position);

                Bundle bundle = new Bundle();

                bundle.putString(bundleTourName, tour.tourName);
                bundle.putString(bundleDescrip, tour.descrip);
                bundle.putString(bundleAuthor, tour.author);
                bundle.putString(bundleCity, tour.city);
                bundle.putInt(bundleTourId, tour.tourID);
                bundle.putString(bundleImageURL, tour.imageURL);

                Intent intent = new Intent(getBaseContext(), TourDetailActivity.class);
                intent.putExtras(bundle);

                startActivity(intent);

            }
        });
    }


}
