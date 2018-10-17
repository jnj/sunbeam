package org.joshjoyce.sunbeam;

public class DoubleTuple {
    protected final double[] components;

    public DoubleTuple(double... items) {
        this.components = new double[items.length];
        System.arraycopy(items, 0, components, 0, items.length);
    }

    public double get(int i) {
        return components[i];
    }
}
