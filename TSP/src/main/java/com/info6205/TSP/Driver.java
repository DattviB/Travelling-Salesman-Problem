package com.info6205.TSP;

import com.info6205.TSP.graph.City;
import com.info6205.TSP.graph.Graph;

import com.info6205.TSP.optimization.tactical.ThreeOptOptimization;
import com.info6205.TSP.optimization.tactical.TwoOptOptimization;

import com.info6205.TSP.ui.ViewTSPAlgo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Driver {


    public static double calculateTourDistance(List<City> tour) {
        double totalDistance = 0.0;
        Graph graph = new Graph(tour);
        for (int i = 0; i < tour.size() - 1; i++) {
            City cityA = tour.get(i);
            City cityB = tour.get(i + 1);
            double distance = graph.distance(cityA, cityB);
            totalDistance += distance;
        }
        // Add distance from last city to first city to complete the tour
        City firstCity = tour.get(0);
        City lastCity = tour.get(tour.size() - 1);
        double distance = graph.distance(lastCity, firstCity);
        totalDistance += distance;
        return totalDistance;
    }


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
        List<City> hamiltonianTour = christofides.getTour();
//        for(City c: tour) System.out.println(c.getId());
        // Calculate the total tour distance in meters
        double totalHamiltonianDistance = christofides.calculateTourDistance(hamiltonianTour);

        // Print the tour and total distance
//        System.out.println("Tour: " + tour);
//        System.out.println("Total Distance: " + totalDistance + " meters");



        List<City> twoOptTour = TwoOptOptimization.twoOpt(hamiltonianTour);
        double totalTwoOptDistance = calculateTourDistance(twoOptTour);
        System.out.println("Total Two Opt Distance: " + totalTwoOptDistance + " meters");

//        ThreeOptOptimization threeOpt = new ThreeOptOptimization(tour);
//        threeOpt.threeOpt();

//        RandomSwapping randomSwap = new RandomSwapping(tour);
//        randomSwap.randomSwap();
//        ViewTSPAlgo view =  new ViewTSPAlgo(tour);
//        view.setVisible(true);

        ViewTSPAlgo view =  new ViewTSPAlgo();
        view.viewFinalTour(hamiltonianTour);




    }

}
