package com.info6205.TSP.optimization.strategic;

import com.info6205.TSP.graph.City;
import com.info6205.TSP.graph.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GeneticAlgorithm {

    private double[][] distanceMatrix;
    public GeneticAlgorithm(double[][] distanceMatrix) {
        this.distanceMatrix = distanceMatrix;
    }

    //run the genetic algorithm
    public void run() {
        //initialize population
        int populationSize = 100;
        double[][] population = new double[populationSize][distanceMatrix.length];
        for (int i = 0; i < populationSize; i++) {
            population[i] = generateRandomChromosome(distanceMatrix);
        }
        //evaluate fitness
        double[] fitness = evaluateFitness(distanceMatrix);
        //select parents
        double[][] parents = selectParents(population, fitness);
        //crossover
        double[] children = crossover(parents);
        //mutate
        double[] mutatedChildren = mutate(children);
        //find best solution
        double[] bestSolution = findBestSolution(mutatedChildren, fitness);
        //print best solution
//        System.out.println("Best Solution: " + Arrays.toString(bestSolution));
        //sort the best solution
        double[] sortedBestSolution = sortBestSolution(bestSolution);
        double bestFitness = sortedBestSolution[sortedBestSolution.length - 1];
//        System.out.println("##### Genetic Algorithm minimum cost:: " + bestFitness);
    }

    private double[] sortBestSolution(double[] bestSolution) {
        double[] sortedBestSolution = new double[bestSolution.length];
        for (int i = 0; i < bestSolution.length; i++) {
            sortedBestSolution[i] = bestSolution[i] ;
        }
        Arrays.sort(sortedBestSolution);
        return sortedBestSolution;
    }

    private double[] findBestSolution(double[] mutatedChildren, double[] fitness) {
        double[] bestSolution = new double[distanceMatrix.length];
        double bestFitness = Double.POSITIVE_INFINITY;
        for (int i = 0; i < mutatedChildren.length; i++) {
            if (fitness[i] < bestFitness) {
                bestFitness = fitness[i];
                bestSolution = mutatedChildren;
            }
        }
        return bestSolution;
    }

    private double[][] selectParents(double[][] population, double[] fitness) {
        double[][] parents = new double[population.length][distanceMatrix.length];
        for (int i = 0; i < population.length; i++) {
            parents[i] = selectParent(distanceMatrix, fitness);
        }
        return parents;
    }

    //generate random chromosome
    public double[] generateRandomChromosome(double[][] distanceMatrix) {
        double[] chromosome = new double[distanceMatrix.length];
        for (int i = 0; i < distanceMatrix.length; i++) {
            chromosome[i] = i;
        }
        for (int i = 0; i < distanceMatrix.length; i++) {
            int randomIndex = (int) (Math.random() * distanceMatrix.length);
            double temp = chromosome[randomIndex];
            chromosome[randomIndex] = chromosome[i];
            chromosome[i] = temp;
        }
        return chromosome;
    }

    public double[] evaluateFitness(double[][] distanceMatrix) {
        double[] fitness = new double[distanceMatrix.length];
        for (int i = 0; i < distanceMatrix.length; i++) {
            double sum = 0;
            for (int j = 0; j < distanceMatrix.length; j++) {
                sum += distanceMatrix[i][j];
            }
            fitness[i] = sum;
        }
        return fitness;
    }


    //Select a chromosome from the population based on its fitness
    public double[] selectParent(double[][] distanceMatrix, double[] fitness){
        double[] parent = new double[distanceMatrix.length];
        double fitnessSum = 0;
        for (int i = 0; i < fitness.length; i++) {
            fitnessSum += fitness[i];
        }
        double rand = Math.random() * fitnessSum;
        for (int i = 0; i < fitness.length; i++) {
            rand -= fitness[i];
            if (rand <= 0) {
                parent = distanceMatrix[i];
                break;
            }
        }
        return parent;
    }

    //Crossover two parents to create a child
    public double[] crossover(double[][] parents){
        double[] parent1 = parents[0];
        double[] parent2 = parents[1];
        double[] child = new double[parent1.length];
        int start = (int) (Math.random() * parent1.length);
        int end = (int) (Math.random() * parent1.length);
        for (int i = 0; i < child.length; i++) {
            if (start < end && i > start && i < end) {
                child[i] = parent1[i];
            } else if (start > end) {
                if (!(i < start && i > end)) {
                    child[i] = parent1[i];
                }
            }
        }
        for (int i = 0; i < parent2.length; i++) {
            if (!Arrays.asList(child).contains(parent2[i])) {
                for (int j = 0; j < child.length; j++) {
                    if (child[j] == 0) {
                        child[j] = parent2[i];
                        break;
                    }
                }
            }
        }
        return child;
    }


    //Mutate a child by swapping two cities
    public double[] mutate(double[] child){
        int city1 = (int) (child.length * Math.random());
        int city2 = (int) (child.length * Math.random());
        double temp = child[city1];
        child[city1] = child[city2];
        child[city2] = temp;
        return child;
    }

    //Create a new population by selecting parents, crossing them over, and mutating them
    public double[][] evolvePopulation(double[][] distanceMatrix){
        double[][] newPopulation = new double[distanceMatrix.length][distanceMatrix.length];
        double[] fitness = evaluateFitness(distanceMatrix);
        for (int i = 0; i < distanceMatrix.length; i++) {
            double[][] parents = selectParents(distanceMatrix, fitness);
            double[] child = crossover(parents);
            newPopulation[i] = mutate(child);
        }
        return newPopulation;
    }

    //Repeat the evolvePopulation method until a solution is found
    public double[][] geneticAlgorithm(double[][] distanceMatrix){
        double[][] population = new double[distanceMatrix.length][distanceMatrix.length];
        for (int i = 0; i < distanceMatrix.length; i++) {
            population[i] = generateRandomChromosome(distanceMatrix);
        }
        int generation = 1;
        while (true) {
            System.out.println("Generation: " + generation);
            double[][] newPopulation = evolvePopulation(population);
            double[] newFitness = evaluateFitness(newPopulation);
            double[] oldFitness = evaluateFitness(population);
            double bestFitness = 0;
            double bestIndex = 0;
            for (int i = 0; i < newFitness.length; i++) {
                if (newFitness[i] < oldFitness[i]) {
                    if (newFitness[i] > bestFitness) {
                        bestFitness = newFitness[i];
                        bestIndex = i;
                    }
                }
            }
            if (bestFitness == 0) {
                generation++;
                population = newPopulation;
            } else {
                System.out.println("Found solution in " + generation + " generations.");
                return newPopulation;
            }
        }
    }

    //Print the final solution's distance
    public void printDistance(){

    }
}
