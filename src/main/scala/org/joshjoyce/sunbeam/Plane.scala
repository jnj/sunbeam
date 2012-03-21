package org.joshjoyce.sunbeam

class Plane(val point: Point3d,  val normal: Normal) extends Geometric {
  val epsilon: Real = 1e-6

  override def hit(ray: Ray, shade: ShadeRec) = {
    val t = (point - ray.origin).dot(normal) / (ray.direction.dot(normal))

    if (t > epsilon) {
      val hitPoint = ray.origin + (ray.direction * t)
      val sr = shade.copy(hitPoint=hitPoint, normal=normal)
      Some(Hit(t, sr))
    }

    None
  }
}
