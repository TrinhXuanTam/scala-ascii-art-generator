package Core.Factories

import Core.ImageLoaders.{GIFLoader, JPGLoader, PNGLoader}
import Modules.ImageLoader.PathImageLoader

object ImageLoaderFactory {

  // Supported file extensions
  private val _loaders = Map(
    ".png" -> new PNGLoader,
    ".gif" -> new GIFLoader,
    ".jpg" -> new JPGLoader)

  // Instantiate a PathImageLoader object with supported file extensions
  def pathImageLoader(): PathImageLoader = new PathImageLoader(_loaders)
}
