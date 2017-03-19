package com.example.jacobtutu.walkytalky;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TourDetailActivity extends AppCompatActivity {

    private TextView activityDescription;

    // Tour selectedTour = (Tour) getIntent().getSerializableExtra("tour");
    // String descrip = selectedTour.descrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_detail);

//        activityDescription = (TextView) findViewById(R.id.descriptionDisplayed);
//        activityDescription.setText();


    }



    public void nextView(View view) {
        Intent appInfo = new Intent(getBaseContext(), TourScreen.class);
        // appInfo.putExtra("tour", selectedTour);

        startActivity(appInfo);
    }
}
