package org.joshjoyce.sunbeam;

public class Hit {
    private final double min;
    private final ShadeRec shade;

    public Hit(double tMin, ShadeRec shade) {
        min = tMin;
        this.shade = shade;
    }

    double getMin() {
        return min;
    }

    ShadeRec getShade() {
        return shade;
    }
}
