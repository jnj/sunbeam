package org.joshjoyce.stingray

import scala.math._

object Roots {

  def linear(a: BigDecimal, b: BigDecimal) = {
    if (a == 0) {
      None
    } else {
      Some(Seq(-1 * (a / b)))
    }
  }

  def quad(a: BigDecimal, b: BigDecimal, c: BigDecimal) = {
    if (a == 0) {
      linear(b, c)
    } else {
      val d = (b * b) - 4 * a * c

      if (d == 0) {
        Some(Seq((-1 * b) / (2 * c)))
      } else {
        if (d > 0) {
          val r = -1 * b
          val s = sqrt(d.toDouble)
          val t = 2 * a
          Some(Seq((r + s) / t, (r - s) / t))
        } else {
          None
        }
      }
    }
  }
  
  def cubic(a: BigDecimal, b: BigDecimal, c: BigDecimal, d: BigDecimal) = {
    if (a == 0) {
      quad(b, c, d)
    } else {
      val p = (3*c/a - (b/a).pow(2)) / 3
      val q = (2*(b/a).pow(3) - 9*b*c/a/a + 27*d/a) / 27
      val de = (p/3).pow(3) + (q/2).pow(2)
      
      if (de >= 0) {
        val x = q/(-2)
        val y = sqrt(de.toDouble)
        val u = cbrt((x + y).toDouble)
        val v = cbrt((x - y).toDouble)
        val y1 = u + v
        val y2 = -1*(u+v)/2
      } else {
      }
    }
  }
}
