package org.joshjoyce.sunbeam

import javax.swing.JFrame

case class Point(x: Double, y: Double, z: Double)
case class Ray(origin: Point, direction: Vector3d)

object Sunbeam {
  def main(args: Array[String]) {
    val frame = new JFrame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    frame.setLocationRelativeTo(null)

    val displayWindow = new DisplayWindow(640, 480)
    frame.add(displayWindow)
    frame.pack()
    frame.setSize(displayWindow.widthPixels, displayWindow.heightPixels)
    frame.setVisible(true)
  }
}

