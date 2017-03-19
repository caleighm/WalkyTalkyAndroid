package com.example.jacobtutu.walkytalky;

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


import java.util.ArrayList;

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
       // Tour tour = (Tour) getIntent().getSerializableExtra("tour");


        // Add a marker in Sydney and move the camera

//        List<TourPoint> points = tour.points;
//        PolylineOptions pointOptions = new PolylineOptions();
//        for (TourPoint p : points) {
//            mMap.addMarker(new MarkerOptions().position(p.latLon).title(p.name));
//            pointOptions.add(p.latLon);
//        }
//        mMap.addPolyline(pointOptions);
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(tour.points.get(0).latLon));

//        mMap.addMarker(new MarkerOptions().position(new LatLng(49, -133)).title("Canada"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(49, -133)));

        List<TourPoint> points = new ArrayList<>();

        points.add(new TourPoint("Cool", 1, 1, 1, new LatLng(49.2670278, -123.2621906)));
        points.add(new TourPoint("Cool2", 2, 1, 2, new LatLng(49.269574, -123.2587997)));
        points.add(new TourPoint("Cool3", 3, 1, 3, new LatLng(49.2666566, -123.2573003)));
        points.add(new TourPoint("Cool4", 4, 1, 4, new LatLng(49.2666867, -123.2726212)));



//         // points = tour.points;
//
// //        PolylineOptions pointOptions = new PolylineOptions();
//          for (TourPoint p : points) {
//              mMap.addMarker(new MarkerOptions().position(p.latLon).title(p.name));
// //             pointOptions.add(p.latLon);
//          }
//
//
//         // mMap.addPolyline(pointOptions);
//         mMap.moveCamera(CameraUpdateFactory.newLatLng(points.get(0).latLon));

//         mMap.addPolyline(pointOptions);
//         mMap.moveCamera(CameraUpdateFactory.newLatLng(tour.points.get(0).latLon));

//        mMap.addPolyline(pointOptions);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(points.get(0).latLon, 13));

//        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(points.get(0).latLon, 15, 0, 0)));


    }

}
