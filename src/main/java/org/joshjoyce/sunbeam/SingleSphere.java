package org.joshjoyce.sunbeam;

import java.awt.*;

public class SingleSphere extends Tracer {
    private final World world;

    public SingleSphere(World world) {
        this.world = world;
    }

    @Override
    public Color trace(Ray ray) {
        var sr = new ShadeRec(world, false, new Point3d(0, 0, 0), new Normal(0, 0, 0), Color.BLACK);
        var md = new MutDouble();
        if (world.sphere.hit(ray, sr, md)) {
            return Color.RED;
        } else {
            return Color.BLACK;
        }
    }
}
