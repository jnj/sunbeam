package org.joshjoyce.sunbeam;

import java.util.Arrays;

public class Matrix {
    private final int width;
    private final int height;
    private final double[] elements;

    public Matrix(int width, int height) {
        this.width = width;
        this.height = height;
        this.elements = new double[width * height];
    }

    public void setRow(int row, double[] vals) {
        int index = row * width;
        System.arraycopy(vals, 0, elements, index, width);
    }

    public void set(int row, int col, double x) {
        elements[indexOf(row, col)] = x;
    }

    public double get(int row, int col) {
        return elements[indexOf(row, col)];
    }

    private int indexOf(int row, int col) {
        return row * width + col;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Matrix)) {
            return false;
        }

        Matrix m = (Matrix) o;

        if (m.width != width || m.height != height) {
            return false;
        }

        return Arrays.equals(m.elements, elements);
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();

        for (var row = 0; row < height; row++) {
            buf.append("|");

            for (var col = 0; col < width; col++) {
                buf.append(String.format("%.3f", get(row, col)));
                if (col < width - 1) {
                    buf.append(" ");
                }
            }

            buf.append("|");

            if (row < height - 1) {
                buf.append("\n");
            }
        }

        return buf.toString();
    }

    Matrix multiply(Matrix b) {
        var product = new Matrix(b.width, height);

        for (var row = 0; row < height; row++) {
            for (var col = 0; col < b.width; col++) {
                var x = 0D;

                for (var i = 0; i < height; i++) {
                    x += get(row, i) * b.get(i, col);
                }

                product.set(row, col, x);
            }
        }

        return product;
    }
}
