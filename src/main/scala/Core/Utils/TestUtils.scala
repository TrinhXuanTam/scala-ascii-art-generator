package Core.Utils

import java.awt.image.BufferedImage

object TestUtils {
  def compareImages(A: BufferedImage, B: BufferedImage): Boolean = {
    if (A.getWidth() != B.getWidth() || A.getHeight() != B.getHeight())
      return false

    for {
      x <- 0 until A.getWidth()
      y <- 0 until A.getHeight()
    } if (A.getRGB(x, y) != B.getRGB(x, y))
      return false

    true
  }
}
