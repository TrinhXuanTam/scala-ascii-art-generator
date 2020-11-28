package Core.Factories

import Core.ImageLoaders.{GIFLoader, JPGLoader, PNGLoader}
import Modules.ImageLoader.PathImageLoader

object ImageLoaderFactory {
  private val _loaders = Map(
    ".png" -> new PNGLoader,
    ".gif" -> new GIFLoader,
    ".jpg" -> new JPGLoader)

  def pathImageLoader(): PathImageLoader = new PathImageLoader(_loaders)
}
