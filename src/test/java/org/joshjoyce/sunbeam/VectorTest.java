package org.joshjoyce.sunbeam;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class VectorTest {

    @Test
    public void normalization() {
        var r = new Random(1123L);
        int bound = 1000;

        for (var i = 0; i < 10000; i++) {
            var v = new Vector(
                    (r.nextBoolean() ? 1 : -1) * r.nextInt(bound),
                    (r.nextBoolean() ? 1 : -1) * r.nextInt(bound),
                    (r.nextBoolean() ? 1 : -1) * r.nextInt(bound));
            System.out.println(v);
            var n = v.normalize();
            assertEquals(1, n.magnitude(), 1e-9);
        }
    }
}