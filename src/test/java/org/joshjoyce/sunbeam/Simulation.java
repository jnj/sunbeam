package org.joshjoyce.sunbeam;

public class Simulation {
    public static void main(String[] args) {
        Canvas canvas = Canvas.create(900, 550);
        var p = new Projectile(new Point3d(0, 1, 0), Vector.for3d(1, 1.8, 0).normalize().scale(11.25));
        var w = new SimWorld(Vector.for3d(-0.01, 0, 0), Vector.for3d(0, -0.1, 0));
        var yellow = new RgbColor(1, 1, 0);
        while (p.point.y() >= 0) {
            p = tick(w, p);
            canvas.setPixel((int) p.point.x(), (int) p.point.y(), yellow);
        }
    }

    static Projectile tick(SimWorld w, Projectile p) {
        var newPos = p.point.add(p.velocity);
        var newVel = p.velocity.add(w.gravity).add(w.wind);
        return new Projectile(newPos, newVel);
    }
}

class Projectile {
    public final Point3d point;
    public final Vector velocity;

    Projectile(Point3d point, Vector velocity) {
        this.point = point;
        this.velocity = velocity;
    }
}

class SimWorld {
    public final Vector wind;
    public final Vector gravity;

    SimWorld(Vector wind, Vector gravity) {
        this.wind = wind;
        this.gravity = gravity;
    }
}
