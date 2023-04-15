package com.info6205.TSP.algorithms;

import com.info6205.TSP.graph.City;
import com.info6205.TSP.graph.Edge;
import com.info6205.TSP.graph.Graph;

import java.util.List;

public class Christofides {
    private Graph graph;
    private List<City> tour;

    public Christofides(List<City> cities) {
        this.graph = new Graph(cities);
    }

    public void findTour(){

    }
    public List<City> getTour() {
        return tour;
    }

}
