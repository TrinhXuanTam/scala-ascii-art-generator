package ImageLoader

import java.awt.image.BufferedImage
import java.io.File

import ImageLoader.Loaders.{GIFLoader, JPGLoader, PNGLoader}


class FileImageLoader {
  private def _getFileExtension(name: String): String = {
    val index = name.lastIndexOf('.')
    if (index < 0) throw new IllegalArgumentException("Unsupported file!")
    name.substring(index)
  }

  def load(file: File) : BufferedImage = {
    val extension = _getFileExtension(file.getName)

    extension match {
      case ".png" => new PNGLoader().load(file)
      case ".jpg" => new JPGLoader().load(file)
      case ".gif" => new GIFLoader().load(file)
      case _ => throw new IllegalArgumentException(s"Unsupported file extension: $extension!")
    }
  }
}
