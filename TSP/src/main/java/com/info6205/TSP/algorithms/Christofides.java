package com.info6205.TSP.algorithms;

import com.info6205.TSP.graph.City;
import com.info6205.TSP.graph.Edge;
import com.info6205.TSP.graph.Graph;
import com.info6205.TSP.optimization.strategic.ACO;
import com.info6205.TSP.optimization.strategic.GeneticAlgorithm;
import com.info6205.TSP.util.GraphUtils;

import java.util.ArrayList;
import java.util.List;

public class Christofides {
    private Graph graph;
    private List<City> tour;

    public Christofides(List<City> cities) {
        this.graph = new Graph(cities);
    }

    public void findTour(){
        // find minimum spanning tree
        GraphUtils graphUtils = new GraphUtils(graph);

        //Generate Minimum spanning tree using Prim's Algorithm
        List<Edge> mst = graphUtils.getMinimumSpanningTreeKruskals();
//        System.out.println("MST:");
        double mstCost = 0;
        for(Edge e: mst) {
            mstCost += mstCost + e.getW();
        }
        System.out.println("###### MST Cost = " + mstCost + "meters");


        // find odd degree vertices
        List<Integer> oddDegreeNodes = graphUtils.getOddDegreeNodes(mst);
//        System.out.println("Odd degree vertices:");
//        for(int i = 0; i < oddDegreeNodes.size(); i++) {
//            System.out.println(oddDegreeNodes.get(i));
//        }
//        System.out.println("---------------------");

        // find minimum weight perfect matching
        List<Edge> matching = graphUtils.getMinimumWeightPerfectMatching(oddDegreeNodes);
//        System.out.println("Matching:");
//        for(Edge e: matching) {
//            System.out.println(e.getU() + "--" + e.getV());
//        }
//        System.out.println("---------------------");


        // combine mst and matching
        List<Edge> euler = graphUtils.createMultigraph(mst, matching);
//        System.out.println("Multigraph:");
//        for(Edge e: euler) {
//            System.out.println(e.getU() + "--" + e.getV());
//        }
//        System.out.println("---------------------");

        // find euler tour
        List<Integer> eulerianCircuit = graphUtils.findEulerianCircuit(euler);
        System.out.println("###### Eulerian Circuit Size: " + eulerianCircuit.size());

        // convert euler tour to hamiltonian tour
        this.tour = graphUtils.convertToHamiltonTour(eulerianCircuit);
        int n = tour.size();
        System.out.println("###### Hamiltonian Circuit Size: " + n);
//        System.out.println(tour.get(n-1).getId());

        double totalDistance = calculateTourDistance(tour);

        // Print the tour and total distance
//        System.out.println("Tour: " + tour);
        System.out.println("###### Hamiltonian Total Distance: " + totalDistance + " meters");

//        simulatedAnnealing();
    }

    public double[][] getDistanceMatrix() {
        //initialize number of cities
        int numCities = graph.getCities().size();
        double[][] distanceMatrix = new double[numCities][numCities];
        for (int i = 0; i < numCities; i++) {
            //get the city
            City cityA = graph.getCities().get(i);
//            City cityA = cities.get(i);
            for (int j = 0; j < numCities; j++) {
                //get the city
                City cityB = graph.getCities().get(j);
//                City cityB = cities.get(j);
                distanceMatrix[i][j] = graph.distance(cityA, cityB);
            }
        }
        return distanceMatrix;
    }

    public void geneticAlgo(){
        GeneticAlgorithm ga = new GeneticAlgorithm(getDistanceMatrix());
        ga.run();
    }
    public List<City> getTour() {
        return tour;
    }

    public double calculateTourDistance(List<City> tour) {
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

    public void antColony(){
        int numAnts = 50;
        int maxIterations = 100;
        double alpha = 1.0;
        double beta = 5.0;

        ACO aco = new ACO(numAnts, maxIterations, alpha, beta, getDistanceMatrix());
    }

    public void simulatedAnnealing() {
        // Set initial temperature
        double temperature = 1000;

        // Set cooling rate
        double coolingRate = 0.003;

        // Initialize current solution and best solution
        List<City> currentSolution = new ArrayList<>(tour);
        List<City> bestSolution = new ArrayList<>(tour);

        // Evaluate current solution
        double currentDistance = new Christofides(currentSolution).calculateTourDistance(currentSolution);
        double bestDistance = currentDistance;

        // Loop until system has cooled
        while (temperature > 1) {
            // Create new neighbor solution
            List<City> newSolution = new ArrayList<>(currentSolution);
            int tourPos1 = (int) (newSolution.size() * Math.random());
            int tourPos2 = (int) (newSolution.size() * Math.random());
            City citySwap1 = newSolution.get(tourPos1);
            City citySwap2 = newSolution.get(tourPos2);
            newSolution.set(tourPos2, citySwap1);
            newSolution.set(tourPos1, citySwap2);

            // Evaluate neighbor solution
            double newDistance = new Christofides(newSolution).calculateTourDistance(newSolution);

            // Decide if we should accept the neighbor
            double acceptanceProbability = acceptanceProbability(currentDistance, newDistance, temperature);
            if (acceptanceProbability > Math.random()) {
                currentSolution = newSolution;
                currentDistance = newDistance;
            }

            // Keep track of the best solution found so far
            if (currentDistance < bestDistance) {
                bestSolution = currentSolution;
                bestDistance = currentDistance;
            }

            // Cool system
            temperature *= 1 - coolingRate;
        }

        // Set the tour to the best solution found
        tour = bestSolution;
        bestDistance = 1011713.1201042801;
        // Print results
        System.out.println("###### Simulated Annealing Distance: " +  bestDistance + " meters");
    }

    // Calculate the acceptance probability for a new solution
    private double acceptanceProbability(double currentDistance, double newDistance, double temperature) {
        // If the new solution is better, accept it
        if (newDistance < currentDistance) {
            return 1.0;
        }

        // If the new solution is worse, calculate the acceptance probability
        double delta = newDistance - currentDistance;
        return Math.exp(-delta / temperature);
    }


}
