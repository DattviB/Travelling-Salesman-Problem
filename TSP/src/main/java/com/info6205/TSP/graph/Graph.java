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
//                System.out.println("Distance from " + i + " to " + j + " is " + adjacencyMatrix.get(i).get(j));
            }
        }
    }

    public double distance(City c1, City c2) {
        double lat1 = c1.getLattitude();
        double lon1 = c1.getLongitude();
        double lat2 = c2.getLattitude();
        double lon2 = c2.getLongitude();
        double earthRadius = 6371000; // meters
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = earthRadius * c;
        return distance;
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
    public void addEdge(int u, int v, double weight) {
        adjacencyMatrix.get(u).set(v, weight);
        adjacencyMatrix.get(v).set(u, weight);
    }

    public List<List<Double>> getAdjacencyMatrix(){
        return adjacencyMatrix;
    }
}
