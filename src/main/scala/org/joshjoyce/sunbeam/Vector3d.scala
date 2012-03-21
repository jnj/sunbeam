package org.joshjoyce.sunbeam

import scala.math._

case class Vector3d(x: Real, y: Real, z: Real) {
  def +(v: Vector3d) = Vector3d(x + v.x, y + v.y, z + v.z)

  def +(n: Normal) = Vector3d(x + n.x, y + n.y, z + n.z)

  def -(v: Vector3d) = Vector3d(x - v.x, y - v.y, z - v.z)

  def *(a: Real) = Vector3d(a * x, a * y, a * z)

  def /(a: Real) = this * (1 / a)

  def norm = sqrt(normSquared)

  def normSquared = x * x + y * y + z * z

  def dot(v: Vector3d) = x * v.x + y * v.y + z * v.z
  
  def dot(v: Normal) = x * v.x + y * v.y + z * v.z

  def cross(v: Vector3d) = {
    Vector3d(y * v.z - z * v.y, z * v.x - x * v.z, x * v.y - y * v.x)
  }
}
