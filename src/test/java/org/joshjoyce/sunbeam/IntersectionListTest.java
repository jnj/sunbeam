package org.joshjoyce.sunbeam;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class IntersectionListTest {

    @Test
    public void hitAllPositive() {
        var s = Sphere.unit();
        var l = new IntersectionList(2);
        Intersection i1 = new Intersection(1, s);
        l.add(new Intersection(2, s));
        l.add(i1);
        var h = l.hit();
        assertEquals(i1, h);
    }

    @Test
    public void hitSomeNegative() {
        var s = Sphere.unit();
        var l = new IntersectionList(2);
        Intersection i1 = new Intersection(-1, s);
        Intersection i2 = new Intersection(1, s);
        l.add(i2);
        l.add(i1);
        var h = l.hit();
        assertEquals(i2, h);
    }

    @Test
    public void hitAllNegative() {
        var s = Sphere.unit();
        var l = new IntersectionList(2);
        Intersection i1 = new Intersection(-1, s);
        l.add(new Intersection(-2, s));
        l.add(i1);
        var h = l.hit();
        assertNull(h);
    }

    @Test
    public void hitLowestNonNegative() {
        var s = Sphere.unit();
        var l = new IntersectionList(2);
        var i1 = new Intersection(5, s);
        var i2 = new Intersection(7, s);
        var i3 = new Intersection(-3, s);
        var i4 = new Intersection(2, s);
        l.add(i1);
        l.add(i2);
        l.add(i3);
        l.add(i4);
        var h = l.hit();
        assertEquals(i4, h);
    }
}