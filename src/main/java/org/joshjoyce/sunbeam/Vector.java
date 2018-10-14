package org.joshjoyce.sunbeam;

import java.util.Arrays;
import java.util.Objects;

public class Vector {
    public final double[] components;

    public static Vector for3d(double x, double y, double z) {
        return new Vector(new double[]{x, y, z});
    }

    public Vector(double[] ds) {
        components = new double[ds.length];
        System.arraycopy(ds, 0, components, 0, ds.length);
    }

    public Vector negate() {
        var v = new Vector(components);

        for (int i = 0; i < v.components.length; i++) {
            v.components[i] = -v.components[i];
        }

        return v;
    }

    public int size() {
        return components.length;
    }

    public Vector add(Vector v) {
        double[] sum = new double[v.size()];

        for (int i = 0; i < sum.length; i++) {
            sum[i] = components[i] + v.components[i];
        }

        return new Vector(sum);
    }

    public Vector subtract(Vector v) {
        double[] diff = new double[v.size()];

        for (int i = 0; i < diff.length; i++) {
            diff[i] = components[i] - v.components[i];
        }

        return new Vector(diff);
    }

    public Vector scale(double d) {
        double[] scaled = new double[size()];

        for (int i = 0; i < scaled.length; i++) {
            scaled[i] = components[i] * d;
        }

        return new Vector(scaled);
    }

    public Vector divide(double d) {
        return scale(1 / d);
    }

    public double magnitude() {
        return Math.sqrt(normSquared());
    }

    public Vector normalize() {
        return divide(magnitude());
    }

    public double normSquared() {
        double sum = 0;

        for (double d : components) {
            sum += (d * d);
        }

        return sum;
    }

    public double dot(Vector v) {
        double sum = 0;

        for (int i = 0; i < components.length; i++) {
            sum += (components[i] * v.components[i]);
        }

        return sum;
    }

    public Vector cross(Vector v) {
        assert size() == 3 && v.size() == 3;
        double[] d = new double[3];
        d[0] = components[1] * v.components[2] - components[2] * v.components[1];
        d[1] = components[2] * v.components[0] - components[0] * v.components[2];
        d[2] = components[0] * v.components[1] - components[1] * v.components[0];
        return new Vector(d);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector d = (Vector) o;
        return Arrays.equals(d.components, components);
    }

    @Override
    public int hashCode() {
        return Objects.hash(components);
    }

    @Override
    public String toString() {
        return Arrays.toString(components);
    }
}
