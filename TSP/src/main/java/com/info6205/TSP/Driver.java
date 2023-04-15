package com.info6205.TSP;

import com.info6205.TSP.algorithms.Christofides;
import com.info6205.TSP.graph.City;
import com.info6205.TSP.ui.ViewTSPAlgo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Driver {


    public static List<City> readCitiesFromFile(String filename) throws FileNotFoundException {
        List<City> cities = new ArrayList<>();

        File file = new File(filename);
        Scanner scanner = new Scanner(file);

        // Skip the first line since it contains the headers
        scanner.nextLine();
        int count = 0;
        while (scanner.hasNextLine() && count < 5) {
            String line = scanner.nextLine();
            String[] fields = line.split(",");
            String name = fields[0];
            double latitude = Double.parseDouble(fields[1]);
            double longitude = Double.parseDouble(fields[2]);
            City city = new City(name, latitude, longitude);
            cities.add(city);
            count += 1;
        }

        scanner.close();

        return cities;
    }

    public static void main(String[] args) {
        List<City> cities = null;
        try {
            cities = readCitiesFromFile("TSP//src//main//java//com//info6205//TSP//data//cities_sample.csv");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        /*
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
        */

        Christofides christofides = new Christofides(cities);
        christofides.findTour();
        List<City> tour = christofides.getTour();
        for (City city : tour) {
            System.out.println(city.id + " " + city.longitude + " " + city.longitude);
        }

        ViewTSPAlgo view =  new ViewTSPAlgo(tour);
        view.setVisible(true);

    }

}
