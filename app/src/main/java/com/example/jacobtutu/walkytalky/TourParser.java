package com.example.jacobtutu.walkytalky;

import android.media.Rating;

import com.example.jacobtutu.walkytalky.providers.DataProvider;
import com.example.jacobtutu.walkytalky.providers.FileDataProvider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

/**
 * Created by jacobtutu on 18/03/17.
 */

public class TourParser {
    private String filename;
    private String tourID;
    private String tourName;
    private String descrip;
    private String author;
    private Date dateCreated;
    private String city;
    private Rating rating;
    private URL imageURL;
    private URL audioIntroURL;

    public TourParser(String filename) {
        this.filename = filename;
    }

    public void parse() throws IOException, JSONException {
        DataProvider dataProvider = new FileDataProvider(filename);

        parseTours(dataProvider.dataSourceToString());
    }

    public void parseTours(String jsonResponse) throws JSONException, MalformedURLException {
        JSONArray tourArray = new JSONArray(jsonResponse);
        for (int i = 0; i < tourArray.length(); i++) {
            JSONObject tourObject = new JSONObject(tourArray.get(i).toString());
            parseTourObject(tourObject);
            storeToTour();
        }
    }

    public void parseTourObject(JSONObject tourObject) throws JSONException, MalformedURLException{
        tourID = tourObject.get("TourID").toString();
        tourName = tourObject.get("Name").toString();
        descrip = tourObject.get("Description").toString();
        author = tourObject.get("Author").toString();
        dateCreated = parseDate(tourObject.get("Date").toString());
        city = tourObject.get("City").toString();
        rating = parseRating(tourObject.get("Rating").toString());
        imageURL = new URL(tourObject.get("ImageURL").toString());
        audioIntroURL = new URL(tourObject.get("AudioIntroURL").toString());
    }

    public Date parseDate (String date) {
        long dateLong;
        String [] dateElements = date.split(";");
        date = dateElements[0] + dateElements[1] + dateElements[2];
        dateLong = Long.parseLong(date);
        return new Date(dateLong);
    }

    public Rating parseRating (String rating) {
        return Rating.newStarRating(Rating.RATING_5_STARS, Float.parseFloat(rating));
    }

    public void storeToTour() {

    }
}
