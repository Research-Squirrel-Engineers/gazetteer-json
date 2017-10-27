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
import org.json.simple.parser.JSONParser;
import org.linkedgeodesy.gazetteerjson.utils.Functions;
import org.linkedgeodesy.org.gazetteerjson.json.GGeoJSONFeatureCollection;
import org.linkedgeodesy.org.gazetteerjson.json.GGeoJSONFeatureObject;

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
            String prefName = rootNode.getChildText("name");
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
            // add prefName to names array
            if (names.getNamesByLanguage("en") != null) {
                names.addName("en", prefName);
            } else {
                HashSet hs = new HashSet();
                hs.add(prefName);
                names.setName((String) "en", hs);
            }
            json.setGeometry(geometry);
            json.setProperties("http://sws.geonames.org/" + id, id, "geonames", names);
        }
        return json;

    }

    public static GGeoJSONFeatureCollection getPlacesByBBox(String upperleftLat, String upperleftLon, String upperrightLat, String upperrightLon,
                                                            String lowerrightLat, String lowerrightLon, String lowerleftLat, String lowerleftLon) throws IOException, ParseException {
        GGeoJSONFeatureCollection json = new GGeoJSONFeatureCollection();
        String url = "http://api.geonames.org/searchJSON";
        String urlParameters = "?username=chron.ontology";
        urlParameters += "&featureClass=A&featureClass=P";
        urlParameters += "&style=full";
        urlParameters += "&south=" + upperrightLat + "&north=" + lowerleftLat + "&west=" + upperrightLon + "&east=" + lowerleftLon;
        URL obj = new URL(url + urlParameters);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json;charset=UTF-8");
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
            JSONObject resultObject = (JSONObject) new JSONParser().parse(response.toString());
            JSONArray geonames = (JSONArray) resultObject.get("geonames");
            for (Object item : geonames) {
                JSONObject tmp = (JSONObject) item;
                // get names
                String prefName = (String) tmp.get("name");
                JSONArray alternateNames = (JSONArray) tmp.get("alternateNames");
                NamesJSONObject names = new NamesJSONObject();
                if (alternateNames != null) {
                    for (Object item2 : alternateNames) {
                        JSONObject tmp2 = (JSONObject) item2;
                        if (tmp2.get("lang") != null) {
                            HashSet hs = new HashSet();
                            hs.add(tmp2.get("name"));
                            if (!tmp2.get("lang").equals("link")) {
                                names.setName((String) tmp2.get("lang"), hs);
                            }
                        }
                    }
                }
                // add prefName to names array
                if (names.getNamesByLanguage("en") != null) {
                    names.addName("en", prefName);
                } else {
                    HashSet hs = new HashSet();
                    hs.add(prefName);
                    names.setName((String) "en", hs);
                }
                // get geometry
                Double lon = Double.parseDouble((String) tmp.get("lng"));
                Double lat = Double.parseDouble((String) tmp.get("lat"));
                GGeoJSONFeatureObject feature = new GGeoJSONFeatureObject();
                JSONArray point = new JSONArray();
                point.add(lon);
                point.add(lat);
                JSONObject geometry = new JSONObject();
                geometry.put("type", "Point");
                geometry.put("coordinates", point);
                feature.setGeometry(geometry);
                feature.setProperties("http://sws.geonames.org/" + Long.toString((long) tmp.get("geonameId")), Long.toString((long) tmp.get("geonameId")), "geonames", names);
                json.setFeature(feature);
                // get distance
                JSONArray bbox = Functions.bboxCenter(Double.parseDouble(lowerrightLon), Double.parseDouble(upperleftLon), Double.parseDouble(upperleftLat), Double.parseDouble(upperrightLat));
                Double bboxlon = (Double) bbox.get(1);
                Double bboxlat = (Double) bbox.get(0);
                feature.setPropertiesDistanceSimilarity(bboxlon, bboxlat, lat, lon);
                json.setMetadata("geonames", upperleftLat, upperleftLon, upperrightLat, upperrightLon, lowerrightLat, lowerrightLon, lowerleftLat, lowerleftLon, null);
            }
        }
        return json;
    }

}
