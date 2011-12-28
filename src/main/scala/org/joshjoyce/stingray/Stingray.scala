package org.joshjoyce.stingray
import java.awt.Canvas
import java.awt.Color
import java.awt.Dimension
import java.awt.Frame
import java.awt.GraphicsConfiguration
import java.awt.GraphicsEnvironment
import java.awt.Panel
import javax.swing.JFrame

import scala.math._

case class Point(x: Double, y: Double, z: Double)
case class Direction(x: Double, y: Double, z: Double)
case class Ray(origin: Point, direction: Direction)

object Stingray {
  def main(args: Array[String]) {
    val sizeX = 640
    val sizeY = 480

    val gfxEnv = GraphicsEnvironment.getLocalGraphicsEnvironment
    val gfxCfg = gfxEnv.getDefaultScreenDevice.getDefaultConfiguration
    val frame = new JFrame("Stingray", gfxCfg)
    val canvas = new Canvas

    canvas.setPreferredSize(new Dimension(640, 480))
    canvas.setBackground(new Color(34,45,56))
    val gfx = canvas.getGraphics


    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    frame.add(canvas)
    frame.pack()
    frame.setVisible(true)
  }

  def putPixel(r: Double, g: Double, b: Double) {
    println(convertColor(r) + ":" + convertColor(g) + ":" + convertColor(b))
  }

  def convertColor(c: Double) = floor(min(c * 255, 255)).toInt
}

