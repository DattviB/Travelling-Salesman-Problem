package com.info6205.TSP.util;

import com.info6205.TSP.graph.Edge;
import com.info6205.TSP.graph.Graph;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GraphUtilTest {

    @Test
    public void testGetMinimumSpanningTreeKruskals() {
        double[][] dist = {{0.0, 2.0, 3.0, 4.0},
                            {2.0, 0.0, 1.0, 5.0},
                            {3.0, 1.0, 0.0, 6.0},
                            {4.0, 5.0, 6.0, 0.0}
                            };
        Graph graph = new Graph(dist);
//        graph.setDistance(0, 1, 2.0);
//        graph.setDistance(0, 2, 3.0);
//        graph.setDistance(0, 3, 4.0);
//        graph.setDistance(1, 2, 1.0);
//        graph.setDistance(1, 3, 5.0);
//        graph.setDistance(2, 3, 6.0);
        GraphUtils graphUtils = new GraphUtils(graph);
        List<Edge> mst = graphUtils.getMinimumSpanningTreeKruskals();

        assertEquals(mst.size(), 3);
        assertTrue(mst.contains(new Edge(0, 1, 2.0)));
        assertTrue(mst.contains(new Edge(0, 2, 3.0)));
        assertTrue(mst.contains(new Edge(1, 2, 1.0)));
    }

//    @Test
//    public void testGetMinimumSpanningTreePrims() {
//        Graph graph = new Graph(4);
//        graph.setDistance(0, 1, 2.0);
//        graph.setDistance(0, 2, 3.0);
//        graph.setDistance(0, 3, 4.0);
//        graph.setDistance(1, 2, 1.0);
//        graph.setDistance(1, 3, 5.0);
//        graph.setDistance(2, 3, 6.0);
//        GraphUtils graphUtils = new GraphUtils(graph);
//        List<Edge> mst = graphUtils.getMinimumSpanningTreePrims();
//        assertEquals(mst.size(), 3);
//        assertTrue(mst.contains(new Edge(0, 1, 2.0)));
//        assertTrue(mst.contains(new Edge(0, 2, 3.0)));
//        assertTrue(mst.contains(new Edge(1, 2, 1.0)));
//    }

//    @Test
//    public void testGetOddDegreeNodes() {
//        Graph graph = new Graph(4);
//        graph.setDistance(0, 1, 2.0);
//        graph.setDistance(0, 2, 3.0);
//        graph.setDistance(0, 3, 4.0);
//        graph.setDistance(1, 2, 1.0);
//        graph.setDistance(1, 3, 5.0);
//        graph.setDistance(2, 3, 6.0);
//        GraphUtils graphUtils = new GraphUtils(graph);
//        List<Edge> mst = graphUtils.getMinimumSpanningTreePrims();
//        List<Integer> oddDegreeNodes = graphUtils.getOddDegreeNodes(mst);
//        assertEquals(oddDegreeNodes.size(), 2);
//        assertTrue(oddDegreeNodes.contains(0));
//        assertTrue(oddDegreeNodes.contains(3));
//    }

//    @Test
//    public void testGetMinimumWeightPerfectMatching() {
//        Graph graph = new Graph(4);
//        graph.setDistance(0, 1, 2.0);
//        graph.setDistance(0, 2, 3.0);
//        graph.setDistance(0, 3, 4.0);
//        graph.setDistance(1, 2, 1.0);
//        graph.setDistance(1, 3, 5.0);
//        graph.setDistance(2, 3, 6.0);
//        GraphUtils graphUtils = new Graph

}
