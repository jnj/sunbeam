package org.joshjoyce.sunbeam;

import org.junit.Test;

import static org.joshjoyce.sunbeam.Assertions.assertPoint3d;
import static org.junit.Assert.assertEquals;

public class RayTest {

    @Test
    public void position() {
        var r = new Ray(new Point3d(2, 3, 4), Vector.for3d(1, 0, 0));
        var p = r.position(0);
        assertPoint3d(r.origin, p.x(), p.y(), p.z());
        p = r.position(1);
        assertPoint3d(new Point3d(3, 3, 4), p.x(), p.y(), p.z());
        p = r.position(2);
        assertPoint3d(new Point3d(4, 3, 4), p.x(), p.y(), p.z());
    }

    @Test
    public void translation() {
        var r = new Ray(1, 2, 3, 0, 1, 0);
        var t = Matrix.translation(3, 4, 5);
        var translated = r.transform(t);
        Point3d o = translated.origin;
        Assertions.assertPoint3d(new Point3d(4, 6, 8), o.x(), o.y(), o.z());
        assertEquals(Vector.for3d(0, 1, 0), translated.direction);
    }

    @Test
    public void scaling() {
        var r = new Ray(1, 2, 3, 0, 1, 0);
        var t = Matrix.scaling(2, 3, 4);
        var translated = r.transform(t);
        Point3d o = translated.origin;
        Assertions.assertPoint3d(new Point3d(2, 6, 12), o.x(), o.y(), o.z());
        assertEquals(Vector.for3d(0, 3, 0), translated.direction);
    }
}