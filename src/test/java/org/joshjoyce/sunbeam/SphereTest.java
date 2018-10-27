package org.joshjoyce.sunbeam;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SphereTest {

    @Test
    public void intersect() {
        var ray = new Ray(new Point3d(0, 0, -5), Vector.for3d(0, 0, 1));
        var sphere = Sphere.unit();
        var ts = sphere.intersect(ray);
        assertEquals(2, ts.size());
        assertEquals(4, ts.getDouble(0), 1e-9);
        assertEquals(6, ts.getDouble(1), 1e-9);
    }

    @Test
    public void tangent() {
        var ray = new Ray(new Point3d(0, 1, -5), Vector.for3d(0, 0, 1));
        var sphere = Sphere.unit();
        var ts = sphere.intersect(ray);
        assertEquals(2, ts.size());
        for (int i = 0; i < 2; i++) {
            assertEquals(5, ts.getDouble(i), 1e-9);
        }
    }

    @Test
    public void miss() {
        var ray = new Ray(new Point3d(0, 2, -5), Vector.for3d(0, 0, 1));
        var sphere = Sphere.unit();
        var ts = sphere.intersect(ray);
        assertTrue(ts.isEmpty());
    }

    @Test
    public void rayOriginatesInside() {
        var ray = new Ray(0, 0, 0, 0, 0, 1);
        var sphere = Sphere.unit();
        var ts = sphere.intersect(ray);
        assertEquals(2, ts.size());
        assertEquals(-1, ts.getDouble(0), 1e-9);
        assertEquals(1, ts.getDouble(1), 1e-9);
    }

    @Test
    public void behind() {
        var ray = new Ray(0, 0, 5, 0, 0, 1);
        var sphere = Sphere.unit();
        var ts = sphere.intersect(ray);
        assertEquals(2, ts.size());
        assertEquals(-6, ts.getDouble(0), 1e-9);
        assertEquals(-4, ts.getDouble(1), 1e-9);
    }
}