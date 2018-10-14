package org.joshjoyce.sunbeam;

import java.awt.*;

public class RgbColor extends Vector {

    public RgbColor(double r, double g, double b) {
        super(new double[]{r, g, b});
    }

    public double r() {
        return components[0];
    }

    public double g() {
        return components[1];
    }

    public double b() {
        return components[2];
    }

    public RgbColor hadamard(RgbColor that) {
        return new RgbColor(that.r() * r(), that.g() * g(), that.b() * b());
    }

    public Color toColor() {
        return new Color((float) r(),(float) g(), (float) b());
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
