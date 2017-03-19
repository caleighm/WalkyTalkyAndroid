package com.example.jacobtutu.walkytalky;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;


import org.json.JSONException;

import java.net.MalformedURLException;
import java.util.ArrayList;

import java.util.List;

public class TourScreen extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Tour tour;
    private List<TourPoint> points;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String jsonPoints = bundle.getString(TourDetailActivity.JSON_POINTS);

        TourPointParser parser = new TourPointParser();
        try {
            points = parser.parsePoints(jsonPoints);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

//        tour = (Tour) getIntent().getSerializableExtra("tour");
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        for (TourPoint p : points) {
            mMap.addMarker(new MarkerOptions().position(p.latLon).title(p.name));
        }
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(points.get(0).latLon, 13, 0, 0)));

    }

}
