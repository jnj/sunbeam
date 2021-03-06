package org.joshjoyce.sunbeam;

import java.util.Arrays;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Matrix {
    private final int width;
    private final int height;
    private final double[] elements;

    public Matrix(int width, int height) {
        this.width = width;
        this.height = height;
        this.elements = new double[width * height];
    }

    public static Matrix shear(double xy, double xz, double yx, double yz, double zx, double zy) {
        var m = identity(4);
        m.set(0, 1, xy);
        m.set(0, 2, xz);
        m.set(1, 0, yx);
        m.set(1, 2, yz);
        m.set(2, 0, zx);
        m.set(2, 1, zy);
        return m;
    }

    public static Matrix rotateX(double radians) {
        var m = identity(4);
        var cosTheta = cos(radians);
        var sinTheta = sin(radians);
        m.set(1, 1, cosTheta);
        m.set(2, 1, sinTheta);
        m.set(1, 2, -sinTheta);
        m.set(2, 2, cosTheta);
        return m;
    }

    public static Matrix rotateY(double radians) {
        var m = identity(4);
        var cosTheta = cos(radians);
        var sinTheta = sin(radians);
        m.set(0, 0, cosTheta);
        m.set(0, 2, sinTheta);
        m.set(2, 0, -sinTheta);
        m.set(2, 2, cosTheta);
        return m;
    }

    public static Matrix rotateZ(double radians) {
        var m = identity(4);
        var cosTheta = cos(radians);
        var sinTheta = sin(radians);
        m.set(0, 0, cosTheta);
        m.set(1, 0, sinTheta);
        m.set(0, 1, -sinTheta);
        m.set(1, 1, cosTheta);
        return m;
    }

    public static Matrix scaling(double x, double y, double z) {
        var m = identity(4);
        m.set(0, 0, x);
        m.set(1, 1, y);
        m.set(2, 2, z);
        return m;
    }

    public static Matrix translation(double x, double y, double z) {
        var m = identity(4);
        m.set(0, 3, x);
        m.set(1, 3, y);
        m.set(2, 3, z);
        return m;
    }

    public static Matrix identity(int length) {
        var m = new Matrix(length, length);

        for (var i = 0; i < length; i++) {
            m.set(i, i, 1);
        }

        return m;
    }

    public Matrix transpose() {
        //noinspection SuspiciousNameCombination
        var t = new Matrix(height, width);

        for (var row = 0; row < height; row++) {
            for (var col = 0; col < width; col++) {
                t.set(col, row, get(row, col));
            }
        }

        return t;
    }

    public void setRow(int row, double... vals) {
        assert vals.length == width;
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

    public Matrix multiply(Matrix b) {
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

    public Point3d multiply(Point3d p) {
        var product = new double[3];

        for (int i = 0; i < product.length; i++) {
            var sum = 0D;

            for (int j = 0; j < width; j++) {
                var v = get(i, j);
                var c = p.get(j);
                sum += v * c;
            }

            product[i] = sum;
        }

        return new Point3d(product[0], product[1], product[2]);
    }

    public Vector multiply(Vector b) {
        var product = new double[b.size()];

        for (int i = 0; i < product.length; i++) {
            var sum = 0D;

            for (int j = 0; j < width; j++) {
                var v = get(i, j);
                var c = b.components[j];
                sum += v * c;
            }

            product[i] = sum;
        }

        return new Vector(product);
    }

    public double determinant() {
        if (width == 2 && height == 2) {
            return elements[0] * elements[3] - elements[1] * elements[2];
        } else {
            var sum = 0D;

            for (int c = 0; c < width; c++) {
                double elem = elements[c];
                double cof = cofactor(0, c);
                sum += (elem * cof);
            }

            return sum;
        }
    }

    public Matrix subMatrix(int row, int col) {
        var m = new Matrix(width - 1, height - 1);

        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                double x = get(r, c);

                if (c < col && r < row) {
                    m.set(r, c, x);
                } else if (c < col && r > row) {
                    m.set(r - 1, c, x);
                } else if (c > col && r < row) {
                    m.set(r, c - 1, x);
                } else if (c > col && r > row) {
                    m.set(r - 1, c - 1, x);
                }
            }
        }

        return m;
    }

    public int height() {
        return this.height;
    }

    public int width() {
        return this.width;
    }

    public double minor(int row, int col) {
        return subMatrix(row, col).determinant();
    }

    public double cofactor(int row, int col) {
        var m = minor(row, col);
        if (((row + col) & 1) != 0) {
            return -m;
        }

        return m;
    }

    public boolean isInvertible() {
        var det = determinant();
        return det > 0 || det < 0;
    }

    public Matrix invert() {
        var det = determinant();
        var cofactors = new Matrix(width, height);

        for (var row = 0; row < height; row++) {
            for (var col = 0; col < width; col++) {
                cofactors.set(row, col, cofactor(row, col));
            }
        }

        var t = cofactors.transpose();
        t.scale(1 / det);

        return t;
    }

    private void scale(double f) {
        for (var i = 0; i < elements.length; i++) {
            elements[i] *= f;
        }
    }

    double[] rowArray(int i) {
        var a = new double[width];
        System.arraycopy(elements, i * width, a, 0, a.length);
        return a;
    }
}
