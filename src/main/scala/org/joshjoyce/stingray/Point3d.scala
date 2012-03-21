package org.joshjoyce.stingray

import org.joshjoyce.stingray._
import scala.math._

object Point3d {
  def apply(v: Vector3d): Point3d = Point3d(v.x, v.y, v.z)
  def apply(n: Normal): Point3d = Point3d(n.x, n.y, n.z)
}

case class Point3d(x: Real, y: Real, z: Real) {
  def +(v: Point3d) = Point3d(x + v.x, y + v.y, z + v.z)
  
  def -(v: Point3d) = Point3d(x - v.x, y - v.y, z - v.z)
  
  def -(v: Vector3d) = Vector3d(x - v.x, y - v.y, z - v.z)
  
  def *(r: Real) = Point3d(r * x, r * y, r * z)

  def distSquared(v: Point3d) = {
    square(x - v.x) + square(y - v.y) + square(z - v.z)
  }

  def dist(v: Point3d) = sqrt(distSquared(v))

  private def square(r: Real) = r * r
}
