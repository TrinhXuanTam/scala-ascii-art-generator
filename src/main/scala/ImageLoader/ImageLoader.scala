package ImageLoader

import java.awt.image.BufferedImage
import java.io.File

import javax.imageio.ImageIO

import scala.collection.immutable.HashSet

class ImageLoader {
  private val _allowedExtensions: HashSet[String] = HashSet(".jpg", ".png", ".gif")

  private def _getFileExtension(name: String): String = {
    val index = name.lastIndexOf('.')
    if (index < 0) throw new IllegalArgumentException("File extension not found!")
    name.substring(index)
  }

  private def _checkFileExtension(file: File): Unit = {
    val extension = _getFileExtension(file.getName)
    if (_allowedExtensions.contains(extension)) throw new IllegalArgumentException("Unsupported file extension!")
  }

  def _loadImage(file: File): Option[Image] = {
    _checkFileExtension(file)
    val bufferedImage: BufferedImage = ImageIO.read(file)
    Some(new Image(bufferedImage))
  }

  def loadFromPath(path: String): Option[Image] = {
    val file = new File(path)
    _loadImage(file)
  }
}
