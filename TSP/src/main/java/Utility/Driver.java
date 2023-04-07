package Utility;

import main.CrimeSite;

import java.util.ArrayList;
import java.util.List;

public class Driver {
    public static void main(String[] args) {
        List<CrimeSite> points = new ArrayList<>();
        points.add(new CrimeSite(51.5074, 0.1278)); // London
        points.add(new CrimeSite(48.8566, 2.3522)); // Paris
        points.add(new CrimeSite(40.7128, -74.0060)); // New York
        points.add(new CrimeSite(-33.8651, 151.2094)); // Sydney
        points.add(new CrimeSite(-34.6037, -58.3816)); // Buenos Aires
        Christofides cf = new Christofides();
        cf.constructGraph(points);
    }
}
