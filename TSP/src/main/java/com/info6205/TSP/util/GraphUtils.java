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

}
