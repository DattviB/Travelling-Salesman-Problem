package com.info6205.TSP.algorithms;

import static org.junit.Assert.*;


import com.info6205.TSP.graph.City;
import com.info6205.TSP.graph.Graph;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;

public class ChristofidesTest {
    List<City> cities;
    private Christofides christofides;

    @Test
    public void testCalculateTourDistance() {
        cities = new ArrayList<>();
        cities.add(new City("A", 0, 0));
        cities.add(new City("B", 0, 1));
        cities.add(new City("C", 1, 1));
        cities.add(new City("D", 1, 0));

       double expectedDistance = getExpectedDistance(cities);


        Christofides christofides = new Christofides(cities);
        christofides.findTour();

        int[] tour = {0, 1, 2, 3, 0};
//        double expectedDistance = 4.0;
        double actualDistance = christofides.calculateTourDistance(christofides.getTour());
        assertEquals(expectedDistance, actualDistance, 0.00001);
    }

    @Test
    public void testCalculateTourDistance1() {
        // Arrange

        cities = new ArrayList<>();
        cities.add(new City("City0", 0.0, 0.0)); // City with ID "City0" at (0,0) coordinates
        cities.add(new City("City1", 1.0, 2.0)); // City with ID "City1" at (1,2) coordinates
        cities.add(new City("City2", 2.0, 3.0)); // City with ID "City2" at (2,3) coordinates

        Christofides christofides = new Christofides(cities);
        List<City> tour1 = new ArrayList<>();
        tour1.add(cities.get(0));
        tour1.add(cities.get(1));
        tour1.add(cities.get(2));

        List<City> tour2 = new ArrayList<>();
        tour2.add(cities.get(2));
        tour2.add(cities.get(1));
        tour2.add(cities.get(0));

        // Act
        double distance1 = christofides.calculateTourDistance(tour1);
        double distance2 = christofides.calculateTourDistance(tour2);
        double expectedDistance = getExpectedDistance(cities);
        // Assert
        Assertions.assertEquals(expectedDistance, distance1, 0.00001); // Assert distance with a delta of 0.00001
        Assertions.assertEquals(expectedDistance, distance2, 0.00001); // Assert distance with a delta of 0.00001
    }
    @Test
    public void findTour() {
        // Create a list of cities
        cities = new ArrayList<>(Arrays.asList(
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
        double expectedDistance = getExpectedDistance(cities);
        // Test the distance of the tour
        Assertions.assertEquals(expectedDistance, christofides.calculateTourDistance(tour));
    }

    @Test
    public void findTour1() {
        // Arrange
        cities = new ArrayList<>();
        cities.add(new City("City0", 0.0, 0.0)); // City with ID "City0" at (0,0) coordinates
        cities.add(new City("City1", 1.0, 2.0)); // City with ID "City1" at (1,2) coordinates
        cities.add(new City("City2", 2.0, 3.0)); // City with ID "City2" at (2,3) coordinates

        Christofides christofides = new Christofides(cities);

        // Act
        christofides.findTour();

        // Assert
        List<City> tour = christofides.getTour();
        Assertions.assertNotNull(tour); // Assert that the returned tour is not null
        Assertions.assertEquals(cities.size() + 1, tour.size()); // Assert that the tour has the correct number of cities + 1 (to account for returning to the starting city)
    }

    private double getExpectedDistance(List<City> cities){
        double expectedDistance = 0;
        Graph graph = new Graph(cities);
        for (int i = 0; i < cities.size() - 1; i++) {
            City cityA = cities.get(i);
            City cityB = cities.get(i + 1);
            double distance = graph.distance(cityA, cityB);
            expectedDistance += distance;
        }
        expectedDistance += graph.distance(cities.get(0), cities.get(cities.size() - 1));
        return expectedDistance;
    }

}


