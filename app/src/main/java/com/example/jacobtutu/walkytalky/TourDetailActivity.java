package com.example.jacobtutu.walkytalky;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class TourDetailActivity extends AppCompatActivity {

    private TextView activityDescription;
    private ImageView activityImage;
    final static String TAG = "MyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_detail);

        activityImage = (ImageView) findViewById(R.id.imgView);
        activityDescription = (TextView) findViewById(R.id.descriptionDisplayed);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String descrip = bundle.getString(TourSearch.bundleDescrip);

        Log.d(TAG, descrip);

//        if (bundle.getBundle("bundleDescrip") != null) {
//            descrip = (String) bundle.getBundle("bundleDescrip").toString();
//            activityDescription.setText(descrip);
//        } else {
//            descrip = " Coming soon!";
            activityDescription.setText(descrip);
//        }
        Log.d(TAG, "onItemClick: " + descrip);



//        try {
//            URL aURL = new URL("http://604now.com/wp-content/uploads/2016/07/storybrooke-steveston.jpg");
//            String sURL = aURL.toString();
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }

//        try {
//            activityImage.setImageDrawable(grabImageFromUrl("http://604now.com/wp-content/uploads/2016/07/storybrooke-steveston.jpg"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


    }

//    private Drawable grabImageFromUrl(String url) throws Exception {
//        return Drawable.createFromStream((InputStream)new URL(url).getContent(), "src");
//    }


    public void nextView(View view) {
        Intent appInfo = new Intent(getBaseContext(), TourScreen.class);
        startActivity(appInfo);
    }
}
