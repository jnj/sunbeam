package org.joshjoyce.sunbeam;

class Plane extends Geometric {
    private static final double EPSILON = 1e-6;
    private final Point3d point;
    private final Normal normal;

    public Plane(Point3d point, Normal normal) {
        this.point = point;
        this.normal = normal;
    }

    @Override
    boolean hit(Ray ray, ShadeRec shade, MutDouble tMin) {
        double t = point.subtract(ray.origin).dot(normal.scale(1 / ray.direction.dot(normal)));

        if (t > EPSILON) {
            tMin.d = t;
            shade.normal = normal;
            shade.hitPoint = ray.origin.add(ray.direction.scale(t));
            return true;
        }

        return false;
    }
}
