package com.info6205.TSP.graph;

public class Edge {
    public int u;
    public int v;
    public double w;

    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
    }

    public double getW() {
        return w;
    }

    public void setW(double w) {
        this.w = w;
    }

    public int getU() {
        return u;
    }

    public void setU(int u) {
        this.u = u;
    }

    public Edge(int u, int v, double w) {
        this.u = u;
        this.v = v;
        this.w = w;
    }

    public int compareTo(Edge e) {
        return Double.compare(this.w, e.w);
    }

    public static <T> double getWeight(T t) {
        if (t instanceof Edge) {
            return ((Edge) t).w;
        } else {
            return 0;
        }
    }
}
