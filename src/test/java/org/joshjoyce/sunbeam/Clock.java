package org.joshjoyce.sunbeam;

public class Clock {
    public static void main(String[] args) {
        int sideLength = 400;
        var canvas = Canvas.create(sideLength, sideLength);
        var origin = new Point3d(0, 0, 0);
        var radius = sideLength * 0.75 * 0.5;
        var twelve = origin.add(Vector.for3d(0, radius, 0));
        var matrix = Matrix.rotateZ(Math.PI / 6.0);
        var move = Matrix.translation(sideLength / 2.0, sideLength / 2.0, 0);
        var lastHour = twelve;

        for (var i = 0; i < 12; i++) {
            var moved = move.multiply(lastHour);
            canvas.setPixel((int) moved.x(), (int) moved.y(), new RgbColor(1, 0, 0));
            lastHour = matrix.multiply(lastHour);
        }
    }
}
