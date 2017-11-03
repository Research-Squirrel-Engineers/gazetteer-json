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
     * set multiple names for one language
     *
     * @param language
     * @param names
     */
    public void addNameArray(String language, JSONArray namesArray) {
        JSONArray tmp = (JSONArray) super.get(language);
        if (tmp == null) {
            tmp = namesArray;
        }
        tmp.add(namesArray);
        super.put(language, tmp);
    }

    /**
     * set an addition name to a language
     *
     * @param language
     * @param name
     */
    public void addSingleName(String language, String name) {
        if (language == null) {
            language = "unknown";
        }
        if (language.equals("")) {
            language = "unknown";
        }
        JSONArray namesArray = (JSONArray) super.get(language);
        if (namesArray == null) {
            JSONArray tmp = new JSONArray();
            tmp.add(name);
            namesArray = tmp;
        }
        if (!namesArray.contains(name)) {
            namesArray.add(name);
        }
        super.remove(language);
        super.put(language, namesArray);
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
        if (language == null) {
            language = "unknown";
        }
        if (language.equals("")) {
            language = "unknown";
        }
        JSONObject prefName = new JSONObject();
        prefName.put("name", name);
        prefName.put("lang", language);
        super.put("prefName", prefName);
        JSONArray namesArray = new JSONArray();
        namesArray.add(name);
        super.remove(language);
        super.put(language, namesArray);
    }

}
