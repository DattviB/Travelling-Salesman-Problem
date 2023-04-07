package Utility;

import main.CrimeSite;

import java.util.List;

public class Christofides {
    public Christofides() {

    }
    public static void constructGraph(List<CrimeSite> points) {
        MinimumSpanningTree mst = new MinimumSpanningTree();
        mst.constructGraph(points);
    }

}
