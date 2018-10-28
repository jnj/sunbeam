package org.joshjoyce.sunbeam;

public class Sphere {
    public final Point3d center;
    public final double radius;

    public Sphere(Point3d center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    public static Sphere unit() {
        return new Sphere(Point3d.ORIGIN, 1);
    }

    public IntersectionList intersect(Ray ray) {
        var sphereToRay = ray.origin.subtract(center);
        var a = ray.direction.dot(ray.direction);
        var b = 2 * ray.direction.dot(sphereToRay);
        var c = sphereToRay.dot(sphereToRay) - 1;
        var discriminant = b * b - 4 * a * c;

        if (discriminant < 0) {
            return IntersectionList.EMPTY;
        }

        var list = new IntersectionList(2);
        var t1 = (-b - Math.sqrt(discriminant)) / (2 * a);
        var t2 = (-b + Math.sqrt(discriminant)) / (2 * a);

        if (t1 > t2) {
            double t = t1;
            t1 = t2;
            t2 = t;
        }

        list.add(new Intersection(t1, this));
        list.add(new Intersection(t2, this));
        return list;
    }
}
