package org.joshjoyce.sunbeam

import java.awt.Color

abstract class Geometric {
  var color: Color = _
  
  def hit(ray: Ray, shade: ShadeRec): Option[Hit]
}
