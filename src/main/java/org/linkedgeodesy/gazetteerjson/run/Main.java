package org.linkedgeodesy.gazetteerjson.run;

import org.linkedgeodesy.gazetteerjson.config.POM_gazetteerjson;
import org.linkedgeodesy.gazetteerjson.log.Logging;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
            // get POM Info
            o.add(POM_gazetteerjson.getInfo().toJSONString());
            FileOutput.writeFile(o);
        } catch (Exception e) {
            System.out.println(Logging.getMessageJSON(e, "org.linkedgeodesy.gazetteerjson.run.Main").toJSONString());
            o.add(Logging.getMessageJSON(e, "org.linkedgeodesy.gazetteerjson.run.Main").toJSONString());
            FileOutput.writeFile(o);
        }
    }

}
