# gazetteer-json

A Library for Gazetteer JSON access.

[![build](https://api.travis-ci.org/linkedgeodesy/gazetteer-json.svg?branch=master)](https://travis-ci.org/linkedgeodesy/gazetteer-json) [![version](https://img.shields.io/badge/version-1.0--SNAPSHOT-green.svg)](#)  [![java](https://img.shields.io/badge/jdk-1.8-red.svg)](#)  [![maven](https://img.shields.io/badge/maven-3.5.0-orange.svg)](#) [![output](https://img.shields.io/badge/output-jar-red.svg)](#)  [![docs](https://img.shields.io/badge/apidoc-v0.1-blue.svg)](https://linkedgeodesy.github.io/gazetteer-json/)  [![license](https://img.shields.io/badge/License-MIT-yellow.svg)](https://github.com/linkedgeodesy/gazetteer-json/blob/master/LICENSE)

## Prerequisites

The code is developed using and tested with:

* maven 3.5.0
* Netbeans 8.2
* Apache Tomcat 8.0.27.0
* JDK 1.8

## Maven

The `gazetteer-json` library is build using `maven` as JAR-file.

For details have a look at [pom.xml](https://github.com/linkedgeodesy/gazetteer-json/blob/master/gazetteer-json/pom.xml).

[Download](http://maven.apache.org/download.cgi) and  [install](https://www.mkyong.com/maven/how-to-install-maven-in-windows/) `maven` and [run](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html) it.

## Setup

Download and install `maven`.

Run git clone `https://github.com/linkedgeodesy/gazetteer-json.git` to create a local copy of this repository.

Run `mvn install` to install all required dependencies.

Run `mvn clean install site` for cleaning, building, testing and generating the documentation files.

Run the jar-file using `mvn exec:java`.

In order to run the Main Class in Netbeans use `Run / Debug`.

Running `mvn test` will run the unit tests with `JUnit`.

## Documentation

Look at [GitHub Pages](https://linkedgeodesy.github.io/gazetteer-json/) for the latest developer documentation like `maven` and `javadoc`.

## Developer Hints

Look at [Gist](https://gist.github.com/florianthiery/0f8c0c015555939c96eb13428bbf1cd4) hints for `Configurations for JAVA projects using Maven`.

## Dependency Information

*latest stabile version: v0.1*

**Apache Maven** *via jitpack.io*

```xml
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>

<dependency>
  <groupId>com.github.linkedgeodesy</groupId>
  <artifactId>gazetteer-json</artifactId>
  <version>${version}</version>
</dependency>
```

**gradle** *via jitpack.io*

```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
    compile 'com.github.linkedgeodesy:gazetteer-json:${version}'
}
```

**sbt** *via jitpack.io*

```
resolvers += "jitpack" at "https://jitpack.io"
libraryDependencies += "com.github.linkedgeodesy" % "gazetteer-json" % "${version}"
```

**leiningen** *via jitpack.io*

```
:repositories [["jitpack" "https://jitpack.io"]]   
:dependencies [[com.github.linkedgeodesy/gazetteer-json "${version}"]]
```

## Repo Developers

Florian Thiery M.Sc. (i3mainz)

## javadoc

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
