package org.joshjoyce.sunbeam;

import javax.swing.*;
import java.awt.*;

class World {
    ViewPlane viewPlane;
    Color bgColor;
    Sphere sphere;
    Tracer tracer;

    public World() {

    }

    public void build() {
        viewPlane.hres = 200;
        viewPlane.vres = 200;
        viewPlane.s = 1.0F;
        viewPlane.setGamma(1.0F);
        bgColor = Color.BLACK;
        tracer = new SingleSphere(this);
        sphere.center = new Point3d(0, 0, 0);
        sphere.radius = 85;
    }

    void renderScene() {
        Color pixelColor;
        Ray ray;
        double zw = 100;
        double x;
        double y;

        Canvas window = new Canvas(new GridBagLayout(), true, 640, 480);
    }

    void openWindow() {
        var frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        var displayWindow = new Canvas(new GridBagLayout(), true, 640, 480);
        frame.add(displayWindow);
        frame.pack();
        frame.setSize(displayWindow.widthPixels, displayWindow.heightPixels);
        frame.setVisible(true);
    }

}
