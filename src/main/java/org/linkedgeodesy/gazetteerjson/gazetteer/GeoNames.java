package org.linkedgeodesy.gazetteerjson.gazetteer;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.linkedgeodesy.org.gazetteerjson.json.GGeoJSONSingleFeature;
import org.linkedgeodesy.org.gazetteerjson.json.NamesJSONObject;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

/**
 * functions to query the iDAI gazetteer
 *
 * @author Florian Thiery
 */
public class GeoNames {

    public static GGeoJSONSingleFeature getPlaceById(String id) throws IOException, ParseException, JDOMException {
        GGeoJSONSingleFeature json = new GGeoJSONSingleFeature();
        String uri = "http://api.geonames.org/get?geonameId=" + id + "&username=chron.ontology";
        URL url = new URL(uri);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/xml;charset=UTF-8");
        int responseCode = con.getResponseCode();
        System.out.println("GeoNames Response Code : " + responseCode + " - " + url);
        if (responseCode < 400) {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            InputStream stream = new ByteArrayInputStream(response.toString().getBytes("UTF-8"));
            SAXBuilder builder = new SAXBuilder();
            Document document = (Document) builder.build(stream);
            Element rootNode = document.getRootElement();
            // add and get geometry
            Double lat = Double.parseDouble(rootNode.getChildText("lat"));
            Double lng = Double.parseDouble(rootNode.getChildText("lng"));
            JSONObject geometry = new JSONObject();
            JSONArray point = new JSONArray();
            point.add(lng);
            point.add(lat);
            geometry.put("type", "Point");
            geometry.put("coordinates", point);
            // add and get names
            NamesJSONObject names = new NamesJSONObject();
            List alternateNames = rootNode.getChildren("alternateName");
            for (Object item : alternateNames) {
                Element node = (Element) item;
                HashSet hs = new HashSet();
                hs.add(node.getText());
                if (node.getAttributeValue("lang") != null) {
                    if (!node.getAttributeValue("lang").equals("link")) {
                        names.setName((String) node.getAttributeValue("lang"), hs);
                    }
                }
            }
            json.setGeometry(geometry);
            json.setProperties("http://sws.geonames.org/" + id, id, "geonames", names);
        }
        return json;

    }

}
