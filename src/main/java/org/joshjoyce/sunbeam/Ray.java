package org.joshjoyce.sunbeam;

public class Ray {
    public final Point3d origin;
    public final Vector direction;

    Ray(Point3d origin, Vector direction) {
        this.origin = origin;
        this.direction = direction;
    }

    Ray(Ray r) {
        this.origin = r.origin;
        this.direction = r.direction;
    }


}
