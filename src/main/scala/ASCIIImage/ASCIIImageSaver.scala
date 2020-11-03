package ASCIIImage

import java.io.OutputStream

class ASCIIImageSaver {
  private def grayscaleToChar(grayscale: Int): Char = {
    require(0 <= grayscale && grayscale <= 255, "Invalid greyscale value!")
    grayscale match {
      case grayscale if grayscale < 25 => '@'
      case grayscale if grayscale < 50 =>'%'
      case grayscale if grayscale < 75 => '#'
      case grayscale if grayscale < 100 => '*'
      case grayscale if grayscale < 125 => '+'
      case grayscale if grayscale < 150 => '='
      case grayscale if grayscale < 175 => '-'
      case grayscale if grayscale < 200 => ':'
      case grayscale if grayscale < 225 => '.'
      case _ => ' '
    }
  }

  private def _saveToStream(data: Array[Array[Char]], width: Int, height: Int, outputStream: OutputStream): Unit = {
    for(x <- 0 until width) {
      for( y <- 0 until height) {
        outputStream.write(data(x)(y))
      }
      outputStream.write('\n')
    }
    outputStream.flush()
  }

  def save(image: ASCIIImage, dst: Seq[OutputStream]): Unit = {
    val data = image.getData.map(_.map(grayscaleToChar))

    for(outputStream <- dst)
      _saveToStream(data, image.width, image.height, outputStream)
  }
}
