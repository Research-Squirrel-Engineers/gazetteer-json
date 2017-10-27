package org.linkedgeodesy.gazetteerjson.gazetteer;

import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.linkedgeodesy.org.gazetteerjson.json.GGeoJSONSingleFeature;

/**
 * IDAIGazetteer Test
 *
 * @author Florian Thiery
 */
public class IDAIGazetteerTest {

    public IDAIGazetteerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getPlaceById method, of class IDAIGazetteer.
     */
    @Test
    public void testIDAIGazetteerGetPlaceByIdValidation() throws Exception {
        System.out.println("TEST: testIDAIGazetteerGetPlaceByIdValidation");
        GGeoJSONSingleFeature feature = IDAIGazetteer.getPlaceById("2181124");
        JSONObject propertiesObj = (JSONObject) feature.get("properties");
        String id = (String) propertiesObj.get("@id");
        String gazetteerid = (String) propertiesObj.get("gazetteerid");
        String gazetteertype = (String) propertiesObj.get("gazetteertype");
        JSONObject names = (JSONObject) propertiesObj.get("names");
        assertEquals(id,"https://gazetteer.dainst.org/place/2181124");
        assertEquals(gazetteerid,"2181124");
        assertEquals(gazetteertype,"dai");
        assertNotSame(names,new JSONObject());
    }

}
