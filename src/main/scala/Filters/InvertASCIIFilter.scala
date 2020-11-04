package Filters
import ASCIIImage.{ASCIIImage, ASCIIImageFactory}

class InvertASCIIFilter extends IASCIIFilter {
  override def transform(image: ASCIIImage): ASCIIImage = {
    val res = image.getData

    for(x <- 0 until image.width; y <- 0 until image.height)
      res(x)(y) = 255 - res(x)(y)

    ASCIIImageFactory.fromGrayscaleGrid(res)
  }
}
