package org.joshjoyce.sunbeam;

import java.awt.*;

abstract class Geometric {
  Color color;
  
  abstract boolean hit(Ray ray, ShadeRec shade, MutDouble tMin);
}
