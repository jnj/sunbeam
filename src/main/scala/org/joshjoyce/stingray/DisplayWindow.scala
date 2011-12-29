package org.joshjoyce.stingray
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D

import javax.swing.JPanel

class DisplayWindow(val widthPixels: Int, val heightPixels: Int) extends JPanel {

  setVisible(true)
  setSize(widthPixels, heightPixels)

  override def paint(g: Graphics) {
    super.paint(g)
    val g2d = g.asInstanceOf[Graphics2D];
    val img = g2d.getDeviceConfiguration().createCompatibleImage(widthPixels, heightPixels)

    (0 to 100).foreach {
      i => {
        img.setRGB(i, i + 5, Color.BLUE.getRGB());
      }
    }
      
    g2d.drawImage(img, 0, 0, null)
  }
}
