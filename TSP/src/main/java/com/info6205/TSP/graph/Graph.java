package com.info6205.TSP.graph;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private List<City> cities;
    private List<List<Double>> adjacencyMatrix;

    public Graph(List<City> cities) {
        this.cities = cities;
        adjacencyMatrix = new ArrayList<>();
        for (int i = 0; i < cities.size(); i++) {
            adjacencyMatrix.add(new ArrayList<>());
            for (int j = 0; j < cities.size(); j++) {
                adjacencyMatrix.get(i).add(distance(cities.get(i), cities.get(j)));
                System.out.println("Distance from " + i + " to " + j + " is " + adjacencyMatrix.get(i).get(j));
            }
        }
    }

    private double distance(City c1, City c2) {
        double lat1 = Math.toRadians(c1.lat);
        double lat2 = Math.toRadians(c2.lat);
        double lon1 = Math.toRadians(c1.lon);
        double lon2 = Math.toRadians(c2.lon);
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double radius = 6371000; // radius of earth in kilometers
        return radius * c;
    }

    public double getDistance(int i, int j) {
        return adjacencyMatrix.get(i).get(j);
    }

    public int size() {
        return cities.size();
    }

    public List<City> getCities() {
        return cities;
    }
}
