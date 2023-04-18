package com.info6205.TSP.optimization.tactical;

import com.info6205.TSP.graph.City;

import java.util.*;

public class TwoOptOptimization {

    public static List<City> twoOpt(List<City> cities) {
        int n = cities.size();
        List<City> newCities = new ArrayList<>(cities);
        boolean improved = true;

        int maxIterations = 10000;
        int iterationCount = 0;
        while (improved && iterationCount < maxIterations) {
            improved = false;
            for (int i = 0; i < n - 2; i++) {
                for (int j = i + 2; j < n; j++) {
                    double d1 = distance(cities.get(i), cities.get(i + 1)) + distance(cities.get(j), cities.get((j + 1) % n));
                    double d2 = distance(cities.get(i), cities.get(j)) + distance(cities.get(i + 1), cities.get((j + 1) % n));
                    if (d1 > d2) {
                        reverse(newCities, i + 1, j);
                        improved = true;
                    }
                }
            }
            iterationCount++;
        }
        return newCities;
    }

    private static void reverse(List<City> cities, int i, int j) {
        while (i < j) {
            City temp = cities.get(i);
            cities.set(i, cities.get(j));
            cities.set(j, temp);
            i++;
            j--;
        }
    }

//    public static List<City> twoOpt(List<City> cities) {
//
//        City[] cityArray = cities.toArray(new City[cities.size()]);
//        int n = cityArray.length;
//        City[] newCities = Arrays.copyOf(cityArray, n);
//        boolean improved = true;
//        double[][] distances = precomputeDistances(cityArray);
//
//        int maxIterations = 10000;
//        int iterationCount = 0;
//        while (improved && iterationCount < maxIterations) {
//            improved = false;
//            List<int[]> pairs = generatePairs(n);
//            for (int[] pair : pairs) {
//                int i = pair[0];
//                int j = pair[1];
//                double d1 = distances[i][i + 1] + distances[j][((j + 1) % n)];
//                double d2 = distances[i][j] + distances[i + 1][((j + 1) % n)];
//                if (d1 > d2) {
//                    reverse(newCities, i + 1, j);
//                    improved = true;
//                }
//            }
//            iterationCount++;
//        }
//
////        City[] cityArray = new City[10]; // initialize the array with cities
//        List<City> newCityList = Arrays.asList(newCities);
//        return newCityList;
//    }
//
//    private static double[][] precomputeDistances(City[] cities) {
//        int n = cities.length;
//        double[][] distances = new double[n][n];
//        for (int i = 0; i < n; i++) {
//            for (int j = i + 1; j < n; j++) {
//                double distance = distance(cities[i], cities[j]);
//                distances[i][j] = distance;
//                distances[j][i] = distance;
//            }
//        }
//        return distances;
//    }
//
//    private static List<int[]> generatePairs(int n) {
//        List<int[]> pairs = new ArrayList<>();
//        Random rand = new Random();
//        for (int i = 0; i < n - 2; i++) {
//            for (int j = i + 2; j < n; j++) {
//                if (rand.nextBoolean()) {
//                    pairs.add(new int[]{i, j});
//                }
//            }
//        }
//        return pairs;
//    }
//
//    private static void reverse(City[] cities, int i, int j) {
//        while (i < j) {
//            City temp = cities[i];
//            cities[i] = cities[j];
//            cities[j] = temp;
//            i++;
//            j--;
//        }
//    }
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
