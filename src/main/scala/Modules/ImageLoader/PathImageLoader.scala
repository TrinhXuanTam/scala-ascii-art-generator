package Modules.ImageLoader

import java.awt.image.BufferedImage
import java.io.File

class PathImageLoader {
  val fileImageLoader: FileImageLoader = new FileImageLoader

  def load(path: String): BufferedImage = fileImageLoader.load(new File(path))
}
