package com.info6205.TSP.optimization.tactical;

import com.info6205.TSP.graph.City;

import java.util.*;

public class TwoOptOptimization {

    public static List<City> twoOpt(List<City> cities) {
        int n = cities.size();
        List<City> newCities = new ArrayList<>(cities);

        boolean improved = true;
        int iterationCount = 0;
        int maxIterations = 2000;
        int maxNonImprovingIterations = 100;
        int nonImprovingIterations = 0;

        while (improved && iterationCount < maxIterations && nonImprovingIterations < maxNonImprovingIterations) {
            improved = false;

            List<int[]> pairs = generatePairs(n, 5);
            // Generate pairs of cities to optimize
            // List<int[]> pairs = generatePairs(n);

            for (int[] pair : pairs) {
                int i = pair[0];
                int j = pair[1];

                // Calculate the new distance after reversing the path between cities i+1 and j
                double d1 = distance(newCities.get(i), newCities.get(i + 1)) + distance(newCities.get(j), newCities.get((j + 1) % n));
                double d2 = distance(newCities.get(i), newCities.get(j)) + distance(newCities.get(i + 1), newCities.get((j + 1) % n));

                if (d1 > d2) {
                    // Reverse the path between cities i+1 and j
                    reverse(newCities, i + 1, j);
                    improved = true;
                    nonImprovingIterations = 0;
                }
            }

            iterationCount++;

            // Check if the tour has improved
            if (!improved) {
                nonImprovingIterations++;
            }
        }

        return newCities;
    }

    private static void reverse(List<City> list, int start, int end) {
        while (start < end) {
            City temp = list.get(start);
            list.set(start, list.get(end));
            list.set(end, temp);
            start++;
            end--;
        }
    }



//    private static List<int[]> generatePairs(int n, int numPairs) {
//        List<int[]> pairs = new ArrayList<>();
//        Random random = new Random();
//        for (int i = 0; i < numPairs; i++) {
//            int j = random.nextInt(n);
//            int k = random.nextInt(n - 1);
//            if (k >= j) {
//                k++;
//            }
//            pairs.add(new int[]{j, k});
//        }
//        return pairs;
//    }

    private static List<int[]> generatePairs(int n, int k) {
        List<int[]> pairs = new ArrayList<>();
        Random rand = new Random();

        // Generate pairs of adjacent cities
        for (int i = 0; i < n - 1; i++) {
            pairs.add(new int[]{i, i + 1});
        }

        // Generate pairs of non-adjacent cities using the k-opt heuristic
        for (int i = 0; i < n - k; i++) {
            int[] pair = new int[k];
            pair[0] = i;
            for (int j = 1; j < k; j++) {
                pair[j] = (i + j) % n;
            }
            pairs.add(pair);
        }

        return pairs;
    }


    public static double distance(City c1, City c2) {
        double lat1 = c1.getLattitude();
        double lon1 = c1.getLongitude();
        double lat2 = c2.getLattitude();
        double lon2 = c2.getLongitude();
        double earthRadius = 6371000; // meters
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = earthRadius * c;
        return distance;
    }




}
