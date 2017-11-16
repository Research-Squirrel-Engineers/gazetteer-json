package org.linkedgeodesy.org.gazetteerjson.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * JSONObject to store gazetter search results
 *
 * @author Florian Thiery
 */
public class GGeoJSONSingleFeature extends JSONObject {

    public GGeoJSONSingleFeature() {
        super();
        super.put("type", "Feature");
        super.put("geometry", new JSONObject());
        super.put("properties", new JSONObject());
    }

    /**
     * set GeoJSON geometry
     *
     * @param geometry
     */
    public void setGeometry(JSONObject geometry) {
        super.remove("geometry");
        super.put("geometry", geometry);
    }

    /**
     * get geometry
     *
     * @return geometry json object
     */
    public JSONObject getGeometry() {
        return (JSONObject) super.get("geometry");
    }

    /**
     * set GeoJSON properties
     *
     * @param url
     * @param gazetteerid
     * @param gazetteertype
     * @param names
     */
    public void setProperties(String url, String gazetteerid, String gazetteertype, NamesJSONObject names) {
        JSONObject properties = new JSONObject();
        super.remove("properties");
        properties.put("@id", url);
        properties.put("gazetteerid", gazetteerid);
        properties.put("gazetteertype", gazetteertype);
        properties.put("names", names);
        super.put("properties", properties);
    }

    /**
     * get properties
     *
     * @return properties json object
     */
    public JSONObject getProperties() {
        return (JSONObject) super.get("properties");
    }

    public static JSONObject getJSONLD(JSONObject json) throws IOException {
        try {
            JSONObject jsonld = new JSONObject();
            // read GeoJSON-LD Context
            JSONObject data = new JSONObject();
            URL obj = new URL("https://raw.githubusercontent.com/linkedgeodesy/geojson-plus-ld/master/geojson-context-lg.jsonld");
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            if (con.getResponseCode() == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                data = (JSONObject) new JSONParser().parse(response.toString());
            }
            // get context
            JSONObject context = (JSONObject) data.get("@context");
            // get properties
            JSONObject properties = (JSONObject) json.get("properties");
            // get and transform names
            JSONObject names = (JSONObject) properties.get("names");
            JSONArray namesLD = new JSONArray();
            for (Iterator iterator = names.keySet().iterator(); iterator.hasNext();) {
                String key = (String) iterator.next();
                if (key.equals("prefName")) {
                    JSONObject prefName = (JSONObject) names.get("prefName");
                    //System.out.println("prefName: " + prefName.get("name") + "@" + prefName.get("lang"));
                    properties.put("prefName", prefName.get("name") + "@" + prefName.get("lang"));
                } else {
                    JSONArray tmp = (JSONArray) names.get(key);
                    for (Object item : tmp) {
                        String thisItem = (String) item;
                        //System.out.println(thisItem + "@" + key);
                        namesLD.add(thisItem + "@" + key);
                    }
                }
            }
            properties.remove("names");
            properties.put("names", namesLD);
            // transform id
            String id = (String) properties.get("@id");
            properties.remove("@id");
            // write JSONLD
            jsonld.put("@context", context);
            jsonld.put("type", json.get("type"));
            jsonld.put("id", id);
            jsonld.put("geometry", json.get("geometry"));
            jsonld.put("properties", properties);
            // output
            return jsonld;
        } catch (Exception e) {
            return null;
        }
    }

}
