package org.joshjoyce.sunbeam;

public class Intersection {
    public final double t;
    public final Sphere object;

    public Intersection(double t, Sphere object) {
        this.t = t;
        this.object = object;
    }
}
