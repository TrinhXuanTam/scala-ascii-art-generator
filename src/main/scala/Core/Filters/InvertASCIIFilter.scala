package Core.Filters

import Modules.ASCIIImage.{AsciiImage, IAsciiFilter}

// ASCII filter that inverts the grayscale values
class InvertASCIIFilter extends IAsciiFilter {
  override def transform(image: AsciiImage): AsciiImage = {
    val res = image.getGrayScaleData

    for {
      x <- 0 until image.width
      y <- 0 until image.height
    } res(x)(y) = 255 - res(x)(y)

    new AsciiImage(res)
  }
}
