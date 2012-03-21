package org.joshjoyce.sunbeam

import scala.math._

case class Sphere(center: Point3d, radius: Real) extends Geometric {
  val epsilon: Real = 1e-6

  override def hit(ray: Ray, shade: ShadeRec) = {
    val temp = ray.origin - center
    val a = ray.direction.dot(ray.direction)
    val b = (temp * 2.0).dot(ray.direction)
    val c = temp.dot(temp) - radius * radius
    val disc = b * b - 4.0 * a * c

    if (disc < 0) {
      None
    } else {
      val e = sqrt(disc)
      val denom = 2 * a
      var t = (-b - e) / denom

      val makeHit = (tVal: Real) => {
        val hp = ray.origin + (ray.direction * tVal)
        val nr = (temp + ray.direction * tVal) / radius
        val sh = shade.copy(hitPoint = hp, normal = Normal(nr))
        Some(Hit(tVal, sh))
      }

      if (t > epsilon) {
        makeHit(t)
      } else {
        t = (-b + e) / denom
        if (t > epsilon) {
          makeHit(t)
        }
      }
      None
    }
  }
}
