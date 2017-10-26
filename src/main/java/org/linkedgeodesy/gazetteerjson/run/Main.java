package org.linkedgeodesy.gazetteerjson.run;

import org.linkedgeodesy.gazetteerjson.config.POM_gazetteerjson;
import org.linkedgeodesy.gazetteerjson.log.Logging;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
            //o.add(POM_gazetteerjson.getInfo().toJSONString());
            o.add(IDAIGazetteer.getPlaceById("2181124").toJSONString() + "\r\n");
            String upperleft = "50.082665;8.161050";
            String lowerleft = "50.082665;8.371850";
            String upperright = "49.903887;8.161050";
            String lowerright = "49.903887;8.371850";
            o.add(IDAIGazetteer.getPlaceByBBox("50.082665", "8.161050", "49.903887", "8.161050", "49.903887", "8.371850", "50.082665", "8.371850").toJSONString() + "\r\n");
            FileOutput.writeFile(o);
        } catch (Exception e) {
            System.out.println(Logging.getMessageJSON(e, "org.linkedgeodesy.gazetteerjson.run.Main").toJSONString());
            o.add(Logging.getMessageJSON(e, "org.linkedgeodesy.gazetteerjson.run.Main").toJSONString());
            FileOutput.writeFile(o);
        }
    }

}
