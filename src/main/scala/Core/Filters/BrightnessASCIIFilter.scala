package Core.Filters

import Modules.ASCIIImage.{AsciiImage, IAsciiFilter}

class BrightnessASCIIFilter(brightness: String) extends IAsciiFilter {
  private val _brightness = Integer.parseInt(brightness)

  private def addBrightness(grayscale: Int): Int = {
    var res = grayscale + _brightness
    if (res < 0) res = 0
    if (res > 255) res = 0
    res
  }

  override def transform(image: AsciiImage): AsciiImage = {
    val res = image.getGrayScaleData

    for {
      x <- 0 until image.width
      y <- 0 until image.height
    } res(x)(y) = addBrightness(res(x)(y))

    new AsciiImage(res)
  }
}
