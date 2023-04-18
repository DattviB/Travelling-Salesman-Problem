package com.info6205.TSP.util;

import com.info6205.TSP.graph.City;
import com.info6205.TSP.graph.Edge;
import com.info6205.TSP.graph.Graph;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GraphUtilsTest {

    @Test
    public void testGetMinimumSpanningTree() {
        // Create a sample graph for testing
        List<City> cities = new ArrayList<>();
        cities.add(new City("City1", 0.0, 0.0));
        cities.add(new City("City2", 1.0, 1.0));
        cities.add(new City("City3", 2.0, 2.0));
        cities.add(new City("City4", 3.0, 3.0));
        Graph graph = new Graph(cities);

        // Add edges manually for the sample graph
        graph.addEdge(0, 1, 2.0); // Add edges with weights
        graph.addEdge(0, 2, 3.0);
        graph.addEdge(1, 3, 1.0);
        graph.addEdge(2, 3, 4.0);
        //graph.addEdge(3, 4, 3.0);

        // Call getMinimumSpanningTree() method
        GraphUtils graphUtils = new GraphUtils(graph);
        List<Edge> mst = graphUtils.getMinimumSpanningTreeKruskals();

        // Assert the size of the minimum spanning tree is correct
        assertEquals(3, mst.size());

        // Assert that all edges in the minimum spanning tree are valid (no cycles, connected graph)
        UnionFind uf = new UnionFind(graph.size());
        for (Edge edge : mst) {
            uf.union(edge.u, edge.v);
        }
        assertTrue(uf.isConnected());

        // Assert that the weight of the minimum spanning tree is minimum possible
        double totalWeight = 0;
        for (Edge edge : mst) {
            totalWeight += edge.getW(); // Access weight directly from Edge object
        }
        assertEquals(6.0, totalWeight, 0.001); // Assuming double values, use delta for comparison
    }
    @Test
    public void testGraphConstructorWithValidCities() {
        // Create a list of cities
        List<City> cities = new ArrayList<>();
        cities.add(new City("City1", 0, 0));
        cities.add(new City("City2", 1, 1));
        cities.add(new City("City3", 2, 2));
        cities.add(new City("City4", 3, 3));

        // Create a graph using the Graph constructor
        Graph graph = new Graph(cities);

        // Assert the size of the adjacency matrix
        assertEquals(cities.size(), graph.getAdjacencyMatrix().size());
    }
    @Test
    public void testGetOddDegreeNodes() {
        // Create a sample graph
        List<City> cities = new ArrayList<>();
        cities.add(new City("City1", 0, 0));
        cities.add(new City("City2", 1, 1));
        cities.add(new City("City3", 2, 2));
        cities.add(new City("City4", 3, 3));
        Graph graph = new Graph(cities);
        graph.addEdge(0, 1, graph.distance(cities.get(0), cities.get(1)));
        graph.addEdge(0, 2, graph.distance(cities.get(0), cities.get(2)));
        graph.addEdge(1, 3, graph.distance(cities.get(1), cities.get(3)));
        graph.addEdge(2, 3, graph.distance(cities.get(2), cities.get(3)));
        // Create a minimum spanning tree for the graph
        List<Edge> mst = new ArrayList<>();
        mst.add(new Edge(0, 1, 2.0));
        mst.add(new Edge(1, 3, 1.0));
        mst.add(new Edge(0, 2, 3.0));

        // Call the getOddDegreeNodes() method
        GraphUtils graphUtils = new GraphUtils(graph);
        List<Integer> oddDegreeNodes = graphUtils.getOddDegreeNodes(mst);

        // Expected list of odd degree nodes
        List<Integer> expected = Arrays.asList(0, 2);

        // Assert the expected list of odd degree nodes with the actual result
        assertEquals(expected, oddDegreeNodes);
    }

    @Test
    public void testGetOddDegreeNodesWithEmptyMST() {
        List<City> cities = new ArrayList<>();
        cities.add(new City("A", 0, 0));
        cities.add(new City("B", 0, 1));
        cities.add(new City("C", 1, 1));
        cities.add(new City("D", 1, 0));

        // Create a graph from the list of cities
        Graph graph = new Graph(cities);

        // Add edges between the cities
        graph.addEdge(0, 1, 2.0);
        graph.addEdge(0, 2, 3.0);
        graph.addEdge(1, 3, 1.0);
        graph.addEdge(2, 3, 4.0);

        // Create an empty minimum spanning tree
        List<Edge> mst = new ArrayList<>();

        // Call the getOddDegreeNodes() method
        GraphUtils graphUtils = new GraphUtils(graph);
        List<Integer> oddDegreeNodes = graphUtils.getOddDegreeNodes(mst);

        // Expected list of odd degree nodes (all vertices since the MST is empty)
        List<Integer> expected = Arrays.asList();

        // Assert the expected list of odd degree nodes with the actual result
        assertEquals(expected, oddDegreeNodes);
    }


}
