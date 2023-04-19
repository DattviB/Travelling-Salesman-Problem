package com.info6205.TSP.optimization.tactical;

import com.info6205.TSP.graph.City;

import java.util.*;

public class ThreeOptOptimization {
    List<City> tour;
//    Graph graph;

    public ThreeOptOptimization(List<City> tour) {
        this.tour = tour;
//        this.graph = new Graph(tour);
    }

    public void threeOpt() {
        int improvement = 0;
        int count = 0;
        Map<City, Integer> cityToIndex = new HashMap<>();  //city index mapping
        for (int i = 0; i < tour.size(); i++) {
            cityToIndex.put(tour.get(i), i);
        }
        int maxIterations = 1000;
        while (improvement == 0 && count < maxIterations) {
            improvement = 1;
            for (int i = 0; i < tour.size() - 2; i++) {
                for (int j = i + 1; j < tour.size() - 1; j++) {
                    for (int k = j + 1; k < tour.size(); k++) {
                        if (k - i == 1 || j - i == 1 || k - j == 1) continue;

                        double distance1 = distance(tour.get(i), tour.get(i + 1))
                                + distance(tour.get(j), tour.get(j + 1))
                                + distance(tour.get(k), tour.get((k + 1) % tour.size()));
                        double distance2 = distance(tour.get(i), tour.get(j + 1))
                                + distance(tour.get(k), tour.get(i + 1))
                                + distance(tour.get(j), tour.get((k + 1) % tour.size()));
                        double distance3 = distance(tour.get(i), tour.get(k))
                                + distance(tour.get(j + 1), tour.get((k + 1) % tour.size()))
                                + distance(tour.get(j), tour.get(i + 1));

                        if (distance2 < distance1 || distance3 < distance1) {
                            if (distance2 < distance3) {
                                // 2-opt swap
                                reverse(tour, j + 1, k);
                                improvement = 0;
                            } else {
                                // 3-opt swap
                                reverse(tour, i + 1, j);
                                reverse(tour, j + 1, k);
                                improvement = 0;
                            }
                            // update the position of cities in the hashmap
                            for (int index = i + 1; index <= k; index++) {
                                cityToIndex.put(tour.get(index), index);
                            }
                        }
                    }
                    if (improvement == 0) {
                        break;
                    }
                }
                if (improvement == 0) {
                    break;
                }
            }
            count++;
        }
        double tourDistance = 0;
        for (int i = 0; i < tour.size() - 1; i++) {
            tourDistance += distance(tour.get(i), tour.get(i + 1));
        }
//        tourDistance += graph.distance(tour.get(tour.size() - 1), tour.get(0)); // add distance from last city to first city
        System.out.println("Three-opt Distance: " + tourDistance);
//        System.out.println("Three-opt improvement: " + count);
    }

    private void reverse(List<City> list, int start, int end) {
        while (start < end) {
            City temp = list.get(start);
            list.set(start, list.get(end));
            list.set(end, temp);
            start++;
            end--;
        }
    }

    public double distance(City c1, City c2) {
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
