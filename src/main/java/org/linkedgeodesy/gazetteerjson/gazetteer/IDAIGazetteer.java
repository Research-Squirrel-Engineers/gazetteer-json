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
import org.linkedgeodesy.org.gazetteerjson.json.GGeoJSONFeatureCollection;
import org.linkedgeodesy.org.gazetteerjson.json.GGeoJSONSingleFeature;
import org.linkedgeodesy.org.gazetteerjson.json.NamesJSONObject;

/**
 * JSONObject to store names information
 *
 * @author Florian Thiery
 */
public class IDAIGazetteer {

    public static GGeoJSONSingleFeature getPlaceById(String id) throws IOException, ParseException {
        GGeoJSONSingleFeature json = new GGeoJSONSingleFeature();
        String uri = "http://gazetteer.dainst.org/place/" + id;
        URL url = new URL(uri);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("Accept", "application/vnd.geo+json");
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        System.out.println("DAI Gazetteer Response Code : " + responseCode + " - " + url);
        boolean redirect = false;
        int status = con.getResponseCode();
        if (status != HttpURLConnection.HTTP_OK) {
            if (status == HttpURLConnection.HTTP_MOVED_TEMP
                    || status == HttpURLConnection.HTTP_MOVED_PERM
                    || status == HttpURLConnection.HTTP_SEE_OTHER) {
                redirect = true;
            }
        }
        if (redirect) {
            String newUrl = con.getHeaderField("Location");
            // open the new connnection again
            con = (HttpURLConnection) new URL(newUrl).openConnection();
            con.setRequestProperty("Accept", "application/vnd.geo+json");
            int responseCode2 = con.getResponseCode();
            System.out.println("Redirect to URL : " + responseCode + " - " + newUrl);
        }
        if (responseCode < 400) {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(response.toString());
            JSONObject properties = (JSONObject) jsonObject.get("properties");
            JSONArray dainames = (JSONArray) properties.get("names");
            NamesJSONObject names = new NamesJSONObject();
            for (Object item : dainames) {
                JSONObject tmp = (JSONObject) item;
                if (tmp.get("language") != null) {
                    HashSet hs = new HashSet();
                    hs.add(tmp.get("title"));
                    names.setName((String) tmp.get("language"), hs);
                }
            }
            json.setGeometry((JSONObject) jsonObject.get("geometry"));
            json.setProperties(uri, id, "dai", names);
        }
        return json;
    }
    
    public static GGeoJSONFeatureCollection getPlaceByBBox(String id) throws IOException, ParseException {
        GGeoJSONFeatureCollection json = new GGeoJSONFeatureCollection();
        return json;
    }

}
