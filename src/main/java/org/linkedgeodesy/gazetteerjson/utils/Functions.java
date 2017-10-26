package org.linkedgeodesy.gazetteerjson.utils;

import org.json.simple.JSONArray;

public class Functions {

    private static final double rho = Math.PI / 180;
    private static final double r_earth = 6378.388; // Hayford-Ellipsoid, 1910/24, Äquatorradius

    public static double round(final double value, final int frac) {
        return Math.round(Math.pow(10.0, frac) * value) / Math.pow(10.0, frac);
    }

    public static double getKilometers(double lat1, double lon1, double lat2, double lon2) {
        lat1 = lat1 * rho;
        lon1 = lon1 * rho;
        lat2 = lat2 * rho;
        lon2 = lon2 * rho;
        // http://www.kompf.de/gps/distcalc.html
        return r_earth * Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon2 - lon1));
    }

    public static JSONArray bboxCenter(Double lowerrightLon, Double upperleftLon, Double upperleftLat, Double upperrightLat) {
        JSONArray result = new JSONArray();
        Double lonDiff = upperleftLon + (lowerrightLon - upperleftLon) / 2;
        Double latDiff = upperrightLat + (upperleftLat - upperrightLat) / 2;
        result.add(lonDiff);
        result.add(latDiff);
        return result;
    }

}