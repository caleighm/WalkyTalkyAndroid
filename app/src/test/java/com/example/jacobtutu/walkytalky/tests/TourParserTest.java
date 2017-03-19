package com.example.jacobtutu.walkytalky.tests;

import com.example.jacobtutu.walkytalky.Tour;
import com.example.jacobtutu.walkytalky.TourParser;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by jacobtutu on 18/03/17.
 */
public class TourParserTest {
    TourParser testTP;

    @Before
    public void setUp() throws Exception {
        testTP = new TourParser("tour.json");
    }

    @Test
    public void parse() throws IOException, JSONException{
        List<Tour> tours = testTP.parse();
        assertEquals(3, tours.size());
    }

}