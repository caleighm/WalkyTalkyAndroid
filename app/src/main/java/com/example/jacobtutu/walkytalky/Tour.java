package com.example.jacobtutu.walkytalky;

import android.media.Rating;


import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

import com.example.jacobtutu.walkytalky.util.LatLon;
import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;
import java.net.MalformedURLException;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jacobtutu on 18/03/17.
 */


public class Tour implements Serializable{

    public int tourID;
    public String tourName;
    public String descrip;
    public String author;
    public Date dateCreated;
    public String city;
    public Rating rating;
    public String imageURL;
    public String audioIntroURL;
    public TourCategory category;
    public static List<TourPoint> points;


    public Tour(String tourName, String author, String city, int tourID, TourCategory category) {
        this.tourName = tourName;
        this.author = author;
        this.city = city;
        this.tourID = tourID;
        this.category = category;
        this.points = new ArrayList<>();
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setAudioIntroURL(String audioIntroURL) {
        this.audioIntroURL = audioIntroURL;
    }

    public void addPoint(TourPoint tp) {
        points.add(tp);
    }

    public void setPoints(List<TourPoint> points) {
        this.points = points;
    }


    public static ArrayList<Tour> getTours() throws MalformedURLException {

        ArrayList<Tour> tours = new ArrayList<Tour>();
        Tour cTour = new Tour("Van Tour", "Caleigh", "Vancouver", 1, TourCategory.SIGHTSEEING);
//        TourPoint point = new TourPoint("Cool", 1, 1, 1, new LatLng(49.2670278, -123.2621906));
//        point.addCategory(PointCategory.ENTERTAINMENT);
//        cTour.addPoint(point);
        tours.add(cTour);
        tours.add(new Tour("UBC", "Nicole", "Vancouver", 2, TourCategory.HISTORY));
        tours.add(new Tour("Gastown", "Ostap", "Vancouver", 3, TourCategory.FOOD));

        Tour testTour = new Tour("Steveston Film Spot Tour", "Jacob", "Richmond", 4, TourCategory.FOOD);
        tours.add(testTour);
        testTour.setDescrip("With its fairy tale charm, it's no wonder visitors flock to the picturesque seaside village of Steveston. ");

        String testURL = "http://604now.com/wp-content/uploads/2016/07/storybrooke-steveston.jpg";
        testTour.setImageURL(testURL);

        TourPoint p1 = new TourPoint("name1", 1, 1, 1, new LatLng(22, 22));
        TourPoint p2 = new TourPoint("name2", 1, 1, 1, new LatLng(33, 33));
        TourPoint p3 = new TourPoint("name3", 1, 1, 1, new LatLng(44, 44));
        points = new ArrayList<>();
        points.add(p1);
        points.add(p2);
        points.add(p3);
        testTour.setPoints(points);


        return tours;
    }
}
