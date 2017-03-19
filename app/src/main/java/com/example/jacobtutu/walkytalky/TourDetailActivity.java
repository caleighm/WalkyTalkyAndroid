package com.example.jacobtutu.walkytalky;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import okhttp3.Response;


public class TourDetailActivity extends AppCompatActivity {

    final static String TAG = "TourDetailActivity";
    private HttpTask httpTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_detail);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int tourid = bundle.getInt(TourSearch.bundleTourId);

        httpTask = new HttpTask(this);
        httpTask.getPoints(tourid, new HttpTask.HttpCallback() {
            @Override
            public void onFailure(Exception e) {

            }

            @Override
            public void onError(Response response) {

            }

            @Override
            public void onSuccess(Response response) {
                try {
                    String jsonPoints = response.body().string();
                    response.body().close();
                    TourPointParser parser = new TourPointParser();
                    final ArrayList<TourPoint> points = parser.parsePoints(jsonPoints);

                    Log.d(TAG, "POINTS START HERE");
                    for (TourPoint point : points) {
                        Log.d(TAG, point.name);
                    }

//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            loadListView(points);
//                        }
//                    });
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        TextView tvName = (TextView) findViewById(R.id.tvName);
        TextView tvAuthor = (TextView) findViewById(R.id.tvAuthor);
        TextView tvDesc = (TextView) findViewById(R.id.tvDesc);


        String name = bundle.getString(TourSearch.bundleTourName);
        String author = bundle.getString(TourSearch.bundleAuthor);
        String descrip = bundle.getString(TourSearch.bundleDescrip);

        tvName.setText(name);
        tvAuthor.setText(author);
        tvDesc.setText(descrip);


    }
}
