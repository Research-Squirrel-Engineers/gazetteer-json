package org.linkedgeodesy.org.gazetteerjson.json;

import java.util.HashSet;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * JSONObject to store names information
 *
 * @author Florian Thiery
 */
public class NamesJSONObject extends JSONObject {

    public NamesJSONObject() {
        super();
    }

    /**
     * set multiple names for one language
     *
     * @param language
     * @param names
     */
    public void setName(String language, HashSet names) {
        JSONArray namesArray = new JSONArray();
        for (Object item : names) {
            String tmp = (String) item;
            namesArray.add(tmp);
        }
        super.put(language, namesArray);
    }

    /**
     * set an addition name to a language
     *
     * @param language
     * @param name
     */
    public void addName(String language, String name) {
        JSONArray tmp = (JSONArray) super.get(language);
        tmp.add(name);
    }

    /**
     * get array of names by language
     *
     * @param language
     * @return
     */
    public JSONArray getNamesByLanguage(String language) {
        return (JSONArray) super.get(language);
    }
    
    public void addPrefName(String language, String name) {
        JSONObject prefName = new JSONObject();
        prefName.put("name", name);
        prefName.put("lang", language);
        super.put("prefName", prefName);
    }

}
