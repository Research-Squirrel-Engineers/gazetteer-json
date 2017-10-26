# gazetteer-json

A Library for Gazetteer JSON access.

## ChronOntology GeoJSON

GeoJSON einer ChronOntology Ressource: [Info](https://github.com/i3mainz/chronontology-spatialapi/blob/master/jsonobjects.md#chronontology-geojson).

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

GeoJSON einer Gazetteer Ressource: [Info](https://github.com/i3mainz/chronontology-spatialapi/blob/master/jsonobjects.md#gazetteer-ressource).

```java
package run;

import org.linkedgeodesy.gazetteerjson.log.Logging;
import org.linkedgeodesy.gazetteerjson.gazetteer.IDAIGazetteer;
import java.io.IOException;
import java.sql.SQLException;

public class Main {
  public static void main(String[] args) throws IOException, SQLException {
    try {
      IDAIGazetteer.getPlaceById("2181124")            
    } catch (Exception e) {
      System.out.println(Logging.getMessageJSON(e, "org.linkedgeodesy.gazetteerjson.run.Main").toJSONString());
    }
  }
}
```

### Gazetteer Suche - Bounding Box

GeoJSON einer Gazetteer Suche: [Info](https://github.com/i3mainz/chronontology-spatialapi/blob/master/jsonobjects.md#gazetteer-suche).

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
    } catch (Exception e) {
      System.out.println(Logging.getMessageJSON(e, "org.linkedgeodesy.gazetteerjson.run.Main").toJSONString());
    }
  }
}
```

### Gazetteer Suche - Zeichenkette

GeoJSON einer Gazetteer Suche: [Info](https://github.com/i3mainz/chronontology-spatialapi/blob/master/jsonobjects.md#gazetteer-suche).

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
    } catch (Exception e) {
      System.out.println(Logging.getMessageJSON(e, "org.linkedgeodesy.gazetteerjson.run.Main").toJSONString());
    }
  }
}
```
