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
        for(Edge e: matching){
            System.out.println("edge u and v: " + e.u + " - " + e.v);
        }


        // combine mst and matching
        List<Edge> euler = graphUtils.createMultigraph(mst, matching);
        //print euler
        System.out.println("Euler:");
        for(int i = 0; i < euler.size(); i++) {
            System.out.println(euler.get(i).getU() + " " + euler.get(i).getV() + " ");
        }
        System.out.println("---------------------");

        // find euler tour
        List<Integer> eulerianCircuit = graphUtils.findEulerianCircuit(euler);
        System.out.println("temp: " + eulerianCircuit);

        // convert euler tour to hamiltonian tour
        this.tour = graphUtils.convertToHamiltonTour(eulerianCircuit);
    }
    public List<City> getTour() {
        return tour;
    }

}
