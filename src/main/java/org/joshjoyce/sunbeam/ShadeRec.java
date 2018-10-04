package org.joshjoyce.sunbeam;

import java.awt.Color;

public class ShadeRec {
    private final World w;
    private final boolean object;
    private final Color color;

    Point3d hitPoint;
    Normal normal;

    ShadeRec(World w, boolean hitObject, Point3d hitPoint, Normal normal, Color color) {
        this.w = w;
        object = hitObject;
        this.hitPoint = hitPoint;
        this.normal = normal;
        this.color = color;
    }
}
