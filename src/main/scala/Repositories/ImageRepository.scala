package Repositories

import Core.Factories.ImageLoaderFactory
import Models.Image
import Modules.ImageLoader.PathImageLoader

class ImageRepository {
  private val _pathImageLoader: PathImageLoader =
    ImageLoaderFactory.pathImageLoader()

  // Load image from path
  def fromPath(path: String): Image =
    new Image(_pathImageLoader.load(path))
}
