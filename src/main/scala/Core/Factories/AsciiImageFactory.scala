package Core.Factories

import Models.Image
import Modules.ASCIIImage.{AsciiImage, GrayscaleGrid}

case object AsciiImageFactory {
  def _createGrayscaleGrid(image: Image): GrayscaleGrid = {
    val grid = Array.ofDim[Int](image.width, image.height)

    for {
      x <- 0 until image.width
      y <- 0 until image.height
    } {
      val color = image.getColorAt(x, y)
      grid(x)(y) =
        ((0.3 * color.getRed) + (0.59 * color.getGreen) + (0.11 * color.getBlue)).toInt
      require(0 <= grid(x)(y) && grid(x)(y) <= 255, "Invalid grayscale value!")
    }

    new GrayscaleGrid(grid)
  }

  def fromImage(image: Image): AsciiImage =
    new AsciiImage(_createGrayscaleGrid(image))

}
