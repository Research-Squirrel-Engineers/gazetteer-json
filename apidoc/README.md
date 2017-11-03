# *Gazetteer JSON - apidoc*

## ChronOntology GeoJSON

GeoJSON einer ChronOntology Ressource.

Die resultierende JSON-Struktur ist [hier](https://github.com/i3mainz/chronontology-spatialapi/blob/master/jsonobjects.md#chronontology-geojson) einsehbar.

```java
package run;

import org.linkedgeodesy.gazetteerjson.log.Logging;
import org.linkedgeodesy.gazetteerjson.gazetteer.ChronOntology;
import org.linkedgeodesy.gazetteerjson.gazetteer.IDAIGazetteer;
import java.io.IOException;
import java.sql.SQLException;

public class Main {
  public static void main(String[] args) throws IOException, SQLException {
    try {
      ChronOntology.getPlacesById("3vsiBEzefcc5");
    } catch (Exception e) {
      System.out.println(Logging.getMessageJSON(e, "org.linkedgeodesy.gazetteerjson.run.Main").toJSONString());
    }
  }
}
```

## Gazetteer GeoJSON

### Gazetteer Ressource

GeoJSON einer Gazetteer Ressource (iDAI.gazetteer, GeoNames).

Die resultierende JSON-Struktur ist [hier](https://github.com/i3mainz/chronontology-spatialapi/blob/master/jsonobjects.md#gazetteer-ressource) einsehbar.

```java
package run;

import org.linkedgeodesy.gazetteerjson.log.Logging;
import org.linkedgeodesy.gazetteerjson.gazetteer.IDAIGazetteer;
import java.io.IOException;
import java.sql.SQLException;

public class Main {
  public static void main(String[] args) throws IOException, SQLException {
    try {
      IDAIGazetteer.getPlaceById("2181124");
      GeoNames.getPlaceById("2874225");
      GettyTGN.getPlaceById("7004449");
      Pleiades.getPlaceById("109169");
    } catch (Exception e) {
      System.out.println(Logging.getMessageJSON(e, "org.linkedgeodesy.gazetteerjson.run.Main").toJSONString());
    }
  }
}
```

### Gazetteer Suche - Bounding Box

GeoJSON einer Gazetteer Suche im iDAI.gazetteer, GeoNames.

Die resultierende JSON-Struktur ist [hier](https://github.com/i3mainz/chronontology-spatialapi/blob/master/jsonobjects.md#gazetteer-suche) einsehbar.

```java
package run;

import org.linkedgeodesy.gazetteerjson.log.Logging;
import org.linkedgeodesy.gazetteerjson.gazetteer.IDAIGazetteer;
import java.io.IOException;
import java.sql.SQLException;

public class Main {
  public static void main(String[] args) throws IOException, SQLException {
    try {     
      IDAIGazetteer.getPlacesByBBox("50.082665", "8.161050",
                                    "49.903887", "8.161050",
                                    "49.903887", "8.371850",
                                    "50.082665", "8.371850");
      GeoNames.getPlacesByBBox("50.082665", "8.161050",
                               "49.903887", "8.161050",
                               "49.903887", "8.371850",
                               "50.082665", "8.371850");
      GettyTGN.getPlacesByBBox("50.082665", "8.161050",
                               "49.903887", "8.161050",
                               "49.903887", "8.371850",
                               "50.082665", "8.371850");
      Pleiades.getPlacesByBBox("50.082665", "8.161050",
                               "49.903887", "8.161050",
                               "49.903887", "8.371850",
                               "50.082665", "8.371850");
    } catch (Exception e) {
      System.out.println(Logging.getMessageJSON(e, "org.linkedgeodesy.gazetteerjson.run.Main").toJSONString());
    }
  }
}
```

### Gazetteer Suche - Zeichenkette

GeoJSON einer Gazetteer Suche im iDAI.gazetteer, GeoNames.

Die resultierende JSON-Struktur ist [hier](https://github.com/i3mainz/chronontology-spatialapi/blob/master/jsonobjects.md#gazetteer-suche) einsehbar.

```java
package run;

import org.linkedgeodesy.gazetteerjson.log.Logging;
import org.linkedgeodesy.gazetteerjson.gazetteer.IDAIGazetteer;
import java.io.IOException;
import java.sql.SQLException;

public class Main {
  public static void main(String[] args) throws IOException, SQLException {
    try {
      IDAIGazetteer.getPlacesByString("Mainz");
      GeoNames.getPlacesByString("Mainz");
      GettyTGN.getPlacesByString("Mainz");
      Pleiades.getPlacesByString("Mainz");
    } catch (Exception e) {
      System.out.println(Logging.getMessageJSON(e, "org.linkedgeodesy.gazetteerjson.run.Main").toJSONString());
    }
  }
}
```
