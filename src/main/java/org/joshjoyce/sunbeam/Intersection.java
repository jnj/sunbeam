package org.joshjoyce.sunbeam;

import java.util.Objects;

public class Intersection {
    public final double t;
    public final Sphere object;

    public Intersection(double t, Sphere object) {
        this.t = t;
        this.object = object;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Intersection that = (Intersection) o;
        return Double.compare(that.t, t) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(t);
    }

    @Override
    public String toString() {
        return "Intersection{" +
               "t=" + t +
               '}';
    }
}
