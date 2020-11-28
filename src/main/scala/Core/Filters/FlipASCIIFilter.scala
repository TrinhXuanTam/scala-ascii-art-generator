package Core.Filters

import Modules.ASCIIImage.{AsciiImage, GrayscaleGrid, IAsciiFilter}

class FlipASCIIFilter(direction: String) extends IAsciiFilter {
  require(
    direction.equalsIgnoreCase("y") || direction.equalsIgnoreCase("x"),
    "Invalid flip direction entered!")

  override def transform(image: AsciiImage): AsciiImage = {
    val src = image.getGrayScaleData
    val res = new GrayscaleGrid(image.width, image.height)

    for {
      x <- 0 until image.width
      y <- 0 until image.height
    } if (direction.equalsIgnoreCase("x"))
      res(x)(y) = src(image.width - 1 - x)(y)
    else
      res(x)(y) = src(x)(image.height - 1 - y)

    new AsciiImage(res)
  }
}
