package org.joshjoyce.sunbeam;

import java.util.Objects;

public class Vector3d {
    public final double x;
    public final double y;
    public final double z;

    public Vector3d(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3d negate() {
        return new Vector3d(-x, -y, -z);
    }

    public Vector3d add(Vector3d v) {
        return new Vector3d(x + v.x, y + v.y, z + v.z);
    }

    public Vector3d add(Normal v) {
        return new Vector3d(x + v.x, y + v.y, z + v.z);
    }

    public Vector3d subtract(Vector3d v) {
        return new Vector3d(x - v.x, y - v.y, z - v.z);
    }

    public Vector3d scale(double d) {
        return new Vector3d(x * d, y * d, z * d);
    }

    public Vector3d divide(double d) {
        return scale(1 / d);
    }

    public double magnitude() {
        return Math.sqrt(normSquared());
    }

    public Vector3d normalize() {
        return divide(magnitude());
    }

    public double normSquared() {
        return x * x + y * y + z * z;
    }

    public double dot(Vector3d v) {
        return x * v.x + y * v.y + z * v.z;
    }

    public double dot(Normal v) {
        return x * v.x + y * v.y + z * v.z;
    }

    public Vector3d cross(Vector3d v) {
        return new Vector3d(y * v.z - z * v.y, z * v.x - x * v.z, x * v.y - y * v.x);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector3d d = (Vector3d) o;
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
