package com.example.jacobtutu.walkytalky;

import com.example.jacobtutu.walkytalky.providers.DataProvider;
import com.example.jacobtutu.walkytalky.providers.FileDataProvider;
import com.example.jacobtutu.walkytalky.util.LatLon;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jacobtutu on 18/03/17.
 */

public class TourPointParser {
    private String filename;
    private int tourID;
    private int pointID;
    private int orderInTour;
    private double lat;
    private double lon;
    private LatLng latLon;
    private String pointName;
    private String address;
    private String imageURL;
    private String audioURL;
    private List<PointCategory> categories;

    public List<TourPoint> parse() throws IOException, JSONException {
        DataProvider dataProvider = new FileDataProvider(filename);

        return parsePoints(dataProvider.dataSourceToString());
    }

    public ArrayList<TourPoint> parsePoints(String jsonResponse) throws JSONException, MalformedURLException {
        JSONArray pointArray = new JSONArray(jsonResponse);
        ArrayList<TourPoint> points = new ArrayList();
        for (int i = 0; i < pointArray.length(); i++) {
            JSONObject pointObject = new JSONObject(pointArray.get(i).toString());
            parsePointObject(pointObject);
            points.add(makePoint());
        }
        return points;
    }

    public void parsePointObject(JSONObject pointObject) throws JSONException, MalformedURLException{
        tourID = Integer.parseInt(pointObject.get("TourID").toString());
        pointID = Integer.parseInt(pointObject.get("PointID").toString());
        orderInTour = Integer.parseInt(pointObject.get("OrderInTour").toString());
        pointName = pointObject.get("Name").toString();
        address = pointObject.get("Address").toString();
        lat = Long.parseLong(pointObject.get("Latitude").toString());
        lon = Long.parseLong(pointObject.get("Longitude").toString());
        latLon = new LatLng(lat, lon);
        imageURL = pointObject.get("ImageURL").toString();
        audioURL = pointObject.get("AudioIntroURL").toString();
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
        TourPoint tourPoint = new TourPoint(pointName, pointID, tourID, orderInTour, latLon);
        tourPoint.setCategories(categories);
        tourPoint.setAddress(address);
        tourPoint.setImageURL(imageURL);
        tourPoint.setAudioURL(audioURL);
        return tourPoint;
    }
}
