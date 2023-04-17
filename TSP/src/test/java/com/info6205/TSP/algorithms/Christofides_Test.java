package com.info6205.TSP.algorithms;

import static org.junit.Assert.*;


import java.io.IOException;

import com.info6205.TSP.algorithms.Christofides;
import com.info6205.TSP.graph.City;
import com.info6205.TSP.graph.Graph;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.info6205.TSP.util.*;

import static org.junit.Assert.*;
import org.junit.Test;
import com.info6205.TSP.util.*;
import org.junit.jupiter.api.Assertions;

public class Christofides_Test {

    @Test
    public void testCalculateTourDistance() {
        List<City> cities = new ArrayList<>();
        cities.add(new City("A", 0, 0));
        cities.add(new City("B", 0, 1));
        cities.add(new City("C", 1, 1));
        cities.add(new City("D", 1, 0));

        Graph graph = new Graph(cities);
        double expectedDistance = 0;
        for (int i = 0; i < cities.size() - 1; i++) {
            City cityA = cities.get(i);
            City cityB = cities.get(i + 1);
            double distance = graph.distance(cityA, cityB);
            expectedDistance += distance;
        }
        expectedDistance += graph.distance(cities.get(0), cities.get(cities.size() - 1));


        Christofides christofides = new Christofides(cities);
        christofides.findTour();

        int[] tour = {0, 1, 2, 3, 0};
//        double expectedDistance = 4.0;
        double actualDistance = christofides.calculateTourDistance(christofides.getTour());
        assertEquals(expectedDistance, actualDistance, 0.00001);
    }


    @Test
    public void findTourTest() {
        // Create a list of cities
        List<City> cities = new ArrayList<>(Arrays.asList(
                new City("City0",  10.0, 20.0 ),
                new City("City1",  30.0, 40.0),
                new City("City2",  50.0, 60.0 ),
                new City("City3",  70.0, 80.0 ),
                new City("City4",  90.0, 100.0 )
        ));

        // Create a Christofides object
        Christofides christofides = new Christofides(cities);

        // Find the tour
        christofides.findTour();

        // Get the tour
        List<City> tour = christofides.getTour();

        // Test the size of the tour
        Assertions.assertEquals(6, tour.size());

        // Test the first city in the tour
//        Assertions.assertEquals("City0", tour.get(0).getId());

        // Test the last city in the tour
//        Assertions.assertEquals("City0", tour.get(tour.size() - 1).getId());

        // Test the distance of the tour
        Assertions.assertEquals(282.842712474619, christofides.calculateTourDistance(tour));
    }

}


