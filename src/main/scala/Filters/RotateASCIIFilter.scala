package Filters
import ASCIIImage.{ASCIIImage, ASCIIImageFactory}

class RotateASCIIFilter(degrees: String) extends IASCIIFilter {
  private val _degrees =  ((Integer.parseInt(degrees) % 360) + 360) % 360

  private def _rotate(width: Int, height: Int, data: Array[Array[Int]]): Array[Array[Int]] = {
    val rotated = Array.ofDim[Int](height, width)

    for(x <- 0 until width; y <- 0 until height) {
      rotated(y)(x) = data(x)(height - 1 - y)
    }

    rotated
  }

  override def transform(image: ASCIIImage): ASCIIImage = {
    require(_degrees % 90 == 0, "Rotations dividable by 90 degrees are mandatory!")
    var res = image.getData

    for(_ <- 0 until _degrees / 90)
      res = _rotate(res.length, res(0).length, res)

    ASCIIImageFactory.fromGrayscaleGrid(res)
  }
}
