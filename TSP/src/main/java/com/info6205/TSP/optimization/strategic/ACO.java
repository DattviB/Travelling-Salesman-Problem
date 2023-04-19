package com.info6205.TSP.optimization.strategic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ACO {
    private int numAnts;
    private int numIterations;
    private double alpha;
    private double beta;
    private double evaporationRate;
    private double initialPheromoneLevel;
    private double[][] distanceMatrix;
    private double[][] pheromoneMatrix;

    private double bestTourDistance;

    ArrayList<Double> bestTour;

    public ACO(int numAnts, int numIterations, double alpha, double beta, double[][] distanceMatrix) {
        this.numAnts = numAnts;
        this.numIterations = numIterations;
        this.alpha = alpha;
        this.beta = beta;
//        this.evaporationRate = evaporationRate;
//        this.initialPheromoneLevel = initialPheromoneLevel;
        this.distanceMatrix = distanceMatrix;
        bestTourDistance = Double.POSITIVE_INFINITY;

        // Initialize pheromone matrix with initial pheromone levels
        int numCities = distanceMatrix.length;
        this.pheromoneMatrix = new double[numCities][numCities];
        for (int i = 0; i < numCities; i++) {
            for (int j = 0; j < numCities; j++) {
                this.pheromoneMatrix[i][j] = initialPheromoneLevel;
            }
        }
        runACO();
    }

    public void runACO() {
        for(int iteration = 0;iteration<numIterations;iteration++){
            // Construct solutions for each ant
            ArrayList<int[]> listOfTours = new ArrayList<>();
            ArrayList<Double> listOfTourDistances = new ArrayList<>();
            for (int ant = 0; ant < numAnts; ant++) {
                int[] solution = constructAntSolution(distanceMatrix, pheromoneMatrix, alpha, beta);
                listOfTours.add(solution);
                double solutionLength = calculateSolutionLength(solution, distanceMatrix);
                listOfTourDistances.add(solutionLength);
                updatePheromoneTrail(solution, solutionLength);
                double tourDistance = calculateSolutionLength(solution, distanceMatrix);
                if(tourDistance < bestTourDistance) {
                    bestTourDistance = tourDistance;
                    //System.out.println("Best tour distance: " + bestTourDistance);
                }
                updatePheromoneEvaporation();
            }
        }
//        System.out.println("Best tour distance: " + bestTourDistance);
        // Run ACO for numIterations iterations
//        for (int iteration = 0; iteration < numIterations; iteration++) {
//            // Construct solutions for each ant
//            for (int ant = 0; ant < numAnts; ant++) {
//                int[] solution = constructAntSolution(distanceMatrix, pheromoneMatrix, alpha, beta);
////                double solutionLength = evaluateSolution(solution, distanceMatrix);
//                updatePheromoneTrail(solution, solution.length);
//                double tourDistance = calculateSolutionLength(solution, distanceMatrix);
//                if(tourDistance < bestTourDistance) {
//                    bestTourDistance = tourDistance;
//                }
//            }
//        }
    }

    //update pheromone evaporation
    private void updatePheromoneEvaporation() {
        int numCities = distanceMatrix.length;
        for (int i = 0; i < numCities; i++) {
            for (int j = 0; j < numCities; j++) {
                pheromoneMatrix[i][j] *= evaporationRate;
            }
        }
    }

    //evaporate pheromone trails
    private void evaporatePheromoneTrails(double[][] pheromoneMatrix, double evaporationRate) {
        int numCities = pheromoneMatrix.length;
        for (int i = 0; i < numCities; i++) {
            for (int j = 0; j < numCities; j++) {
                pheromoneMatrix[i][j] *= evaporationRate;
            }
        }
    }

    // Other methods for constructing ant solutions, evaluating solutions, and updating pheromone trails...
    //construct ant solution
    private int[] constructAntSolution(double[][] distanceMatrix, double[][] pheromoneMatrix, double alpha, double beta) {
        int numCities = distanceMatrix.length;
        int[] solution = new int[numCities];
        boolean[] visited = new boolean[numCities];

        // Start at a random city
        int currentCity = (int) (Math.random() * numCities);
        solution[0] = currentCity;
        visited[currentCity] = true;

        // Construct solution by visiting each city once
        for (int i = 1; i < numCities; i++) {
            double[] distances = calculateDistances(currentCity, distanceMatrix, visited);
            double[] pheromones = calculatePheromones(currentCity, pheromoneMatrix, visited);
            double[] probabilities = calculateProbabilities(distances, pheromones, visited, alpha, beta);
            currentCity = chooseNextCity(probabilities);
            solution[i] = currentCity;
            visited[currentCity] = true;
        }

        return solution;
    }

    private double[] calculatePheromones(int currentCity, double[][] pheromoneMatrix, boolean[] visited) {
        int numCities = pheromoneMatrix.length;
        double[] pheromones = new double[numCities];

        for (int i = 0; i < numCities; i++) {
            if (!visited[i]) {
                pheromones[i] = pheromoneMatrix[currentCity][i];
            }
        }

        return pheromones;
    }

    private double[] calculateDistances(int currentCity, double[][] distanceMatrix, boolean[] visited) {
        int numCities = distanceMatrix.length;
        double[] distances = new double[numCities];

        for (int i = 0; i < numCities; i++) {
            if (!visited[i]) {
                distances[i] = distanceMatrix[currentCity][i];
            }
        }

        return distances;
    }

    //calculate probabilities
    public double[] calculateProbabilities(double[] distances, double[] pheromones, boolean[] visited, double alpha, double beta) {
        int numCities = distances.length;
        double[] probabilities = new double[numCities];
        double total = 0;

        for (int i = 0; i < numCities; i++) {
            if (!visited[i]) {
                probabilities[i] = Math.pow(pheromones[i], alpha) * Math.pow(1.0 / distances[i], beta);
                total += probabilities[i];
            }
        }

        for (int i = 0; i < numCities; i++) {
            probabilities[i] /= total;
        }

        return probabilities;
    }

    //choose next city
    public int chooseNextCity(double[] probabilities) {
        double r = Math.random();
        double cumulative = 0;

        for (int i = 0; i < probabilities.length; i++) {
            cumulative += probabilities[i];
            if (r <= cumulative) {
                return i;
            }
        }

        return probabilities.length - 1;
    }

    //evaluate solution
    private double evaluateSolution(int[] solution, double[][] distanceMatrix) {
        double totalDistance = 0;
        int numCities = solution.length;

        for (int i = 0; i < numCities - 1; i++) {
            int city1 = solution[i];
            int city2 = solution[i + 1];
            totalDistance += distanceMatrix[city1][city2];
        }

        // Add distance from last city to first city
        int lastCity = solution[numCities - 1];
        int firstCity = solution[0];
        totalDistance += distanceMatrix[lastCity][firstCity];

        return totalDistance;
    }

    //update pheromone trail
    private void updatePheromoneTrail(int[] Tour, double tourLen) {
        int numCities = Tour.length;
        double pheromone = 1.0 / tourLen;

        for (int i = 0; i < numCities - 1; i++) {
            int city1 = Tour[i];
            int city2 = Tour[i + 1];
            pheromoneMatrix[city1][city2] += pheromone;
            pheromoneMatrix[city2][city1] += pheromone;
        }

        // Add pheromone from last city to first city
        int lastCity = Tour[numCities - 1];
        int firstCity = Tour[0];
        pheromoneMatrix[lastCity][firstCity] += pheromone;
        pheromoneMatrix[firstCity][lastCity] += pheromone;
    }

    public double calculateSolutionLength(int[] antSolution, double[][] distanceMatrix) {
        double solutionLength = 0;
        for (int i = 0; i < antSolution.length - 1; i++) {
            int fromCity = antSolution[i];
            int toCity = antSolution[i + 1];
            //add distance of i to i+1 in solutionLength
            solutionLength += distanceMatrix[fromCity][toCity];
        }

        int fromCity = antSolution[antSolution.length - 1];
        int toCity = antSolution[0];
        //add last edge to solutionLength
        solutionLength += distanceMatrix[fromCity][toCity];

        return solutionLength;
    }


}
