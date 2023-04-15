package com.info6205.TSP.graph;

import java.awt.*;

public class City extends Point {
    public String id;
    public double lattitude;
    public double longitude;

    public City(String id, double lat, double lon) {
        this.id = id;
        this.lattitude = lat;
        this.longitude = lon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getLattitude() {
        return lattitude;
    }

    public void setLattitude(double lattitude) {
        this.lattitude = lattitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
