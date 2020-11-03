package ImageLoader

import java.awt.image.BufferedImage
import java.io.File
import java.net.URI

class URIImageLoader {
  val fileImageLoader: FileImageLoader = new FileImageLoader

  def load(url: String): BufferedImage = fileImageLoader.load(new File(new URI(url)))
}
