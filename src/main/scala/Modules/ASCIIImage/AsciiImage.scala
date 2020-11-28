package Modules.ASCIIImage

class AsciiImage(private val _grayscaleGrid: GrayscaleGrid) {

  val width: Int = _grayscaleGrid.width

  val height: Int = _grayscaleGrid.height

  private def grayscaleToChar(grayscale: Int): Char = {
    require(0 <= grayscale && grayscale <= 255, "Invalid greyscale value!")
    grayscale match {
      case grayscale if grayscale < 25  => '@'
      case grayscale if grayscale < 50  => '%'
      case grayscale if grayscale < 75  => '#'
      case grayscale if grayscale < 100 => '*'
      case grayscale if grayscale < 125 => '+'
      case grayscale if grayscale < 150 => '='
      case grayscale if grayscale < 175 => '-'
      case grayscale if grayscale < 200 => ':'
      case grayscale if grayscale < 225 => '.'
      case _                            => ' '
    }
  }

  def getGrayScaleData: GrayscaleGrid = _grayscaleGrid

  def getArt: Array[Array[Char]] =
    _grayscaleGrid.getRawData.map(_.map(grayscaleToChar))
}
