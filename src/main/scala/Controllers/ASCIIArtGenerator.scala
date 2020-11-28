package Controllers

import Models.Image
import Services.{ImageService, InputService}

// Main controller
class ASCIIArtGenerator {

  // Create an image object bound to the input service
  def bindImageLoaders(inputService: InputService): Option[Image] = {
    var image: Option[Image] = None

    inputService.mapStringToCallback(
      "--path",
      (path: String) => image = Some(new ImageService().loadFromPath(path)))

    image
  }

  // Transform an image into ASCII art
  def run(args: Seq[String]): Unit = {
    val inputService = new InputService
    var image: Option[Image] = bindImageLoaders(inputService)

    inputService.processInput(args)
  }
}
