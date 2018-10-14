package org.joshjoyce.sunbeam;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Canvas extends JPanel {

    final int widthPixels;
    final int heightPixels;
    private final BufferedImage image;

    public static Canvas create(int width, int height) {
        var frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        var canvas = new Canvas(new GridBagLayout(), true, width, height);
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
        return canvas;
    }

    Canvas(LayoutManager layout, boolean isDoubleBuffered, int widthPixels, int heightPixels) {
        super(layout, isDoubleBuffered);
        this.widthPixels = widthPixels;
        this.heightPixels = heightPixels;
        setPreferredSize(new Dimension(widthPixels, heightPixels));
        setVisible(true);
        image = new BufferedImage(widthPixels, heightPixels, BufferedImage.TYPE_INT_RGB);
    }

    public void setPixel(int x, int y, RgbColor rgbColor) {
        var color = rgbColor.toColor();
        var ty = Math.max(heightPixels - y - 1, 0);
        if (checkBounds(x, ty)) {
            image.setRGB(x, ty, color.getRGB());
            repaint();
        }
    }

    private boolean checkBounds(int x, int y) {
        return x >= 0 && y >= 0 && x < widthPixels && y < heightPixels;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g1 = (Graphics2D) g;
        g1.drawImage(image, 0, 0, null);
    }
}
