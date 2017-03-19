package com.example.jacobtutu.walkytalky;

import android.media.Rating;

import com.example.jacobtutu.walkytalky.providers.DataProvider;
import com.example.jacobtutu.walkytalky.providers.FileDataProvider;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
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
    private int pointID;
    private int pointTourID;
    private int orderInTour;
    private double lat;
    private double lon;
    private LatLng latLon;
    private String pointName;
    private String address;
    private URL pointImageURL;
    private URL audioURL;
    private List<PointCategory> categories;
    private List<TourPoint> tourPoints;

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
            Tour tour = makeTour();
            tour.setPoints(tourPoints);
            tours.add(tour);
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
        pointImageURL = new URL(tourObject.get("ImageURL").toString());
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
        JSONArray points = new JSONArray(tourObject.get("Points").toString());
        tourPoints = new ArrayList<>();
        for (int i = 0; i < points.length(); i++) {
            JSONObject pointObject = new JSONObject(points.get(i).toString());
            parsePointObject(pointObject);
            tourPoints.add(makePoint());
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
        tour.setImageURL(pointImageURL);
        tour.setAudioIntroURL(audioIntroURL);
        return tour;
    }

    public void parsePointObject(JSONObject pointObject) throws JSONException, MalformedURLException{
        pointTourID = Integer.parseInt(pointObject.get("TourID").toString());
        pointID = Integer.parseInt(pointObject.get("PointID").toString());
        orderInTour = Integer.parseInt(pointObject.get("OrderInTour").toString());
        pointName = pointObject.get("Name").toString();
        address = pointObject.get("Address").toString();
        lat = Long.parseLong(pointObject.get("Latitude").toString());
        lon = Long.parseLong(pointObject.get("Longitude").toString());
        latLon = new LatLng(lat, lon);
        pointImageURL = new URL(pointObject.get("ImageURL").toString());
        audioURL = new URL(pointObject.get("AudioIntroURL").toString());
        String[] c = pointObject.get("Categories").toString().split(",");
        for (int i = 0; i < c.length; i++) {
            switch (c[i]) {
                case "Garden" :
                    categories.add(PointCategory.GARDEN);
                case "Food" :
                    categories.add(PointCategory.FOOD);
                case "Sightseeing" :
                    categories.add(PointCategory.SIGHTSEEING);
                case "Entertainment" :
                    categories.add(PointCategory.ENTERTAINMENT);
                case "Library" :
                    categories.add(PointCategory.LIBRARY);
            }
        }
    }

    public TourPoint makePoint() {
        TourPoint tourPoint = new TourPoint(pointName, pointID, pointTourID, orderInTour, latLon);
        tourPoint.setCategories(categories);
        tourPoint.setAddress(address);
        tourPoint.setImageURL(pointImageURL);
        tourPoint.setAudioURL(audioURL);
        return tourPoint;
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
