package Utility;

import main.CrimeSite;

import java.util.*;

public class MinimumSpanningTree {

    public static void constructGraph(List<CrimeSite> points) {
        int n = points.size();
        double[][] dist = new double[n][n];

        // Calculate the distance matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    dist[i][j] = 0;
                } else {
                    dist[i][j] = CalculateDistance.distance(points.get(i), points.get(j));
                }
            }
        }

        // Apply Prim's algorithm to find the minimum spanning tree
        int[] parent = new int[n];
        double[] key = new double[n];
        boolean[] visited = new boolean[n];
        Arrays.fill(key, Double.POSITIVE_INFINITY);

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingDouble(i -> key[i]));
        pq.offer(0);
        key[0] = 0;
        parent[0] = -1;

        while (!pq.isEmpty()) {
            int u = pq.poll();
            visited[u] = true;
            for (int v = 0; v < n; v++) {
                if (!visited[v] && dist[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = dist[u][v];
                    pq.offer(v);
                }
            }
        }

        // Print the edges of the minimum spanning tree
        for (int i = 1; i < n; i++) {
            System.out.println("Edge " + parent[i] + "-" + i + " with weight " + dist[parent[i]][i]);
        }
    }
//    public static void main(String[] args) {
//        List<CrimeSite> points = new ArrayList<>();
//        points.add(new CrimeSite(51.5074, 0.1278)); // London
//        points.add(new CrimeSite(48.8566, 2.3522)); // Paris
//        points.add(new CrimeSite(40.7128, -74.0060)); // New York
//        points.add(new CrimeSite(-33.8651, 151.2094)); // Sydney
//        points.add(new CrimeSite(-34.6037, -58.3816)); // Buenos Aires
//        constructGraph(points);
//    }
}
