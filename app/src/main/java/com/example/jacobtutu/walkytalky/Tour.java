package com.example.jacobtutu.walkytalky;

import android.media.Rating;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by jacobtutu on 18/03/17.
 */

public class Tour {
    public int tourID;
    public String tourName;
    public String descrip;
    public String author;
    public Date dateCreated;
    public String city;
    public Rating rating;
    public URL imageURL;
    public URL audioIntroURL;

    public Tour(String tourName, String author, String city, int tourID) {
        this.tourName = tourName;
        this.author = author;
        this.city = city;
        this.tourID = tourID;
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

    public void setImageURL(URL imageURL) {
        this.imageURL = imageURL;
    }

    public void setAudioIntroURL(URL audioIntroURL) {
        this.audioIntroURL = audioIntroURL;
    }

    public static ArrayList<Tour> getTours() {
        ArrayList<Tour> tours = new ArrayList<Tour>();
        tours.add(new Tour("Van Tour", "Caleigh", "Vancouver", 1));
        tours.add(new Tour("UBC", "Nicole", "Vancouver", 2));
        tours.add(new Tour("Gastown", "Ostap", "Vancouver", 3));
        return tours;
    }
}
