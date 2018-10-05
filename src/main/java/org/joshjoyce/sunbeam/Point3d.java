package org.joshjoyce.sunbeam;

import java.util.Objects;

public class Point3d {
    public final double x;
    public final double y;
    public final double z;

    public static Point3d fromVector3d(Vector3d v) {
        return new Point3d(v.x, v.y, v.z);
    }

    public static Point3d fromNormal(Normal n) {
        return new Point3d(n.x, n.y, n.z);
    }

    public Point3d(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point3d add(Point3d p) {
        return new Point3d(x + p.x, y + p.y, z + p.z);
    }

    public Point3d add(Vector3d p) {
        return new Point3d(x + p.x, y + p.y, z + p.z);
    }

    public Point3d subtract(Vector3d p) {
        return new Point3d(x - p.x, y - p.y, z - p.z);
    }

    public double dot(Normal n) {
        return x * n.x + y * n.y + z * n.z;
    }

    public double dot(Vector3d n) {
        return x * n.x + y * n.y + z * n.z;
    }

    public Vector3d subtract(Point3d p) {
        return new Vector3d(x - p.x, y - p.y, z - p.z);
    }

    public Point3d scale(double d) {
        return new Point3d(d * x, d * y, d * z);
    }

    public double distSquared(Point3d v) {
        return square(x - v.x) + square(y - v.y) + square(z - v.z);
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
        return Double.compare(d.x, x) == 0 &&
               Double.compare(d.y, y) == 0 &&
               Double.compare(d.z, z) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    @Override
    public String toString() {
        return "[" + x +
               ", " + y +
               ", " + z +
               ']';
    }
}
