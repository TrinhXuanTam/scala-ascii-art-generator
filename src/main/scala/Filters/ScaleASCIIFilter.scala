package Filters
import ASCIIImage.{ASCIIImage, ASCIIImageFactory}

class ScaleASCIIFilter(private val _scaleFactor: String) extends IASCIIFilter {

  private def _scale025(image: ASCIIImage): ASCIIImage = {
    val src = image.getData
    val res = Array.ofDim[Int](image.width / 2, image.height / 2)

    for(x <- 0 until image.width / 2; y <- 0 until image.height / 2)
      res(x)(y) = (src(x * 2)(y * 2) + src(x * 2 + 1)(y * 2) + src(x * 2 + 1)(y * 2 + 1) + src(x * 2)(y * 2 + 1)) / 4

    ASCIIImageFactory.fromGrayscaleGrid(res)
  }

  private def _scale4(image: ASCIIImage): ASCIIImage = {
    val src = image.getData
    val res = Array.ofDim[Int](image.width * 2, image.height * 2)

    for(x <- 0 until image.width; y <- 0 until image.height) {
      res(x * 2)(y * 2) = src(x)(y)
      res(x * 2 + 1)(y * 2) = src(x)(y)
      res(x * 2 + 1)(y * 2 + 1) = src(x)(y)
      res(x * 2)(y * 2 + 1) = src(x)(y)
    }

    ASCIIImageFactory.fromGrayscaleGrid(res)
  }

  override def transform(image: ASCIIImage): ASCIIImage = {
    require(_scaleFactor.equals("1") ||  _scaleFactor.equals("0.25") || _scaleFactor.equals("4"), "Only scaling factors 0.25, 1 and 4 are supported!")
    _scaleFactor match {
      case "0.25" => _scale025(image)
      case "1" => image
      case "4" => _scale4(image)
    }
  }
}
