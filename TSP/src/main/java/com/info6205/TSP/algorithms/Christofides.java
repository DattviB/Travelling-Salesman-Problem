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


    }
    public List<City> getTour() {
        return tour;
    }

}
