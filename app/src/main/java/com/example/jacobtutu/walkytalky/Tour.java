package com.example.jacobtutu.walkytalky;

import android.media.Rating;

import java.net.URL;
import java.util.Date;

/**
 * Created by jacobtutu on 18/03/17.
 */

public class Tour {
    private String tourID;
    private String tourName;
    private String descrip;
    private String author;
    private Date dateCreated;
    private String city;
    private Rating rating;
    private URL imageURL;
    private URL audioIntroURL;

    public Tour(String tourName, String author, String city) {
        this.tourName = tourName;
        this.author = author;
        this.city = city;
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
}
