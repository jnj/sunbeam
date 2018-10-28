package org.joshjoyce.sunbeam;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SphereTest {

    @Test
    public void intersect() {
        var ray = new Ray(new Point3d(0, 0, -5), Vector.for3d(0, 0, 1));
        var sphere = Sphere.unit();
        var ts = sphere.intersect(ray);
        assertEquals(2, ts.size());
        assertEquals(4, ts.get(0).t, 1e-9);
        assertEquals(6, ts.get(1).t, 1e-9);
    }

    @Test
    public void tangent() {
        var ray = new Ray(new Point3d(0, 1, -5), Vector.for3d(0, 0, 1));
        var sphere = Sphere.unit();
        var ts = sphere.intersect(ray);
        assertEquals(2, ts.size());
        for (int i = 0; i < 2; i++) {
            assertEquals(5, ts.get(i).t, 1e-9);
        }
    }

    @Test
    public void miss() {
        var ray = new Ray(new Point3d(0, 2, -5), Vector.for3d(0, 0, 1));
        var sphere = Sphere.unit();
        var ts = sphere.intersect(ray);
        assertEquals(0, ts.size());
    }

    @Test
    public void rayOriginatesInside() {
        var ray = new Ray(0, 0, 0, 0, 0, 1);
        var sphere = Sphere.unit();
        var ts = sphere.intersect(ray);
        assertEquals(2, ts.size());
        assertEquals(-1, ts.get(0).t, 1e-9);
        assertEquals(1, ts.get(1).t, 1e-9);
    }

    @Test
    public void behind() {
        var ray = new Ray(0, 0, 5, 0, 0, 1);
        var sphere = Sphere.unit();
        var ts = sphere.intersect(ray);
        assertEquals(2, ts.size());
        assertEquals(-6, ts.get(0).t, 1e-9);
        assertEquals(-4, ts.get(1).t, 1e-9);
    }

    @Test
    public void scaledSphereIntersection() {
        var r = new Ray(0, 0, -5, 0, 0, 1);
        var s = Sphere.unit();
        s.setTransformation(Matrix.scaling(2, 2, 2));
        var inters = s.intersect(r);
        assertEquals(2, inters.size());
        assertEquals(3, inters.get(0).t, 1e-9);
        assertEquals(7, inters.get(1).t, 1e-9);
    }

    @Test
    public void translatedSphereIntersection() {
        var r = new Ray(0, 0, -5, 0, 0, 1);
        var s = Sphere.unit();
        s.setTransformation(Matrix.translation(5, 0, 0));
        var inters = s.intersect(r);
        assertEquals(0, inters.size());
    }
}