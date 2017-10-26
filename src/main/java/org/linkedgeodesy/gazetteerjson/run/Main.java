package org.linkedgeodesy.gazetteerjson.run;

import org.linkedgeodesy.gazetteerjson.config.POM_gazetteerjson;
import org.linkedgeodesy.gazetteerjson.log.Logging;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.linkedgeodesy.gazetteerjson.gazetteer.ChronOntology;
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
            o.add(IDAIGazetteer.getPlaceByBBox("50.082665", "8.161050", "49.903887", "8.161050", "49.903887", "8.371850", "50.082665", "8.371850").toJSONString() + "\r\n");
            o.add("# IDAIGazetteer.getPlaceByString" + "\r\n");
            o.add(IDAIGazetteer.getPlaceByString("Mainz").toJSONString() + "\r\n");
            o.add("# ChronOntology.getPlacesById" + "\r\n");
            o.add(ChronOntology.getPlacesById("Ehf6FBZm0Huc") + "\r\n");
            FileOutput.writeFile(o);
        } catch (Exception e) {
            System.out.println(Logging.getMessageJSON(e, "org.linkedgeodesy.gazetteerjson.run.Main").toJSONString());
            o.add(Logging.getMessageJSON(e, "org.linkedgeodesy.gazetteerjson.run.Main").toJSONString());
            FileOutput.writeFile(o);
        }
    }

}
