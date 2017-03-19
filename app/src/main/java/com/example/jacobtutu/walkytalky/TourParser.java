package com.example.jacobtutu.walkytalky;

import android.media.Rating;

import com.example.jacobtutu.walkytalky.providers.DataProvider;
import com.example.jacobtutu.walkytalky.providers.FileDataProvider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jacobtutu on 18/03/17.
 */

public class TourParser {
    private String filename;
    private int tourID;
    private String tourName;
    private String descrip;
    private String author;
    private Date dateCreated;
    private String city;
    private Rating rating;
    private URL imageURL;
    private URL audioIntroURL;
    private TourCategory category;

    public TourParser(String filename) {
        this.filename = filename;
    }

    public List<Tour> parse() throws IOException, JSONException {
        DataProvider dataProvider = new FileDataProvider(filename);

        return parseTours(dataProvider.dataSourceToString());
    }

    public List<Tour> parseTours(String jsonResponse) throws JSONException, MalformedURLException {
        JSONArray tourArray = new JSONArray(jsonResponse);
        List<Tour> tours = new ArrayList();
        for (int i = 0; i < tourArray.length(); i++) {
            JSONObject tourObject = new JSONObject(tourArray.get(i).toString());
            parseTourObject(tourObject);
            tours.add(makeTour());
        }
        return tours;
    }

    public void parseTourObject(JSONObject tourObject) throws JSONException, MalformedURLException{
        tourID = Integer.parseInt(tourObject.get("TourID").toString());
        tourName = tourObject.get("Name").toString();
        descrip = tourObject.get("Description").toString();
        author = tourObject.get("Author").toString();
        dateCreated = parseDate(tourObject.get("Date").toString());
        city = tourObject.get("City").toString();
        rating = parseRating(tourObject.get("Rating").toString());
        imageURL = new URL(tourObject.get("ImageURL").toString());
        audioIntroURL = new URL(tourObject.get("AudioIntroURL").toString());
        String c = tourObject.get("Category").toString();
        switch (c) {
            case "Food":
                category = TourCategory.FOOD;
            case "Sightseeing":
                category = TourCategory.SIGHTSEEING;
            case "History":
                category = TourCategory.HISTORY;
            case "Art":
                category = TourCategory.ART;
        }
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

    public Tour makeTour() {
        Tour tour = new Tour(tourName, author, city, tourID, category);
        tour.setDateCreated(dateCreated);
        tour.setDescrip(descrip);
        tour.setRating(rating);
        tour.setImageURL(imageURL);
        tour.setAudioIntroURL(audioIntroURL);
        return tour;
    }

//    public String loadJSONFromAsset() {
//        String json = null;
//        try {
//
//            InputStream is = getAssets().open("file_name.json");
//
//            int size = is.available();
//
//            byte[] buffer = new byte[size];
//
//            is.read(buffer);
//
//            is.close();
//
//            json = new String(buffer, "UTF-8");
//
//
//        } catch (IOException ex) {
//            ex.printStackTrace();
//            return null;
//        }
//        return json;
//
//    }
}
