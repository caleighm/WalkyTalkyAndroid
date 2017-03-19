package com.example.jacobtutu.walkytalky;

import com.example.jacobtutu.walkytalky.util.LatLon;

import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by jacobtutu on 18/03/17.
 */

public class TourPoint {
    private int tourID;
    private int pointID;
    private String name;
    private String address;
    private int orderInTour;
    private URL audioURL;
    private URL imageURL;
    private List<PointCategory> categories;
    private LatLon latLon;

    public TourPoint(String name, int pointID, int tourID, int orderInTour, LatLon latLon) {
        this.name = name;
        this.pointID = pointID;
        this.latLon = latLon;
        this.tourID = tourID;
        this.categories = new ArrayList<>();
        this.orderInTour = orderInTour;
    }

    public void setAddress(String ad) {
        this.address = ad;
    }

    public void setAudioURL(URL audioURL) {
        this.audioURL = audioURL;
    }

    public void setImageURL(URL imageURL) {
        this.imageURL = imageURL;
    }

    public void addCategory(PointCategory c) {
        categories.add(c);
    }

    public void setCategories(List<PointCategory> categories) {
        this.categories = categories;
    }
}
