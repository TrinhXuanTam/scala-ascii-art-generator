package Filters
import ASCIIImage.{ASCIIImage, ASCIIImageFactory}

class RotateASCIIFilter(private val _degrees: String) extends IASCIIFilter {
  override def transform(image: ASCIIImage): ASCIIImage = {
    val res = image.getData

    ASCIIImageFactory.fromGrayscaleGrid(res)
  }
}
