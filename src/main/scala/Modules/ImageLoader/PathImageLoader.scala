package Modules.ImageLoader

import java.awt.image.BufferedImage
import java.io.File

// Loads file from path
class PathImageLoader(val _loaders: Map[String, ILoader]) {
  val fileImageLoader: FileImageLoader = new FileImageLoader(_loaders)

  def load(path: String): BufferedImage = fileImageLoader.load(new File(path))
}
