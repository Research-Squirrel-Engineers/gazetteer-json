package org.linkedgeodesy.gazetteerjson.gazetteer;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import org.jdom.Element;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.linkedgeodesy.org.gazetteerjson.json.GGeoJSONSingleFeature;
import org.linkedgeodesy.org.gazetteerjson.json.NamesJSONObject;

/**
 * functions to query Getty Thesaurus of Geographic Names
 *
 * @author Florian Thiery
 */
public class GettyTGN {

    public static GGeoJSONSingleFeature getPlaceById(String id) throws IOException, ParseException {
        GGeoJSONSingleFeature json = new GGeoJSONSingleFeature();
        String url = "http://vocab.getty.edu/sparql.json";
        String queryString = "prefix ontogeo: <http://www.ontotext.com/owlim/geo#> "
                + "PREFIX geo: <http://www.w3.org/2003/01/geo/wgs84_pos#> "
                + "PREFIX skos: <http://www.w3.org/2004/02/skos/core#>"
                + "PREFIX tgn: <http://vocab.getty.edu/tgn/> "
                + "PREFIX foaf: <http://xmlns.com/foaf/0.1/> "
                + "PREFIX gvp: <http://vocab.getty.edu/ontology#> "
                + "PREFIX skosxl: <http://www.w3.org/2008/05/skos-xl#> "
                + "PREFIX dc: <http://purl.org/dc/elements/1.1/> "
                + "SELECT DISTINCT ?place ?prefLabel ?altLabel ?lat ?long WHERE { "
                + "?place skos:inScheme tgn: . "
                + "?place dc:identifier \"" + id + "\" . "
                + "?place xl:prefLabel [skosxl:literalForm ?prefLabel] . "
                + "OPTIONAL { ?place xl:altLabel [skosxl:literalForm ?altLabel] }. "
                + "?place foaf:focus ?p. "
                + "?p geo:lat ?lat . "
                + "?p geo:long ?long . "
                + "}";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        String urlParameters = "query=" + queryString;
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();
        int responseCode = con.getResponseCode();
        System.out.println("GettyTGN.getPlaceById() - " + responseCode + " - " + url);
        if (responseCode < 400) {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(response.toString());
            JSONObject results = (JSONObject) jsonObject.get("results");
            JSONArray bindings = (JSONArray) results.get("bindings");
            JSONObject binding0 = (JSONObject) bindings.get(0);
            // get URI
            JSONObject place = (JSONObject) binding0.get("place");
            String placeURI = (String) place.get("value");
            // add and get geometry
            JSONObject lat = (JSONObject) binding0.get("lat");
            JSONObject lng = (JSONObject) binding0.get("long");
            JSONObject geometry = new JSONObject();
            JSONArray point = new JSONArray();
            point.add(Double.parseDouble((String) lng.get("value")));
            point.add(Double.parseDouble((String) lat.get("value")));
            geometry.put("type", "Point");
            geometry.put("coordinates", point);
            // add and get names
            NamesJSONObject names = new NamesJSONObject();
            // get prefLabels
            for (Object item : bindings) {
                JSONObject binding = (JSONObject) item;
                JSONObject prefLabelObj = (JSONObject) binding.get("prefLabel");
                String prefLabelString = (String) prefLabelObj.get("value");
                String prefLabelLang = (String) prefLabelObj.get("xml:lang");
                if (prefLabelLang == null) {
                    prefLabelLang = "en";
                }
                HashSet hs = new HashSet();
                hs.add(prefLabelString);
                names.setName(prefLabelLang, hs);
            }
            // get altLabels
            for (Object item : bindings) {
                JSONObject binding = (JSONObject) item;
                JSONObject altLabelObj = (JSONObject) binding.get("altLabel");
                String altLabelString = (String) altLabelObj.get("value");
                String altLabelLang = (String) altLabelObj.get("xml:lang");
                // add altLabel to names array
                if (altLabelLang == null) {
                    altLabelLang = "en";
                }
                if (names.getNamesByLanguage(altLabelLang) != null) {
                    names.addName(altLabelLang, altLabelString);
                } else {
                    HashSet hs = new HashSet();
                    hs.add(altLabelString);
                    names.setName(altLabelLang, hs);
                }
            }
            json.setGeometry((geometry));
            json.setProperties(placeURI, id, "getty", names);
        }
        return json;
    }

}
