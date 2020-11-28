package Core.Filters

import Modules.ASCIIImage.{AsciiImage, GrayscaleGrid, IAsciiFilter}

// ASCII filter that scales up or down an ASCII image
// Only factors of 0.25, 1 and 4 are supported
class ScaleASCIIFilter(private val _scaleFactor: String) extends IAsciiFilter {

  private def _scale025(image: AsciiImage): AsciiImage = {
    val src = image.getGrayScaleData
    val res = new GrayscaleGrid(image.width / 2, image.height / 2)

    // Save the average of neighbouring pixels
    for {
      x <- 0 until image.width / 2
      y <- 0 until image.height / 2
    } res(x)(y) = (src(x * 2)(y * 2) + src(x * 2 + 1)(y * 2) + src(x * 2 + 1)(
      y * 2 + 1) + src(x * 2)(y * 2 + 1)) / 4

    new AsciiImage(res)
  }

  private def _scale4(image: AsciiImage): AsciiImage = {
    val src = image.getGrayScaleData
    val res = new GrayscaleGrid(image.width * 2, image.height * 2)

    // Replicate every pixel four times
    for {
      x <- 0 until image.width
      y <- 0 until image.height
    } {
      res(x * 2)(y * 2) = src(x)(y)
      res(x * 2 + 1)(y * 2) = src(x)(y)
      res(x * 2 + 1)(y * 2 + 1) = src(x)(y)
      res(x * 2)(y * 2 + 1) = src(x)(y)
    }

    new AsciiImage(res)
  }

  override def transform(image: AsciiImage): AsciiImage = {
    require(
      _scaleFactor.equals("1") || _scaleFactor.equals("0.25") || _scaleFactor
        .equals("4"),
      "Only scaling factors 0.25, 1 and 4 are supported!")
    _scaleFactor match {
      case "0.25" => _scale025(image)
      case "1"    => image
      case "4"    => _scale4(image)
    }
  }
}
