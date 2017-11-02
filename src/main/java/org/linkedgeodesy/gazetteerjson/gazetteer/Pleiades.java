package org.linkedgeodesy.gazetteerjson.gazetteer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.linkedgeodesy.org.gazetteerjson.json.GGeoJSONSingleFeature;
import org.linkedgeodesy.org.gazetteerjson.json.NamesJSONObject;

/**
 * functions to query Pleiades Places
 *
 * @author Florian Thiery
 */
public class Pleiades {

    public static GGeoJSONSingleFeature getPlaceById(String id) throws IOException, ParseException {
        GGeoJSONSingleFeature json = new GGeoJSONSingleFeature();
        String uri = "http://pelagios.org/peripleo/places/http:%2F%2Fpleiades.stoa.org%2Fplaces%2F" + id;
        URL url = new URL(uri);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        System.out.println("Pleiades.getPlaceById() - " + responseCode + " - " + url);
        if (responseCode < 400) {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(response.toString());
            // get geom
            JSONObject geo_bounds = (JSONObject) jsonObject.get("geo_bounds");
            JSONObject geometry = new JSONObject();
            JSONArray point = new JSONArray();
            point.add((Double) geo_bounds.get("min_lon"));
            point.add((Double) geo_bounds.get("min_lat"));
            geometry.put("type", "Point");
            geometry.put("coordinates", point);
            // get and add prefName
            NamesJSONObject names = new NamesJSONObject();
            String prefName = (String) jsonObject.get("title");
            names.addPrefName("unknown", prefName);
            // get and add alternative names
            JSONArray namesArray = (JSONArray) jsonObject.get("names");
            names.setName("unknown", new HashSet());
            for (Object item : namesArray) {
                String tmp = (String) item;
                names.addName("unknown", tmp);
            }
            json.setGeometry(geometry);
            json.setProperties("https://pleiades.stoa.org/places/" + id, id, "pleiades", names);
        }
        return json;
    }

}
