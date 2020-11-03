package Image

case object ASCIIImageFactory {
  def fromImage(image: Image): ASCIIImage = {
    new ASCIIImage {
      override val _data: Array[Array[Int]] = _createGreyscaleGrid(image)

      def _createGreyscaleGrid(image: Image): Array[Array[Int]] = {
        val grid = Array.ofDim[Int](image.width, image.height)

        for(x <- 0 until image.width; y <- 0 until image.height) {
          val color = image.getColorAt(x, y)
          grid(x)(y) =  ((0.3 * color.getRed) + (0.59 * color.getGreen) + (0.11 * color.getBlue)).toInt
        }

        grid
      }
    }
  }

  def fromGreyscaleGrid(data: Array[Array[Int]]): ASCIIImage =
    new ASCIIImage {
      override val _data: Array[Array[Int]] = data
    }
}
