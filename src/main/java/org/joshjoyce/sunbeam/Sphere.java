package org.joshjoyce.sunbeam;

public class Sphere {
    public final Point3d center;
    public final double radius;

    Matrix transformation = Matrix.identity(4);

    public Sphere(Point3d center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    public static Sphere unit() {
        return new Sphere(Point3d.ORIGIN, 1);
    }

    public void setTransformation(Matrix m) {
        this.transformation = m;
    }

    public IntersectionList intersect(Ray ray) {
        var translated = ray.transform(transformation.invert());
        var sphereToRay = translated.origin.subtract(center);
        var a = translated.direction.dot(translated.direction);
        var b = 2 * translated.direction.dot(sphereToRay);
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
