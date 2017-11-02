package org.linkedgeodesy.gazetteerjson.run;

import org.linkedgeodesy.gazetteerjson.config.POM_gazetteerjson;
import org.linkedgeodesy.gazetteerjson.log.Logging;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.linkedgeodesy.gazetteerjson.gazetteer.ChronOntology;
import org.linkedgeodesy.gazetteerjson.gazetteer.GeoNames;
import org.linkedgeodesy.gazetteerjson.gazetteer.GettyTGN;
import org.linkedgeodesy.gazetteerjson.gazetteer.IDAIGazetteer;

/**
 * main class for running
 */
public class Main {

    /**
     * main method
     *
     * @param args
     * @throws IOException
     * @throws SQLException
     */
    public static void main(String[] args) throws IOException, SQLException {
        List<String> o = new ArrayList();
        try {
            o.add("# POM_gazetteerjson.getInfo" + "\r\n");
            o.add(POM_gazetteerjson.getInfo().toJSONString() + "\r\n");
            o.add("# IDAIGazetteer.getPlaceById" + "\r\n");
            o.add(IDAIGazetteer.getPlaceById("2181124").toJSONString() + "\r\n");
            o.add("# IDAIGazetteer.getPlaceByBBox" + "\r\n");
            o.add(IDAIGazetteer.getPlacesByBBox("50.082665", "8.161050", "49.903887", "8.161050", "49.903887", "8.371850", "50.082665", "8.371850").toJSONString() + "\r\n");
            o.add("# IDAIGazetteer.getPlaceByString" + "\r\n");
            o.add(IDAIGazetteer.getPlacesByString("Mainz").toJSONString() + "\r\n");
            o.add("# ChronOntology.getPlacesById" + "\r\n");
            o.add(ChronOntology.getPlacesById("EfFq8qCFODK8") + "\r\n");
            o.add("# GeoNames.getPlaceById" + "\r\n");
            o.add(GeoNames.getPlaceById("2874225").toJSONString() + "\r\n");
            o.add("# GeoNames.getPlacesByBBox" + "\r\n");
            o.add(GeoNames.getPlacesByBBox("50.082665", "8.161050", "49.903887", "8.161050", "49.903887", "8.371850", "50.082665", "8.371850").toJSONString() + "\r\n");
            o.add("# GeoNames.getPlacesByString" + "\r\n");
            o.add(GeoNames.getPlacesByString("Mainz").toJSONString() + "\r\n");
            o.add("# GettyTGN.getPlaceById" + "\r\n");
            o.add(GettyTGN.getPlaceById("7004449").toJSONString() + "\r\n"); // Mainz
            o.add(GettyTGN.getPlaceById("7008038").toJSONString() + "\r\n"); // Paris
            o.add("# GettyTGN.getPlacesByBBox" + "\r\n");
            o.add(GettyTGN.getPlacesByBBox("50.082665", "8.161050", "49.903887", "8.161050", "49.903887", "8.371850", "50.082665", "8.371850").toJSONString() + "\r\n");
            o.add(GettyTGN.getPlacesByBBox("48.866667", "2.333333", "48.866667", "2.333333", "48.866667", "2.333333", "48.866667", "2.333333").toJSONString() + "\r\n");
            FileOutput.writeFile(o);
        } catch (Exception e) {
            System.out.println(Logging.getMessageJSON(e, "org.linkedgeodesy.gazetteerjson.run.Main").toJSONString());
            o.add(Logging.getMessageJSON(e, "org.linkedgeodesy.gazetteerjson.run.Main").toJSONString());
            FileOutput.writeFile(o);
        }
    }

}
