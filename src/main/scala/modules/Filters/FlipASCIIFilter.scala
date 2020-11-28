package modules.Filters
import modules.ASCIIImage.{ASCIIImage, ASCIIImageFactory}

class FlipASCIIFilter(direction: String) extends IASCIIFilter {
  require(direction.equalsIgnoreCase("y") || direction.equalsIgnoreCase("x"), "Invalid flip direction entered!")

  override def transform(image: ASCIIImage): ASCIIImage = {
    val src = image.getData
    val res = Array.ofDim[Int](image.width, image.height)

    for(x <- 0 until image.width; y <- 0 until image.height) {
      if(direction.equalsIgnoreCase("x"))
        res(x)(y) = src(image.width - 1 - x)(y)
      else
        res(x)(y) = src(x)(image.height - 1 - y)
    }

    ASCIIImageFactory.fromGrayscaleGrid(res)
  }
}
