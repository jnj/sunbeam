package org.joshjoyce.sunbeam;

import org.junit.Test;

import java.util.Random;

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
        assertRowEquals(c, 0, new double[]{24, 49, 98, 196});
        assertRowEquals(c, 1, new double[]{31, 64, 128, 256});
        assertRowEquals(c, 2, new double[]{38, 79, 158, 316});
        assertRowEquals(c, 3, new double[]{45, 94, 188, 376});
    }

    @Test
    public void matrixVectorMultiplication() {
        var a = new Matrix(4, 4);
        a.setRow(0, 1, 2, 3, 4);
        a.setRow(1, 2, 4, 4, 2);
        a.setRow(2, 8, 6, 4, 1);
        a.setRow(3, 0, 0, 0, 1);
        var b = new Vector(new double[]{1, 2, 3, 1});
        Vector v = a.multiply(b);
        assertEquals(4, v.size());
        assertEquals(new Vector(new double[]{18, 24, 33, 1}), v);
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
    public void subMatrix1() {
        var m = new Matrix(3, 3);
        m.setRow(0, 1, 5, 0);
        m.setRow(1, -3, 2, 7);
        m.setRow(2, 0, 6, -3);
        var sub = m.subMatrix(0, 2);
        assertEquals(2, sub.height());
        assertEquals(2, sub.width());
        assertRowEquals(sub, 0, new double[]{-3, 2});
        assertRowEquals(sub, 1, new double[]{0, 6});
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
        assertRowEquals(sub, 0, new double[]{-6, 1, 6});
        assertRowEquals(sub, 1, new double[]{-8, 8, 6});
        assertRowEquals(sub, 2, new double[]{-7, -1, 1});
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

    private void assertRowEquals(Matrix c, int row, double[] doubles) {
        for (int j = 0; j < doubles.length; j++) {
            var d = doubles[j];
            var x = c.get(row, j);
            assertEquals("(" + row + ", " + j + ")", d, x, EPS);
        }
    }
}