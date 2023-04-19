package com.info6205.TSP.optimization.tactical;

import com.info6205.TSP.graph.City;
import com.info6205.TSP.optimization.tactical.TwoOptOptimization;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TwoOptOptimizationTest {

    @Test
    void testTwoOpt() {
        List<City> cities = new ArrayList<>();
        cities.add(new City("A", 0, 0));
        cities.add(new City("B", 1, 0));
        cities.add(new City("C", 1, 1));
        cities.add(new City("D", 0, 1));

        List<City> newCities = TwoOptOptimization.twoOpt(cities);

        assertNotNull(newCities);
        assertEquals(cities.size(), newCities.size());

        // Check that the new tour is shorter than the original tour
        double originalDistance = calculateDistance(cities);
        double newDistance = calculateDistance(newCities);
        assertTrue(newDistance <= originalDistance);
    }

    private double calculateDistance(List<City> cities) {
        double distance = 0;
        for (int i = 0; i < cities.size(); i++) {
            City c1 = cities.get(i);
            City c2 = cities.get((i + 1) % cities.size());
            distance += TwoOptOptimization.distance(c1, c2);
        }
        return distance;
    }
}
