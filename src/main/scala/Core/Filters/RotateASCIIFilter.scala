package Core.Filters

import Modules.AsciiImage.{AsciiImage, GrayscaleGrid, IAsciiFilter}

// ASCII filter that rotates the image by any multiple of 90
class RotateASCIIFilter(degrees: String) extends IAsciiFilter {
  private val _degrees = ((Integer.parseInt(degrees) % 360) + 360) % 360

  private def _rotate(grayscaleGrid: GrayscaleGrid): GrayscaleGrid = {
    val rotated = new GrayscaleGrid(grayscaleGrid.width, grayscaleGrid.height)

    for {
      x <- 0 until grayscaleGrid.width
      y <- 0 until grayscaleGrid.height
    } rotated(y)(x) = grayscaleGrid(x)(grayscaleGrid.height - 1 - y)

    rotated
  }

  override def transform(image: AsciiImage): AsciiImage = {
    require(
      _degrees % 90 == 0,
      "Only rotations dividable by 90 degrees are supported!")
    var res = image.getGrayScaleData

    for (_ <- 0 until _degrees / 90)
      res = _rotate(res)

    new AsciiImage(res)
  }
}
