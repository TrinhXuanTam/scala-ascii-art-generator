package modules.Filters
import modules.ASCIIImage.{ASCIIImage, ASCIIImageFactory}

class BrightnessASCIIFilter(brightness: String) extends IASCIIFilter {
  private val _brightness = Integer.parseInt(brightness)

  private def addBrightness(grayscale: Int): Int = {
    var res = grayscale + _brightness
    if(res < 0) res = 0
    if(res > 255) res = 0
    res
  }

  override def transform(image: ASCIIImage): ASCIIImage = {
    val res = image.getData

    for(x <- 0 until image.width; y <- 0 until image.height)
      res(x)(y) = addBrightness(res(x)(y))

    ASCIIImageFactory.fromGrayscaleGrid(res)
  }
}
