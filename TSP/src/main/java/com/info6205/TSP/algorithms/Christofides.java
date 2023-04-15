package com.info6205.TSP.algorithms;

import com.info6205.TSP.graph.City;
import com.info6205.TSP.graph.Edge;
import com.info6205.TSP.graph.Graph;
import com.info6205.TSP.util.GraphUtils;

import java.util.List;

public class Christofides {
    private Graph graph;
    private List<City> tour;

    public Christofides(List<City> cities) {
        this.graph = new Graph(cities);
    }

    public void findTour(){
        // find minimum spanning tree
        GraphUtils graphUtils = new GraphUtils(graph);

        List<Edge> mst = graphUtils.getMinimumSpanningTree();
        System.out.println("MST:");
        for(int i = 0; i < mst.size(); i++) {
            System.out.println(mst.get(i).getU() + " " + mst.get(i).getV() + " ");
        }
        System.out.println("---------------------");


        // find odd degree vertices
        List<Integer> oddDegreeNodes = graphUtils.getOddDegreeNodes(mst);
        System.out.println("Odd degree vertices:");
        for(int i = 0; i < oddDegreeNodes.size(); i++) {
            System.out.println(oddDegreeNodes.get(i));
        }
        System.out.println("---------------------");

        // find minimum weight perfect matching
        List<Edge> matching = graphUtils.getMinimumWeightPerfectMatching(oddDegreeNodes);
        System.out.println("Matching:");
        for(Edge e: matching) {
            System.out.println(e.getU() + "--" + e.getV());
        }
        System.out.println("---------------------");


        // combine mst and matching
        List<Edge> euler = graphUtils.createMultigraph(mst, matching);
        System.out.println("Multigraph:");
        for(Edge e: euler) {
            System.out.println(e.getU() + "--" + e.getV());
        }
        System.out.println("---------------------");

        // find euler tour
        List<Integer> eulerianCircuit = graphUtils.findEulerianCircuit(euler);
        System.out.println("Eulerian Circuit: " + eulerianCircuit);

        // convert euler tour to hamiltonian tour
        this.tour = graphUtils.convertToHamiltonTour(eulerianCircuit);
//        for(City c: tour)
//            System.out.println("Tour: " + c.getId());
    }
    public List<City> getTour() {
        return tour;
    }

}
