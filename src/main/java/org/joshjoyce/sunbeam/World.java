package org.joshjoyce.sunbeam;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

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

        DisplayWindow window = new DisplayWindow(new GridBagLayout(), true, 640, 480);
    }

    void openWindow() {
        var frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        var displayWindow = new DisplayWindow(new GridBagLayout(), true, 640, 480);
        frame.add(displayWindow);
        frame.pack();
        frame.setSize(displayWindow.widthPixels, displayWindow.heightPixels);
        frame.setVisible(true);
    }

    public class DisplayWindow extends JPanel {

        final int widthPixels;
        final int heightPixels;

        DisplayWindow(LayoutManager layout, boolean isDoubleBuffered, int widthPixels, int heightPixels) {
            super(layout, isDoubleBuffered);
            this.widthPixels = widthPixels;
            this.heightPixels = heightPixels;
            setVisible(true);
            setSize(widthPixels, heightPixels);
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            Graphics2D g2d = (Graphics2D) g;
            BufferedImage img = g2d.getDeviceConfiguration().createCompatibleImage(widthPixels, heightPixels);



            g2d.drawImage(img, 0, 0, null);
        }
    }
}
