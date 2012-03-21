package org.joshjoyce.stingray

import org.joshjoyce.stingray._

object Normal {
  def apply(p: Point3d): Normal = Normal(p.x, p.y, p.z)
  def apply(v: Vector3d): Normal = Normal(v.x, v.y, v.z)
}

case class Normal(x: Real, y: Real, z: Real) {
  def +(n: Normal) = Normal(x + n.x, y + n.y, z + n.z)
  
  def +(v: Vector3d) = Vector3d(x + v.x, y + v.y, z + v.z)

  def dot(n: Normal) = n.x * x + n.y * y + n.z * z
  
  def *(a: Real) = Normal(a * x, a * y, a * z)
