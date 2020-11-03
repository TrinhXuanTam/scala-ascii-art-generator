package ImageLoader

import java.awt.Color
import java.awt.image.BufferedImage

class Image(private var _data: BufferedImage) {
  val width: Int = _data.getWidth()

  val height: Int = _data.getHeight()

  def getColorAt(x: Int, y: Int): Color = new Color(_data.getRGB(x, y))
}
