package modules.ASCIIImage

import modules.ImageLoader.Image

case object ASCIIImageFactory {
  def fromImage(image: Image): ASCIIImage = {
    new ASCIIImage {
      override val _data: Array[Array[Int]] = _createGrayscaleGrid(image)

      def _createGrayscaleGrid(image: Image): Array[Array[Int]] = {
        val grid = Array.ofDim[Int](image.width, image.height)

        for(x <- 0 until image.width; y <- 0 until image.height) {
          val color = image.getColorAt(x, y)
          grid(x)(y) =  ((0.3 * color.getRed) + (0.59 * color.getGreen) + (0.11 * color.getBlue)).toInt
          require(0 <= grid(x)(y) && grid(x)(y) <= 255, "Invalid grayscale value!")
        }

        grid
      }

      override val width: Int = image.width
      override val height: Int = image.height
    }
  }

  def fromGrayscaleGrid(data: Array[Array[Int]]): ASCIIImage = {
    require(data.length > 0, "Invalid image dimension!")

    new ASCIIImage {
      override val _data: Array[Array[Int]] = data
      override val width: Int = data.length
      override val height: Int = data(0).length
    }
  }
}
