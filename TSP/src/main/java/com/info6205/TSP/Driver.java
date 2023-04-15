package com.info6205.TSP;

import com.info6205.TSP.algorithms.Christofides;
import com.info6205.TSP.graph.City;

import java.util.ArrayList;
import java.util.List;

public class Driver {

    public static void main(String[] args) {

        List<City> cities = new ArrayList<>();
        cities.add(new City("New York", 40.712776, -74.005974));

        cities.add(new City("Chicago", 41.878114, -87.629798));
        cities.add(new City("Houston", 29.760427, -95.369803));
        cities.add(new City("Phoenix", 33.448376, -112.074036));
        cities.add(new City("Philadelphia", 39.952584, -75.165222));
        cities.add(new City("San Antonio", 29.424122, -98.493628));
        cities.add(new City("Los Angeles", 34.052235, -118.243683));
        cities.add(new City("San Diego", 32.715736, -117.161087));

        cities.add(new City("Dallas", 32.776665, -96.796989));
        cities.add(new City("San Jose", 37.338207, -121.886330));
        cities.add(new City("Austin", 30.267153, -97.743057));
        cities.add(new City("Jacksonville", 30.332184, -81.655647));
        cities.add(new City("Fort Worth", 32.755489, -97.330765));
        cities.add(new City("Columbus", 39.961178, -82.998795));
        cities.add(new City("San Francisco", 37.774929, -122.419418));

        Christofides christofides = new Christofides(cities);
        christofides.findTour();

        System.out.println("Graph created for cities");



    }

}
