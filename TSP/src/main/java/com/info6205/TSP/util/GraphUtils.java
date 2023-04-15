package com.info6205.TSP.util;

import com.info6205.TSP.graph.City;
import com.info6205.TSP.graph.Edge;
import com.info6205.TSP.graph.Graph;

import java.util.*;
import java.util.function.Supplier;

public class GraphUtils {

    private Graph graph;
    private List<City> tour;

    public GraphUtils(Graph graph) {
        this.graph = graph;
    }

    public List<Edge> getMinimumSpanningTree() {
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < graph.size(); i++) {
            for (int j = i + 1; j < graph.size(); j++) {
                edges.add(new Edge(i, j, graph.getDistance(i, j)));
            }
        }
        Supplier<Comparator<Edge>> weightComparatorSupplier = () -> Comparator.comparingDouble(Edge::getWeight);
        Collections.sort(edges, weightComparatorSupplier.get());
//        Collections.sort(Supplier.get(edges));
        UnionFind uf = new UnionFind(graph.size());
        List<Edge> mst = new ArrayList<>();
        for (Edge edge : edges) {
            if (uf.find(edge.u) != uf.find(edge.v)) {
                uf.union(edge.u, edge.v);
                mst.add(edge);
            }
        }
        return mst;
    }

    public List<Integer> getOddDegreeNodes(List<Edge> mst) {
        // Use the countEdges() method to get a HashMap of the number of incident edges for each city
        Map<Integer, Integer> edgeCounts = countEdges(mst);
        // Create an empty list to store the indices of vertices with an odd degree
        List<Integer> odd = new ArrayList<>();
        // Loop over all the vertices in the graph
        for (int i = 0; i < graph.size(); i++) {
            // If the number of incident edges for the current vertex is odd,
            // add its index to the list of vertices with an odd degree
            if (edgeCounts.get(i) % 2 == 1) {
                odd.add(i);
            }
        }
        // Return the list of indices of vertices with an odd degree
        return odd;
    }


    public Map<Integer, Integer> countEdges(List<Edge> mst) {
        // Create a new HashMap to store the number of incident edges for each city
        Map<Integer, Integer> edgeCounts = new HashMap<>();
        // Initialize all the edge counts to zero
        for (int i = 0; i < graph.size(); i++) {
            edgeCounts.put(i, 0);
        }
        // Loop over all the edges in the MST
        for (Edge edge : mst) {
            // Increment the edge counts for the two vertices in the current edge
            edgeCounts.put(edge.u, edgeCounts.get(edge.u) + 1);
            edgeCounts.put(edge.v, edgeCounts.get(edge.v) + 1);
        }
        // Return the HashMap of edge counts
        return edgeCounts;
    }


    public List<Edge> getMinimumWeightPerfectMatching(List<Integer> odd) {
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < odd.size(); i++) {
            for (int j = i + 1; j < odd.size(); j++) {
                edges.add(new Edge(odd.get(i), odd.get(j), graph.getDistance(odd.get(i), odd.get(j))));
            }
        }
        Supplier<Comparator<Edge>> weightComparatorSupplier = () -> Comparator.comparingDouble(Edge::getWeight);
        Collections.sort(edges, weightComparatorSupplier.get());
//        Collections.sort(edges);
        UnionFind uf = new UnionFind(graph.size());
        List<Edge> matching = new ArrayList<>();
        for (Edge edge : edges) {
            if (uf.find(edge.u) != uf.find(edge.v)) {
                uf.union(edge.u, edge.v);
                matching.add(edge);
            }
        }
        return matching;
    }


    public List<Edge> createMultigraph(List<Edge> mst, List<Edge> matching) {
        List<Edge> edges = new ArrayList<>(mst);
        edges.addAll(matching);
        return edges;
    }

}
