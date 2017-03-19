package com.example.jacobtutu.walkytalky;

import com.example.jacobtutu.walkytalky.util.LatLon;

import java.net.URL;

/**
 * Created by jacobtutu on 18/03/17.
 */

public class TourPoint {
    private int tourID;
    private int pointID;
    private String name;
    private String address;
    private double lat;
    private double lon;
    private int orderInTour;
    private URL audioURL;
    private URL imageURL;

    private LatLon latLon;
}
