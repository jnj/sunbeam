package org.joshjoyce.sunbeam;

class Sphere extends Geometric {
    private static final double EPSILON = 1e-6;

    Point3d center;
    double radius;

    Sphere(Point3d center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    @Override
    boolean hit(Ray ray, ShadeRec shade, MutDouble tMin) {
        Vector3d temp = ray.origin.subtract(center);
        double a = ray.direction.dot(ray.direction);
        double b = temp.scale(2.0).dot(ray.direction);
        double c = temp.dot(temp) - (radius * radius);
        double disc = b * b - 4.0 * a * c;

        if (disc < 0) {
            return false;
        }

        double e = Math.sqrt(disc);
        double denom = 2 * a;
        var t = (-b - e) / denom;

        if (t > EPSILON) {
            tMin.d = t;
            shade.normal = Normal.fromVector3d(temp.add(ray.direction.scale(t)).divide(radius));
            shade.hitPoint = ray.origin.add(ray.direction.scale(t));
            return true;
        }

        t = (-b + e) / denom;

        if (t > EPSILON) {
            tMin.d = t;
            shade.normal = Normal.fromVector3d(temp.add(ray.direction.scale(t)).divide(radius));
            shade.hitPoint = ray.origin.add(ray.direction.scale(t));
            return true;
        }

        return false;
    }
}
