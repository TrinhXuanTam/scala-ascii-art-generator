package Modules.ImageLoader

import java.awt.image.BufferedImage
import java.io.File

import scala.collection.mutable

class FileImageLoader {
  private val _loaders = mutable.HashMap[String, ILoader]()

  private def _getFileExtension(name: String): String = {
    val index = name.lastIndexOf('.')
    require(index >= 0, "Unsupported file!")
    name.substring(index)
  }

  def load(file: File): BufferedImage = {
    val extension = _getFileExtension(file.getName)

    require(
      _loaders.contains(extension),
      s"Unsupported file extension: $extension!")

    _loaders(extension).load(file)
  }
}
