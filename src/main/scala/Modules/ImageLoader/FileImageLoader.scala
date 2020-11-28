package Modules.ImageLoader

import java.awt.image.BufferedImage
import java.io.File

class FileImageLoader(val _loaders: Map[String, ILoader]) {

  // Get file extension from file name
  private def _getFileExtension(name: String): String = {
    val index = name.lastIndexOf('.')
    // File is not supported if extension can't be resolved
    require(index >= 0, "Unsupported file!")
    name.substring(index)
  }

  // Load image with file loader object for a given extension
  def load(file: File): BufferedImage = {
    val extension = _getFileExtension(file.getName)

    require(
      _loaders.contains(extension),
      s"Unsupported file extension: $extension!")

    _loaders(extension).load(file)
  }
}
