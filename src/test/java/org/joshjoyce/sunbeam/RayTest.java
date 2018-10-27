package org.joshjoyce.sunbeam;

import org.junit.Test;

import static org.joshjoyce.sunbeam.Assertions.assertPoint3d;

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
}