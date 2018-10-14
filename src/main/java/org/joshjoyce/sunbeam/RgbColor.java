package org.joshjoyce.sunbeam;

public class RgbColor extends Vector3d {

    public RgbColor(double r, double g, double b) {
        super(r, g, b);
    }

    public double r() {
        return x;
    }

    public double g() {
        return y;
    }

    public double b() {
        return z;
    }

    public RgbColor hadamard(RgbColor that) {
        return new RgbColor(that.r() * r(), that.g() * g(), that.b() * b());
    }

    @Override
    public String toString() {
        return "RgbColor{" +
               "r=" + r() +
               ", g=" + g() +
               ", b=" + b() +
               '}';
    }
}
