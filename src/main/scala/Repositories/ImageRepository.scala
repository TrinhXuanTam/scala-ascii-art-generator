package Repositories

import Models.Image
import Modules.ImageLoader.PathImageLoader

class ImageRepository {
  def fromPath(path: String): Image =
    new Image(new PathImageLoader().load(path))
}
