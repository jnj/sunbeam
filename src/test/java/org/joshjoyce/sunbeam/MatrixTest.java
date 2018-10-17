package org.joshjoyce.sunbeam;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class MatrixTest {

    public static final double EPS = 1e-9;

    @Test
    public void asString() {
        var m = new Matrix(3, 3);
        var r = new Random();

        for (var i = 0; i < 3; i++) {
            for (var j = 0; j < 3; j++) {
                m.set(i, j, r.nextInt(10000) * r.nextDouble());
            }
        }

        System.out.println(m.toString());
    }

    private static Matrix fromString(String block) {
        List<double[]> nums = new ArrayList<>();
        var lines = block.lines().map(String::trim).collect(Collectors.toList());

        for (var l : lines) {
            var parts = l.split("\\s+");
            double d[] = new double[parts.length];

            for (int i = 0; i < d.length; i++) {
                d[i] = Double.parseDouble(parts[i]);
            }

            nums.add(d);
        }

        var m = new Matrix(nums.get(0).length, nums.size());
        int r = 0;

        for (double[] row : nums) {
            m.setRow(r++, row);
        }

        return m;
    }

    @Test
    public void matrixVectorMultiplication() {
        var a = new Matrix(4, 4);
        a.setRow(0, 1, 2, 3, 4);
        a.setRow(1, 2, 4, 4, 2);
        a.setRow(2, 8, 6, 4, 1);
        a.setRow(3, 0, 0, 0, 1);
        var b = new Vector(1, 2, 3, 1);
        Vector v = a.multiply(b);
        assertEquals(4, v.size());
        assertEquals(new Vector(18, 24, 33, 1), v);
    }

    @Test
    public void identityMultiplication() {
        var a = new Matrix(4, 4);
        a.setRow(0, 1, 2, 3, 4);
        a.setRow(1, 2, 4, 4, 2);
        a.setRow(2, 8, 6, 4, 1);
        a.setRow(3, 0, 0, 0, 1);
        var i = Matrix.identity(4);
        var b = a.multiply(i);
        assertEquals(a, b);
    }

    @Test
    public void transpose() {
        var a = new Matrix(4, 3);
        a.setRow(0, 1, 2, 3, 4);
        a.setRow(1, 2, 4, 4, 2);
        a.setRow(2, 8, 6, 4, 1);

        var b = new Matrix(3, 4);
        b.setRow(0, 1, 2, 8);
        b.setRow(1, 2, 4, 6);
        b.setRow(2, 3, 4, 4);
        b.setRow(3, 4, 2, 1);

        var t = a.transpose();
        assertEquals(b, t);
    }

    @Test
    public void idTranspose() {
        var i = Matrix.identity(5);
        assertEquals(i, i.transpose());
    }

    @Test
    public void twoByTwoDeterminant() {
        var m = new Matrix(2, 2);
        m.setRow(0, 1, 5);
        m.setRow(1, -3, 2);
        assertEquals(17, m.determinant(), EPS);
    }

    @Test
    public void matrixMultiplication() {
        var a = new Matrix(4, 4);
        var b = new Matrix(4, 4);

        a.setRow(0, 1, 2, 3, 4);
        a.setRow(1, 2, 3, 4, 5);
        a.setRow(2, 3, 4, 5, 6);
        a.setRow(3, 4, 5, 6, 7);

        b.setRow(0, 0, 1, 2, 4);
        b.setRow(1, 1, 2, 4, 8);
        b.setRow(2, 2, 4, 8, 16);
        b.setRow(3, 4, 8, 16, 32);

        var c = a.multiply(b);
        assertRowEquals(c, 0, EPS, 24, 49, 98, 196);
        assertRowEquals(c, 1, EPS, 31, 64, 128, 256);
        assertRowEquals(c, 2, EPS, 38, 79, 158, 316);
        assertRowEquals(c, 3, EPS, 45, 94, 188, 376);
    }

    @Test
    public void subMatrix1() {
        var m = new Matrix(3, 3);
        m.setRow(0, 1, 5, 0);
        m.setRow(1, -3, 2, 7);
        m.setRow(2, 0, 6, -3);
        var sub = m.subMatrix(0, 2);
        assertEquals(2, sub.height());
        assertEquals(2, sub.width());
        assertRowEquals(sub, 0, EPS, -3, 2);
        assertRowEquals(sub, 1, EPS, 0, 6);
    }

    @Test
    public void minor() {
        var m = new Matrix(3, 3);
        m.setRow(0, 3, 5, 0);
        m.setRow(1, 2, -1, -7);
        m.setRow(2, 6, -1, 5);
        var b = m.subMatrix(1, 0);
        assertEquals(25, b.determinant(), EPS);
        assertEquals(25, m.minor(1, 0), EPS);
    }

    @Test
    public void cofactor() {
        var m = new Matrix(3, 3);
        m.setRow(0, 3, 5, 0);
        m.setRow(1, 1, -1, -7);
        m.setRow(2, 6, -1, 5);
        assertEquals(-12, m.cofactor(0, 0), EPS);
        assertEquals(-25, m.cofactor(1, 0), EPS);
    }

    @Test
    public void determinant() {
        var m = new Matrix(3, 3);
        m.setRow(0, 1, 2, 6);
        m.setRow(1, -5, 8, -4);
        m.setRow(2, 2, 6, 4);
        assertEquals(56, m.cofactor(0, 0), EPS);
        assertEquals(12, m.cofactor(0, 1), EPS);
        assertEquals(-46, m.cofactor(0, 2), EPS);
        assertEquals(-196, m.determinant(), EPS);
    }

    @Test
    public void determinant4x4() {
        var m = new Matrix(4, 4);
        m.setRow(0, -2, -8, 3, 5);
        m.setRow(1, -3, 1, 7, 3);
        m.setRow(2, 1, 2, -9, 6);
        m.setRow(3, -6, 7, 7, -9);
        assertEquals(-4071, m.determinant(), EPS);
    }

    @Test
    public void invertible() {
        var m = new Matrix(4, 4);
        m.setRow(0, 6, 4, 4, 4);
        m.setRow(1, 5, 5, 7, 6);
        m.setRow(2, 4, -9, 3, -7);
        m.setRow(3, 9, 1, 7, -6);
        assertTrue(m.isInvertible());
    }

    @Test
    public void notInvertible() {
        var m = new Matrix(4, 4);
        m.setRow(0, -4, 2, -2, -3);
        m.setRow(1, 9, 6, 2, 6);
        m.setRow(2, 0, -5, 1, -5);
        m.setRow(3, 0, 0, 0, 0);
        assertFalse(m.isInvertible());
    }

    @Test
    public void subMatrix2() {
        var m = new Matrix(4, 4);
        m.setRow(0, -6, 1, 1, 6);
        m.setRow(1, -8, 5, 8, 6);
        m.setRow(2, -1, 0, 8, 2);
        m.setRow(3, -7, 1, -1, 1);
        var sub = m.subMatrix(2, 1);
        assertEquals(3, sub.width());
        assertEquals(3, sub.height());
        assertRowEquals(sub, 0, EPS, -6, 1, 6);
        assertRowEquals(sub, 1, EPS, -8, 8, 6);
        assertRowEquals(sub, 2, EPS, -7, -1, 1);
    }

    @Test
    public void invert() {
        var m = fromString(
                " -5  2  6 -8 \n" +
                "  1 -5  1  8 \n" +
                "  7  7 -6 -7 \n" +
                "  1 -3  7  4"
        );
        var inverted = m.invert();
        assertRowEquals(inverted, 0, 1e-5, 0.21805, 0.45113, 0.24060, -0.04511);
        assertRowEquals(inverted, 1, 1e-5, -0.80827, -1.45677, -0.44361, 0.52068);
        assertRowEquals(inverted, 2, 1e-5, -0.07895, -0.22368, -0.05263, 0.19737);
        assertRowEquals(inverted, 3, 1e-5, -0.52256, -0.81391, -0.30075, 0.30639);

        var a = fromString(
                " 3  -9  7  3 \n" +
                " 3  -8  2  -9 \n" +
                " -4  4  4  1 \n" +
                " -6  5  -1  1 "
        );

        var b = fromString(
                " 8 2 2 2 \n" +
                " 3 -1 7 0 \n" +
                " 7 0 5 4 \n" +
                " 6  -2  0  5 "
        );

        var c = a.multiply(b);
        var aAgain = c.multiply(b.invert());

        for (int i = 0; i < 4; i++) {
            assertRowEquals(aAgain, i, 1e-5, a.rowArray(i));
        }
    }

    @Test
    public void invertId() {
        var m = Matrix.identity(4);
        var i = m.invert();
        System.out.println(i);
    }

    @Test
    public void transform() {
        var m = Matrix.translation(5, -3, 2);
        var v = m.multiply(new Point3d(-3, 4, 5));
        assertEquals(new Point3d(2, 1, 7), v);

        Vector u = Vector.for3d(-3, 4, 5);
        var w = m.multiply(u);
        assertEquals(u, w);
    }

    @Test
    public void scale() {
        var m = Matrix.scaling(2, 3, 4);
        var p = new Point3d(-4, 6, 8);
        var r = m.multiply(p);
        assertEquals(new Point3d(-8, 18, 32), r);
    }

    @Test
    public void vectorScale() {
        var m = Matrix.scaling(2, 3, 4);
        var v = Vector.for3d(-4, 6, 8);
        var scaled = m.multiply(v);
        assertEquals(Vector.for3d(-8, 18, 32), scaled);

        var rscale = m.invert();
        var w = rscale.multiply(v);
        assertEquals(Vector.for3d(-2, 2, 2), w);
    }

    @Test
    public void reflection() {
        var m = Matrix.scaling(-1, 1, 1);
        var p = new Point3d(2, 3, 4);
        var r = m.multiply(p);
        assertEquals(new Point3d(-2, 3, 4), r);
    }

    @Test
    public void xRotation() {
        var p = new Point3d(0, 1, 0);
        var half_quarter = Matrix.rotateX(Math.PI / 4);
        var full_quarter = Matrix.rotateX(Math.PI / 2);
        var x = Math.sqrt(2) / 2.0;
        Point3d half = half_quarter.multiply(p);
        Point3d full = full_quarter.multiply(p);
        assertPoint3d(half, 0, x, x);
        assertPoint3d(full, 0, 0, 1);

        var half_inv = half_quarter.invert().multiply(p);
        assertPoint3d(half_inv, 0, x, -x);
    }

    @Test
    public void yRotation() {
        var p = new Point3d(0, 0, 1);
        var half_quarter = Matrix.rotateY(Math.PI / 4);
        var full_quarter = Matrix.rotateY(Math.PI / 2);
        var x = Math.sqrt(2) / 2.0;
        Point3d half = half_quarter.multiply(p);
        Point3d full = full_quarter.multiply(p);
        assertPoint3d(half, x, 0, x);
        assertPoint3d(full, 1, 0, 0);

        var half_inv = half_quarter.invert().multiply(p);
        assertPoint3d(half_inv, -x, 0, x);
    }

    @Test
    public void zRotation() {
        var p = new Point3d(0, 1, 0);
        var half_quarter = Matrix.rotateZ(Math.PI / 4);
        var full_quarter = Matrix.rotateZ(Math.PI / 2);
        var x = Math.sqrt(2) / 2.0;
        Point3d half = half_quarter.multiply(p);
        Point3d full = full_quarter.multiply(p);
        assertPoint3d(half, -x, x, 0);
        assertPoint3d(full, -1, 0, 0);
    }

    private void assertPoint3d(Point3d p, double... ds) {
        double eps = 1e-7;
        assertEquals(ds[0], p.x(), eps);
        assertEquals(ds[1], p.y(), eps);
        assertEquals(ds[2], p.z(), eps);
    }

    private void assertRowEquals(Matrix c, int row, double eps, double... doubles) {
        for (int j = 0; j < doubles.length; j++) {
            var d = doubles[j];
            var x = c.get(row, j);
            assertEquals("(" + row + ", " + j + ")", d, x, eps);
        }
    }
}