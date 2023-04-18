package com.info6205.TSP.optimization.tactical;

import com.info6205.TSP.Christofides;
import com.info6205.TSP.graph.City;
import com.info6205.TSP.graph.Graph;

import java.util.List;
import java.util.Random;

public class RandomSwapping {

    List<City> tour;
    Graph graph;

    public RandomSwapping(List<City> tour){

        this.tour = tour;
        this.graph = new Graph(tour);
    }

    public void randomSwap() {
        Random rand = new Random();
        int count = 0;
        int maxIterations = 10000;
        while (count < maxIterations) {
            int i = rand.nextInt(tour.size() - 1);
            int j = rand.nextInt(tour.size() - 1);
            if (i == j) continue;
            City temp = tour.get(i);
            tour.set(i, tour.get(j));
            tour.set(j, temp);
            count++;
        }
        System.out.println("Random Swap Distance: " + new Christofides(tour).calculateTourDistance(tour));
        System.out.println("Random Swap iterations: " + count);
    }

}
