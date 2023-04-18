package com.info6205.TSP;

import com.info6205.TSP.algorithms.Christofides;
import com.info6205.TSP.graph.City;
import com.info6205.TSP.graph.Graph;
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
        while (scanner.hasNextLine()) {
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
            cities = readCitiesFromFile("TSP//src//main//java//com//info6205//TSP//data//crimeSample.csv");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        //480
        Christofides christofides = new Christofides(cities);
        christofides.findTour();
        List<City> tour = christofides.getTour();
//        for(City c: tour) System.out.println(c.getId());
        // Calculate the total tour distance in meters
        double totalDistance = christofides.calculateTourDistance(tour);

        // Print the tour and total distance
//        System.out.println("Tour: " + tour);
        System.out.println("Total Distance: " + totalDistance + " meters");

        ViewTSPAlgo view =  new ViewTSPAlgo();
        view.viewFinalTour(tour);


    }

}
