package com.example.jacobtutu.walkytalky;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.List;

public class TourScreen extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Tour tour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
        Tour tour = (Tour) getIntent().getSerializableExtra("tour");

        // Add a marker in Sydney and move the camera
        List<TourPoint> points = tour.points;
        PolylineOptions pointOptions = new PolylineOptions();
        for (TourPoint p : points) {
            mMap.addMarker(new MarkerOptions().position(p.latLon).title(p.name));
            pointOptions.add(p.latLon);
        }
        mMap.addPolyline(pointOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(tour.points.get(0).latLon));
    }

}
