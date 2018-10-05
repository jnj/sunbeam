package org.joshjoyce.sunbeam;

import java.awt.*;

public class Simulation {
    public static void main(String[] args) {
        Canvas canvas = Canvas.create(100, 100);
        var p = new Projectile(new Point3d(0, 1, 0), new Vector3d(1, 1, 0).normalize());
        var w = new SimWorld(new Vector3d(-0.01, 0, 0), new Vector3d(0, -0.1, 0));

        while (p.point.y >= 0) {
            //System.out.println(p.point);
            p = tick(w, p);
            canvas.setPixel((int) p.point.x, (int) p.point.y, Color.YELLOW.getRGB());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
    public final Vector3d velocity;

    Projectile(Point3d point, Vector3d velocity) {
        this.point = point;
        this.velocity = velocity;
    }
}

class SimWorld {
    public final Vector3d wind;
    public final Vector3d gravity;

    SimWorld(Vector3d wind, Vector3d gravity) {
        this.wind = wind;
        this.gravity = gravity;
    }
}
