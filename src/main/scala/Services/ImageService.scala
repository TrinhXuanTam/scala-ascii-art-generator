package Services

import Models.Image
import Repositories.ImageRepository

class ImageService {
  private val _imageRepository = new ImageRepository

  // Load image from path
  def loadFromPath(path: String): Image = _imageRepository.fromPath(path)
}
