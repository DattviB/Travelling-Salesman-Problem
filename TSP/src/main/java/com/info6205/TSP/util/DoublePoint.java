package com.info6205.TSP.util;

import java.awt.*;

public class DoublePoint extends Point {
    private double latitude;
    private double longitude;
    private String cityName;

    public DoublePoint(double latitude, double longitude,String cityName) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.cityName = cityName;

    }

    // Getter and Setter methods for latitude and longitude

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
