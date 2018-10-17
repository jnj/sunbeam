package org.joshjoyce.sunbeam;

import java.util.Arrays;
import java.util.Objects;

public class Point3d extends DoubleTuple {

    public Point3d(double x, double y, double z) {
        super(x, y, z, 1.0);
    }

    public double x() {
        return get(0);
    }

    public double y() {
        return get(1);
    }

    public double z() {
        return get(2);
    }

    public Point3d add(Point3d p) {
        return new Point3d(x() + p.x(), y() + p.y(), z() + p.z());
    }

    public Point3d add(Vector p) {
        return new Point3d(x() + p.components[0], y() + p.components[1], z() + p.components[2]);
    }

    public Point3d subtract(Vector p) {
        return new Point3d(x() - p.components[0], y() - p.components[1], z() - p.components[2]);
    }

    public double dot(Vector n) {
        return x() * n.components[0] + y() * n.components[1] + z() * n.components[2];
    }

    public Vector subtract(Point3d p) {
        return new Vector(x() - p.x(), y() - p.y(), z() - p.z());
    }

    public Point3d scale(double d) {
        return new Point3d(d * x(), d * y(), d * z());
    }

    public double distSquared(Point3d v) {
        return square(x() - v.x()) + square(y() - v.y()) + square(z() - v.z());
    }

    public double dist(Point3d v) {
        return Math.sqrt(distSquared(v));
    }

    private static double square(double r) {
        return r * r;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point3d d = (Point3d) o;
        return Arrays.equals(components, d.components);
    }

    @Override
    public int hashCode() {
        return Objects.hash(components);
    }

    @Override
    public String toString() {
        return "[" + x() +
               ", " + y() +
               ", " + z() +
               ']';
    }
}
