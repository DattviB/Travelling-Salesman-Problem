package com.info6205.TSP.optimization.stategic;

import static org.junit.Assert.*;

import com.info6205.TSP.optimization.strategic.GeneticAlgorithm;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GeneticAlgorithmTest {

    private double[][] distanceMatrix;
    private GeneticAlgorithm geneticAlgorithm;

    @Before
    public void setUp() throws Exception {
        distanceMatrix = new double[][] {
                {0, 10, 15, 20},
                {10, 0, 35, 25},
                {15, 35, 0, 30},
                {20, 25, 30, 0}
        };
        geneticAlgorithm = new GeneticAlgorithm(distanceMatrix);
    }

    @Test
    public void testGenerateRandomChromosome() {
        double[] chromosome = geneticAlgorithm.generateRandomChromosome(distanceMatrix);
        assertEquals(distanceMatrix.length, chromosome.length);
        assertTrue(isPermutation(chromosome));
    }

    @Test
    public void testEvaluateFitness() {
        double[] fitness = geneticAlgorithm.evaluateFitness(distanceMatrix);
        assertEquals(distanceMatrix.length, fitness.length);
        assertEquals(45, fitness[0], 0.001);
        assertEquals(70, fitness[1], 0.001);
        assertEquals(80, fitness[2], 0.001);
        assertEquals(75, fitness[3], 0.001);
    }

    @Test
    public void testSelectParent() {
        double[] parent = geneticAlgorithm.selectParent(distanceMatrix, new double[] {45, 70, 80, 75});
        assertTrue(isPermutation(parent));
    }

    @Test
    public void testCrossover() {
        double[] parent1 = new double[] {0, 1, 2, 3};
        double[] parent2 = new double[] {3, 2, 1, 0};
        double[][] parents = new double[][] {parent1, parent2};
        double[] child = geneticAlgorithm.crossover(parents);
        assertTrue(isPermutation(child));
        assertTrue(!Arrays.equals(child, parent1) && !Arrays.equals(child, parent2));
    }

    @Test
    public void testMutate() {
        double[] child = new double[] {0, 1, 2, 3};
        double[] mutatedChild = geneticAlgorithm.mutate(child);
        assertTrue(isPermutation(mutatedChild));
        assertTrue(!Arrays.equals(mutatedChild, child));
    }

    private boolean isPermutation(double[] arr) {
        List<Double> list = new ArrayList<Double>();
        for (double d : arr) {
            list.add(d);
        }
        Collections.sort(list);
        for (int i = 0; i < arr.length; i++) {
            if (list.get(i) != (double) i) {
                return false;
            }
        }
        return true;
    }
}
