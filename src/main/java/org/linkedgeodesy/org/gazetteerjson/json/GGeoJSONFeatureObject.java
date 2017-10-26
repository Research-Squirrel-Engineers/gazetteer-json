package org.linkedgeodesy.org.gazetteerjson.json;

import org.json.simple.JSONObject;
import org.linkedgeodesy.gazetteerjson.utils.Functions;

/**
 * JSONObject to store single feature information for CGeoJON objects
 *
 * @author Florian Thiery
 */
public class GGeoJSONFeatureObject extends GGeoJSONSingleFeature {

    public GGeoJSONFeatureObject() {
        super();
    }

    /**
     * set GeoJSON single feature properties
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
     * set GeoJSON single feature properties
     *
     * @param url
     * @param gazetteerid
     * @param gazetteertype
     * @param names
     */
    public void setPropertiesStringSimilarity(Double levenshtein, Double normalizedlevenshtein, Double dameraulevenshtein, Double jarowinkler, String searchString, String gazetteerString) {
        JSONObject properties = (JSONObject) super.get("properties");
        JSONObject similarity = new JSONObject();
        similarity.put("searchString", searchString);
        similarity.put("gazetteerString", gazetteerString);
        similarity.put("levenshtein", levenshtein);
        similarity.put("normalizedlevenshtein", normalizedlevenshtein);
        similarity.put("dameraulevenshtein", dameraulevenshtein);
        similarity.put("jarowinkler", jarowinkler);
        properties.put("similarity", similarity);
        super.put("properties", properties);
    }

}
