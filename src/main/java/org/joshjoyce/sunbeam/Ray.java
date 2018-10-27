package org.joshjoyce.sunbeam;

public class Ray {
    public final Point3d origin;
    public final Vector direction;

    public Ray(double x, double y, double z, double a, double b, double c) {
        this(new Point3d(x, y, z), Vector.for3d(a, b, c));
    }

    public Ray(Point3d origin, Vector direction) {
        this.origin = origin;
        this.direction = direction;
    }

    public Point3d position(double t) {
        return origin.add(direction.scale(t));
    }
}

