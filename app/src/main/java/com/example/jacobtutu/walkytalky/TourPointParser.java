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
        tourID = Integer.parseInt(pointObject.get("tour_id").toString());
        pointID = Integer.parseInt(pointObject.get("point_id").toString());
        orderInTour = Integer.parseInt(pointObject.get("order_in_tour").toString());
        pointName = pointObject.get("name").toString();
        address = pointObject.get("address").toString();
        lat = Double.parseDouble(pointObject.get("lat").toString());
        lon = Double.parseDouble(pointObject.get("lon").toString());
        latLon = new LatLng(lat, lon);
        imageURL = pointObject.get("image_url").toString();
        audioURL = pointObject.get("audio_url").toString();
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
