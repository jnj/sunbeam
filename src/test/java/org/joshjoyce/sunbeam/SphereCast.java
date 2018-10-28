package org.joshjoyce.sunbeam;

public class SphereCast {
    public static void main(String[] args) {
        final var canvasPixels = 800;
        var canvas = Canvas.create(canvasPixels, canvasPixels);
        var color = new RgbColor(1, 0, 0);
        var wallSize = 7.0;
        var wallZ = 10.0;
        var half = wallSize / 2;
        var pixelSize = wallSize / canvasPixels;
        var rayOrig = new Point3d(0, 0, -5);
        var sphere = Sphere.unit();

        for (var y = 0; y < canvasPixels; y++) {
            var worldY = half - pixelSize * y;

            for (var x = 0; x < canvasPixels; x++) {
                var worldX = -half + pixelSize * x;
                var pos = new Point3d(worldX, worldY, wallZ);
                var ray = new Ray(rayOrig, pos.subtract(rayOrig).normalize());
                var inters = sphere.intersect(ray);

                if (inters.hit() != null) {
                    canvas.setPixel(x, y, color);
                }
            }
        }
    }
}
