package Core.Filters

import Modules.ASCIIImage.{AsciiImage, IAsciiFilter}

// ASCII filter that changes the brightness of an ASCII image
class BrightnessASCIIFilter(brightness: String) extends IAsciiFilter {
  // Brightness value can be negative
  private val _brightness = Integer.parseInt(brightness)

  private def addBrightness(grayscale: Int): Int = {
    var res = grayscale + _brightness
    if (res < 0) res = 0
    if (res > 255) res = 0
    res
  }

  override def transform(image: AsciiImage): AsciiImage = {
    val res = image.getGrayScaleData

    // Add brightness to every grayscale value
    for {
      x <- 0 until image.width
      y <- 0 until image.height
    } res(x)(y) = addBrightness(res(x)(y))

    new AsciiImage(res)
  }
}
