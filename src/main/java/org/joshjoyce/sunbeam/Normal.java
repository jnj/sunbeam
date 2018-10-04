package org.joshjoyce.sunbeam;

public class Normal {
    public final double x;
    public final double y;
    public final double z;

    public static Normal fromPoint3d(Point3d p) {
        return new Normal(p.x, p.y, p.z);
    }

    public static Normal fromVector3d(Vector3d v) {
        return new Normal(v.x, v.y, v.z);
    }

    public Normal(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Normal add(Normal n) {
        return new Normal(x + n.x, y + n.y, z + n.z);
    }

    public Normal add(Vector3d v) {
        return new Normal(x + v.x, y + v.y, z + v.z);
    }

    public double dot(Normal n) {
        return x * n.x + y * n.y + z * n.z;
    }

    public Normal scale(double d) {
        return new Normal(d * x, d * y, d * z);
    }
}
