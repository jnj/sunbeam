package org.joshjoyce.sunbeam;

import static org.junit.Assert.assertEquals;

public class Assertions {

    public static void assertPoint3d(Point3d p, double... ds) {
        double eps = 1e-7;
        assertEquals(ds[0], p.x(), eps);
        assertEquals(ds[1], p.y(), eps);
        assertEquals(ds[2], p.z(), eps);
    }
}
