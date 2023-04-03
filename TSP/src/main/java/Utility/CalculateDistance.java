package Utility;

import main.CrimeSite;

public class CalculateDistance {
    private static final double R = 6371; // Earth's radius in kilometers
    CrimeSite siteA;
    CrimeSite siteB;

    public CalculateDistance(CrimeSite siteA, CrimeSite siteB) {
        this.siteA = siteA;
        this.siteB = siteB;
    }

    public static double distance(CrimeSite p1, CrimeSite p2) {

        double dLat = Math.toRadians(p2.lat - p1.lat);
        double dLng = Math.toRadians(p2.lng - p1.lng);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(p1.lat)) * Math.cos(Math.toRadians(p2.lat)) *
                        Math.sin(dLng/2) * Math.sin(dLng/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double distance = R * c;
        return distance;
    }

}
