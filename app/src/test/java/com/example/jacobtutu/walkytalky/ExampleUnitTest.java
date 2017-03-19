package com.example.jacobtutu.walkytalky;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    TourParser testTP;

    @Before
    public void setUp() throws Exception {
        testTP = new TourParser("tour.json");
    }

    @Test
    public void testParse() throws IOException, JSONException {
        List<Tour> tours = testTP.parseTours("[\n" +
                "  {\n" +
                "    \"TourID\": 1,\n" +
                "    \"Name\": \"UBC TOUR\",\n" +
                "    \"Description\": \"Stepping foot on UBC’s Vancouver campus. \",\n" +
                "    \"Author\": \"Nicole Li\",\n" +
                "    \"dateCreated\": \"\",\n" +
                "    \"City\": \"VANCOUVER\" ,\n" +
                "    \"PointCategory\": \"\",\n" +
                "    \"Rating\": 5,\n" +
                "    \"ImageURL\": \"https://www.ubc.ca/_assets/img/martha-piper-plaza-1920x700.jpg\",\n" +
                "    \"AudioIntroURL\": \"https://www.google.ca\",\n" +
                "  },\n" +
                "  {\n" +
                "    \"TourID\": 2,\n" +
                "    \"Name\": \"VanCity Street Food Tour\",\n" +
                "    \"Description\": \"Taste of the local eats. \",\n" +
                "    \"Author\": \"Alfred Hong\",\n" +
                "    \"dateCreated\": \"\",\n" +
                "    \"City\": \"VANCOUVER\" ,\n" +
                "    \"PointCategory\": \"\",\n" +
                "    \"Rating\": 4,\n" +
                "    \"ImageURL\": \"https://vfs.edu/sites/all/themes/vfs/img/home/vancouver-desktop.jpg\",\n" +
                "    \"AudioIntroURL\": \"\",\n" +
                "  },\n" +
                "  {\n" +
                "    \"TourID\": 3,\n" +
                "    \"Name\": \"Gastown Haunted History Tour\",\n" +
                "    \"Description\": \"Blast from the past tour. \",\n" +
                "    \"Author\": \"Ostap M.\",\n" +
                "    \"dateCreated\": \"\",\n" +
                "    \"City\": \"VANCOUVER\" ,\n" +
                "    \"PointCategory\": \"\",\n" +
                "    \"Rating\": 5,\n" +
                "    \"ImageURL\": \"https://static.wixstatic.com/media/d70212_6c7ff1c293604cb29851d75b75042d86.jpg_srz_957_638_85_22_0.50_1.20_0.00_jpg_srz\",\n" +
                "    \"AudioIntroURL\": \"\",\n" +
                "  },\n" +
                "]");
        assertEquals(3, tours.size());
    }
}