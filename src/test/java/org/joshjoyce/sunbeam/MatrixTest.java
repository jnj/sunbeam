package org.joshjoyce.sunbeam;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class MatrixTest {

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
    public void multiply() {
        var a = new Matrix(4, 4);
        var b = new Matrix(4, 4);

        a.setRow(0, new double[]{1, 2, 3, 4});
        a.setRow(1, new double[]{2, 3, 4, 5});
        a.setRow(2, new double[]{3, 4, 5, 6});
        a.setRow(3, new double[]{4, 5, 6, 7});

        b.setRow(0, new double[]{0, 1, 2, 4});
        b.setRow(1, new double[]{1, 2, 4, 8});
        b.setRow(2, new double[]{2, 4, 8, 16});
        b.setRow(3, new double[]{4, 8, 16, 32});

        var c = a.multiply(b);
        assertRowEquals(c, 0, new double[]{24, 49, 98, 196});
        assertRowEquals(c, 1, new double[]{31, 64, 128, 256});
        assertRowEquals(c, 2, new double[]{38, 79, 158, 316});
        assertRowEquals(c, 3, new double[]{45, 94, 188, 376});
    }

    private void assertRowEquals(Matrix c, int row, double[] doubles) {
        for (int j = 0; j < doubles.length; j++) {
            var d = doubles[j];
            var x = c.get(row, j);
            assertEquals("(" + row + ", " + j + ")", d, x, 1e-9);
        }
    }
}